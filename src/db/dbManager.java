package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbManager{
    private String userName;
    private String passward;
    private int port;
    private String dbName;

    public dbManager() {
        this.userName = "kin";
        this.passward = "kin_password";
        this.port = 3314;
        this.dbName = "facebook";
    }

    // コネクションを貼る
    public Connection getConnection(Connection con) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:" + port + "/" + dbName + "?user=" + userName + "&password=" + passward;
            con = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    //　リソース（コネクションなど）を閉じる
    public void closeResources(PreparedStatement st, ResultSet rs, Connection con) {
        try {
            if(con != null) {
                con.close();
                con = null;
            }
            if(st != null) st.close();
            if(rs != null) rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
