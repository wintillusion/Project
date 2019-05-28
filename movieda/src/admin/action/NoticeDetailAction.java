package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notice.service.NoticeDetailService;
import vo.ActionForward;
import notice.vo.NoticeBean;

public class NoticeDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int num=Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");
		
		NoticeDetailService noticeDetailService = new NoticeDetailService();
		NoticeBean noticeBean = noticeDetailService.getArticle(num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("noticeBean", noticeBean);
		forward.setPath("admin/notice_detail.jsp");
		return forward;
	}

}
