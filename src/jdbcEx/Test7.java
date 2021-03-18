package jdbcEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test7 {

    public static void main(String[] args) {
        // 0. import java.sql.*;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 1. JDBC 드라이버 (Oracle) 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. Connection 얻어오기
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
            System.out.println("접속성공");

            // 3. SQL문 준비 / 바인딩 / 실행
            String query = " UPDATE author " + 
                           "    SET author_name = ?, " + 
                           "        author_desc = ?  " + 
                           "  WHERE author_id = ? ";

            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, "박경리");
            pstmt.setString(2, "ㅎ0ㅎㅎ0ㅎㅎ0ㅎ");
            pstmt.setInt(3, 6);  //3 , 6<-- 이표시는 순차적 해당되는곳에 수정 숫자지정 
            
            
            int count = pstmt.executeUpdate();

            // 4.결과처리
            System.out.println(count + "건 처리");
        } catch (ClassNotFoundException e) {
            System.out.println("error: 드라이버 로딩 실패 - " + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            // 5. 자원정리
            try {
                if (rs != null) { rs.close(); }                
                if (pstmt != null) { pstmt.close(); }
                if (conn != null) { conn.close(); }
            } catch (SQLException e) {
                System.out.println("error:" + e);
            }
        }

    }

}
