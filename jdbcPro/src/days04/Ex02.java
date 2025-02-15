package days04;

public class Ex02 {

	public static void main(String[] args) {

		int totalRecord = 136;
		int currentPage = 1;
		int numberPerPage = 15;
		
		int totalPages = (int) Math.ceil((double) totalRecord / numberPerPage);
		System.out.println("TOTAL PAGES : " + totalPages);

		int start;
		int end; 
		
		for (int i = 1; i <= totalPages; i++) {
			start = (i-1)*numberPerPage +1;
			end = start+numberPerPage-1;
			if (end>totalRecord)end = totalRecord;
			System.out.printf("%d 페이지 : start = %d ~ end = %d \n", i, start, end);
		}
	}

}
