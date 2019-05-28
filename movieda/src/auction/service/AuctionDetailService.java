package auction.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AuctionDAO;
import auction.vo.AuctionBean;
import auction.vo.StockBean;
import auction.vo.AuctionInfoBean;

public class AuctionDetailService {
	
	public AuctionBean getAuction(int num) throws Exception {

		AuctionBean auctionBean = null;
		Connection con = getConnection();
		AuctionDAO auctionDAO = AuctionDAO.getInstance();
		auctionDAO.setConnection(con);
		
		auctionBean = auctionDAO.selectAuction(num);
		close(con);
		return auctionBean;
	}
	
	public StockBean getStock(int num) throws Exception {

		StockBean stockBean = null;
		Connection con = getConnection();
		AuctionDAO auctionDAO = AuctionDAO.getInstance();
		auctionDAO.setConnection(con);
		
		stockBean = auctionDAO.selectStock(num);
		close(con);
		return stockBean;
	}
	
	public ArrayList<AuctionInfoBean> getAuctionInfoList(int num) throws Exception {
	
		ArrayList<AuctionInfoBean> auctionInfoList = null;
		Connection con = getConnection();
		AuctionDAO auctionDAO = AuctionDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		auctionDAO.setConnection(con);
		auctionInfoList = auctionDAO.auctionInfoList(num);
		close(con);
		return auctionInfoList;
	}
}
