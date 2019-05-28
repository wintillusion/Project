package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import member.service.LoginService;
import vo.ActionForward;
//import member.vo.MemberBean;
import util.SHA256Util;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		// TODO Auto-generated method stub
		
		ActionForward forward=null;
		String id=request.getParameter("id");
		
		//MemberBean memberbean=new MemberBean();
		LoginService loginSerivce=new LoginService();
		boolean isRightUser=loginSerivce.isMemberLogin(id, SHA256Util.testSHA256(request.getParameter("password")));
		//비밀번호 암호화된 값과 비교해서 로그인.

		
		
		if(!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();	
			out.println("<script>");
			out.println("alert('아이디와 비밀번호를 확인해주세요.')");
			out.println("history.back();");
			out.println("</script>");
		}  else {
			
			if(id.equals("admin")) {
				forward=new ActionForward();
				session.setAttribute("id", id);
				forward.setRedirect(true);			
				forward.setPath("adminMain.admin");
			}else {
				forward=new ActionForward();
				session.setAttribute("id", id);
				forward.setRedirect(true);			
				forward.setPath("main.main");	
			}
		}
		
		return forward;
	}
}
