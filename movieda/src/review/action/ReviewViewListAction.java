package review.action;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import review.service.ReviewListService;
import vo.ActionForward;
import review.vo.ReviewBean;
import review.vo.PageInfo;

public class ReviewViewListAction implements Action {	//게시글 볼때 리스트도 함께 띄워주기
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//execute 메서드를 실행하고 ActionForward를 리턴함. 연결하는 방법과 연결하는 경로가 만들어져있음.
		ArrayList<ReviewBean> reviewList=new ArrayList<ReviewBean>();
		//db에서 받은 select값을 전부다 arrayList에 한줄씩 넣어줌. 아니면 resultSet객체를 계속 열어놔야함. BoardBean에 담는다.
		String sO="";//searchOption
		String sW="";//searchWord
		
		if(request.getParameter("searchOption")!=null){
			sO=request.getParameter("searchOption");
		}
		if(request.getParameter("searchWord")!=null){
			sW=request.getParameter("searchWord");
		}
		//System.out.println(sO);
		
		int page=1;	//현재 페이지
		int limit=5;	//한페이지당 보여줄 글의 목록 갯수
		int limitPage=3;	//화면에 보이는 페이지 목록 갯수
		
		if(request.getParameter("page")!=null) {	//페이지 값 변경
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ReviewListService reviewListService = new ReviewListService();
		int listCount=reviewListService.getListCount(sO,sW);//sO, sW넘겨줘야함.
		//총 리스트 수를 받아옴
		reviewList = reviewListService.getReviewList(page,limit,sO,sW);	//값을 넘길때 페이지에서 몇개를 가져올지?+sW,sO
		//리스트를 받아옴
		//총페이지 수
		int maxPage=(int)((double)listCount/limit+0.95);	//1.2면 두페이지가 필요하기때문에 대충 0.95를 더해준 것.
		//0.95를 더해서 올림 처리
		
		//현재 페이지를 보여줄 시작 페이지수(1,11,21등)
		int startPage = (((int) ((double)page/10 + 0.9)) -1) * limitPage+1;	//몇페이지부터 시작할 것이냐.
		
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30등)
		int endPage = startPage+limitPage-1;
		
		if(endPage>maxPage) endPage=maxPage;
		
		//구한 값을 세팅하고 jsp로 넘기기
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("pageInfo", pageInfo);	//페이지 값을 넘겨줌1
		request.setAttribute("reviewList", reviewList);// 결과값을 넘겨줌2 속성으로 넘겨야함.
		request.setAttribute("sW", sW);
		request.setAttribute("sO", sO);
		ActionForward forward = new ActionForward();
		forward.setPath("/reviewBoard/review_view_list.jsp");
		return forward;
	}

}
