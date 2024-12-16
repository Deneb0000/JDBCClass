package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex01 {

	public static void main(String[] args) {
		System.out.println("Hello World");
		// JDBCClass 폴더 생성
		// 이클립스 실행 설정
		// java Project 생성 : jdbcPro
		// jdbc는 표준 인터페이스다
		// jar = 자바 압축 파일
		// ~~매니저 : ~~를 관리하는 드라이브
		
		//암기
		//Class.forName() 으로 드라이브 로딩 
		//DriverManager의 getConnection()이용하여 Connection을 객체 생성
		// 필요한 작업하고
		//Connection 종료 -> Connection close()
		
		// oracle.jdbc.driver.OracleDriver : 표준 자바 인터페이스를 구현한 클래스
		
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, password);
			// 3. CRUD작업
			System.out.println(conn);
			if (conn != null) {
				conn.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}

	}

}
