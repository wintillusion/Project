package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.vo.MemberBean;
import member.service.DetailService;
import vo.ActionForward;
import action.Action;

public class idCheckAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		
		DetailService memberSvc = new DetailService();
		MemberBean member = memberSvc.getMemberBean(id);
		
		if(member==null) {
			request.setAttribute("passibleID", true);
		}else {
			request.setAttribute("passibleID", false);
		}
		request.setAttribute("id", id);
		forward = new ActionForward();
		forward.setPath("member/idCheck.jsp");
		return forward;
	}
}
