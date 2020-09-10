package socket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB_user {
	private static ConnectDB_user instance = new ConnectDB_user();

    public static ConnectDB_user getInstance() {
        return instance;
    }
    public ConnectDB_user() {  }

    // oracle 계정
    String jdbcUrl = "jdbc:oracle:thin:@192.168.0.43:1521:orcl";
    String userId = "system";
    String userPw = "1234";

    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;

    String sql = "";
    String sql2 = "";
    String returns = "a";
    String name="a";
    
    public String connectionDB(String id, String pw) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT * FROM userTBL WHERE id = ? AND pw = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	name=rs.getString("name");
                returns ="S~"+name+"~";
            } else {
                returns="F~F";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();   } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {}
        }
        return returns;
    }
}
