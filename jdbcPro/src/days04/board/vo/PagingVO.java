package days04.board.vo;

import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

import days04.board.persistence.BoardDAO;
import days04.board.persistence.BoardDAOImpl;

public class PagingVO {

	public int currentPage = 1;
	public int start ;
	public int end ;
	public boolean prev ;
	public boolean next ;
	
	
	public PagingVO( int currentPage, int numberPerPage, int numberOfpageBlock) {
		Connection conn = DBConn.getConnection();
		BoardDAO dao = new BoardDAOImpl(conn);
		try {
			int totalPages = dao.getTotalPages(numberPerPage);
			
			start = (currentPage-1)/numberOfpageBlock*numberOfpageBlock+1; 
			end = start + numberOfpageBlock -1;
			if(end > totalPages) end = totalPages;

//			System.out.printf("%d\t", currentPage);
//
			if (start != 1) this.prev = true;
//			for (int i = start; i <= end; i++) {
//				System.out.printf(i==currentPage? "[%1$d] " : "%1$d ", i);
//			}

			if (end != totalPages) this.next = true;
			System.out.println();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


	public PagingVO(int currentPage, int numberPerPage, int numberOfpageBlock, String searchCondition,
			String searchWord) {
		Connection conn = DBConn.getConnection();
		BoardDAO dao = new BoardDAOImpl(conn);
		try {
			int totalPages = dao.getTotalPages(numberPerPage
					,searchCondition, searchWord);
			
			start = (currentPage-1)/numberOfpageBlock*numberOfpageBlock+1; 
			end = start + numberOfpageBlock -1;
			if(end > totalPages) end = totalPages;

//			System.out.printf("%d\t", currentPage);
//
			if (start != 1) this.prev = true;
//			for (int i = start; i <= end; i++) {
//				System.out.printf(i==currentPage? "[%1$d] " : "%1$d ", i);
//			}

			if (end != totalPages) this.next = true;
			System.out.println();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
