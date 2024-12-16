package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

/*
 * 
 * select dept
 * 
 * 
 * */
public class Ex07_04 {

	public static void main(String[] args) {
		// up_updateDEPT프로시저
		Scanner scanner = new Scanner(System.in);
		String sql = "{CALL up_updateDEPT(?, ?, ?)}"; 

		Connection conn = null;
		CallableStatement cstmt = null;

		conn = DBConn.getConnection();
		ResultSet rs = null;
		
		System.out.print(">수정할 부서 번호를 입력하세요?");
		int deptno = scanner.nextInt();
		
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, deptno); //부서명입력
			cstmt.executeQuery();
			rs = (ResultSet) cstmt.getObject(1);
			
			if (! rs.next()) {
				System.out.println("수정할 부서가 존재하지 않았습니다");
				return;
			}//if
			
			String odname = rs.getString("dname");
			String oloc = rs.getString("loc");
			System.out.println(" Original DNAME : " + odname);
			System.out.println(" Original LOC : " + oloc);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				cstmt.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}
		System.out.print("> 수정할 부서명, 지역명 입력하세요 ?");
		String dneme = scanner.next();
		String loc = scanner.next();
		
		DBConn.close();
		
		
			
			
		
	} // main

} // class
