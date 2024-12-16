package days04.board.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import days04.board.domain.BoardDTO;



public class BoardDAOImpl implements BoardDAO{

	private Connection conn = null; //-> DBConn.getConnection(); 이러면 결합력이 높아서 안좋다 밖에서 받아오자
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	//생성자 DI
	public BoardDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	//Setter DI :주입받는것
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public 	ArrayList<BoardDTO> select() throws SQLException {
		long seq;
		String title, writer, email;
		Date writedate;
		int readed;

		ArrayList<BoardDTO> list = null;

		String sql = " SELECT seq, title, writer, email, writedate, readed"
				+ " FROM tbl_cstVSBoard "
				+ " ORDER BY seq desc ";

		//부서조회()S
		BoardDTO dto = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<BoardDTO>();
				do {
					seq = rs.getLong("seq");
					title = rs.getString("title");
					writer = rs.getString("writer");
					email = rs.getString("email");
					writedate = rs.getDate("writedate");
					readed = rs.getInt("readed");
					dto = new BoardDTO().builder()
							.seq(seq).title(title)
							.writer(writer).email(email)
							.writedate(writedate).readed(readed).build();

					list.add(dto);
				} while (rs.next());

			} // if

		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<BoardDTO> select(int currentPage, int numberPerPage) throws SQLException {
		long seq;
		String title, writer, email;
		Date writedate;
		int readed;

		ArrayList<BoardDTO> list = null;

		String sql = " SELECT *"
				+ " FROM ("
				+ " SELECT ROWNUM no, t.*  "
				+ " FROM (SELECT seq, title, writer, email,   writedate, readed, tag "
				+ " FROM tbl_cstVSBoard "
				+ " ORDER BY seq desc "
				+ " )t "
				+ " )b "
				+ " WHERE no BETWEEN ? AND ? ";

		//부서조회()S

		BoardDTO dto = null;

		int  start = (currentPage-1)*numberPerPage+1;
		int end = start + numberPerPage -1;
		int totalRecord = getTotalRecords();
		if (end > totalRecord) end = totalRecord;


		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<BoardDTO>();
				do {
					seq = rs.getLong("seq");
					title = rs.getString("title");
					writer = rs.getString("writer");
					email = rs.getString("email");
					writedate = rs.getDate("writedate");
					readed = rs.getInt("readed");
					dto = new BoardDTO().builder()
							.seq(seq).title(title)
							.writer(writer).email(email)
							.writedate(writedate).readed(readed).build();

					list.add(dto);
				} while (rs.next());

			} // if

		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int getTotalRecords() throws SQLException {
		int totalRecords = 0;      
		String sql = "SELECT COUNT(*) FROM tbl_cstvsboard";
		this.pstmt = this.conn.prepareStatement(sql);
		this.rs =  this.pstmt.executeQuery();      
		if( this.rs.next() ) totalRecords = rs.getInt(1);      
		this.rs.close();
		this.pstmt.close();            
		return totalRecords;
	}

