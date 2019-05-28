package request.service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import dao.RequestDAO;
import request.vo.RequestBean;

public class RequestListService {
	
	public int getListCount(String sO, String sW) throws Exception{

		int listCount = 0;
		Connection con = getConnection();	//컨넥션 객체 생성(jdbcUtil에 있는 것)
		RequestDAO requestDAO = RequestDAO.getInstance();	//boardDAO 객체 생성
		requestDAO.setConnection(con);	//객체 주입.(스프링에서 개념이 나옴, 체크필요)
		listCount = requestDAO.selectListCount(sO, sW);		//갯수를 구함
		close(con);	//컨넥션만 여기서 생성하고 닫아줌. stmt와 rs는 DAO에서 닫아줌.
		return listCount;	//리턴
	}
	
	public ArrayList<RequestBean> getRequestList(int page, int limit, String sO, String sW) throws Exception{
		
		ArrayList<RequestBean> requestList = null;
		Connection con = getConnection();
		RequestDAO requestDAO = RequestDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		requestDAO.setConnection(con);
		requestList = requestDAO.selectArticleList(page, limit, sO, sW);
		close(con);
		return requestList;
	}
	
	
//member_main
	public int getListCount() throws Exception{

		int listCount = 0;
		Connection con = getConnection();	//컨넥션 객체 생성(jdbcUtil에 있는 것)
		RequestDAO requestDAO = RequestDAO.getInstance();	//boardDAO 객체 생성
		requestDAO.setConnection(con);	//객체 주입.(스프링에서 개념이 나옴, 체크필요)
		listCount = requestDAO.selectListCount();		//갯수를 구함
		close(con);	//컨넥션만 여기서 생성하고 닫아줌. stmt와 rs는 DAO에서 닫아줌.
		return listCount;	//리턴
	}
	
	public ArrayList<RequestBean> getArticleList(int page, int limit, String id) throws Exception{
		
		ArrayList<RequestBean> requestList = null;
		Connection con = getConnection();
		RequestDAO requestDAO = RequestDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		requestDAO.setConnection(con);
		requestList = requestDAO.memberArticleList(page, limit, id);
		close(con);
		return requestList;
	}
	
//mainPage
public ArrayList<RequestBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<RequestBean> requestList = null;
		Connection con = getConnection();
		RequestDAO requestDAO = RequestDAO.getInstance();	//여기서는 새로 만들어지고 이미 만들어진 객체로 사용.
		requestDAO.setConnection(con);
		requestList = requestDAO.mainArticleList(page, limit);
		close(con);
		return requestList;
	}



}
