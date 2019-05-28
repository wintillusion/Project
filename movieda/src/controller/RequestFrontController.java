package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import request.action.RequestWriteProAction;
import request.action.RequestModifyFormAction;
import request.action.RequestModifyProAction;
import request.action.RequestViewListAction;
import request.action.RequestListAction;
import request.action.RequestDeleteProAction;
import request.action.RequestDetailAction;
import request.action.RequestLikeyAction;
import vo.ActionForward;

@WebServlet(urlPatterns= {"*.request","/file_down"})		//주소 호출. ~~.request라는 것들은 전부다 가져온다.(확장자가 request인것들)

public class RequestFrontController extends javax.servlet.http.HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();	//어떤 페이지를 호출했는지 경로 구하는거 1
		String contextPath = request.getContextPath();	//어떤 페이지를 호출했는지 경로 구하는거 2
		String command = RequestURI.substring(contextPath.length());	//어떤 페이지를 호출했는지 경로 구하는거 3. 앞에 다짤리고 슬러시 뒤에 ~~~.bo가 나옴.
		
		ActionForward forward = null;
		Action action = null;
//		System.out.println(RequestURI);	//전체경로
//		System.out.println(contextPath);	//웹컨텐츠까지
//		System.out.println(command);	//나머지 경로가 다나옴.
		if (command.equals("/requestWriteForm.request")) {		//글쓰기폼을 호출하게 되면
			forward = new ActionForward();
			forward.setPath("/requestBoard/request_write.jsp");	//바로 여기서 페이지가 넘어가버림.
		} else if (command.equals("/requestWritePro.request")) {
			action = new RequestWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/requestList.request")) {
			action = new RequestListAction();		//action 인터페이스를 사용해서 통일성을 주는 것. 아니면 다 다른이름으로 객체를 만들어주어야한다.
			try {
				forward = action.execute(request, response);		//다이렉트냐 디스패쳐냐 뭘로보낼지.
			} catch (Exception e) {
				e.printStackTrace();		//상위 인터페이스에 있는 것으로 action을 받고, 나머지 코드들에게 통일성을 주는 것.
			}
		} else if (command.equals("/requestViewList.request")) {
			action = new RequestViewListAction();		
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/requestDetail.request")) {
			action = new RequestDetailAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/requestModifyForm.request")) {
			
			action = new RequestModifyFormAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/requestModifyPro.request")) {
			action = new RequestModifyProAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/requestDeletePro.request")) {
			action = new RequestDeleteProAction();		//proaction실행
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/requestLikey.request")) {
			action = new RequestLikeyAction();		//proaction실행
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	
		/*
		else if(command.equals("/boardReplyForm.bo")) {
			action =  new BoardReplyFormAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardReplyPro.bo")) {
			action = new BoardReplyProAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/boardDeleteForm.bo")) {
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			request.setAttribute("board_num", board_num);	
			forward=new ActionForward();		//페이지 값과 board_num을 넘겨줌
			forward.setPath("/board/qna_board_delete.jsp"); //객체생성하는게 아니라 바로 보내줌.
		}
		*/
		if(forward != null) {		//포워드 값에 따라 경로를 어떻게 연결을 할 것이냐.
			if(forward.isRedirect()) {		//다이렉트가 yes면 다이렉트로 보내고(넘겨주는 정보가 없으면)
				response.sendRedirect(forward.getPath());
			}else {		//아니면 disapatcher로 보내는 것.	넘겨주는 정보가 있으면 dispatcher로 넘김.
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	

}
