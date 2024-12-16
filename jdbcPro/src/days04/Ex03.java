package days04;

public class Ex03 {

	public static void main(String[] args) {
		/*
		 * [상세보기] 구현하기 위한 처리 과정
		 * 글목록에서 글제목을 눌렀을때부터 시작이지만 클릭을 못하니깐
		 * 게시글 번호로 입력해서 출력하게 하면 되지않을까
		 * 게시글 번호를 입력받아서
		 * 게시글을 연동시켜서
		 * "SELET *
		 * FROM 게시글
		 * WHERE 게시글넘버"
		 * 게시글을 찾게 만들고
		 * 찾은 게시글의 내용을 출력할수 있게하면 될것같은데
		 * 
		 * */
		
		/*Ex01		boardController			BoardService				BoardDaOImp			BoardDAO		Oracle연동
		 * main() ->	boardStart()
		 * 				  ㄴ메뉴출력() 
		 * 				  ㄴ메뉴선택() 4
		 * 				  ㄴ메뉴처리()
		 * 					ㄴ상세보기() -> viewService(seq 150) ->		increaseReaded(seq) ->
		 * 									트랜젝션 처리				BoardDTO view(seq)
		 * 					보 글번호? 150 	1) 조회수 증가
		 * 									2) 게시글 정보
		 * 				BoardDTO dto = service.riewService(seq)
		 * 				게시글정보 출력
		 * 	
		 * 
		 * 				검색하기()		-> ArrayList<BoardDTO> search...
		 * 
		 * */

	} //main

}//class
