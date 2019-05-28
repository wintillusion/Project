package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import member.service.DetailService;
import vo.ActionForward;
import member.vo.MemberBean;

public class MemberDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		String id=request.getParameter("id");
		System.out.println("Detail Action : " + id);
		String page=request.getParameter("page");
		DetailService detilService=new DetailService();
		MemberBean memberBean=detilService.getMemberBean(id);
		request.setAttribute("memberBean", memberBean);
		request.setAttribute("page", page);
		forward.setPath("/admin/member_view.jsp");
		return forward;
	}
}
