package ua.nure.korotkov.SummaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.db.entity.*;
import ua.nure.korotkov.SummaryTask4.exception.DBException;
import ua.nure.korotkov.SummaryTask4.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 07.01.2017.
 */
public final class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static DBManager instance;

    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/periodicals");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    private DataSource ds;

    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SQL_FIND_ALL_EDITION = "SELECT * FROM edition";
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";

    private static final String SQL_FIND_ALL_EDITION_SORT_BY_NAME_ASC = "SELECT * FROM edition ORDER BY edition.name ASC";
    private static final String SQL_FIND_ALL_EDITION_SORT_BY_NAME_DESC = "SELECT * FROM edition ORDER BY edition.name DESC";

    private static final String SQL_ADD_NEW_USER = "INSERT INTO users(login, pass, privilege_id, mail, age) VALUES (?,?,2,?,?)";
    private static final String SQL_ADD_NEW_ACCOUNT = "INSERT INTO account(account_number, balance,user_id1) VALUE (?,?,last_insert_id())";

    private static final String SQL_UPDATE_USER = "UPDATE users SET users.pass=?, users.mail=?, users.age=? WHERE users.id=?";
    private static final String SQL_UPDATE_ACCOUNT ="UPDATE account SET account.account_number=? WHERE account.user_id1=?";

    private static final String SQL_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_LOCK_USER = "UPDATE users SET users.lock=? WHERE users.id=?";
    private static final String SQL_PRIVILEGE_USER = "UPDATE users SET users.privilege_id=? WHERE users.id=?";
    private static final String SQL_NEW_EDITION = "INSERT INTO edition(name, topic, price, publisher) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE_EDITION = "UPDATE edition SET edition.name=?, edition.topic=?, edition.price=?, edition.publisher=? WHERE edition.id=?";
    private static final String SQL_DELETE_EDITION = "DELETE FROM edition WHERE edition.id=?";
    private static final String SQL_ALL_SUBSCRIPTIONS = "SELECT users.login, edition.* FROM users, edition, subscription WHERE users.id=subscription.user_id AND edition.id=subscription.edition_id";
    private static final String SQL_ALL_SUBSCRIPTIONS_THIS_USER = "SELECT users.login, edition.* FROM users, edition, subscription WHERE users.id=? AND users.id=subscription.user_id AND edition.id=subscription.edition_id";
    private static final String SQL_USER_ACCOUNT = "SELECT * FROM account WHERE user_id1=?";

    //user
    private static final String SQL_REFILL_ACCOUNT = "UPDATE account SET account.balance=? WHERE account.id=?";
    private static final String SQL_REFILL_ACCOUNT_BY_USER_ID = "UPDATE account SET account.balance=? WHERE account.user_id1=?";
    private static final String SQL_FIND_EDITIONS_BY_TOPIC = "SELECT * FROM edition WHERE edition.topic=?";
    private static final String SQL_FIND_EDITIONS_BY_NAME = "SELECT * FROM edition WHERE edition.name=?";

    private static final String SQL_SUBSCRIPTION_USER = "SELECT * FROM subscription WHERE user_id=?";
    private static final String SQL_NEW_SUBSCRIPTION ="INSERT INTO subscription(user_id, edition_id) VALUES (?,?)";


    public Connection getConnection() throws DBException {
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }

    public List<Edition> findEditions() throws DBException {
        List<Edition> listEditions = new ArrayList<Edition>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_EDITION);
            while (rs.next()) {
                listEditions.add(extractEdition(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_EDITION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_EDITION, ex);
        } finally {
            close(con, stmt, rs);
        }
        return listEditions;
    }

    public List<Edition> findEditionsSortNameASC() throws DBException {
        List<Edition> listEditions = new ArrayList<Edition>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_EDITION_SORT_BY_NAME_ASC);
            while (rs.next()) {
                listEditions.add(extractEdition(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_EDITION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_EDITION, ex);
        } finally {
            close(con, stmt, rs);
        }
        return listEditions;
    }

    public List<Edition> findEditionsSortNameDESC() throws DBException {
        List<Edition> listEditions = new ArrayList<Edition>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_EDITION_SORT_BY_NAME_DESC);
            while (rs.next()) {
                listEditions.add(extractEdition(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_EDITION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_EDITION, ex);
        } finally {
            close(con, stmt, rs);
        }
        return listEditions;
    }

    public User findUserByLogin(String login) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    public List<User> findUsers() throws DBException {
        List<User> listUsers = new ArrayList<User>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_ALL_USERS);
            while (rs.next()) {
                listUsers.add(extractUser(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER, ex);
        } finally {
            close(con, stmt, rs);
        }
        return listUsers;
    }

    public User findUserById(int id) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
            pstmt.setString(1, String.valueOf(id));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    public void addNewEdition(String name, String topic, double price, String publisher) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_NEW_EDITION);
            pstmt.setString(1, name);
            pstmt.setString(2, topic);
            pstmt.setDouble(3, price);
            pstmt.setString(4, publisher);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_ADD_EDITION, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void userLocked(int id, int lock) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_LOCK_USER);
            pstmt.setInt(1, lock);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_LOCKED, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void userNewPrivilege(int id, int priv) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_PRIVILEGE_USER);
            pstmt.setInt(1, priv);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_NEW_PRIVILEGE, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void addNewUserAndHimAccount(String login, String pass, String mail, int age, int accountNumber, double balance) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(SQL_ADD_NEW_USER);
            pstmt.setString(1, login);
            pstmt.setString(2, pass);
            pstmt.setString(3, mail);
            pstmt.setInt(4, age);
            pstmt.executeUpdate();

            PreparedStatement pstmt1 = con.prepareStatement(SQL_ADD_NEW_ACCOUNT);
            pstmt1.setInt(1, accountNumber);
            pstmt1.setDouble(2, balance);
            pstmt1.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_ADD_USER, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void updateEdition(int id, String name, String topic, double price, String publisher) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_EDITION);
            pstmt.setString(1, name);
            pstmt.setString(2, topic);
            pstmt.setDouble(3, price);
            pstmt.setString(4, publisher);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_EDITION, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void deleteEditionById(int id) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_EDITION);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_DELETE_EDITION_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public List<SubscriptionAll> findSubscriptions() throws DBException {
        List<SubscriptionAll> subscriptions = new ArrayList<SubscriptionAll>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_ALL_SUBSCRIPTIONS);
            while (rs.next()) {
                subscriptions.add(extractSubscriptionAll(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_ABOUT_SUBSCRIPTIONS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_ABOUT_SUBSCRIPTIONS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return subscriptions;
    }

    public List<SubscriptionAll> findSubscriptionsThisUser(int id) throws DBException {
        List<SubscriptionAll> subscriptions = new ArrayList<SubscriptionAll>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_ALL_SUBSCRIPTIONS_THIS_USER);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                subscriptions.add(extractSubscriptionAll(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_SUBSCRIPTIONS_THIS_USER, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return subscriptions;
    }

    public Account findAccountThisUser(int id) throws DBException {
        Account account = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_USER_ACCOUNT);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                account = extractAccount(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ACCOUNT_THIS_USER, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return account;
    }

    public void refillAccountById(int id, double sum) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_REFILL_ACCOUNT);
            pstmt.setDouble(1, sum);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_REFILL_ACCOUNT, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public List<Subscription> findSubscriptionsUser(int id) throws DBException {
        List<Subscription> subscriptions = new ArrayList<Subscription>();
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_SUBSCRIPTION_USER);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                subscriptions.add(extractSubscription(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_SUBSCRIPTIONS_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_SUBSCRIPTIONS_USER, ex);
        } finally {
            close(con, stmt, rs);
        }
        return subscriptions;
    }

    public void newSubscriptionAndWritingDown(double balance, int idUser, int idEdition) throws DBException {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(SQL_NEW_SUBSCRIPTION);
            pstmt.setInt(1, idUser);
            pstmt.setInt(2, idEdition);
            pstmt.executeUpdate();

            pstmt1 = con.prepareStatement(SQL_REFILL_ACCOUNT_BY_USER_ID);
            pstmt1.setDouble(1, balance);
            pstmt1.setInt(2, idUser);
            pstmt1.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_ADD_SUBSCRIPTION, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void UpdateUser(int idUser, String pass, String mail, int age, int accountNumber) throws DBException {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(SQL_UPDATE_USER);
            pstmt.setString(1, pass);
            pstmt.setString(2, mail);
            pstmt.setInt(3, age);
            pstmt.setInt(4, idUser);
            pstmt.executeUpdate();

            pstmt1 = con.prepareStatement(SQL_UPDATE_ACCOUNT);
            pstmt1.setInt(1, accountNumber);
            pstmt1.setInt(2, idUser);
            pstmt1.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_ADD_SUBSCRIPTION, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public List<Edition> findEditionsByTopic(String topic) throws DBException {
        List<Edition> editions = new ArrayList<Edition>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_EDITIONS_BY_TOPIC);
            pstmt.setString(1, topic);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                editions.add(extractEdition(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_FIND_EDITIONS_BY_TOPIC, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return editions;
    }

    public List<Edition> findEditionsByName(String name) throws DBException {
        List<Edition> editions = new ArrayList<Edition>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_EDITIONS_BY_NAME);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                editions.add(extractEdition(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_FIND_EDITIONS_BY_NAME, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return editions;
    }










    private void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    private void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

    private Edition extractEdition(ResultSet rs) throws SQLException {
        Edition edition = new Edition();
        edition.setId(rs.getInt(Fields.ENTITY_ID));
        edition.setName(rs.getString(Fields.EDITION_NAME));
        edition.setTopic(rs.getString(Fields.EDITION_TOPIC));
        edition.setPrice(rs.getDouble(Fields.EDITION_PRICE));
        edition.setPublisher(rs.getString(Fields.EDITION_PUBLISHER));
        return edition;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPass(rs.getString(Fields.USER_PASS));
        user.setPrivilegeId(rs.getInt(Fields.USER_PRIVILEGE_ID));
        user.setMail(rs.getString(Fields.USER_MAIL));
        user.setAge(rs.getInt(Fields.USER_AGE));
        user.setLock(rs.getInt(Fields.USER_LOCK_ID));
        return user;
    }

    private Account extractAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt(Fields.ENTITY_ID));
        account.setAccountNumber(rs.getInt(Fields.ACCOUNT_NUMBER));
        account.setBalance(rs.getDouble(Fields.ACCOUNT_BALANCE));
        account.setUserId1(rs.getInt(Fields.ACCOUNT_USER_ID));
        return account;
    }

    private SubscriptionAll extractSubscriptionAll(ResultSet rs) throws SQLException {
        SubscriptionAll subscriptionAll = new SubscriptionAll();
        subscriptionAll.setLoginUser(rs.getString(Fields.USER_LOGIN));
        subscriptionAll.setEditionId(rs.getInt(Fields.ENTITY_ID));
        subscriptionAll.setEditionName(rs.getString(Fields.EDITION_NAME));
        subscriptionAll.setEditionTopic(rs.getString(Fields.EDITION_TOPIC));
        subscriptionAll.setEditionPrice(rs.getDouble(Fields.EDITION_PRICE));
        subscriptionAll.setEditionPublisher(rs.getString(Fields.EDITION_PUBLISHER));
        return subscriptionAll;
    }

    private Subscription extractSubscription(ResultSet rs) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(rs.getInt(Fields.ENTITY_ID));
        subscription.setUserId(rs.getInt(Fields.SUBSCRIPTION_USER_ID));
        subscription.setEditionId(rs.getInt(Fields.SUBSCRIPTION_EDITION_ID));
        return subscription;
    }
}
