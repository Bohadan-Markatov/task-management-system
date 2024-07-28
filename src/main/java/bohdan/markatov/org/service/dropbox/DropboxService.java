package bohdan.markatov.org.service.dropbox;

import org.springframework.web.multipart.MultipartFile;

public interface DropboxService {

    String uploadFile(MultipartFile file, Long taskId);

    String getFilePublicLink(String fileId);

    void deleteById(String filedId);
}
