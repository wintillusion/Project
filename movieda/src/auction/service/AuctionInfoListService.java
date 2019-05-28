package auction.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AuctionDAO;
import auction.vo.AuctionInfoBean;

public class AuctionInfoListService {
	
	public int getListCount() throws Exception{

		int listCount = 0;
		Connection con = getConnection();	//컨넥션 객체 생성(jdbcUtil에 있는 것)
		AuctionDAO auctionDAO = AuctionDAO.getInstance();	//boardDAO 객체 생성
		auctionDAO.setConnection(con);	//객체 주입.(스프링에서 개념이 나옴, 체크필요)
		listCount = auctionDAO.selectListCount();		//갯수를 구함
		close(con);	//컨넥션만 여기서 생성하고 닫아줌. stmt와 rs는 DAO에서 닫아줌.
		return listCount;	//리턴
	}
	
	public ArrayList<AuctionInfoBean> getAuctionInfoList(int page, int limit, String id) throws Exception{
		
		ArrayList<AuctionInfoBean> auctionInfoList = null;
		Connection con = getConnection();
		AuctionDAO auctionDAO = AuctionDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		auctionDAO.setConnection(con);
		auctionInfoList = auctionDAO.memberAuctionList(page, limit, id);
		close(con);
		return auctionInfoList;
	}

}
