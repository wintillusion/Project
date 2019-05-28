package admin.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import admin.service.NoticeWriteProService;
import notice.vo.NoticeBean;
import vo.ActionForward;

public class NoticeWriteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward=null;
		NoticeBean noticeBean = null;
		String realFolder="";
		String saveFolder="/noticeUpload";

		//저장폴더가 필요. boardUpload. 프로젝트 서버 밑에 존재하는 폴더. 폴더이름만 적어주는게 좋음. 업데이트 할 때 주소가 아예 바뀔 수 있기때문.
		int fileSize=5*1024*1024;	//5mb까지 가능. 몇mb이하 등록해달라고 공지를 해주는게 좋음.
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);	//저장장소 확인.
		MultipartRequest multi=new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		//멀티 객체 생성.멀티가 있어야 파일업로드 가능. 멀티가 생성될 때 파일이 업로드 됨.
		
		noticeBean = new NoticeBean();
		noticeBean.setWriter(multi.getParameter("writer"));
		noticeBean.setSubject(multi.getParameter("subject"));
		noticeBean.setContent(multi.getParameter("content"));	//form에서 넘겨준 값을 다 받음. 이 정보를 DAO로 보내야함.
		
		NoticeWriteProService noticeWriteProService = new NoticeWriteProService();
		boolean isWriteSuccess = noticeWriteProService.registNotice(noticeBean);
		

		if(!isWriteSuccess) {	
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('공지 등록에 실패했습니다. 다시 시도해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {	//업로드가 됐으면
			forward = new ActionForward();
			forward.setRedirect(true);	
			forward.setPath("noticeList.admin");
			
		}
		return forward;
	}
}
