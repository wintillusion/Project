package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import member.service.DetailService;
import member.vo.MemberBean;
import vo.ActionForward;

public class MemberPWFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		String id = request.getParameter("id");
		DetailService Detailservice = new DetailService();
		MemberBean memberBean = Detailservice.getMemberBean(id);
		request.setAttribute("memberBean", memberBean);
		forward.setPath("/admin/member_pass.jsp");
		return forward;
	}
}
