package auction.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import auction.service.AuctionDetailService;
import auction.vo.AuctionBean;
import auction.vo.StockBean;
import vo.ActionForward;

public class AuctionDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");

		AuctionDetailService auctionDetailService = new AuctionDetailService();
		AuctionBean auctionBean = auctionDetailService.getAuction(num);
		StockBean stockBean = auctionDetailService.getStock(num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("auctionBean", auctionBean);
		request.setAttribute("stockBean", stockBean);
		forward.setPath("auction/auction_detail.jsp");
		return forward;
	}

}
