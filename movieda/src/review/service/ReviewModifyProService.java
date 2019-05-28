package review.service;
import static db.JdbcUtil.*;
import java.sql.Connection;
import review.vo.ReviewBean;
import dao.ReviewDAO;

public class ReviewModifyProService {
	
	public boolean modifyArticle(ReviewBean reviewBean) throws Exception{
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		int updateCount = reviewDAO.updateArticle(reviewBean);
		
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
