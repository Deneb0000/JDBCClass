package days04;import java.util.Currency;

public class Ex04 {

	public static void main(String[] args) { //페이징 블럭 만들기
		// 		 	1	2	3	4	5	6	7	8	9	10	NEXT
		// PREV 	11	12	13	14	15	16	17	18	19	20	NEXT
		//		int currentPage = 1; //현재보고있는 페이지 번호
		int numberOfpageBlock = 10;
		int totalRecords = 151;
		int numberPerPage = 10; //1페이지에 뿌릴 게시글의 수
		int totalpages = (int) Math.ceil((double)151/10);
		int start = 1, end = 10;

		for (int currentPage = 1; currentPage <= totalpages; currentPage++) {
			start = (currentPage-1)/numberOfpageBlock*numberOfpageBlock+1; 
			end = start + numberOfpageBlock -1;
			if(end > totalpages) end = totalpages;

			System.out.printf("%d\t", currentPage);

			if (start != 1) { System.out.print("prev<");

			}
			for (int i = start; i <= end; i++) {
				System.out.printf(i==currentPage? "[%1$d] " : "%1$d ", i);
			}

			if (end != totalpages) { System.out.print(">next");}
			System.out.println();
		}
	}
}


