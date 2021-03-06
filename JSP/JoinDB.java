package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinDB {
	 private static JoinDB instance = new JoinDB();

	    public static JoinDB getInstance() {
	        return instance;
	    }
	    public JoinDB() {  }

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
	                returns ="~SUCCESS~";
	            } else {
	                returns="~FAIL~";
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