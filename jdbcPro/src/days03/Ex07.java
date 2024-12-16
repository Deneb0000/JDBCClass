package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

/*
 * 
 * select dept
 * 
 * 
 * */
public class Ex07 {

	public static void main(String[] args) {
		String sql = "{call UP_SELECTDEPT(?)}";

		Connection conn = null;
		CallableStatement cstmt = null;

		conn = DBConn.getConnection();

		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(sql);	
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.executeQuery();
			rs = (ResultSet) cstmt.getObject(1);

			int deptno;
			String dname, loc;
			while (rs.next()) {
				deptno = rs.getInt("deptno");
				dname = rs.getString("dname");
				loc = rs.getString("loc");
				System.out.printf("%d\t%s\t%s\n", deptno, dname, loc);
			} // while

		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				cstmt.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		}


		DBConn.close();

	} // main

} // class

/*
		-- DEPT 테이블의 모든 부서 정보를 조회하는 저장프로시저
		CREATE OR REPLACE PROCEDURE up_selectdept
		(
		   pdeptcursor OUT SYS_REFCURSOR
		)
		IS  
		BEGIN
		   OPEN pdeptcursor FOR
		     SELECT * 
		     FROM dept;
		--EXCEPTION
		--  WHEN OTHERS THEN
		--    RAISE AP_E)
		END;
 * */
