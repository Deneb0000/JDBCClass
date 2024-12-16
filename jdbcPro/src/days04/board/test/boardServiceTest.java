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
import days04.board.service.boardService;

class boardServiceTest {
	
	Connection conn = null;
	BoardDAO dao = null;
	boardService service = null;
	
	public boardServiceTest() {
		this.conn = DBConn.getConnection();
		this.dao = new BoardDAOImpl(this.conn);
		this.service = new boardService(this.dao);
	}

	
	
	@Test
	void testSelectService() {
		ArrayList<BoardDTO> list = this.service.selectService(0, 0);
			list.forEach(dto->{
				System.out.println(dto);
			});
	}
}
