package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import comment.action.CommentAction;
import comment.action.CommentListAction;
import vo.ActionForward;

/**
 * Servlet implementation class MamberController
 */
@WebServlet("*.comment")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
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
		String RequestURI=request.getRequestURI();  	
		String contextPath=request.getContextPath();		
		String command=RequestURI.substring(contextPath.length()); 		
		
	
		
		ActionForward forward=null;
		Action action=null;
		//System.out.println(RequestURI);
		//System.out.println(contextPath);
		//System.out.println(command);
			
		
		
		if(command.equals("/commentWrite.comment")) {
			action=new CommentAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/commentList.comment")) {
			
			action=new CommentListAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
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
