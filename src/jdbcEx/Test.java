package jdbcEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

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
            String query = "select zipCode,   sido ,   sidoE,   sigungu,   sigunguE, dong, building from post where building like '%대학교%' and sido ='서울특별시' order by 7 desc";
            pstmt = conn.prepareStatement(query);

            rs = pstmt.executeQuery();

            // 4.결과처리
            while (rs.next()) {
                String zipcode = rs.getString("zipcode");
                String sido = rs.getString("sido");
                String sidoE = rs.getString("sidoE");
                String sigungu = rs.getString("sigungu");
                String sigunguE = rs.getString("sigunguE");
                String dong = rs.getString("dong");
                String building = rs.getString("building");
                
                
                System.out.println(zipcode + "\t" + sido + "\t" + sidoE + "\t" + sigungu + "\t" + sigunguE + "\t" + dong + "\t"+ building + "\t");
            }

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