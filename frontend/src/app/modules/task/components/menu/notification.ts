export interface Notification {
  status?: 'ADDED' | 'DELETED' | 'INFO';
  message?: string;
}
