package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import review.service.ReviewDetailService;
import review.vo.ReviewBean;
import vo.ActionForward;

public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");

		ReviewDetailService reviewDetailService = new ReviewDetailService();
		ReviewBean reviewBean = reviewDetailService.getArticle(num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("reviewBean", reviewBean);
		forward.setPath("admin/review_detail.jsp");
		return forward;
	}
}
