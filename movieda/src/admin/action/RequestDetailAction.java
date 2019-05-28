package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import request.service.RequestDetailService;
import request.vo.RequestBean;
import vo.ActionForward;

public class RequestDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");

		RequestDetailService requestDetailService = new RequestDetailService();
		RequestBean requestBean = requestDetailService.getArticle(num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("requestBean", requestBean);
		forward.setPath("admin/request_detail.jsp");
		return forward;
	}

}
