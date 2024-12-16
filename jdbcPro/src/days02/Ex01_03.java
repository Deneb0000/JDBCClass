package days02;
/*
 * [jdbc] emp 테이블의 모든 사원 정보 조회
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;

import oracle.jdbc.driver.OracleDriver;

public class Ex01_03 {

	public static void main(String[] args) {
		// 1. jdbc 드라이버 로딩 - Class forName();
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
			//	192.168.10.162
		String user = "scott";
		String password = "tiger";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * "
				+ " FROM emp ";
		int empno;
		String job, ename;
		int mgr;
//		String hiredate;
		double sal;
		double comm;
		int deptno;
		Date hiredate;
//		LocalDateTime hiredate;
		
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				empno = rs.getInt("empno");
				ename = rs.getString("ename");
				job = rs.getString("job");
				mgr = rs.getInt("mgr");
				hiredate = rs.getDate("hiredate");
				sal = rs.getDouble("sal");
				comm = rs.getDouble("comm");
				deptno = rs.getInt("deptno");
				
				System.out.printf("%d\t%s\t%s\t%d\t%s\t%.2f\t%.2f\t%d\n"
								, empno, ename, job, mgr, hiredate,sal, comm, deptno);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		// 2. Connection 객체 - DriverManager
		// 3. CRUD 작업 - 
		// 4. Connection 객체 닫기
		
	}

}
