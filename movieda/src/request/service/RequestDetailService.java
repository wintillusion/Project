package request.service;

import java.sql.Connection;
import dao.RequestDAO;
import request.vo.RequestBean;
import static db.JdbcUtil.*;

public class RequestDetailService {
	
	public RequestBean getArticle(int num) throws Exception{
		
		RequestBean requestBean = null;
		Connection con = getConnection();
		RequestDAO requestDAO = RequestDAO.getInstance();
		requestDAO.setConnection(con);
		int updateCount = requestDAO.updateReadCount(num);
		
		if(updateCount >0) {
			commit(con);
		}else {
			rollback(con);
		}
		requestBean = requestDAO.selectArticle(num);
		close(con);
		return requestBean;
	}

}
