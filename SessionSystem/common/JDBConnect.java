package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
    public Connection con;            // DB와 연결을 하는 역할
    public Statement stmt;           // 인파라미터가 없는 정적 쿼리문을 실행할 때 사용
    public PreparedStatement psmt;     /* 인파라미터가 있는 동적 쿼리문을 실행할 때 사용
                                  인파라미터는 쿼리문 작성시 매개변수로 전달된 값을 설정할 때 사용 [?로 표현함] */
    public ResultSet rs;            // SELECT 쿼리문의 결과를 저장할 때 사용

    // 기본 생성자
    /* DB의 접속정보를 클래스안에 모두 입력하는 방식
     * 단순 접속테스트용도로 사용하나, 실무에선 지양하는 방식
     * 접속정보가 변경될 때마다 클래스 수정해야하는 번거로움 */
    public JDBConnect() {                                 // JDBConnect생성자는 JDBC드라이버를 이용해 오라클 DB에 연결하는 역할을 함
        try {
            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");            /* JDBC드라이버를 메모리에 로드, forName()는 new키워드 대신 클래스명을 통해 
                                                      직접 객체를 생성한 후 메모리에 로드하는 메소드, 인수=오라클드라이버이름*/
            // DB에 연결
            String url = "jdbc:oracle:thin:@localhost:1522:xe";     // "오라클프로토콜:@호스트명:포트번호:SID"
            String id = "musthave";                           // DB USER ID
            String pwd = "1234";                            // DB USER PW
            con = DriverManager.getConnection(url, id, pwd);       /* Stirng url,id,pwd를 인수로 DriverManager클래스의 getConnection()을 
                                                         호출하면 되고, 연결성공시 Connection객체가 반환됨 
                                                         (반환된 Connection객체를 통해 오라클에 연결함) */

            System.out.println("DB 연결 성공(기본 생성자)");
        }
        catch (Exception e) {            
            e.printStackTrace();                           /* printStackTrace 예외발생시 당시의 호출스택에 있던 메소드의 정보와 예외결과를 
                                                         화면에 출력 예외상황을 분석하기 위한 용도로 사용 (개발자에게 디버깅할 힌트를 제공)*/
        }
    }            

    // 두 번째 생성자 [접속정보를 외부로부터 받는 방식]
    /* web.xml에 입력한 후 내장 객체를 통해 가져오는 방식
     * DB접속이 필요할 때마다 동일한 코드를 JSP에서 반복해서 기술해야하는 번거로움 */
    public JDBConnect(String driver, String url, String id, String pwd) {
        try {
            // JDBC 드라이버 로드
            Class.forName(driver);  

            // DB에 연결
            con = DriverManager.getConnection(url, id, pwd);

            System.out.println("DB 연결 성공(인수 생성자 1)");
        }
        catch (Exception e) {            
            e.printStackTrace();
        }
    }

    // 세 번째 생성자
    /* web.xml에 기술한 매개변수를 생성자에서 직접 가져올 수 있도록 하는 방식[권장] */
    public JDBConnect(ServletContext application) {
        try {
            // JDBC 드라이버 로드
            String driver = application.getInitParameter("OracleDriver"); 
            Class.forName(driver); 

            // DB에 연결
            String url = application.getInitParameter("OracleURL"); 
            String id = application.getInitParameter("OracleId");
            String pwd = application.getInitParameter("OraclePwd");
            con = DriverManager.getConnection(url, id, pwd);

            System.out.println("DB 연결 성공(인수 생성자 2)"); 
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 연결 해제(자원 반납)
    public void close() {                      // DB작업 종료 후 자원절약을 위해 연결해제 close()메소드 사용
        try {            
            if (rs != null) rs.close();          // 조건문을 사용해 사용된 객체만 닫아줌
            if (stmt != null) stmt.close();
            if (psmt != null) psmt.close();
            if (con != null) con.close(); 

            System.out.println("JDBC 자원 해제");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
