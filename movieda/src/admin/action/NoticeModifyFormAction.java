package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notice.service.NoticeDetailService;
import notice.vo.NoticeBean;
import vo.ActionForward;

public class NoticeModifyFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("num"));
		ActionForward forward = new ActionForward();
		int num = Integer.parseInt(request.getParameter("num"));
		NoticeDetailService noticeDetailService = new NoticeDetailService(); // 하나의 글 내용을 가져오기 때문에 글쓰기폼에 있던걸 가져오는 것.
		NoticeBean noticeBean = noticeDetailService.getArticle(num);
		request.setAttribute("noticeBean", noticeBean);
		forward.setPath("/admin/notice_modify.jsp");
		return forward;
	}
}
