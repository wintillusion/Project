package member.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import request.service.RequestListService;
import review.service.ReviewListService;
import request.vo.RequestBean;
import review.vo.ReviewBean;
import vo.ActionForward;
import request.vo.PageInfo;
import action.Action;

public class MyArticleAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
	
		ArrayList<RequestBean> requestList=new ArrayList<RequestBean>();
		ArrayList<ReviewBean> reviewList=new ArrayList<ReviewBean>();
		
		HttpSession session = request.getSession();
		int page=1;	//현재 페이지
		int limit=5;	//한페이지당 보여줄 글의 목록 갯수
		int limitPage=3;	//화면에 보이는 페이지 목록 갯수
		
		String id=null;
		if(session.getAttribute("id")!=null){
		 		id=(String)session.getAttribute("id");
		 }
		
		
		
		RequestListService requestListService = new RequestListService();
		ReviewListService reviewListService = new ReviewListService();
		int listCount=requestListService.getListCount();

		requestList = requestListService.getArticleList(page, limit, id);	//값을 넘길때 페이지에서 몇개를 가져올지?
		reviewList = reviewListService.getArticleList(page, limitPage, id);
		int maxPage=(int)((double)listCount/limit+0.95);	//1.2면 두페이지가 필요하기때문에 대충 0.95를 더해준 것.
		//0.95를 더해서 올림 처리
		
		//현재 페이지를 보여줄 시작 페이지수(1,11,21등)
		int startPage = (((int) ((double)page/10 + 0.9)) -1) * 10+1;	//몇페이지부터 시작할 것이냐.
		
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30등)
		int endPage = startPage+10-1;
		
		if(endPage>maxPage) endPage=maxPage;
		
		
		
		//구한 값을 세팅하고 jsp로 넘기기
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		//pageInfo.setListCount(listCount2);
		
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("id", id);
		request.setAttribute("pageInfo", pageInfo);	//페이지 값을 넘겨줌1
		request.setAttribute("requestList", requestList);// 결과값을 넘겨줌2 속성으로 넘겨야함.
		request.setAttribute("reviewList", reviewList);
		ActionForward forward = new ActionForward();
		forward.setPath("/member/member_main.jsp");
		return forward;
	}
}
