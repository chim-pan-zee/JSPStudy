package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
   /*
    * DB커넥션풀을 사용하는 이유
    * 웹 은 클라이언트의 요청에 서버가 응답하는 구조방식
    * 요청 시 마다 DB와 새로연결했다가 해제하면 시간이 오래걸림
    * Connection 객체를 미리 생성해 Pool(풀)에 넣고 요청이 있을 때 이미 생성된 Connection 객체를 가져다 사용하는 기법
    * 사용완료된 객체는 연결을 해제하는 것이 아닌 Pool(풀)에 반납하여 필요할 때 재사용함
    * */
    public Connection con;
    public Statement stmt;
    public PreparedStatement psmt;
    public ResultSet rs;

    // 기본 생성자
    public DBConnPool() {
        try {
            // 커넥션 풀(DataSource) 얻기
            Context initCtx = new InitialContext();                     // 이름과 실제를 연결해주는 객체 생성
            Context ctx = (Context)initCtx.lookup("java:comp/env");         /* lookup()메소드에 이름을 건네 원하는 
                                                               객체를 찾아올 수 있음 
                                                               "java:comp/env" 웹애플리케이션 최상위 경로*/
            DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");   //dbcp_myoracle 자원을 얻어옴

            // 커넥션 풀을 통해 연결 얻기
            con = source.getConnection();                           //dbcp_myoracle 연결객체를 커넥션에 저장

            System.out.println("DB 커넥션 풀 연결 성공");
        }
        catch (Exception e) {
            System.out.println("DB 커넥션 풀 연결 실패");
            e.printStackTrace();
        }
    }

    // 연결 해제(자원 반납)
    public void close() {
        try {            
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (psmt != null) psmt.close();
            if (con != null) con.close();  // 자동으로 커넥션 풀로 반납됨

            System.out.println("DB 커넥션 풀 자원 반납");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
