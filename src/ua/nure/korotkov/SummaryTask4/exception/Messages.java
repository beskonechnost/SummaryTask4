package ua.nure.korotkov.SummaryTask4.exception;


public class Messages {

    public static final String ERR_CANNOT_OBTAIN_EDITION = "Cannot obtain edition";
    public static final String ERR_CANNOT_OBTAIN_USER = "Cannot obtain user";
    public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";
    public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";
    public static final String ERR_CANNOT_ADD_EDITION = "Cannot add edition";
    public static final String ERR_LOCKED = "Cannot locked";
    public static final String ERR_ADD_USER = "Cannot add user and him account";
    public static final String ERR_NEW_PRIVILEGE = "I can not assign new privileges";
    public static final String ERR_CANNOT_UPDATE_EDITION = "Cannot update edition";
    public static final String ERR_CANNOT_DELETE_EDITION_BY_ID = "Cannot delete this edition";
    public static final String ERR_CANNOT_OBTAIN_ALL_ABOUT_SUBSCRIPTIONS = "Cannot obtain all about subscriptions";
    public static final String ERR_CANNOT_OBTAIN_SUBSCRIPTIONS_THIS_USER = "Cannot obtain subscription this user";
    public static final String ERR_CANNOT_OBTAIN_ACCOUNT_THIS_USER = "Cannot obtain account this user";
    public static final String ERR_CANNOT_REFILL_ACCOUNT = "Cannot refill account";
    public static final String ERR_CANNOT_OBTAIN_SUBSCRIPTIONS_USER = "Cannot obtain subscriptions user";
    public static final String ERR_ADD_SUBSCRIPTION = "Cannot add subscription and update balance";
    public static final String ERR_CANNOT_FIND_EDITIONS_BY_TOPIC = "Cannot find editions by topic";
    public static final String ERR_CANNOT_FIND_EDITIONS_BY_NAME = "Cannot find editions by name";

    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain connection";
    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close connection";
    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close statement";
    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close resultSet";

    private Messages() {
    }


    //some mess "public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";"
}
