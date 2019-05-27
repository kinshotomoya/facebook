package bean;

import java.io.Serializable;

public class AccountBean implements Serializable {
    private String userName;
    private String hashedPassword;
    private int accountId;

    public AccountBean(String userName, String hashedPassword, int accountId) {
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.accountId = accountId;
    }

    public AccountBean(String userName, String hashedPassword) {
        this.userName = userName;
        this.hashedPassword = hashedPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
