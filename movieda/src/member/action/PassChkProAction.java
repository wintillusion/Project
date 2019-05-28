package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.service.DetailService;
import member.vo.MemberBean;
import util.SHA256Util;
import vo.ActionForward;

public class PassChkProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String id = request.getParameter("id");
		String passChk = SHA256Util.testSHA256(request.getParameter("passchk"));
		
		DetailService detailService=new DetailService();
		MemberBean memberBean=detailService.getMemberBean(id);
		//System.out.println("패스워드체크 액션 값 : " + passChk);
		//System.out.println("현재 비밀번호 값 : " + memberBean.getPassword());
		//System.out.println("현재 아이디 값 : " + memberBean.getId());

		if (!passChk.equals(memberBean.getPassword())) {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 변경에 실패했습니다. 다시 확인해주세요.');");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./passChangeForm.member?id="+id);
		}
		return forward;
	}

}
