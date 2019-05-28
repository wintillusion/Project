package review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import review.service.ReviewLikeyService;
import review.vo.ReviewBean;
import vo.ActionForward;

public class ReviewLikeyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int num = Integer.parseInt(request.getParameter("num"));
		ReviewBean reviewBean = new ReviewBean();
		ReviewLikeyService reviewLikeyService = new ReviewLikeyService();
		reviewBean.setNum(num);
			isModifySuccess = reviewLikeyService.updateLikey(reviewBean);
			
			if(!isModifySuccess) {
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('좋아요 등록에 실패했습니다..');");
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
