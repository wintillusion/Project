package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import member.service.DetailService;
import member.vo.MemberBean;
import vo.ActionForward;

public class PassChkFormAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		String id = request.getParameter("id");
		DetailService Detailservice = new DetailService();
		MemberBean memberBean = Detailservice.getMemberBean(id);
		request.setAttribute("memberBean", memberBean);
		forward.setPath("/member/passChk.jsp");
		return forward;
	}

}
