package request.service;
import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;
import java.sql.Connection;
import dao.RequestDAO;

public class RequestDeleteProService {

	
	public boolean removeArticle(int num) throws Exception{
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		RequestDAO requestDAO = RequestDAO.getInstance();
		requestDAO.setConnection(con);
		int deleteCount = requestDAO.deleteArticle(num);
		
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
