package ua.nure.korotkov.SummaryTask4.exception;

/**
 * Created by Андрей on 07.01.2017.
 */
public class DBException extends AppException {

    private static final long serialVersionUID = -682774689117690688L;

    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
