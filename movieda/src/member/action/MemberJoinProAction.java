package member.action;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import member.service.JoinService;
import vo.ActionForward;
import member.vo.MemberBean;

public class MemberJoinProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward=null;
		MemberBean memberbean=new MemberBean();
		JoinService joinSerivce=new JoinService();
	
		memberbean.setId(request.getParameter("id"));
		memberbean.setName(request.getParameter("name"));
		memberbean.setPassword(request.getParameter("password"));
		memberbean.setPostcode(request.getParameter("postcode"));
		memberbean.setPhone(request.getParameter("phone"));
		memberbean.setAddress1(request.getParameter("address1"));
		memberbean.setAddress2(request.getParameter("address2"));
		memberbean.setEmail(request.getParameter("email"));
		
	    boolean isSuccess = joinSerivce.isJoinWriter(memberbean);
	    
	    if(!isSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('등록에 실패했습니다. 다시 작성해주세요.')");
			out.println("history.back();");
			out.println("</script>");
			
		} else {
			forward=new ActionForward();
			forward.setRedirect(true);	
			forward.setPath("./loginForm2.member");
		}
		return forward;
	}
}
