package admin.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import admin.service.AuctionModifyProService;
import auction.vo.AuctionBean;
import auction.vo.StockBean;
import vo.ActionForward;

public class AuctionModifyProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String realFolder="";
		String saveFolder="/auctionUpload";
		int fileSize=10*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);	
		MultipartRequest multi=new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		ActionForward forward = null;
		boolean isModifySuccess = false;
		System.out.println("auctionModifyPro num : "+multi.getParameter("num"));
		int num = Integer.parseInt(multi.getParameter("num"));
		AuctionBean auctionBean = new AuctionBean();
		StockBean stockBean = new StockBean();
		AuctionModifyProService auctionModifyProService = new AuctionModifyProService();
			
			auctionBean.setNum(num);
			auctionBean.setSubject(multi.getParameter("subject"));
			auctionBean.setContent(multi.getParameter("content"));
			auctionBean.setFstmoney(Integer.parseInt(multi.getParameter("fstmoney")));
			auctionBean.setInstmoney(Integer.parseInt(multi.getParameter("fstmoney")));
			auctionBean.setMem_id(multi.getParameter("mem_id"));
			java.util.Date tempDate = null;
			SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
			SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
			try {
	            // 현재 yyyymmdd로된 날짜 형식으로 java.util.Date객체를 만든다.
	            tempDate = beforeFormat.parse(multi.getParameter("edate"));
	           
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			String transDate = afterFormat.format(tempDate);
			Date d = Date.valueOf(transDate);
			auctionBean.setEdate(d);//date로 형변환 해서 넘겨줘야 한다.
			
			stockBean.setAuction_num(num);
			stockBean.setProduct(multi.getParameter("product"));
			
			isModifySuccess = auctionModifyProService.modifyAuction(auctionBean);
			isModifySuccess = auctionModifyProService.modifyStock(stockBean);
			
			if(!isModifySuccess) {
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정에 실패했습니다.');");
				out.println("history.back()");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("auctionDetail.admin?num="+auctionBean.getNum());
			}
		
		
		return forward;
	}
}
