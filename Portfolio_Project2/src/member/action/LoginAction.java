package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import member.service.LoginService;
import vo.ActionForward;
import member.vo.MemberBean;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		// TODO Auto-generated method stub
		
		ActionForward forward=null;
		String id=request.getParameter("id");
		
		MemberBean memberbean=new MemberBean();
		LoginService loginSerivce=new LoginService();
		boolean isRightUser=loginSerivce.isMemberLogin(id,request.getParameter("password"));

		
		
		if(!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();	
			out.println("<script>");
			out.println("alert('아이디와 비밀번호를 확인해주세요.')");
			out.println("history.back();");
			out.println("</script>");
		}  else {
				forward=new ActionForward();
				session.setAttribute("id", id);
				forward.setRedirect(true);			
				forward.setPath("main.jsp");
			}
		
		return forward;
	}
}
