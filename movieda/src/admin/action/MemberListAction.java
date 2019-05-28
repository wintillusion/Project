package admin.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import member.service.MemberListService;
import vo.ActionForward;
import member.vo.MemberBean;
import member.vo.PageInfo;

public class MemberListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		ActionForward forward=new ActionForward();
		
		
		ArrayList<MemberBean> articleList = new ArrayList<MemberBean>();	//글목록가져오기
		
		int page = 1; 			//현재페이지수
		int limit = 5; 		//한페이지당보여줄 목록
		int limitPage=3;		//몇페이지까지잇는지 보여줌
		
	if(session.getAttribute("id")==null || (!((String)session.getAttribute("id")).equals("admin"))) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('관리자로 로그인하세요')");
		out.println("location.href=loginForm.jsp");
		out.println("</script>");
		
	}else {
			
	
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		MemberListService memberListService=new MemberListService();
		int listCount=memberListService.getListCount();
		
		//총리스트수받아옴
		articleList=memberListService.getArticleList(page, limit);
		
		//리스트받아옴
		//총페이지수
		int maxPage=(int) ((double)listCount/limit+0.95);
		//0.95를 더해서 올림처리
		
		//현재 페이지에 보여줄시작페이지수(1,11,21 .....)
		int startPage=(((int) ((double)page/limitPage+0.9))-1)*limitPage+1;
		
		//현재 페이지에 보여줄 마지막페이지수 (10,20,30......)
		int endPage=startPage+10-1;
		
		if(endPage>maxPage) endPage=maxPage;
		
		PageInfo pageInfo=new PageInfo();
		pageInfo.setEndpage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);			//목록가져와서설정
		forward.setPath("admin/memberList.jsp");				//뷰페이지 dispatcher 로연결
		
	}
	return forward;
	}
	
}
