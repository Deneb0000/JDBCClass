package days04;

import java.sql.Connection;

import com.util.DBConn;

import days04.board.controller.BoardController;
import days04.board.persistence.BoardDAO;
import days04.board.persistence.BoardDAOImpl;
import days04.board.service.boardService;

/*
 * 
 * 게시판 만들기 구현 (모델 2방식 중 MVC 패턴)
 * 
 * http://taeyo.net/ 닷넷사이트
 * 
 * */
public class Ex01 {

	public static void main(String[] args) {
		
		Connection conn = DBConn.getConnection();
		BoardDAO dao = new BoardDAOImpl(conn);
		boardService service = new boardService(dao);
		BoardController controller = new BoardController(service);
		
		controller.boardStart();
		
		/*
		 * 패키지 선언
		 * days04. board
		 * days04. board.controller 모든 것들을 총괄하는 클래스
		 * days04. board.service	확장성을 위해서
		 * days04. board.persistence - DB연동 처리만 하는 애들만 넣을꺼라 DAC 데이터 액세스 
		 * days04. board.domain - DTO, VO 
		 *  
		 *  
테이블 생성

CREATE SEQUENCE seq_tblcatVSBoard
NOCACHE;

CREATE TAbLE tbl_cstVSBoard (

  seq NUMBER NOT NULL PRIMARY KEY,
  writer VARCHAR2 (20) NOT NULL ,
  pwd VARCHAR2 (20) NOT NULL ,
  email VARCHAR2 (100),
  title VARCHAR2 (200) NOT NULL ,
  writedate DATE DEFAULT SYSDATE ,
  readed NUMBER DEFAULT (0),
  tag NUMBER(1)NOT NULL ,
  content CLOB
);

Table TBL_CSTVSBOARD이(가) 생성되었습니다.

3. 더미데이터 생성

--더미 데이터
BEGIN
    FOR i IN 1..150 LOOP
        INSERT INTO tbl_cstvsboard  (seq, writer, pwd, email, title, readed, tag, content)
        VALUES (seq_tblcatVSBoard.NEXTVAL, '홍길동' || MOD(i,10), '1234', '홍길동'||MOD(i,10)||'@sist.co.kr', '더미...', i,0,'더미...'||i );
        END LOOP;
END;
commit;

BEGIN
    UPDATE tbl_cstVSBoard
    SET writer = '박준용'
    WHERE MOD(seq, 15) IN (2);
    commit;
END;

BEGIN
    UPDATE tbl_cstVSBoard
    SET writer = '게시판 구현'
    WHERE MOD(seq, 15) IN (3, 5, 8);
    commit;
END;

도메인에 보드 디티오
		 *  
		 * 
		 * 
		 * [ 메뉴 ]
1. 새글	2. 목록	3. 보기	4. 수정	5. 삭제	6. 검색	7. 종료	
> 메뉴 선택하세요 ? 5
> 삭세할 게시글 번호를 입력? 150

> 게시글 삭제 성공!!!
	int = service.deleteService(seq)
	int = dao.delete(seq)

*/

	}

}
