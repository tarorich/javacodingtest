package jdbcEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test6 {

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
            String query = "INSERT INTO book VALUES (?, ?, ?, TO_DATE(?,'YYYYMMDD'), ? )"; //시퀀스 없을 경우 삽입, 날짜 sql질의
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, 9); //시퀀스 대신 한 책 번호 
            pstmt.setString(2, "개미"); //책제목
            pstmt.setString(3, "천리안"); //출판사
            pstmt.setString(4, "20210308"); //출판일
            pstmt.setInt(5, 24); //저자코드
            
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
