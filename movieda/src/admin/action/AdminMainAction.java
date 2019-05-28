package admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import review.service.ReviewListService;
import request.service.RequestListService;
import notice.service.NoticeListService;
import auction.service.AuctionListService;
import auction.vo.AuctionBean;
import request.vo.RequestBean;
import notice.vo.NoticeBean;
import review.vo.ReviewBean;
import member.vo.MemberBean;
import auction.vo.PageInfo;
import member.service.MemberListService;
import vo.ActionForward;

public class AdminMainAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	
		ArrayList<AuctionBean> auctionList = new ArrayList<AuctionBean>();
		ArrayList<RequestBean> requestList = new ArrayList<RequestBean>();
		ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
		ArrayList<NoticeBean> noticeList = new ArrayList<NoticeBean>();
		ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
		
		HttpSession session = request.getSession();
		int page = 1; // 현재 페이지
		int limit = 5; // 한페이지당 보여줄 글의 목록 갯수
		int limitPage = 3; // 화면에 보이는 페이지 목록 갯수

		String id = null;
		if (session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
		}

		AuctionListService auctionListService = new AuctionListService();
		RequestListService requestListService = new RequestListService();
		ReviewListService reviewListService = new ReviewListService();
		NoticeListService noticeListService = new NoticeListService();
		MemberListService memberListService = new MemberListService();
		
		auctionList = auctionListService.getAuctionList(page, limitPage);
		requestList = requestListService.getArticleList(page, limitPage);
		reviewList = reviewListService.getArticleList(page, limitPage);
		noticeList = noticeListService.getNoticeList(page, limitPage);
		memberList = memberListService.getArticleList(page, limitPage);

		request.setAttribute("id", id);

		request.setAttribute("auctionList", auctionList);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("requestList", requestList);
		request.setAttribute("memberList", memberList);
		ActionForward forward = new ActionForward();
		forward.setPath("/adminMain.jsp");
		return forward;
	}
}
