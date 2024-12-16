package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

/*
 * 로그인 (인증) / 인가 (권한여부 체크)
 * 아이디 / 비밀번호 입력
 * [로그인] [회원가입]
 * ***인가한다 = 로그인 버튼을 누른다
 * 
 * 	emp	/ empno(id) / ename(pw)
 * 
 * */
public class Ex06 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("> 로그인할 ID(empno) / PW(ename)를 입력? ");
		int id = scanner.nextInt();
		String pw = scanner.next();

		String sql = "{call UP_LOGIN(?, ?, ?)}";

		Connection conn = null;
		CallableStatement cstmt = null;
		int check = -1;

		conn = DBConn.getConnection();

		try {
			cstmt = conn.prepareCall(sql);	
			// INPUT ?, OUTPUT ?
			cstmt.setInt(1, id); //입력용 매개변수
			cstmt.setString(2, pw);
			
			cstmt.registerOutParameter(3, OracleTypes.INTEGER);
			
			cstmt.executeQuery();	// 출력용매개변수로 받을꺼다
			check = cstmt.getInt(3);
			if (check==0) {
				System.out.println("로그인 성공");
			}else if(check == 1){
				System.out.println("잘못된 비밀번호 입니다");
			}else if (check == -1) {
				System.out.println("존재하지 않는 아이디 입니다");
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
		
		scanner.close();


	}

}
/*
 * -- ID와 PW를 입력받아서 인증처리하는 저장
CREATE OR REPLACE PROCEDURE up_login
(
  pid IN emp.empno%TYPE
  , ppwd IN emp.ename%TYPE
  , pcheck  OUT NUMBER --   0(성공), 1(ID 존재, pwd x), -1(ID존재 X)
)
IS 
  vpwd emp.ename%TYPE;
BEGIN
   SELECT COUNT(*) INTO pcheck
   FROM emp
   WHERE empno = pid;

   IF pcheck = 1 THEN  -- ID 존재
      SELECT ename INTO vpwd
      FROM emp
      WHERE empno = pid;

      IF vpwd = ppwd THEN -- ID 존재 O, PWD 일치
         pcheck := 0;
      ELSE -- ID 존재 O, PWD X
         pcheck := 1;
      END IF;
   ELSE -- ID 존재
         pcheck := -1;
   END IF;

--EXCEPTION
--  WHEN OTHERS THEN
--    RAISE AP_E)
END;

DECLARE 
    vcheck NUMBER;
BEGIN
    up_login(73699,'SMITHs', vcheck);
    DBMS_OUTPUT.PUT_LINE (vcheck);
END;
 * */