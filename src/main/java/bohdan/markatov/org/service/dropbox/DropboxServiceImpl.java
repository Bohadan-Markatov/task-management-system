package bohdan.markatov.org.service.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.InvalidAccessTokenException;
import com.dropbox.core.oauth.DbxCredential;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.GetTemporaryLinkResult;
import com.dropbox.core.v2.files.WriteMode;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DropboxServiceImpl implements DropboxService {
    private static final int MAX_ATTEMPTS = 1;
    private DbxClientV2 client;
    private DbxRequestConfig config;
    @Value("${dropbox.app.key}")
    private String dropboxAppKey;
    @Value("${dropbox.app.secret}")
    private String dropBoxAppSecret;
    @Value("${dropbox.refresh.token}")
    private String dropboxRefreshToken;

    @PostConstruct
    private void init() {
        config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        client = refreshClient();
    }

    @Override
    public String uploadFile(MultipartFile file, Long taskId) {
        return uploadFile(file, taskId, 0);
    }

    private String uploadFile(MultipartFile file, Long taskId, int attempt) {
        try (InputStream in = file.getInputStream()) {
            FileMetadata metadata = client.files()
                    .uploadBuilder("/folder"
                            + taskId + "/"
                            + getFileName(file.getOriginalFilename()))
                    .withMode(WriteMode.ADD)
                    .uploadAndFinish(in);
            return metadata.getId();
        } catch (InvalidAccessTokenException e) {
            if (attempt > MAX_ATTEMPTS) {
                throw new RuntimeException("Can't upload file");
            }
            client = refreshClient();
            return uploadFile(file, taskId, attempt + 1);
        } catch (Exception e) {
            throw new RuntimeException("Can't upload file");
        }
    }

    @Override
    public String getFilePublicLink(String fileId) {
        return getFilePublicLink(fileId, 0);
    }

    private String getFilePublicLink(String fileId, int attempt) {
        try {
            GetTemporaryLinkResult result = client.files().getTemporaryLink(fileId);
            return result.getLink();
        } catch (InvalidAccessTokenException e) {
            if (attempt > MAX_ATTEMPTS) {
                throw new RuntimeException("Can't get file public link");
            }
            client = refreshClient();
            return getFilePublicLink(fileId, attempt + 1);
        } catch (Exception e) {
            throw new RuntimeException("Can't get file public link");
        }
    }

    @Override
    public void deleteById(String taskId) {
        deleteById(taskId, 0);
    }

    private void deleteById(String taskId, int attempt) {
        try {
            client.files().deleteV2(taskId);
        } catch (InvalidAccessTokenException e) {
            if (attempt > MAX_ATTEMPTS) {
                throw new RuntimeException("Can't delete file");
            }
            client = refreshClient();
            deleteById(taskId, attempt + 1);
        } catch (Exception e) {
            throw new RuntimeException("Can't delete file");
        }
    }

    private String getFileName(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }

    private DbxClientV2 refreshClient() {
        DbxCredential credential = new DbxCredential(
                "",
                -1L,
                dropboxRefreshToken,
                dropboxAppKey,
                dropBoxAppSecret);
        try {
            return new DbxClientV2(config, credential
                    .refresh(new DbxRequestConfig(dropboxAppKey)).getAccessToken());
        } catch (DbxException e) {
            throw new RuntimeException("Can't refresh dropbox client");
        }
    }
}
