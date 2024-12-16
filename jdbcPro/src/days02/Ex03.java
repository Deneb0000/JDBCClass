package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.doit.domain.EmpDeptSalGradelVO;
import org.doit.domain.EmpVO;

import com.util.DBConn;

/*
 *  emp+dept+salgrade => EmpDeptSalGradeVO  
 */
public class Ex03 {

	public static void main(String[] args) {


		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT empno, ename, dname, hiredate , sal+NVL(comm,0) pay, grade "
				+ "FROM emp e JOIN dept d ON e.deptno = d.deptno"
				+ "           JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal ";
		int empno;
		String ename;
		String dname;
		double pay;		
		int grade;
		LocalDateTime hiredate;

		ArrayList<EmpDeptSalGradelVO> list = new ArrayList<>();
		EmpDeptSalGradelVO vo= null;
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				//empno, ename, dname, hiredate , sal+NVL(comm,0) pay, grade
				empno = rs.getInt("empno");
				ename = rs.getString("ename");
				dname = rs.getString("dname");
				hiredate = rs.getTimestamp("hiredate").toLocalDateTime();
				pay = rs.getDouble("pay"); 
				grade= rs.getInt("grade");

				vo = new EmpDeptSalGradelVO(empno, ename, pay, hiredate, dname, grade); 
				list.add(vo);
			}
			list.forEach(evo -> System.out.println(evo));			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				//				conn.close();
				DBConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//main

	}//class
}