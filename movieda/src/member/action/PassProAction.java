package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import member.service.PassChangeService;
import member.vo.MemberBean;
import util.SHA256Util;
import vo.ActionForward;

public class PassProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		boolean isModifySuccess = false;
		String id = request.getParameter("id");
		MemberBean memberBean = new MemberBean();
		PassChangeService passChangeService = new PassChangeService();

		memberBean.setId(id);
		
		memberBean.setPassword(SHA256Util.testSHA256(request.getParameter("passChange2")));
		isModifySuccess = passChangeService.modifyArticle(memberBean);
		if (!isModifySuccess) {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 변경에 실패했습니다. 다시 확인해주세요.');");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./member_view.member?id="+id);
		}
		return forward;
	}
}
