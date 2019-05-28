package review.service;
import java.sql.Connection;
import dao.ReviewDAO;
import review.vo.ReviewBean;
import static db.JdbcUtil.*;

public class ReviewWriteProService {
	
	public boolean registRequest(ReviewBean reviewBean) throws Exception{
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		int insertCount = reviewDAO.insertReview(reviewBean);
		
		
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
