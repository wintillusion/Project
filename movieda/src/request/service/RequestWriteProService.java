package request.service;
import java.sql.Connection;
import dao.RequestDAO;
import request.vo.RequestBean;
import static db.JdbcUtil.*;

public class RequestWriteProService {
	
	public boolean registRequest(RequestBean requestBean) throws Exception{
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		RequestDAO requestDAO = RequestDAO.getInstance();
		requestDAO.setConnection(con);
		int insertCount = requestDAO.insertRequest(requestBean);
		
		
		if(insertCount>0) {	//dao에서 넘겨준 값이 0보다 크면 true로.
			commit(con);
			isWriteSuccess = true;
		}else {
			rollback(con);
		}
		
		close(con);
		return isWriteSuccess;
	}

}
