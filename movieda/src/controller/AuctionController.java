package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import auction.action.AuctionEndAction;
import auction.action.AuctionListAction;
import auction.action.AuctionDepositFormAction;
import auction.action.AuctionDepositProAction;
import auction.action.AuctionUpdateAction;
import auction.action.AuctionUpdateFormAction;
import auction.action.AuctionDetailAction;
import auction.action.AuctionDeliveryAction;
import vo.ActionForward;

/**
 * Servlet implementation class AuctionController
 */
@WebServlet("*.auction")
public class AuctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAuction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAuction(request, response);
	}

	
	protected void doAuction(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
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
			
		 if (command.equals("/auctionList.auction")) {	//경매 리스트
			action = new AuctionListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/auctionDetail.auction")) {	//경매 디테일
			action = new AuctionDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/auctionUpdateForm.auction")) {	//경매 입찰 폼
			
			action = new AuctionUpdateFormAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/auctionUpdate.auction")) {	//경매 입찰
			
			action = new AuctionUpdateAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/auctionEnd.auction")) {
			action=new AuctionEndAction();	//옥션 종료
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/auctionDepositForm.auction")) {
			action=new AuctionDepositFormAction();	//옥션 입금폼
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/auctionDepositPro.auction")) {
			action=new AuctionDepositProAction();	//옥션 입금폼
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/auctionDelivery.auction")) {
			action=new AuctionDeliveryAction();	//옥션 입금폼
			
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
