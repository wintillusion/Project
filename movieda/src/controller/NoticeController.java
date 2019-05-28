package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notice.action.NoticeDetailAction;
import notice.action.NoticeListAction;
import vo.ActionForward;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet("*.notice")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doNotice(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doNotice(request, response);
	}
	
protected void doNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
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
			
		
		if(command.equals("/noticeList.notice")) {
			action=new NoticeListAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/noticeDetail.notice")) {
			action=new NoticeDetailAction();
			
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

