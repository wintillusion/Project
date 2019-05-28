package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import review.action.ReviewDeleteProAction;
import review.action.ReviewDetailAction;
import review.action.ReviewLikeyAction;
import review.action.ReviewListAction;
import review.action.ReviewModifyFormAction;
import review.action.ReviewModifyProAction;
import review.action.ReviewViewListAction;
import review.action.ReviewWriteProAction;
import vo.ActionForward;

/**
 * Servlet implementation class ReviewFrontController
 */
@WebServlet("*.review")
public class ReviewFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();	
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());	
		
		ActionForward forward = null;
		Action action = null;
//		System.out.println(RequestURI);	//전체경로
//		System.out.println(contextPath);	//웹컨텐츠까지
//		System.out.println(command);	//나머지 경로가 다나옴.
		if (command.equals("/reviewWriteForm.review")) {		//글쓰기폼을 호출하게 되면
			forward = new ActionForward();
			forward.setPath("/reviewBoard/review_write.jsp");	//바로 여기서 페이지가 넘어가버림.
		} else if (command.equals("/reviewWritePro.review")) {
			action = new ReviewWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewList.review")) {
			action = new ReviewListAction();		//action 인터페이스를 사용해서 통일성을 주는 것. 아니면 다 다른이름으로 객체를 만들어주어야한다.
			try {
				forward = action.execute(request, response);		//다이렉트냐 디스패쳐냐 뭘로보낼지.
			} catch (Exception e) {
				e.printStackTrace();		//상위 인터페이스에 있는 것으로 action을 받고, 나머지 코드들에게 통일성을 주는 것.
			}
		} else if (command.equals("/reviewViewList.review")) {
			action = new ReviewViewListAction();		
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/reviewDetail.review")) {
			action = new ReviewDetailAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/reviewModifyForm.review")) {
			
			action = new ReviewModifyFormAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/reviewModifyPro.review")) {
			action = new ReviewModifyProAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/reviewDeletePro.review")) {
			action = new ReviewDeleteProAction();		
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/reviewLikey.review")) {
			action = new ReviewLikeyAction();		
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
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



