package notice.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import auction.vo.AuctionBean;
import dao.AuctionDAO;
import dao.NoticeDAO;
import notice.vo.NoticeBean;

public class NoticeListService {
	public int getListCount(String sO, String sW) throws Exception{

		int listCount = 0;
		Connection con = getConnection();	//컨넥션 객체 생성(jdbcUtil에 있는 것)
		NoticeDAO noticeDAO = NoticeDAO.getInstance();	//boardDAO 객체 생성
		noticeDAO.setConnection(con);	//객체 주입.(스프링에서 개념이 나옴, 체크필요)
		listCount = noticeDAO.selectListCount(sO, sW);		//갯수를 구함
		close(con);	//컨넥션만 여기서 생성하고 닫아줌. stmt와 rs는 DAO에서 닫아줌.
		return listCount;	//리턴
	}
	
	public ArrayList<NoticeBean> getNoticeList(int page, int limit, String sO, String sW) throws Exception{
		
		ArrayList<NoticeBean> noticeList = null;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		noticeDAO.setConnection(con);
		noticeList = noticeDAO.selectNoticeList(page, limit, sO, sW);
		close(con);
		return noticeList;
	}
	
	public ArrayList<NoticeBean> getNoticeList(int page, int limit) throws Exception{
		
		ArrayList<NoticeBean> noticeList = null;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		noticeDAO.setConnection(con);
		noticeList = noticeDAO.selectNoticeList(page, limit);
		close(con);
		return noticeList;
	}
}
