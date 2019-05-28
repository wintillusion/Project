package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import vo.ActionForward;
import member.service.modifyProService;
import member.vo.MemberBean;

public class ModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		boolean isModifySuccess = false;
		String id = request.getParameter("id");
		MemberBean memberBean = new MemberBean();
		modifyProService modifyProService = new modifyProService();

		memberBean.setId(id);
		
		memberBean.setName(request.getParameter("name"));
		memberBean.setPhone(request.getParameter("phone"));
		memberBean.setEmail(request.getParameter("email"));
		memberBean.setPostcode(request.getParameter("postcode"));
		memberBean.setAddress1(request.getParameter("address1"));
		memberBean.setAddress2(request.getParameter("address2"));
		isModifySuccess = modifyProService.modifyMember(memberBean);
		if (!isModifySuccess) {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원 정보 수정에 실패했습니다. 다시 입력해주세요.');");
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
