package auction.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import vo.ActionForward;
import auction.vo.AuctionBean;
import auction.vo.AuctionInfoBean;
import auction.service.AuctionUpdateService;

public class AuctionUpdateAction implements Action{
//auctionBean 입찰하는 로직. 처음 할땐 insert. 같은 num에서 입찰한 내용을 덮어쓸땐 update.
//auctionInfo 입찰정보를 저장하는 로직. 처음할땐 insert. 같은 번호할땐 update;
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int num = Integer.parseInt(request.getParameter("num"));
		AuctionBean auctionBean = new AuctionBean();
		AuctionInfoBean auctioninfo = new AuctionInfoBean();
		AuctionUpdateService auctionUpdateService = new AuctionUpdateService();
		System.out.println("auctionupdateAction memid: " + request.getParameter("mem_id"));
		System.out.println("auctionupdateAction memid: " + Integer.parseInt(request.getParameter("instmoney")));
	
		
			auctionBean.setNum(num);	//경매 번호
			auctionBean.setMem_id(request.getParameter("mem_id"));	//입찰할 id
			auctionBean.setInstmoney(Integer.parseInt(request.getParameter("instmoney")));	//입찰가격
			auctioninfo.setMem_id(request.getParameter("mem_id"));
			auctioninfo.setMymoney(Integer.parseInt(request.getParameter("instmoney")));
			auctioninfo.setOrder_num(num);
			isModifySuccess = auctionUpdateService.updateAuction(auctionBean, auctioninfo);
			
			if(!isModifySuccess) {
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('입찰에 실패했습니다.');");
				out.println("history.back()");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("auctionDetail.auction?num="+auctionBean.getNum());	//여기서 바로 결과값을 넘기는게 아닌 결과값을 토대로 info에 등록 후 보내도록?
			
			}
		

		return forward;
	}

}
