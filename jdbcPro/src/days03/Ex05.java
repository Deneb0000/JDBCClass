package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import oracle.jdbc.rowset.OracleCachedRowSet;

/*
 * 	https://docs.oracle.com/cd/E17781_01/appdev.112/e18805/addfunc.htm#TDPJD209
 * 
 * */
public class Ex05 {

	public static void main(String[] args) {
	  // [저장 프로시저]   - 입력받은 ID를 사용 여부 체크하는 프로시저
      //       ㄴ 회원가입
      //             아이디 : [   hong     ] <ID중복체크버튼>
      //             비밀번호      
      //             이메일
      //             주소
      //             연락처
      //             등등

		//emp 테이블의 empno  = 아이디(가정)
	Scanner scanner = new Scanner(System.in);
	System.out.print(">중복 체클할 ID(empno)를 입력 ? ");
	int id = scanner.nextInt(); //7369 존재		9999미존재 : 사용가능한 아이디입니더
	
	// UP_IDCHECK 프로시저 cstmt 사용해서 처리 코딩 
//	 String sql = "{call UP_IDCHECK(?,?)}";
	 String sql = "{call UP_IDCHECK(pid=>?,pcheck=>?)}";
	 
	 Connection conn = null;
	 CallableStatement cstmt = null;
	 int check = -1;
	 
	 conn = DBConn.getConnection();
	 
	 try {
		cstmt = conn.prepareCall(sql);	
		// INPUT ?, OUTPUT ?
		cstmt.setInt(1, id); //입력용 매개변수
		cstmt.registerOutParameter(2, OracleTypes.INTEGER);
		cstmt.executeQuery();	// 출력용매개변수로 받을꺼다
		check = cstmt.getInt(2);
		if (check==0) {
			System.out.println("사용 가능한 ID(empno)입니다");
		}else {
			System.out.println("이미 사용 중인 ID(empno) 입니다");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			cstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	 
	 
	 DBConn.close();
	
	
	}

}
