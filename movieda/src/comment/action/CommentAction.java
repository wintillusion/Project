package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import comment.service.CommentService;
import vo.ActionForward;
import comment.vo.CommentBean;;

public class CommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		CommentBean comment = new CommentBean();
		comment.setMem_id(request.getParameter("id"));
		comment.setContent(request.getParameter("content"));
		comment.setComment_board(Integer.parseInt(request.getParameter("num"))); // board PK : num
		int num = Integer.parseInt(request.getParameter("num"));
		/*
		 * comment.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
		 * comment.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
		 */
		/*
		 * comment.setRe_ref(Integer.parseInt(request.getParameter("Re_ref")));
		 * comment.setRe_lev(Integer.parseInt(request.getParameter("Re_lev")));
		 * comment.setRe_seq(Integer.parseInt(request.getParameter("Re_seq")));
		 */
			CommentService commentService = new CommentService();
			boolean isReplySuccess = commentService.replyArticle(comment);

			if (isReplySuccess) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("requestDetail.request?num=" + num);
	
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('댓글 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			return forward;
		}
	}

