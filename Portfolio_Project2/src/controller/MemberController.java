package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import member.action.LoginAction;
import member.action.LogoutAction;
import member.action.MemberJoinProAction;
import member.action.DetailAction;
import member.action.idCheckAction;
/*import member.action.MemberListAction;
import member.action.ModifyFormAction;
import member.action.ModifyProAction;
import member.action.DeleteAction;*/
import vo.ActionForward;

/**
 * Servlet implementation class MamberController
 */
@WebServlet("*.member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doMember(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doMember(request, response);
	}

	
	protected void doMember(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
		request.setCharacterEncoding("UTF-8");
		
		// 경로구하는 부분 (무슨페이지를 호출햇는지 )
		String RequestURI=request.getRequestURI();  	//전체경로
		String contextPath=request.getContextPath();		// context 앞까지경로
		String command=RequestURI.substring(contextPath.length()); 		// /뒤경로
		
	
		
		ActionForward forward=null;
		Action action=null;
		//System.out.println(RequestURI);
		//System.out.println(contextPath);
		//System.out.println(command); 경로 확인
			
		if(command.equals("/joinForm.member")) {
			forward=new ActionForward();
			forward.setPath("/member/Join.jsp");
			
		}
		
		else if(command.equals("/joinPro.member")) {
			action=new MemberJoinProAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/loginForm.member")) {
			action=new MemberJoinProAction();
			forward=new ActionForward();
			forward.setPath("/member/login.jsp");
			
		}else if(command.equals("/loginForm2.member")) {
			action=new MemberJoinProAction();
			forward=new ActionForward();
			forward.setPath("/member/login2.jsp");
			
		}else if(command.equals("/login.member")) {
			action=new LoginAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/logout.member")) {
			action=new LogoutAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/member_info.member")) {
			action=new DetailAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/idCheck.member")) {
			action=new idCheckAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}/*else if(command.equals("/list.member")) {
			action=new MemberListAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/modifyForm.member")) {
			action=new ModifyFormAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/modifyPro.member")) {
			action=new ModifyProAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Delete.member")) {
			action=new DeleteAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}*/
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
