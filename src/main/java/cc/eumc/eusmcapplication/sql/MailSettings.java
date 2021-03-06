package cc.eumc.eusmcapplication.sql;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

/**
 * @author ElaBosak
 */
public class MailSettings {

    public static Connection getConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.setSharedCache(true);
        config.enableRecursiveTriggers(true);
        SQLiteDataSource ds = new SQLiteDataSource(config);
        String url = System.getProperty("user.dir");
        ds.setUrl("jdbc:sqlite:"+url+"/SQLite/"+"EusMCApplication-Database.db");
        return ds.getConnection();
    }

    public static boolean initTable(Connection con)throws SQLException {
        String sql = "create TABLE IF NOT EXISTS SETTINGS(email String, smtp String, port String, passwd String); ";
        Statement stat = null;
        stat = con.createStatement();
        try {
            stat.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void updateTable(Connection con)throws SQLException {
        String sql = "DROP TABLE IF EXISTS SETTINGS; create TABLE SETTINGS(email String, smtp String, port String, passwd String); ";
        Statement stat = null;
        stat = con.createStatement();
        stat.executeUpdate(sql);
    }

    /**
     * Delete the table completely
     * @param con Database connection data
     * @throws SQLException Database error
     */
    public static void dropTable(Connection con)throws SQLException {
        String sql = "drop table SETTINGS ";
        Statement stat = null;
        stat = con.createStatement();
        stat.executeUpdate(sql);
    }

    /**
     * Add data to the database
     * @param con Database connection data
     * @param email The email address that needs to be set
     * @param smtp Smtp server to be set
     * @param port The smtp server port that needs to be set
     * @param passwd The password or authorization code of the mailbox to be set
     * @return Whether the operation was successful
     * @throws SQLException Database error
     */
    public boolean insert(Connection con, String email, String smtp, String port, String passwd) throws SQLException{
        String sql = "insert into SETTINGS (email, smtp, port, passwd) values(?,?,?,?);";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            int idx = 1 ;
            pstmt.setString(idx++,email);
            pstmt.setString(idx++,smtp);
            pstmt.setString(idx++,port);
            pstmt.setString(idx++,passwd);
            pstmt.executeUpdate();
        }
        return true;
    }

    public void delete(Connection con, String email)throws SQLException {
        String sql = "delete from SETTINGS where email = ?";
        PreparedStatement pst = null;
        pst = con.prepareStatement(sql);
        int idx = 1 ;
        pst.setString(idx++, email);
        pst.executeUpdate();
    }

    public static String selectEmail(Connection con)throws SQLException {
        String sql = "select * from SETTINGS";
        Statement stat = null;
        ResultSet rs = null;
        stat = con.createStatement();
        rs = stat.executeQuery(sql);
        String email = null;
        while(rs.next())
        {
            email = rs.getString("email");
        }
        return email;
    }

    public static String selectSmtp(Connection con)throws SQLException {
        String sql = "select * from SETTINGS";
        Statement stat = null;
        ResultSet rs = null;
        stat = con.createStatement();
        rs = stat.executeQuery(sql);
        String smtp = null;
        while(rs.next())
        {
            smtp = rs.getString("smtp");
        }
        return smtp;
    }

    public static String selectPort(Connection con)throws SQLException {
        String sql = "select * from SETTINGS";
        Statement stat = null;
        ResultSet rs = null;
        stat = con.createStatement();
        rs = stat.executeQuery(sql);
        String port = null;
        while(rs.next())
        {
            port = rs.getString("port");
        }
        return port;
    }

    public static String selectPasswd(Connection con)throws SQLException {
        String sql = "select * from SETTINGS";
        Statement stat = null;
        ResultSet rs = null;
        stat = con.createStatement();
        rs = stat.executeQuery(sql);
        String passwd = null;
        while(rs.next())
        {
            passwd = rs.getString("passwd");
        }
        return passwd;
    }

}
