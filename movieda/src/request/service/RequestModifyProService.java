package request.service;
import static db.JdbcUtil.*;
import java.sql.Connection;
import request.vo.RequestBean;
import dao.RequestDAO;

public class RequestModifyProService {
	
	public boolean modifyArticle(RequestBean requestBean) throws Exception{
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		RequestDAO requestDAO = RequestDAO.getInstance();
		requestDAO.setConnection(con);
		int updateCount = requestDAO.updateArticle(requestBean);
		
		if(updateCount >0) {
			commit(con);
			isModifySuccess=true;	//안넣어주면 수정실패라 뜨고 수정은 되는 기이한 현상이 발생하게 된다.
		}
		else {
			rollback(con);
		}
		
		close(con);
		return isModifySuccess;
	}

}
