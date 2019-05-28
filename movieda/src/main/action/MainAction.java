package main.action;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;
import auction.vo.AuctionBean;
import notice.service.NoticeListService;
import notice.vo.NoticeBean;
import request.vo.RequestBean;
import review.vo.ReviewBean;
import request.service.RequestListService;
import auction.service.AuctionListService;
import review.service.ReviewListService;


public class MainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		int page=1;	//현재 페이지
		int limit=5;	//한페이지당 보여줄 글의 목록 갯수
		int limitPage=3;	//화면에 보이는 페이지 목록 갯수
		
		ArrayList<AuctionBean> auctionList=new ArrayList<AuctionBean>();
		ArrayList<RequestBean> requestList=new ArrayList<RequestBean>();
		ArrayList<ReviewBean> reviewList=new ArrayList<ReviewBean>();
		ArrayList<NoticeBean> noticeList = new ArrayList<NoticeBean>();
		
		AuctionListService auctionListService = new AuctionListService();
		RequestListService requestListService = new RequestListService();
		ReviewListService reviewListService = new ReviewListService();
		NoticeListService noticeListService = new NoticeListService();
		requestList = requestListService.getArticleList(page, limitPage);
		auctionList = auctionListService.getAuctionList(page, limit);
		reviewList = reviewListService.getArticleList(page, limitPage);
		noticeList = noticeListService.getNoticeList(page, limitPage);
	
		request.setAttribute("auctionList", auctionList);
		request.setAttribute("requestList", requestList);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("noticeList", noticeList);
		ActionForward forward = new ActionForward();
		
		forward.setPath("main.jsp");
		return forward;
	}

}
