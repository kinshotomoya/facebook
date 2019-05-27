package DAO;

import bean.AccountBean;
import db.dbManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AccountDAO {
    private Connection con;
    private dbManager db;
    private PreparedStatement st;
    private ResultSet rs;
    private String sql;
    private int accountId;
    private AccountBean beanWithId;

    public AccountDAO() {
        db = new dbManager();
        con = db.getConnection(con);
    }

    // Accountテーブルにデータを格納する
    public int createAccount(AccountBean bean) {
        if(con == null) db.getConnection(con);
        try {
            sql = "insert into account values(?, ?)";
            st = con.prepareStatement(sql);
            st.setString(1, bean.getUserName());
            st.setString(2, bean.getHashedPassword());
            st.executeUpdate();

            // insertしたレコードのidを取得
            rs = st.executeQuery("SELECT LAST_INSERT_ID() AS LAST");
            if(rs != null && rs.next()) {
                accountId = rs.getInt("LAST");
            }
            db.closeResources(st, rs, con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountId;
    }

    public AccountBean findAccountByNameAndPassword(String userName, String hashedPassword) {
        if(con == null) db.getConnection(con);
        try {
            sql = "select * from account where account.name = ? && account.password = ?";
            st = con.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, hashedPassword);
            rs = st.executeQuery();
            if(rs != null && rs.next()) {
                int accountId = rs.getInt("account_id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                beanWithId = new AccountBean(name, password, accountId);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanWithId;
    }
}
