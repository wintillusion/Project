package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.ActionForward;
import member.vo.MemberBean;
import member.service.DetailService;
import action.Action;

public class MemberModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		String id=request.getParameter("id");
		DetailService Detailservice = new DetailService();
		MemberBean memberBean = Detailservice.getMemberBean(id);
		request.setAttribute("memberBean", memberBean);
		forward.setPath("/admin/member_modify.jsp");
		return forward;
	}
}