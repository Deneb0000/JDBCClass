package days03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.doit.domain.DeptVO;
import org.doit.domain.EmpVO;

import com.util.DBConn;

/*
 *       [실행결과]
ACCOUNTING(10)-3명
  empno ename hiredate pay
  empno ename hiredate pay
  empno ename hiredate pay
RESEARCH(20)-3명
  empno ename hiredate pay
  empno ename hiredate pay
  empno ename hiredate pay
SALES(30)-6명
  empno ename hiredate pay
  empno ename hiredate pay
  empno ename hiredate pay
  empno ename hiredate pay
  empno ename hiredate pay
  empno ename hiredate pay
OPERATIONS(40)-1명
  empno ename hiredate pay 
NULL - 1명
  empno ename hiredate pay
 * */
public class Ex04 {

	public static void main(String[] args) {
		 String deptSql = "SELECT d.deptno, dname,  COUNT(empno) cnt "
		            + "   FROM dept d FULL OUTER JOIN emp e ON d.deptno = e.deptno "
		            + "   GROUP BY d.deptno, dname "
		            + "   ORDER BY d.deptno ASC";

		 String empSql = "SELECT empno, ename, hiredate, sal+NVL(comm,0) pay "
		            + "      FROM emp "
		            + "      WHERE deptno = ?";  // deptno IS NULL (기억)


		Connection conn = null;
		PreparedStatement deptPstmt = null;		
		PreparedStatement empPstmt = null;
		
		ResultSet deptRs = null;
		ResultSet empRs = null;
		
		EmpVO evo = null;
		DeptVO dvo = null;
		
		
		ArrayList<EmpVO> empList = null;
		LinkedHashMap<DeptVO, ArrayList<EmpVO>> lhMap = new LinkedHashMap<>();
		
		conn = DBConn. getConnection();
		
		int deptno, cnt;
		String dname;
		
		int empno;
		String ename;
		LocalDateTime hiredate;
		double pay;
		
		try {
			deptPstmt = conn.prepareStatement(deptSql);
			deptRs = deptPstmt.executeQuery();
			while (deptRs.next()) {
				empList = null; //초기화 해두자
				
				deptno = deptRs.getInt("deptno");
				dname = deptRs.getString("dname");
				cnt = deptRs.getInt("cnt");
				
				dvo = new DeptVO(deptno,dname,null,cnt);
				
//				System.out.printf("%s(%d명)\n", dvo.getDname(), dvo.getCnt());
				
				//해당 부서사원 조회 START
//				deptno = null empsql 설정
				if(deptno == 0) { //null = 0
					empSql = " SELECT empno, ename, hiredate, sal+nvl(comm,0) pay "
							+ " FROM emp "
							+ " WHERE deptno IS NULL " ;
				}
				empPstmt = conn.prepareStatement(empSql);
				if(deptno != 0) empPstmt.setInt(1, deptno); //파라미터값 입력
				empRs = empPstmt.executeQuery();
				
				if (empRs.next()) {
					empList = new ArrayList<EmpVO>();
					
					do {
						empno = empRs.getInt("empno");
						ename = empRs.getString("ename");
						hiredate = empRs.getTimestamp("hiredate").toLocalDateTime();
						pay = empRs.getDouble("pay");
						
						evo = new EmpVO().builder()
								.empno(empno)
								.ename(ename)
								.hiredate(hiredate)
								.sal(pay)
								.build();
						empList.add(evo);
						
					} while (empRs.next());
				}
				
				
				//해당 부서사원 조회 END
				// map 	k,v 엔트리 추가
				lhMap.put(dvo, empList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				
				deptRs.close();
				deptPstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		DBConn.close();
		dispLHMap(lhMap);
		
	}

	private static void dispLHMap(LinkedHashMap<DeptVO, ArrayList<EmpVO>> lhMap) {
		Set<Entry<DeptVO, ArrayList<EmpVO>>> eset = lhMap.entrySet();
		Iterator<Entry<DeptVO, ArrayList<EmpVO>>> eir = eset.iterator();
		while (eir.hasNext()) {
			Entry<DeptVO, ArrayList<EmpVO>> entry = eir.next();
			DeptVO dvo = entry.getKey();
			System.out.printf("%s(%d명)\n", dvo.getDname(), dvo.getCnt());
			
			ArrayList<EmpVO> empList = entry.getValue();
			if (empList == null) {
				System.out.println("\t 해당 부서원 존재 x");
				continue;
			}
			
			Iterator<EmpVO> ir = empList.listIterator();
			while (ir.hasNext()) {
				EmpVO evo = ir.next();
				System.out.printf("\t%d\t%s\t%tF\t%.2f\n"
						,evo.getEmpno(),evo.getEname(),evo.getHiredate(),evo.getSal());
			}
		}
	}

}
