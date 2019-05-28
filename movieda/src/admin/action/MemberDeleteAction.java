package admin.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import member.service.DeleteService;
import vo.ActionForward;

public class MemberDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ActionForward forward = null;
//		String id= (String)session.getAttribute("id"); 세션으로 할 경우 admin 관리자 페이지에서 삭제할때 관리자가 삭제될 수도 있음.
		String id = request.getParameter("id");
		
		DeleteService deleteService = new DeleteService();
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
				//session.invalidate();		//session 은 admin 관리자 계정이기에 만료 안시켜도 된다.
				out.println("<script>");
				out.println("alert('회원탈퇴 처리 완료.');");
				out.println("location.href='list.admin'");
				out.println("</script>");
				
			}
		
		return forward;
	}

}