package auction.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import auction.service.AuctionDetailService;
import auction.vo.AuctionBean;
import vo.ActionForward;

public class AuctionDeliveryAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		int num=Integer.parseInt(request.getParameter("num"));
		AuctionDetailService auctionDetailService = new AuctionDetailService();
		AuctionBean auctionBean = auctionDetailService.getAuction(num);
		request.setAttribute("auctionBean", auctionBean);
		forward.setPath("/auction/auction_delivery.jsp");
		return forward;
	}
}
