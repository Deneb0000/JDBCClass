package days03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.DBConn;

/*
 * 	트랜잭션 처리
 * 논리적인 작업 단위		모두 완료, 모두 취소
 * 예) 계좌 이체
 * 1) A UPDATE;
 * 2) B UPDATE; 	COMMIT	ROLLBACK
 * 
 * BEGIN					try
 * 	1) update				insert	50 o
 * 	2) update				insert	50 x - 오류 나도록 설정
 * 		:
 * 	commit;						commit;
 * EXCPTION		catch
 * 	WHEN ??? THEN
 * 	ROLLBACK;					ROLLBACK; -롤백확인
 * END						}
 * */

public class Ex02 {

	public static void main(String[] args) {
		String sql = "INSET INTO dept WALUES ( ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;;
		int rowCount = 0;
		
		
		try {
//		1,2
			conn = DBConn.getConnection();
			conn.setAutoCommit(false); //자동커밋 꺼짐
			pstmt = conn.prepareStatement(sql);
			
			//ㄱ.
			pstmt.setInt(1, 50);
			pstmt.setString(2, "QC");
			pstmt.setString(3, "SEOUL");
			
			rowCount = pstmt.executeUpdate();
			if( rowCount == 1) {
				System.out.println("첫 번째 부서 추가 성공!!! ");
			}
				
			//ㄴ. 에러발생
				pstmt.setInt(1, 50);
				pstmt.setString(2, "QC");
				pstmt.setString(3, "SEOUL");
				
				rowCount = pstmt.executeUpdate();
				if( rowCount == 1) {
					System.out.println("두 번째 부서 추가 성공!!! ");
				
			}@
				conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		DBConn.close();

	}

}
