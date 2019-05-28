package admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.service.NoticeDeleteService;
import vo.ActionForward;

public class NoticeDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		ActionForward forward = null;
		int num = Integer.parseInt(request.getParameter("num"));
		String nowPage = request.getParameter("page");
		NoticeDeleteService noticeDeleteAction = new NoticeDeleteService();
		boolean isDeleteSuccess = noticeDeleteAction.removeNotice(num);

		if (!isDeleteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("<alert('공지 삭제에 실패했습니다.');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("noticeList.admin?=" + nowPage);
		}
		return forward;
	}
}
