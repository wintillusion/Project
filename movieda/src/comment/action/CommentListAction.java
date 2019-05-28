package comment.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import comment.service.CommentListService;
import vo.ActionForward;
import comment.vo.CommentBean;
import request.vo.PageInfo;

public class CommentListAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<CommentBean> commentList=new ArrayList<CommentBean>();
		//db에서 받은 select값을 전부다 arrayList에 한줄씩 넣어줌. 아니면 resultSet객체를 계속 열어놔야함. BoardBean에 담는다.
	
		int page=1;	//현재 페이지
		int limit=5;	//한페이지당 보여줄 글의 목록 갯수
		int limitPage=3;	//화면에 보이는 페이지 목록 갯수
		int comment_board;
		if(request.getParameter("page")!=null) {	//페이지 값 변경
			page = Integer.parseInt(request.getParameter("page"));
			
		}

		comment_board = Integer.parseInt(request.getParameter("num"));

		//System.out.println("listAction: " + comment_board);
		CommentListService commentListService = new CommentListService();
		int listCount=commentListService.getListCount();//sO, sW넘겨줘야함.
		//총 리스트 수를 받아옴
		commentList = commentListService.getCommentList(page, limit, comment_board);
		//리스트를 받아옴
		//총페이지 수
		int maxPage=(int)((double)listCount/limit+0.95);	//1.2면 두페이지가 필요하기때문에 대충 0.95를 더해준 것.
		//0.95를 더해서 올림 처리
		
		//현재 페이지를 보여줄 시작 페이지수(1,11,21등)
		int startPage = (((int) ((double)page/10 + 0.9)) -1) * limitPage+1;	//몇페이지부터 시작할 것이냐.
		
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30등)
		int endPage = startPage+limitPage-1;
		
		if(endPage>maxPage) endPage=maxPage;
		
		//구한 값을 세팅하고 jsp로 넘기기
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("pageInfo", pageInfo);	//페이지 값을 넘겨줌1
		request.setAttribute("commentList", commentList);// 결과값을 넘겨줌2 속성으로 넘겨야함.
	//	request.setAttribute("sW", sW);
	//	request.setAttribute("sO", sW);
		ActionForward forward = new ActionForward();
		forward.setPath("/comment/comment_list.jsp");
		return forward;
	}

}