	@Override
	public int getTotalPages(int numberPerPage) throws SQLException {
		int totalPages = 0;      
		String sql = "SELECT CEIL(COUNT(*)/?) FROM tbl_cstvsboard";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, numberPerPage);
		this.rs =  this.pstmt.executeQuery();      
		if( this.rs.next() ) totalPages = rs.getInt(1);      
		this.rs.close();
		this.pstmt.close();            
		return totalPages;
	}

	@Override
	public int insert(BoardDTO dto) throws SQLException {
		String sql = "INSERT INTO tbl_cstVSBoard "
				+ " (seq, writer, pwd, email, title, tag, content ) "
				+ "VALUES "
				+ " (seq_tblcatVSBoard.NEXTVAL, ?, ?, ?, ?, ?, ? ) ";
		int rowCount = 0;
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, dto.getWriter());
		this.pstmt.setString(2, dto.getPwd());
		this.pstmt.setString(3, dto.getEmail());
		this.pstmt.setString(4, dto.getTitle());
		this.pstmt.setInt(5, dto.getTag());
		this.pstmt.setString(6, dto.getContent());
		rowCount = this.pstmt.executeUpdate();


		return rowCount;
	}

	public int increaseReader(int seq) throws SQLException {
		String sql = " UPDATE tbl_cstVSBoard "
				+ " SET readed = readed + 1 "
				+ " WHERE seq = ? ";
		int rowCount = 0;
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, seq);
		rowCount = this.pstmt.executeUpdate();
		return rowCount;
	}

	 

	@Override
	public int increaseReaded(long seq)   throws SQLException {
		String sql = " UPDATE tbl_cstvsboard "
				+ " SET readed = readed + 1 "
				+ " WHERE seq = ? ";
		int rowCount = 0;
		this.pstmt = this.conn.prepareStatement(sql); 
		this.pstmt.setLong(1, seq); 
		rowCount = this.pstmt.executeUpdate();      
		return rowCount;
	}

	@Override
	public BoardDTO view(long seq)   throws SQLException {

		String title, writer, email;
		Date writedate;
		int readed;
		String content;

		String sql = 
				"SELECT seq, title, writer, email, writedate, readed, content "
						+ "FROM tbl_cstVSBoard "
						+ "WHERE seq = ? ";

		// 부서조회() S
		BoardDTO dto = null;

		try {         
			pstmt = conn.prepareStatement(sql);
			this.pstmt.setLong(1, seq); 
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// seq, title, writer, email, writedate, readed 
				seq = rs.getLong("seq");
				title = rs.getString("title");
				writer = rs.getString("writer");
				email = rs.getString("email");
				writedate = rs.getDate("writedate");
				readed = rs.getInt("readed");
				content = rs.getString("content");
				dto = new BoardDTO().builder()
						.seq(seq)
						.title(title)
						.writedate(writedate)
						.writer(writer)
						.email(email)
						.readed(readed)
						.content(content)
						.build();
			} // if
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 부서조회() E

		return dto;
	}

	@Override
	public int delete(long seq) throws SQLException {
		String sql = " DELETE FROM tbl_cstvsboard "
				+ " WHERE seq = ? ";
		int rowCount = 0;
		this.pstmt = this.conn.prepareStatement(sql); 
		this.pstmt.setLong(1, seq); 
		rowCount = this.pstmt.executeUpdate();      
		return rowCount;
	}

	@Override
	public int update(BoardDTO dto) throws SQLException {
		String sql = "UPDATE tbl_cstVSBoard "
				+ " SET title = ?, content = ?, email = ? "
				+ " WHERE seq = ? " ;
		int rowCount = 0;
		
		this.pstmt = this.conn.prepareStatement(sql); 
		
		this.pstmt.setString(1, dto.getTitle());
		this.pstmt.setString(2, dto.getContent());
		this.pstmt.setString(3, dto.getEmail());
		this.pstmt.setLong(4, dto.getSeq());

		rowCount = this.pstmt.executeUpdate();


		return rowCount;
	}

	@Override
	public ArrayList<BoardDTO> search(String searchCondition, String searchWord) throws SQLException {
		long seq;
		String title, writer, email;
		Date writedate;
		int readed;

		ArrayList<BoardDTO> list = null;

		String sql = " SELECT seq, title, writer, email, writedate, readed "
				+ " FROM tbl_cstVSBoard ";
				//검색조건에 맞는 WHERE절 추가.s
				
		switch (searchCondition) {
		case "t":
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') ";
			break;
		case "w":
			sql += "WHERE REGEXP_LIKE(writer, ?, 'i') ";
			break;
		case "c":
			sql += "WHERE REGEXP_LIKE(content, ?, 'i') ";
			break;
		case "tc":
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') OR REGEXT_LIKE(content, ?, 'i') ";
			break;

		}
		
		//검색조건에 맞는 WHERE절 추가.E
		sql += " ORDER BY seq desc ";
		System.out.println(sql);
		
		//지울수있음
		String sql2 = " SELECT count(*) FROM tbl_cstVSBoard ";
		switch (searchCondition) {
		case "t":
			sql2 += "WHERE REGEXP_LIKE(title, ?, 'i') ";
			break;
		case "w":
			sql2 += "WHERE REGEXP_LIKE(writer, ?, 'i') ";
			break;
		case "c":
			sql2 += "WHERE REGEXP_LIKE(content, ?, 'i') ";
			break;
		case "tc":
			sql2 += "WHERE REGEXP_LIKE(title, ?, 'i') OR REGEXT_LIKE(content, ?, 'i') ";
			break;

		}
	
		//부서조회()S
		BoardDTO dto = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchWord);
			//tc
			if (searchWord.equals("tc")) 
				pstmt.setString(2, searchWord);
				
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<BoardDTO>();
				do {
					seq = rs.getLong("seq");
					title = rs.getString("title");
					writer = rs.getString("writer");
					email = rs.getString("email");
					writedate = rs.getDate("writedate");
					readed = rs.getInt("readed");
					dto = new BoardDTO().builder()
							.seq(seq).title(title)
							.writer(writer).email(email)
							.writedate(writedate).readed(readed).build();

					list.add(dto);
				} while (rs.next());

			} // if

		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public ArrayList<BoardDTO> search(String searchCondition, String searchWord,int currentPage, int numberPerPage) throws SQLException {
		long seq;
		String title, writer, email;
		Date writedate;
		int readed;

		ArrayList<BoardDTO> list = null;

		String sql = " SELECT *"
				+ " FROM ("
				+ " SELECT ROWNUM no, t.*  "
				+ " FROM (SELECT seq, title, writer, email,   writedate, readed, tag "
				+ " FROM tbl_cstVSBoard ";
		//검색조건에 맞는 WHERE절 추가.s
		
		switch (searchCondition) {
		case "t":
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') ";
			break;
		case "w":
			sql += "WHERE REGEXP_LIKE(writer, ?, 'i') ";
			break;
		case "c":
			sql += "WHERE REGEXP_LIKE(content, ?, 'i') ";
			break;
		case "tc":
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') OR REGEXT_LIKE(content, ?, 'i') ";
			break;

		}
		//검색조건에 맞는 WHERE절 추가.E		
		sql +=  " ORDER BY seq desc "
				+ " )t "
				+ " )b "
				+ " WHERE no BETWEEN ? AND ? ";

		//부서조회()S
		System.out.println( sql );

		BoardDTO dto = null;

		int  start = (currentPage-1)*numberPerPage+1;
		int end = start + numberPerPage -1;
		int totalRecord = getTotalRecords();
		if (end > totalRecord) end = totalRecord;


		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchWord); 
			//tc
			if (searchWord.equals("tc")) {
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				pstmt.setString(4, searchWord);
			}else {
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
				
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<BoardDTO>();
				do {
					seq = rs.getLong("seq");
					title = rs.getString("title");
					writer = rs.getString("writer");
					email = rs.getString("email");
					writedate = rs.getDate("writedate");
					readed = rs.getInt("readed");
					dto = new BoardDTO().builder()
							.seq(seq).title(title)
							.writer(writer).email(email)
							.writedate(writedate).readed(readed).build();

					list.add(dto);
				} while (rs.next());

			} // if

		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int getTotalPages(int numberPerPage, String searchCondition, String searchWord) throws SQLException {
		int totalPages = 0;      
		String sql = "SELECT CEIL(COUNT(*)/?) "
				+ "FROM tbl_cstvsboard " ;
				;
		
		switch (searchCondition) {
		case "t":
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') ";
			break;
		case "w":
			sql += "WHERE REGEXP_LIKE(writer, ?, 'i') ";
			break;
		case "c":
			sql += "WHERE REGEXP_LIKE(content, ?, 'i') ";
			break;
		case "tc":
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') OR REGEXT_LIKE(content, ?, 'i') ";
			break;

		}
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, numberPerPage);
		this.pstmt.setString(2, searchWord);
		if( searchWord.equals("tc") ) totalPages = rs.getInt(1); 
		
		this.rs =  this.pstmt.executeQuery();      
		if( this.rs.next() ) totalPages = rs.getInt(1);      
		this.rs.close();
		this.pstmt.close();            
		return totalPages;
	}

}
