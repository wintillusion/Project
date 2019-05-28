package member.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import auction.service.AuctionInfoListService;
import auction.service.AuctionEndViewService;
import auction.vo.AuctionInfoBean;
import auction.vo.AuctionBean;
import vo.ActionForward;
import auction.vo.PageInfo;
import action.Action;

public class MyAuctionAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ArrayList<AuctionInfoBean> auctionInfoList=new ArrayList<AuctionInfoBean>();
		ArrayList<AuctionBean> auctionEndList=new ArrayList<AuctionBean>();
		
		HttpSession session = request.getSession();
		int page=1;	//현재 페이지
		int limit=5;	//한페이지당 보여줄 글의 목록 갯수
		int limitPage=3;	//화면에 보이는 페이지 목록 갯수
		
		String id=null;
		if(session.getAttribute("id")!=null){
		 		id=(String)session.getAttribute("id");
		 }

		AuctionInfoListService auctionInfoListService = new AuctionInfoListService();
		AuctionEndViewService auctionEndViewService = new AuctionEndViewService();
		int listCount=auctionInfoListService.getListCount();

		auctionInfoList = auctionInfoListService.getAuctionInfoList(page, limitPage, id);
		auctionEndList = auctionEndViewService.getArticleList(page, limitPage, id);

		int maxPage=(int)((double)listCount/limit+0.95);	
		
		int startPage = (((int) ((double)page/10 + 0.9)) -1) * 10+1;
		
		
		int endPage = startPage+10-1;
		
		if(endPage>maxPage) endPage=maxPage;
		
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndpage(endPage);
		pageInfo.setListCount(listCount);

		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("id", id);
		request.setAttribute("pageInfo", pageInfo);	
		request.setAttribute("auctionInfoList", auctionInfoList);
		request.setAttribute("auctionEndList", auctionEndList);
		ActionForward forward = new ActionForward();
		forward.setPath("/member/member_auction.jsp");
		return forward;
	}
}
