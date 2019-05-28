package request.vo;

public class PageInfo {		//몇개씩 잘라서 나오게. 1페이지에 15개같은.
	
	private int page;	//현재
	private int maxPage;	//최대페이지
	private int startPage;	//몇페이지부터
	private int endPage;	//몇페이지까지
	private int listCount;	//자료갯수
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	
	

}
