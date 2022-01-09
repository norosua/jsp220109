package board;

public class Pagination {

	// 데이터
	private int currentPageNo = 1;
	private int itemCountPerPage = 3;
	private int pageCountPerBlock = 5;
	private int firstPageNo = 1;
	private int totalItemCount;
	

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}
	
	public Pagination(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public int getTotalItemCount() {
		return this.totalItemCount;
	}
	
	public int getFirstPageNo() {
		return this.firstPageNo;
	}
	
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	
	public int getCurrentPageNo() {
		return this.currentPageNo;
	}
	
	public int getCurrnetBlockNo() {
		return (int)Math.ceil((double)currentPageNo / pageCountPerBlock);
	}
	
	public int getStartPageNoInBlock() {
		return pageCountPerBlock * (getCurrnetBlockNo() - 1) + 1;  
	}
	
	public int getEndPageNoInBlock() {
		
		int endPageNoInBlock = getStartPageNoInBlock() + pageCountPerBlock - 1;
		
		if(endPageNoInBlock > getLastPageNo()) {
			endPageNoInBlock = getLastPageNo();
		}
		
		return endPageNoInBlock;
	}
	
	public int getStartIndex() {
		return itemCountPerPage * (currentPageNo - 1);
	}
	public int getEndIndex() {
		int endIndex = getStartIndex() + itemCountPerPage;
		if(endIndex > totalItemCount) {
			endIndex = totalItemCount;
		}
		return endIndex; 		
	}
	
	public int getLastPageNo() {
		return (int)Math.ceil((double)totalItemCount / itemCountPerPage); // 가장 마지막 페이지 번호
	}
	
}
