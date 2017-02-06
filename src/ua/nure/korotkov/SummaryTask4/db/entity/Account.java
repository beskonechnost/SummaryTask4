package ua.nure.korotkov.SummaryTask4.db.entity;

/**
 * Created by Андрей on 07.01.2017.
 */
public class Account extends Entity {

    private static final long serialVersionUID = 5359981708905511482L;

    private int accountNumber;
    private double balance;
    private int userId1;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    @Override
    public String toString() {
        return "Account{ " +super.toString()+
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", userId1=" + userId1 + "} ";
    }
}
