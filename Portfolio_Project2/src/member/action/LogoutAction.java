package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import vo.ActionForward;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		// TODO Auto-generated method stub
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		session.invalidate();
		forward.setPath("main.jsp");
		return forward;
	}
	
}
