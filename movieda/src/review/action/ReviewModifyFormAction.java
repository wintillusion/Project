package review.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import review.service.ReviewDetailService;
import vo.ActionForward;
import review.vo.ReviewBean;

public class ReviewModifyFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//String nowPage = request.getParameter("page");
		ActionForward forward = new ActionForward();
		int num=Integer.parseInt(request.getParameter("num"));
		ReviewDetailService reviewDetailservice = new ReviewDetailService();	//하나의 글 내용을 가져오기 때문에 글쓰기폼에 있던걸 가져오는 것.
		ReviewBean reviewBean = reviewDetailservice.getArticle(num);
		request.setAttribute("reviewBean", reviewBean);
		//request.setAttribute("page", page);
		forward.setPath("/reviewBoard/review_modify.jsp");
		return forward;
	}
	//현 상황에서 오류는 수정폼에서 뒤로 한다음 목록을 갔을때 조회수가 2배로 올라간다.
}
