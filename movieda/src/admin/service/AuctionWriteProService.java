package admin.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;
import java.sql.Connection;
import dao.AuctionDAO;
import auction.vo.AuctionBean;
import auction.vo.StockBean;

public class AuctionWriteProService {
	public boolean registAuction(AuctionBean auctionBean, StockBean stockBean) throws Exception {

		boolean isWriteSuccess = false;
		Connection con = getConnection();
		AuctionDAO auctionDAO = AuctionDAO.getInstance();
		auctionDAO.setConnection(con);
		int insertCount = auctionDAO.insertAuction(auctionBean, stockBean);

		if (insertCount > 0) { // dao에서 넘겨준 값이 0보다 크면 true로.
			commit(con);
			isWriteSuccess = true;
		} else {
			rollback(con);
		}

		close(con);
		return isWriteSuccess;
	}
}
