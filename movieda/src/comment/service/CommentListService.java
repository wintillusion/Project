package comment.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CommentDAO;
import comment.vo.CommentBean;;

public class CommentListService {
	public int getListCount() throws Exception{

		int listCount = 0;
		Connection con = getConnection();	//컨넥션 객체 생성(jdbcUtil에 있는 것)
		CommentDAO commentDAO = CommentDAO.getInstance();	//boardDAO 객체 생성
		commentDAO.setConnection(con);	//객체 주입.(스프링에서 개념이 나옴, 체크필요)
		listCount = commentDAO.selectListCount();		//갯수를 구함
		close(con);	//컨넥션만 여기서 생성하고 닫아줌. stmt와 rs는 DAO에서 닫아줌.
		return listCount;	//리턴
	}
	
	public ArrayList<CommentBean> getCommentList(int page, int limit, int comment_board) throws Exception{
		
		ArrayList<CommentBean> commentList = null;
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		commentDAO.setConnection(con);
		commentList = commentDAO.selectCommentList(page, limit, comment_board);
		close(con);
		return commentList;
	}
}
