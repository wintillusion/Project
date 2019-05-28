package review.service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import dao.ReviewDAO;
import review.vo.ReviewBean;

public class ReviewListService {
	
	public int getListCount(String sO, String sW) throws Exception{

		int listCount = 0;
		Connection con = getConnection();	//컨넥션 객체 생성(jdbcUtil에 있는 것)
		ReviewDAO reviewDAO = ReviewDAO.getInstance();	//boardDAO 객체 생성
		reviewDAO.setConnection(con);	//객체 주입.(스프링에서 개념이 나옴, 체크필요)
		listCount = reviewDAO.selectListCount(sO, sW);		//갯수를 구함
		close(con);	//컨넥션만 여기서 생성하고 닫아줌. stmt와 rs는 DAO에서 닫아줌.
		return listCount;	//리턴
	}
	
	public ArrayList<ReviewBean> getReviewList(int page, int limit, String sO, String sW) throws Exception{
		
		ArrayList<ReviewBean> reviewList = null;
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		reviewDAO.setConnection(con);
		reviewList = reviewDAO.selectArticleList(page, limit, sO, sW);
		close(con);
		return reviewList;
	}
	
	
//member_main
	public int getListCount() throws Exception{

		int listCount = 0;
		Connection con = getConnection();	
		ReviewDAO reviewDAO = ReviewDAO.getInstance();	
		reviewDAO.setConnection(con);	
		listCount = reviewDAO.selectListCount();
		close(con);	
		return listCount;
	}
	
	public ArrayList<ReviewBean> getArticleList(int page, int limit, String id) throws Exception{
		
		ArrayList<ReviewBean> reviewList = null;
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		reviewDAO.setConnection(con);
		reviewList = reviewDAO.memberArticleList(page, limit, id);
		close(con);
		return reviewList;
	}

	//main page
	public ArrayList<ReviewBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<ReviewBean> reviewList = null;
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		reviewDAO.setConnection(con);
		reviewList = reviewDAO.mainArticleList(page, limit);
		close(con);
		return reviewList;
	}
	
}
