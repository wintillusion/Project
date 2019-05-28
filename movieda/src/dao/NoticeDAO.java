package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
import notice.vo.NoticeBean;

public class NoticeDAO {

	DataSource ds;
	Connection con;
	private static NoticeDAO noticeDAO;
	
	private NoticeDAO() {
		
	}
	
	public static NoticeDAO getInstance() {	
		if(noticeDAO == null) {
			noticeDAO = new NoticeDAO();	
		}
		return noticeDAO;
	}
	
	public void setConnection(Connection con) {	
		this.con = con;
	}
	
	public int selectListCount(String sO, String sW) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select count(*) from notice ";
		if(!sO.equals("")) {
			sql+="where "+sO+" like '%"+sW+"%' ";
		}
//	
		try {
			
			pstmt = con.prepareStatement(sql);	
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount=rs.getInt(1); 
			}
		}catch(Exception ex) {
			System.out.println("getNoticeListCount error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}

		return listCount;	//값이 없으면 0 값을 넘김.
	}
	
	
	//공지글 리스트
	public ArrayList<NoticeBean> selectNoticeList(int page, int limit, String sO, String sW){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//String board_list_sql="select * from board order by BOARD_RE_REF desc, BOARD_RE_SEQ asc limit ?, ?";	//limit sql문 확인
													//order by 정렬을 re_ref로 desc(내림차순)정렬, 그다음 seq를 asc(오름차순)로 정렬.
													//최신글이 먼저 보이게하고, seq는 쓰여진 순서대로 보이게 하기 위해.
		
		String auction_list_sql="select * from notice ";
		if(!sO.equals("")) {
			auction_list_sql+="where "+sO+" like '%"+sW+"%' ";
		}
		auction_list_sql+="order by num desc";

		//		System.out.println("글목록보기 쿼리문 : " + board_list_sql);

		ArrayList<NoticeBean> noticeList = new ArrayList<NoticeBean>();
		NoticeBean notice = null;
		
		
		try {
			pstmt = con.prepareStatement(auction_list_sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				notice=new NoticeBean();		//BoardBean 객체를 만들고 ResultSet의 값들을 BoardBean에 값을 넘겨줌.
				notice.setNum(rs.getInt("num"));
				notice.setSubject(rs.getString("subject"));
				notice.setContent(rs.getString("content"));
				notice.setWriter(rs.getString("writer"));
				notice.setReadcount(rs.getInt("readcount"));
				notice.setDate(rs.getDate("date"));
	
				noticeList.add(notice);
			}
		}catch(Exception ex) {
			System.out.println("getNoticeList error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return noticeList;
	}
	
	public ArrayList<NoticeBean> selectNoticeList(int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//String board_list_sql="select * from board order by BOARD_RE_REF desc, BOARD_RE_SEQ asc limit ?, ?";	//limit sql문 확인
													//order by 정렬을 re_ref로 desc(내림차순)정렬, 그다음 seq를 asc(오름차순)로 정렬.
													//최신글이 먼저 보이게하고, seq는 쓰여진 순서대로 보이게 하기 위해.
		
		String auction_list_sql="select * from notice ";
		
		ArrayList<NoticeBean> noticeList = new ArrayList<NoticeBean>();
		NoticeBean notice = null;
		
		
		try {
			pstmt = con.prepareStatement(auction_list_sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				notice=new NoticeBean();		//BoardBean 객체를 만들고 ResultSet의 값들을 BoardBean에 값을 넘겨줌.
				notice.setNum(rs.getInt("num"));
				notice.setSubject(rs.getString("subject"));
				notice.setContent(rs.getString("content"));
				notice.setWriter(rs.getString("writer"));
				notice.setReadcount(rs.getInt("readcount"));
				notice.setDate(rs.getDate("date"));
	
				noticeList.add(notice);
			}
		}catch(Exception ex) {
			System.out.println("getNoticeList error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return noticeList;
	}

	
	//경매 등록. 경매등록은 admin 계정만 등록이 가능하도록 action에서 설정해두기.
	public int insertNotice(NoticeBean notice) {			
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		int num = 0;
		String sql="";
		int insertCount=0;
		try {
			pstmt=con.prepareStatement("select max(num) from notice");	
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) 
				num = rs.getInt(1)+1;	//불러온 첫번째 열의 값+1
			else 
				num=1;	//자료가 하나도 없을 경우엔 1을 주는 것. 처음에만 글등록한 경우에만 1을 주고, 그 다음부턴 if문이 실행되는 것.
			
			sql="insert into notice (num,subject, date, content, writer)";
			sql+="values(?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  num);		
			pstmt.setString(2, notice.getSubject());	
			pstmt.setString(3, notice.getContent());	
			pstmt.setString(4, notice.getWriter());
			
			
			insertCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("notice Insert error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
			
		}
		return insertCount;
	}
	
	
	//공지 보기
	public NoticeBean selectNotice(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeBean noticeBean = null;
		
		try {
			pstmt = con.prepareStatement("select * from notice where num = ? ");
			pstmt.setInt(1,  num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeBean = new NoticeBean();
				noticeBean.setNum(rs.getInt("num"));
				noticeBean.setSubject(rs.getString("subject"));
				noticeBean.setContent(rs.getString("content"));
				noticeBean.setWriter(rs.getString("writer"));
				noticeBean.setReadcount(rs.getInt("readcount"));
				noticeBean.setDate(rs.getDate("date"));
				
			}
		}catch(Exception ex) {
			System.out.println("notice Detail error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return noticeBean;
	}
	
	
	
	//공지삭제
	public int deleteNotice(int num) {
		PreparedStatement pstmt = null;
		String notice_delete_sql ="delete from notice where num=?";
		int deleteCount=0;
		
		try {
			pstmt=con.prepareStatement(notice_delete_sql);
			pstmt.setInt(1, num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("notice delete error : " + ex);
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	//공지 수정
	public int modifyNotice(NoticeBean noticeBean) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update notice set subject=?, content=? where num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, noticeBean.getSubject());
			pstmt.setString(2, noticeBean.getContent());
			pstmt.setInt(3, noticeBean.getNum());
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("noticeModify error :  " + e);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public int updateReadCount(int num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update notice set readcount = " + "readcount+1 where num = "+num;
		
		try {
			pstmt=con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		}catch(SQLException ex) {
			System.out.println("setReadCountUpdate 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	
	
}
