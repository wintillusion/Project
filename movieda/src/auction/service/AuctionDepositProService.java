package auction.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.AuctionDAO;
import auction.vo.AuctionBean;
import auction.vo.StockBean;

public class AuctionDepositProService {
	public boolean updateDeposit(AuctionBean auctionBean, StockBean stockBean) throws Exception {

		boolean isModifySuccess = false;
		Connection con = getConnection();
		AuctionDAO auctionDAO = AuctionDAO.getInstance();
		auctionDAO.setConnection(con);
		int updateCount = auctionDAO.depositAuction(auctionBean);
		updateCount += auctionDAO.depositStock(stockBean);

		if (updateCount > 0) {
			commit(con);
			isModifySuccess = true; // 안넣어주면 수정실패라 뜨고 수정은 되는 기이한 현상이 발생하게 된다.
		} else {
			rollback(con);
		}

		close(con);
		return isModifySuccess;
	}
}
