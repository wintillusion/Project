package comment.service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CommentDAO;
import comment.vo.CommentBean;

public class CommentService {
public boolean replyArticle(CommentBean comment) throws Exception{
		
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(con);
		insertCount = commentDAO.insertReplyComment(comment);
		
		if(insertCount>0) {
			commit(con);
			isReplySuccess=true;
		}
		else {
			rollback(con);
		}
		
		close(con);
		return isReplySuccess;
	}
}
