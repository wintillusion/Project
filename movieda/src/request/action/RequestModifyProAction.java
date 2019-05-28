package request.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import request.service.RequestModifyProService;
import vo.ActionForward;
import request.vo.RequestBean;

public class RequestModifyProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int num = Integer.parseInt(request.getParameter("num"));
		RequestBean requestBean = new RequestBean();
		RequestModifyProService requestModifyProService = new RequestModifyProService();
			
			requestBean.setNum(num);
			requestBean.setSubject(request.getParameter("subject"));
			requestBean.setContent(request.getParameter("content"));
			isModifySuccess = requestModifyProService.modifyArticle(requestBean);
			
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
				forward.setPath("requestDetail.request?num="+requestBean.getNum());
			}
		
		
		return forward;
	}
}
