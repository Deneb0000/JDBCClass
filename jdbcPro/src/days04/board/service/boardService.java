package days04.board.service;

import java.sql.SQLException;
import java.util.ArrayList;

import days04.board.domain.BoardDTO;
import days04.board.persistence.BoardDAO;
import days04.board.persistence.BoardDAOImpl;

//트랜젝션 처리
public class boardService {

	private BoardDAO dao = null;

	// 1. 생성자 DI

	// 2. Setter DI

	// 1. 게시글 목록 서비스
	public ArrayList<BoardDTO> selectService(int currentPage, int numberPerPage){

		ArrayList<BoardDTO> list = null;

		// 1. DB연동 list
		try {
			((BoardDAOImpl)this.dao).getConn().setAutoCommit(false);
			list = this.dao.select(currentPage, numberPerPage);

			// 3. 문자 전송, 메일 전송 기타 ...
			System.out.println("> 게시글 목록 : 문자/메일 전송 작업 ... ");
		} catch (SQLException e) {
			try {
				((BoardDAOImpl)this.dao).getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				((BoardDAOImpl)this.dao).getConn().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	//게시글 쓰기 서비스
	public int insertService(BoardDTO dto) {
		int rowCount = 0;
		try {
			this.dao.insert(dto);
			// 2. 로그 기록 작업
			System.out.println("> 게시글 쓰기 : 로그 기록 작업 ... ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}

	//상세
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	public boardService(BoardDAO dao) {
		super();
		this.dao = dao;
	}

	public BoardDTO viewService(long seq) {
		int rowCount = 0;
		BoardDTO dto = null;
		try {
			// 1. 조회수 증가
			rowCount =  this.dao.increaseReaded(seq);
			// 2. 게시글
			dto = this.dao.view(seq);
			// 2. 로그 기록 작업
			System.out.println("> 게시글 보기 : 로그 기록 작업 ... ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	public int deleteService(long seq) {
		int rowCount = 0;
		try {
			// 1. 삭제
			rowCount =  this.dao.delete(seq);
			// 2. 로그 기록 작업
			System.out.println("> 게시글 삭제 : 로그 기록 작업 ... ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}

	public int updateService(BoardDTO dto) {
		int rowCount = 0;
		try {
			this.dao.update(dto);
			// 2. 로그 기록 작업
			System.out.println("> 게시글 수정 : 로그 기록 작업 ... ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}
// 검색서비스	
	public ArrayList<BoardDTO> searchService(String searchCondition, String searchWord, int currentPage, int numberPerPage){

		ArrayList<BoardDTO> list = null;

		// 1. DB연동 list
		try {
			((BoardDAOImpl)this.dao).getConn().setAutoCommit(false);
			list = this.dao.search(searchCondition,searchWord, currentPage, numberPerPage);

			// 3. 문자 전송, 메일 전송 기타 ...
			System.out.println("> 게시글 검색 : 문자/메일 전송 작업 ... ");
		} catch (SQLException e) {
			try {
				((BoardDAOImpl)this.dao).getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				((BoardDAOImpl)this.dao).getConn().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	//	public int editService(long seq) {
	//		int rowCount = 0;
	//		try {
	//			this.dao.edit(seq);
	//			// 2. 로그 기록 작업
	//			System.out.println("> 게시글 수정 : 로그 기록 작업 ... ");
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		return rowCount;
	//	}



}
