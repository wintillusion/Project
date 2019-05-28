package review.service;

import java.sql.Connection;
import dao.ReviewDAO;
import review.vo.ReviewBean;
import static db.JdbcUtil.*;

public class ReviewDetailService {
	
	public ReviewBean getArticle(int num) throws Exception{
		
		ReviewBean requestBean = null;
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		int updateCount = reviewDAO.updateReadCount(num);
		
		if(updateCount >0) {
			commit(con);
		}else {
			rollback(con);
		}
		requestBean = reviewDAO.selectArticle(num);
		close(con);
		return requestBean;
	}

}
