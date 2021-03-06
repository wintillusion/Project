package auction.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;
import java.sql.Connection;

import dao.AuctionDAO;
import auction.vo.AuctionBean;
import auction.vo.AuctionInfoBean;

public class AuctionUpdateService {
	public boolean updateAuction(AuctionBean auctionBean, AuctionInfoBean auctioninfo) throws Exception {

		boolean isModifySuccess = false;
		Connection con = getConnection();
		AuctionDAO auctionDAO = AuctionDAO.getInstance();
		auctionDAO.setConnection(con);
		int updateCount = auctionDAO.updateAuction(auctionBean, auctioninfo);

		if (updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}

		close(con);
		return isModifySuccess;
	}
}
