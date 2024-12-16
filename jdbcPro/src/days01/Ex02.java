package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex02 {

	public static void main(String[] args) {
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		Connection conn = null;
		
		Statement stmt = null;//명령을 수행하는 배달의 민족(?)
		ResultSet rs = null; //결과물을 다 갖고 있음
		
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, password);
			// 3. CRUD작업
			
			String sql = " SELECT *"
						+ " FROM dept";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); //select
			
			int deptno;
			String dname, loc;
			
			while (rs.next()) {	//커서 같은거
				deptno = rs.getInt("deptno");
				dname = rs.getString("dname");
				loc = rs.getString("loc");
				System.out.printf("%d\t%s\t%s\n",deptno, dname, loc);
			}
			
			rs.close();
			stmt.cancel();
			
			
//			System.out.println(conn);
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
