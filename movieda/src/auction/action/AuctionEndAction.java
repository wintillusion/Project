package auction.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import vo.ActionForward;
import auction.vo.AuctionBean;
import auction.service.AuctionEndService;

public class AuctionEndAction implements Action{
//auctionBean 입찰하는 로직. 처음 할땐 insert. 같은 num에서 입찰한 내용을 덮어쓸땐 update.
//auctionInfo 입찰정보를 저장하는 로직. 처음할땐 insert. 같은 번호할땐 update;
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int num = Integer.parseInt(request.getParameter("num"));	//경매번호 받아오기.
		AuctionBean auctionBean = new AuctionBean();
		AuctionEndService auctionEndService = new AuctionEndService();
		
			auctionBean.setNum(num);
			auctionBean.setMem_id(request.getParameter("mem_id"));
			auctionBean.setInstmoney(Integer.parseInt(request.getParameter("instmoney")));
			auctionBean.setAuction_code(Integer.parseInt(request.getParameter("auctioncode")));
			isModifySuccess = auctionEndService.endAuction(auctionBean);
			
			System.out.println("End Action mem_id : " + request.getParameter("mem_id"));
			System.out.println("End Action num : " + num);
			System.out.println("End Action instmoney : " +Integer.parseInt(request.getParameter("instmoney")));
			System.out.println("End Action instmoney : " +Integer.parseInt(request.getParameter("auctioncode")));
			
			if(!isModifySuccess) {
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('경매종료실패.');");
				out.println("history.back()");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("auctionDetail.auction?num="+num);
			}
		

		return forward;
	}

}
