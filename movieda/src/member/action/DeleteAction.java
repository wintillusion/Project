package member.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.DeleteService;
import vo.ActionForward;
import action.Action;

public class DeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ActionForward forward = null;
//		String id= (String)session.getAttribute("id"); 세션으로 할 경우 admin 관리자 페이지에서 삭제할때 관리자가 삭제될 수도 있음.
		String id = request.getParameter("id");
		String nowPage = request.getParameter("page");
		DeleteService deleteService = new DeleteService();
//		boolean isArticleWriter =deleteService.isArticleWriter(request.getParameterValues("id");
		
		/*if(!isArticleWriter) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}else {*/
			boolean isDeleteSuccess = deleteService.removeArticle(id);
			
			if(!isDeleteSuccess) {
			
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패');");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}else {
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				session.invalidate();
				out.println("<script>");
				out.println("alert('회원 탈퇴가 완료되었습니다. 이용해주셔서 감사합니다.');");
				out.println("location.href='main.jsp'");
				out.println("</script>");
				
			}
		
		return forward;
	}

}
