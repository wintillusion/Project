package member.vo;

//한페이지에 적당량의 정보를띄움
public class PageInfo {

	
	private int page;			 //현재페이지
	private int maxPage;		 //최대페이지
	private int startPage;		 // ex 1~10 페이지 넘기면 11~20 페이지 
	private int endpage;					
	private int listCount; 		//자료갯수
	
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
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	

}
