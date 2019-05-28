package auction.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import auction.service.AuctionDepositProService;
import auction.vo.AuctionBean;
import auction.vo.StockBean;
import vo.ActionForward;

public class AuctionDepositProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int num = Integer.parseInt(request.getParameter("num"));
		AuctionBean auctionBean = new AuctionBean();
		StockBean stockBean = new StockBean();
		AuctionDepositProService auctionDepositProService = new AuctionDepositProService();
			
			auctionBean.setNum(num);
			auctionBean.setAuction_code(Integer.parseInt(request.getParameter("auction_code")));
			stockBean.setStock_code(Integer.parseInt(request.getParameter("stock_code")));
			stockBean.setNum(num);
			stockBean.setAuction_num(num);
			isModifySuccess = auctionDepositProService.updateDeposit(auctionBean, stockBean);
			
			if(!isModifySuccess) {
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('입금에 실패했습니다.');");
				out.println("history.back()");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("auctionDelivery.auction?num="+auctionBean.getNum());
			}
		
		
		return forward;
	}
}
