package member.service;
import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.MemberDAO;

public class DeleteService {
	
	public boolean isArticleWriter(String id) throws Exception{
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
//		isArticleWriter = memberDAO.isArticleBoardWriter(id);
		close(con);
		return isArticleWriter;
	}
	
	public boolean removeArticle(String id) throws Exception{
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int deleteCount = memberDAO.deleteMember(id);
		
		if(deleteCount>0) {
			commit(con);
			isRemoveSuccess=true;
		}else {
			rollback(con);
		}
		close(con);
		return isRemoveSuccess;
	}

}
