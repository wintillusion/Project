package auction.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import auction.service.AuctionDetailService;
import auction.vo.AuctionBean;
import vo.ActionForward;

public class AuctionDepositFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		int num=Integer.parseInt(request.getParameter("num"));
		AuctionDetailService auctionDetailService = new AuctionDetailService();	//하나의 글 내용을 가져오기 때문에 글쓰기폼에 있던걸 가져오는 것.
		AuctionBean auctionBean = auctionDetailService.getAuction(num);
		request.setAttribute("auctionBean", auctionBean);
		//request.setAttribute("page", page);
		forward.setPath("/auction/auction_deposit.jsp");
		return forward;
	}

}
