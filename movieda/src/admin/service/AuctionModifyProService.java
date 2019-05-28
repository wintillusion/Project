package admin.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.AuctionDAO;
import auction.vo.AuctionBean;
import auction.vo.StockBean;

public class AuctionModifyProService {
	public boolean modifyAuction(AuctionBean auctionBean) throws Exception {

		boolean isModifySuccess = false;
		Connection con = getConnection();
		AuctionDAO auctionDAO = AuctionDAO.getInstance();
		auctionDAO.setConnection(con);
		int updateCount = auctionDAO.modifyAuction(auctionBean);

		if (updateCount > 0) {
			commit(con);
			isModifySuccess = true; 
		} else {
			rollback(con);
		}

		close(con);
		return isModifySuccess;
	}

	public boolean modifyStock(StockBean stockBean) throws Exception {

		boolean isModifySuccess = false;
		Connection con = getConnection();
		AuctionDAO auctionDAO = AuctionDAO.getInstance();
		auctionDAO.setConnection(con);
		int updateCount = auctionDAO.modifyStock(stockBean);

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
