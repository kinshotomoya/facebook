package model;

import DAO.AccountDAO;
import bean.AccountBean;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Account {
    public String doHashPassword(String password) {
        String sha = "";
        try {
            MessageDigest digest =  MessageDigest.getInstance("SHA-1");
            byte[] result = digest.digest(password.getBytes());

            sha = String.format("%040x", new BigInteger(1, result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha;
    }

    public AccountBean idLoginUser(String userName, String hashedPassword) {
        AccountDAO dao = new AccountDAO();
        AccountBean beanWithId = dao.findAccountByNameAndPassword(userName, hashedPassword);
        return beanWithId;
    }
}
