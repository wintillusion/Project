package review.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import review.service.ReviewModifyProService;
import vo.ActionForward;
import review.vo.ReviewBean;

public class ReviewModifyProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int num = Integer.parseInt(request.getParameter("num"));
		ReviewBean reviewBean = new ReviewBean();
		ReviewModifyProService reviewModifyProService = new ReviewModifyProService();
			
			reviewBean.setNum(num);
			reviewBean.setSubject(request.getParameter("subject"));
			reviewBean.setContent(request.getParameter("content"));
			isModifySuccess = reviewModifyProService.modifyArticle(reviewBean);
			
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
				forward.setPath("reviewDetail.review?num="+reviewBean.getNum());
			}
		
		
		return forward;
	}
}
