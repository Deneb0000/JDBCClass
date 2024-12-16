package days04.board.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.util.DBConn;

import days04.board.domain.BoardDTO;
import days04.board.persistence.BoardDAO;
import days04.board.persistence.BoardDAOImpl;

class boardDAOImpTest {
	
	Connection conn = null;
	BoardDAO dao = null;
	
	public boardDAOImpTest() {
		this.conn = DBConn.getConnection();
		this.dao = new BoardDAOImpl(this.conn);
	}

	
	
	@Test
	void testInsert() { //새글쓰기
		//writer, pwd, email, title, tag, content
		BoardDTO dto = new BoardDTO().builder()
				.writer("홍길동")
				.pwd("1234")
				.email("hong@naver.com")
				.title("aaa")
				.tag(0)
				.content("")
				.build();
				
		
		try {
			int rowCount = this.dao.insert(dto);
			if (rowCount == 1) {
				System.out.println("새글 쓰기 성공!!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	void testSearch() {
		try {
//			ArrayList<BoardDTO> list = this.dao.search("w", "홍길동1");
//			ArrayList<BoardDTO> list = this.dao.search("w", "홍길동1", 1,5);
			ArrayList<BoardDTO> list = this.dao.search("w", "홍길동1", 2,5);
			list.forEach(dto ->{
				System.out.println(dto);
			} );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
