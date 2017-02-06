package ua.nure.korotkov.SummaryTask4.db.entity;


public class User extends Entity{

    private static final long serialVersionUID = 7661181708273401694L;

    private String login;
    private String pass;
    private int privilegeId;
    private String mail;
    private int age;
    private int lock;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    @Override
    public String toString() {
        return "User{ " + super.toString()+
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", privilegeId=" + privilegeId +
                ", mail='" + mail + '\'' +
                ", age=" + age +
                ", lock=" + lock + "}";
    }
}
