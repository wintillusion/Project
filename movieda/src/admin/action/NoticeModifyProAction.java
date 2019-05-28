package admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.service.NoticeModifyProService;
import notice.vo.NoticeBean;
import vo.ActionForward;

public class NoticeModifyProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int num = Integer.parseInt(request.getParameter("num"));
	
		NoticeBean noticeBean = new NoticeBean();
		NoticeModifyProService noticeModifyProService = new NoticeModifyProService();
			System.out.println(request.getParameter("content"));
			noticeBean.setNum(num);
			noticeBean.setSubject(request.getParameter("subject"));
			noticeBean.setContent(request.getParameter("content"));
			isModifySuccess = noticeModifyProService.modifyArticle(noticeBean);
			
			if(!isModifySuccess) {
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('공지사항 수정에 실패했습니다.');");
				out.println("history.back()");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("noticeDetail.admin?num="+noticeBean.getNum());
			}
		
		
		return forward;
	}

}
