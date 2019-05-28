package admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import review.service.ReviewDeleteProService;
import vo.ActionForward;

public class ReviewDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = null;
		int num =Integer.parseInt(request.getParameter("num"));
		String nowPage = request.getParameter("page");
		ReviewDeleteProService reviewDeleteProService = new ReviewDeleteProService();
			boolean isDeleteSuccess = reviewDeleteProService.removeArticle(num);
			
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("<alert('게시글 삭제에 실패했습니다.');");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("reviewList.admin?="+nowPage);
			}
		return forward;
	}
}
