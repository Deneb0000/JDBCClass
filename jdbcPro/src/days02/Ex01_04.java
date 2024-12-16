package days02;
/*
 * [jdbc] emp 테이블의 모든 사원 정보 조회
 * org.doti domain 패키지
 * ㄴEmpVO.java (value Object)
 * ArratList<EmpVO> list
 * dispEmp() 출력함수
 * com.utill 패키지
 * 	ㄴDBconn.java
 * 		ㄴ Connection getConnection() 메서드구현
 * 		ㄴ close() 메서드
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.doit.domain.EmpVO;

import com.util.DBConn;

import oracle.jdbc.driver.OracleDriver;

public class Ex01_04 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * "
				+ " FROM emp ";
		int empno;
		String job, ename;
		int mgr;
		double sal;
		double comm;
		int deptno;
		LocalDateTime hiredate;

		ArrayList<EmpVO> list = new ArrayList<>();
		EmpVO vo= null;;
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				empno = rs.getInt("empno");
				ename = rs.getString("ename");
				job = rs.getString("job");
				mgr = rs.getInt("mgr");
				hiredate = rs.getTimestamp("hiredate").toLocalDateTime();
				sal = rs.getDouble("sal");
				comm = rs.getDouble("comm");
				deptno = rs.getInt("deptno");

				vo = new EmpVO(empno, job, ename, mgr, sal, comm, deptno, hiredate);
				list.add(vo);


			}
			dispEmp(list);

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

		}

	}
	

	public static void dispEmp(ArrayList<EmpVO>list) {
		if(list.size() == 0) {
			System.out.println("사원이 존재하지 않습니다.");
			return;
		}
		
		//사원정보출력
		//ㄴ.
		list.forEach(vo->
		System.out.printf("%d\t%s\t%s\t%d\t%tF\t%.2f\t%.2f\t%d\n"
				, vo.getEmpno(), vo.getEname(), vo.getJob(), vo.getMgr(), vo.getHiredate(),vo.getSal(), vo.getComm(), vo.getDeptno()));

		
//		//사원정보출력
//		ㄱ.
//		Iterator<EmpVO> ir =  list.iterator();
//		while (ir.hasNext()) {
//			EmpVO vo = (EmpVO) ir.next();
//			System.out.printf("%d\t%s\t%s\t%d\t%tF\t%.2f\t%.2f\t%d\n"
//					, vo.getEmpno(), vo.getEname(), vo.getJob(), vo.getMgr(), vo.getHiredate(),vo.getSal(), vo.getComm(), vo.getDeptno());
//		}
//	}


	}}
