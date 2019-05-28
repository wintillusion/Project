package request.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.RequestDAO;
import request.vo.RequestBean;

public class RequestLikeyService {
	
public boolean updateLikey(RequestBean requestBean) throws Exception{
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		RequestDAO requestDAO = RequestDAO.getInstance();
		requestDAO.setConnection(con);
		int updateCount = requestDAO.likeyArticle(requestBean);
		
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
