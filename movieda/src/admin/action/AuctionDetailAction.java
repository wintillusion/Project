package admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import auction.service.AuctionDetailService;
import auction.vo.AuctionBean;
import auction.vo.AuctionInfoBean;
import vo.ActionForward;
import auction.vo.StockBean;

public class AuctionDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");

		AuctionDetailService auctionDetailService = new AuctionDetailService();
		AuctionBean auctionBean = auctionDetailService.getAuction(num);
		StockBean stockBean = auctionDetailService.getStock(num);
		ArrayList<AuctionInfoBean> auctionInfoList = auctionDetailService.getAuctionInfoList(num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("auctionBean", auctionBean);
		request.setAttribute("stockBean", stockBean);
		request.setAttribute("auctionInfoList", auctionInfoList);
		forward.setPath("admin/auction_detail.jsp");
		return forward;
	}

}
