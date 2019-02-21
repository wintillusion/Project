package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import member.service.DetailService;
import vo.ActionForward;
import member.vo.MemberBean;

public class DetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		String id=request.getParameter("id");
		String page=request.getParameter("page");
		DetailService detilService=new DetailService();
		MemberBean memberBean=detilService.getArticle(id);
		request.setAttribute("memberBean",memberBean);
		request.setAttribute("page", page);
		forward.setPath("/member/member_view.jsp");
		System.out.println(id);
		return forward;
	}

}
