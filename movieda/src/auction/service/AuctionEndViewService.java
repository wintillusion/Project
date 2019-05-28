package auction.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AuctionDAO;
import auction.vo.AuctionBean;

public class AuctionEndViewService {
public ArrayList<AuctionBean> getArticleList(int page, int limit, String id) throws Exception{
		
		ArrayList<AuctionBean> requestList = null;
		Connection con = getConnection();
		AuctionDAO auctionDAO = AuctionDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		auctionDAO.setConnection(con);
		requestList = auctionDAO.EndAuctionList(page, limit, id);
		close(con);
		return requestList;
	}
}
