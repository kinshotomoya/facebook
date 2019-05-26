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

    public AccountDAO() {
        db = new dbManager();
        con = db.getConnection(con);
    }

    // Accountテーブルにデータを格納する
    public void createAccount(AccountBean bean) {
        if(con == null) db.getConnection(con);
        try {
            sql = "insert into account values(?, ?)";
            st = con.prepareStatement(sql);
            st.setString(1, bean.getUserName());
            st.setString(2, bean.getHashedPassword());
            st.executeUpdate();
            db.closeResources(st, rs, con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
