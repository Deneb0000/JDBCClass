package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import org.doit.domain.EmpVO;
import org.doit.domain.SalgradeVO;

import com.util.DBConn;

/*
[실행결과]
1등급   (     700~1200 ) - 2명                        key
      20   RESEARCH   7369   SMITH   800               value
      30   SALES         7900   JAMES   950
2등급   (   1201~1400 ) - 2명
   30   SALES   7654   MARTIN   2650
   30   SALES   7521   WARD      1750   
3등급   (   1401~2000 ) - 2명
   30   SALES   7499   ALLEN      1900
   30   SALES   7844   TURNER   1500
4등급   (   2001~3000 ) - 4명
    10   ACCOUNTING   7782   CLARK   2450
   20   RESEARCH   7902   FORD   3000
   20   RESEARCH   7566   JONES   2975
   30   SALES   7698   BLAKE   2850
5등급   (   3001~9999 ) - 1명   
   10   ACCOUNTING   7839   KING   5000
 */  

public class Ex04 {

	public static void main(String[] args) {
		String sql = " SELECT grade, s.losal, s.hisal, COUNT(*) cnt "
				+ " FROM emp e JOIN salgrade s ON sal BETWEEN losal AND hisal "
				+ " GROUP BY grade , s.losal , s.hisal "
				+ " ORDER BY grade " ;
		String sql2 = " SELECT d.deptno, dname, empno, ename, sal "
				+ " FROM dept d RIGHT JOIN emp e ON d.deptno = e.deptno "
				+ "            JOIN salgrade s ON sal BETWEEN losal AND hisal"
				+ " WHERE grade = ";
		int deptno;
		String dname;
		int empno;
		String ename;
		double sal;

		Connection conn = null;
		Statement stmt = null, stmt2 = null;
		ResultSet rs = null, rs2 = null;

		int grade;
		int losal, hisal;
		int cnt;

		ArrayList<SalgradeVO> list = new ArrayList<>();
		SalgradeVO vo= null;

		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				grade = rs.getInt("grade");
				losal = rs.getInt("losal");
				hisal = rs.getInt("hisal");
				cnt = rs.getInt("cnt");

				vo = new SalgradeVO().builder()
						.grade(grade)
						.losal(losal)
						.hisal(hisal)
						.cnt(cnt)
						.build();

				//						list.add(vo);
				System.out.printf("%d 등급 (%d ~ %d) - %d명\n"
						, vo.getGrade(), vo.getLosal(), vo.getHisal(), vo.getCnt());
				//그 등급에 해당되는 사원 정보 출력 START 
				String sql3 = sql2 + vo.getGrade();
				stmt2 = conn.createStatement();
				rs2 = stmt2.executeQuery(sql3);

				if(rs2.next()) {
					do {
						//d.deptno, dname, empno, sal "
						deptno = rs2.getInt("deptno");
						dname = rs2.getString("dname");
						empno = rs2.getInt("empno");
						ename = rs2.getString("ename");
						sal = rs2.getDouble("sal");
						System.out.printf("\t%d\t%s\t%d\t%s\t%.2f\n"
								,deptno, dname, empno, ename, sal);
					} while (rs2.next());
				} else {
					System.out.println("\t 사원 존재 x ");}
			}
			rs2.close();
			stmt2.close();
			//END
		 //while

		/*
		list.forEach( svo -> {
			System.out.printf("%d 등급 (%d ~ %d )"
					,svo.getGrade(), svo.getLosal(), svo.getHisal(), svo.getCnt());
		});
		 */
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		//객체닫기
		try {
			rs.close();
			stmt.close();
			//conn.close();
			DBConn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
}