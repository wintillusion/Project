package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import admin.action.MemberDetailAction;
import admin.action.MemberListAction;
import admin.action.MemberModifyAction;
import admin.action.MemberModifyProAction;
import admin.action.MemberDeleteAction;
import admin.action.MemberPWFormAction;
import admin.action.MemberPWProAction;
import admin.action.NoticeDeleteAction;
import admin.action.NoticeDetailAction;
import admin.action.AuctionWriteProAction;
import admin.action.AuctionDeleteAction;
import admin.action.AuctionDetailAction;
import admin.action.AuctionListAction;
import admin.action.AuctionModifyFormAction;
import admin.action.AuctionModifyProAction;
import admin.action.NoticeListAction;
import admin.action.NoticeWriteProAction;
import admin.action.RequestDeleteAction;
import admin.action.RequestDetailAction;
import admin.action.RequestListAction;
import admin.action.ReviewDeleteAction;
import admin.action.ReviewDetailAction;
import admin.action.ReviewListAction;
import admin.action.NoticeModifyFormAction;
import admin.action.NoticeModifyProAction;
import admin.action.AdminMainAction;
import admin.action.AuctionRecordAction;

import vo.ActionForward;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("*.admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAdmin(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAdmin(request, response);
	}

	
	protected void doAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
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
			
		
		
		if(command.equals("/delete.admin")) {
			action=new MemberDeleteAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/list.admin")) {
			action=new MemberListAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/member_info.admin")) {
			action=new MemberDetailAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/modifyForm.admin")) {
			action=new MemberModifyAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/modifypro.admin")) {
			action=new MemberModifyProAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/member_pass.admin")) {
			action=new MemberPWFormAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/passPro.admin")) {
			action=new MemberPWProAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/auctionWrite.admin")) {		
			forward = new ActionForward();
			forward.setPath("/admin/auction_write.jsp");
		}else if (command.equals("/auctionWritePro.admin")) {
			action = new AuctionWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			else if(command.equals("/auctionList.admin")) {
			action=new AuctionListAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/auctionDetail.admin")) {
			action=new AuctionDetailAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/auctionDelete.admin")) {
			action=new AuctionDeleteAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/auctionModifyForm.admin")) {
			action=new AuctionModifyFormAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/auctionModifyPro.admin")) {
			action=new AuctionModifyProAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/noticeList.admin")) {
			action=new NoticeListAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/noticeWriteForm.admin")) {
		
			forward = new ActionForward();
			forward.setPath("/admin/notice_write.jsp");
		}else if(command.equals("/noticeWritePro.admin")) {
			action=new NoticeWriteProAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/noticeModifyPro.admin")) {
			action=new NoticeModifyProAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/noticeModifyForm.admin")) {
			action=new NoticeModifyFormAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/noticeDetail.admin")) {
			action=new NoticeDetailAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/NoticeDelete.admin")) {
			action=new NoticeDeleteAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/adminMain.admin")) {
			action=new AdminMainAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/reviewList.admin")) {
			action=new ReviewListAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/requestList.admin")) {
			action=new RequestListAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/reviewDetail.admin")) {
			action=new ReviewDetailAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/requestDetail.admin")) {
			action=new RequestDetailAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/requestDelete.admin")) {
			action=new RequestDeleteAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/reviewDelete.admin")) {
			action=new ReviewDeleteAction();
			
			try {
				forward=action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/auctionRecord.admin")) {
			action=new AuctionRecordAction();
			
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
