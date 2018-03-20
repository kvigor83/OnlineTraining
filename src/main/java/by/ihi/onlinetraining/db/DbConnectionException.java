package by.ihi.onlinetraining.db;

public class DbConnectionException extends Exception {
    public DbConnectionException() {
        super();
    }

    public DbConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbConnectionException(String message) {
        super(message);
    }

}
