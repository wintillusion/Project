package admin.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import action.Action;
import admin.service.AuctionWriteProService;
import auction.vo.AuctionBean;
import auction.vo.StockBean;
import vo.ActionForward;
import java.text.ParseException;

public class AuctionWriteProAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward=null;
		AuctionBean auctionBean = null;
		StockBean stockBean = null;
		
		String realFolder="";
		String saveFolder="/auctionUpload";

		
		int fileSize=10*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);	
		MultipartRequest multi=new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		

		
		auctionBean = new AuctionBean();
		auctionBean.setSubject(multi.getParameter("subject"));
		auctionBean.setMem_id(multi.getParameter("mem_id"));
		auctionBean.setContent(multi.getParameter("content"));
		
		auctionBean.setFstmoney(Integer.parseInt(multi.getParameter("fstmoney")));
		auctionBean.setAuction_code(Integer.parseInt(multi.getParameter("auction_code")));	//1 입찰시작 코드.
		auctionBean.setWriter(multi.getParameter("writer"));	//작성자 admin 넘겨주기
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
		
		stockBean = new StockBean();	//재고 입력.
		stockBean.setProduct(multi.getParameter("product"));
		stockBean.setStock_code(Integer.parseInt(multi.getParameter("stock_code"))); //0 : 입고
		
		auctionBean.setFile(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		AuctionWriteProService auctionWriteProService = new AuctionWriteProService();
		boolean isWriteSuccess = auctionWriteProService.registAuction(auctionBean, stockBean);
		//boolean으로 한 이유는 업로드를 했냐 안했냐(true냐 false냐). 아니더라도 0이냐 1이냐, 즉 정수값으로 받아도 됨.
		//BoardBean을 service에 넘겨주는 것. 아니면 일일이 매개변수를 다 만들어서 하나씩 넘겨줘야함.
		//service에 컨넥션 객체가 있기 때문.(할 내용이 많이 없는 경우엔 service와 DAO를 합쳐놓는 것도 괜찮음. 많은 경우 분리 해놓는게 좋다.
		
		if(!isWriteSuccess) {	//true가 아니면(업로드가 안됐으면)
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 등록에 실패했습니다. 다시 시도해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {	//업로드가 됐으면
			forward = new ActionForward();
			forward.setRedirect(true);	//기본값은 false인데 true로 해서 바로 보내주는 것.
			forward.setPath("auctionList.admin");
			//바로 qna_board_list를 안한 이유는 데이터베이스를 거쳐서 정보를 가져온 다음에 listAction 간 다음 list를 띄워줌. 프론트액션에서 boardListAction을 한번 갔다 오는 것.
		}
		return forward;
	}

}
