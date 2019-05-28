package request.action;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import request.service.RequestWriteProService;
import vo.ActionForward;
import request.vo.RequestBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;

public class RequestWriteProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward=null;
		RequestBean requestBean = null;
		String realFolder="";
		String saveFolder="/requestUpload";

		//저장폴더가 필요. boardUpload. 프로젝트 서버 밑에 존재하는 폴더. 폴더이름만 적어주는게 좋음. 업데이트 할 때 주소가 아예 바뀔 수 있기때문.
		int fileSize=5*1024*1024;	//5mb까지 가능. 몇mb이하 등록해달라고 공지를 해주는게 좋음.
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);	//저장장소 확인.
		MultipartRequest multi=new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		//멀티 객체 생성.멀티가 있어야 파일업로드 가능. 멀티가 생성될 때 파일이 업로드 됨.
		
		requestBean = new RequestBean();
		requestBean.setMem_id(multi.getParameter("mem_id"));
		requestBean.setSubject(multi.getParameter("subject"));
		requestBean.setContent(multi.getParameter("content"));	//form에서 넘겨준 값을 다 받음. 이 정보를 DAO로 보내야함.
		/*requestBean.setFile(multi.getOriginalFileName((String)multi.getFileNames().nextElement())); 스마트 에디터 대체*/
		RequestWriteProService requestWriteProService = new RequestWriteProService();
		boolean isWriteSuccess = requestWriteProService.registRequest(requestBean);
		//boolean으로 한 이유는 업로드를 했냐 안했냐(true냐 false냐). 아니더라도 0이냐 1이냐, 즉 정수값으로 받아도 됨.
		//BoardBean을 service에 넘겨주는 것. 아니면 일일이 매개변수를 다 만들어서 하나씩 넘겨줘야함.
		//service에 컨넥션 객체가 있기 때문.(할 내용이 많이 없는 경우엔 service와 DAO를 합쳐놓는 것도 괜찮음. 많은 경우 분리 해놓는게 좋다.
		//model : action, service, DAO

		if(!isWriteSuccess) {	//true가 아니면(업로드가 안됐으면)
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 등록에 실패했습니다. 다시 시도해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {	//업로드가 됐으면
			forward = new ActionForward();
			forward.setRedirect(true);	//기본값은 false인데 true로 해서 바로 보내주는 것.
			forward.setPath("requestList.request");
			//바로 qna_board_list를 안한 이유는 데이터베이스를 거쳐서 정보를 가져온 다음에 listAction 간 다음 list를 띄워줌. 프론트액션에서 boardListAction을 한번 갔다 오는 것.
		}
		return forward;
	}

}
