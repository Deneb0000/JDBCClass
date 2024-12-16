package days04.board.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import days04.board.domain.BoardDTO;
 
public interface BoardDAO {
	// 1. 페이징 처리 X + 게시글 목록
	// PREV 	10	11	12	13	14	15	16	17	18	19	20	NEXT
	ArrayList<BoardDTO> select() throws SQLException; //1페이지의 게시글 갯수를 담는 Arraylist
	
	// 1-2. 페이징 처리 + 게시글 목록
	ArrayList<BoardDTO> select(int currentPage, int numberPerPage) throws SQLException; //1페이지의 게시글 갯수를 담는 Arraylist

	//1-3. 총레코드수
	int getTotalRecords() throws SQLException;
		
	//1-4. 총페이지수

	int getTotalPages(int numberPerPage) throws SQLException;
	
	// 2. 게시글 쓰기 (새글) 
	int insert(BoardDTO dto) throws SQLException;

	// 3
	int increaseReaded(long seq)throws SQLException;
    // 3-2
	BoardDTO view(long seq)throws SQLException;

	
	// 4. 삭제
//	long delete (long seq)throws SQLException;
	int delete (long seq) throws SQLException;

	// 5. 게시글 수정 - 제목, 내용, 이메일? 
//	BoardDTO edit(int seq)throws SQLException;
	int update(BoardDTO dto) throws SQLException;
	
	//6.게시글검색
	ArrayList<BoardDTO> search(String searchCondition, String searchWord) throws SQLException;
	
	//6-1.페이징 처리가된 게시글검색
	ArrayList<BoardDTO> search(String searchCondition, String searchWord, int currentPage, int numberPerPage) throws SQLException;

	//검색할때의 총 페이지수를 반환
	int getTotalPages(int numberPerPage, String searchCondition, String searchWord)throws SQLException; 
}
