package member.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import member.vo.MemberBean;

public class MemberListService {

	
	//페이지 할떄만
	public int getListCount() throws Exception {
		int listCount=0;
		Connection con=getConnection();
		MemberDAO memberDAO=MemberDAO.getInstance();
		memberDAO.setConnection(con);
		listCount=memberDAO.selectListCount();
		close(con);
		return listCount;
	}

	// 반드시필요
	public ArrayList<MemberBean> getArticleList(int page,int limit) throws Exception{
		
		ArrayList<MemberBean> articleList=null;
		
		Connection con=getConnection();
		MemberDAO memberDAO=MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		articleList=memberDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
	}
}
