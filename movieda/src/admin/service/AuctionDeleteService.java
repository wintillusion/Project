package admin.service;
import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;
import java.sql.Connection;
import dao.AuctionDAO;

public class AuctionDeleteService {

	
	public boolean removeAuction(int num) throws Exception{
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		AuctionDAO auctionDAO = AuctionDAO.getInstance();
		auctionDAO.setConnection(con);
		int deleteCount = auctionDAO.deleteAuction(num);
		
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
