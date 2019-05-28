package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import request.vo.RequestBean;



public class RequestDAO {
	
	DataSource ds;
	Connection con;
	private static RequestDAO RequestDAO;
	
	private RequestDAO() {
		
	}
	
	public static RequestDAO getInstance() {	
		if(RequestDAO == null) {
			RequestDAO = new RequestDAO();	//null일때만 생성. 작동하는 객체가 이미 있으면 생성시키지 않음. 한번 생성하면 계속 쓰고, 그다음부터 다시 만들지 않아서 리소스 절약이 가능.
		}
		return RequestDAO;
	}
	
	public void setConnection(Connection con) {		//컨넥션 객체를 받아서 넘겨줌.
		this.con = con;
	}
	
	//글 갯수 구하기
	public int selectListCount(String sO, String sW) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select count(*) from requestboard ";
		if(!sO.equals("")) {
			sql+="where "+sO+" like '%"+sW+"%' ";
		}
//		System.out.println("글 갯수 : " + sql);
		try {
			
			pstmt = con.prepareStatement(sql);	//전체 글 갯수 구하기
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount=rs.getInt(1); //값이 있으면 넘겨줌
			}
		}catch(Exception ex) {
			System.out.println("getListCount 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}

		return listCount;	//값이 없으면 0 값을 넘김.
	}
	
	//글목록 보기
	public ArrayList<RequestBean> selectArticleList(int page, int limit, String sO, String sW){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//String board_list_sql="select * from board order by BOARD_RE_REF desc, BOARD_RE_SEQ asc limit ?, ?";	//limit sql문 확인
													//order by 정렬을 re_ref로 desc(내림차순)정렬, 그다음 seq를 asc(오름차순)로 정렬.
													//최신글이 먼저 보이게하고, seq는 쓰여진 순서대로 보이게 하기 위해.
		
		String board_list_sql="select * from requestboard ";
		if(!sO.equals("")) {
			board_list_sql+="where "+sO+" like '%"+sW+"%' ";
		}
		board_list_sql+="order by re_ref desc, re_seq asc limit ?, ?";

		//		System.out.println("글목록보기 쿼리문 : " + board_list_sql);

		ArrayList<RequestBean> requestList = new ArrayList<RequestBean>();
		RequestBean request = null;
		int startrow=(page-1)*limit;//10, 읽기 시작할 row번호	첫번째 시작 위치.
		
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1,  startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				request=new RequestBean();		//BoardBean 객체를 만들고 ResultSet의 값들을 BoardBean에 값을 넘겨줌.
				request.setNum(rs.getInt("num"));
				request.setSubject(rs.getString("subject"));
				request.setContent(rs.getString("content"));
				request.setFile(rs.getString("file"));
				request.setRe_ref(rs.getInt("re_ref"));
				request.setRe_seq(rs.getInt("re_seq"));
				request.setRe_lev(rs.getInt("re_lev"));
				request.setRead_count(rs.getInt("readcount"));
				request.setMem_id(rs.getString("mem_id"));
				request.setDate(rs.getDate("date"));
				request.setLikey(rs.getInt("likey"));
				requestList.add(request);
			}
		}catch(Exception ex) {
			System.out.println("getRequestList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return requestList;
	}
	
	//member_main
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = con.prepareStatement("select count(*) from requestboard");	//전체 글 갯수 구하기
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount=rs.getInt(1); //값이 있으면 넘겨줌
			}
		}catch(Exception ex) {
			System.out.println("getListCount 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}

		return listCount;	//값이 없으면 0 값을 넘김.
	}
	//member_main
	public ArrayList<RequestBean> memberArticleList(int page, int limit, String id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from requestboard where mem_id=? order by re_ref desc, re_seq asc limit ?, ?";	//limit sql문 확인
													//order by 정렬을 re_ref로 desc(내림차순)정렬, 그다음 seq를 asc(오름차순)로 정렬.
													//최신글이 먼저 보이게하고, seq는 쓰여진 순서대로 보이게 하기 위해.
		ArrayList<RequestBean> requestList = new ArrayList<RequestBean>();
		RequestBean request = null;
		int startrow=(page-1)*limit;//10, 읽기 시작할 row번호	첫번째 시작 위치.
		//System.out.println("DAO mem_id : " + id);
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setString(1, id);
			pstmt.setInt(2,  startrow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				request=new RequestBean();		
				request.setNum(rs.getInt("num"));
				request.setSubject(rs.getString("subject"));
				request.setContent(rs.getString("content"));
				request.setFile(rs.getString("file"));
				request.setRe_ref(rs.getInt("re_ref"));
				request.setRe_seq(rs.getInt("re_seq"));
				request.setRe_lev(rs.getInt("re_lev"));
				request.setRead_count(rs.getInt("readcount"));
				request.setMem_id(rs.getString("mem_id"));
				request.setDate(rs.getDate("date"));
				request.setLikey(rs.getInt("likey"));
				requestList.add(request);
			}
		}catch(Exception ex) {
			System.out.println("getBoardList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return requestList;
	}
	
	public ArrayList<RequestBean> mainArticleList(int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from requestboard order by re_ref desc, re_seq asc limit ?, ?";	//limit sql문 확인
													//order by 정렬을 re_ref로 desc(내림차순)정렬, 그다음 seq를 asc(오름차순)로 정렬.
													//최신글이 먼저 보이게하고, seq는 쓰여진 순서대로 보이게 하기 위해.
		ArrayList<RequestBean> requestList = new ArrayList<RequestBean>();
		RequestBean request = null;
		int startrow=(page-1)*limit;//10, 읽기 시작할 row번호	첫번째 시작 위치.
		//System.out.println("DAO mem_id : " + id);
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1,  startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				request=new RequestBean();		//BoardBean 객체를 만들고 ResultSet의 값들을 BoardBean에 값을 넘겨줌.
				request.setNum(rs.getInt("num"));
				request.setSubject(rs.getString("subject"));
				request.setContent(rs.getString("content"));
				request.setFile(rs.getString("file"));
				request.setRe_ref(rs.getInt("re_ref"));
				request.setRe_seq(rs.getInt("re_seq"));
				request.setRe_lev(rs.getInt("re_lev"));
				request.setRead_count(rs.getInt("readcount"));
				request.setMem_id(rs.getString("mem_id"));
				request.setDate(rs.getDate("date"));
				request.setLikey(rs.getInt("likey"));
				requestList.add(request);
			}
		}catch(Exception ex) {
			System.out.println("getrequestList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return requestList;
	}
	
	
	
	//글등록
	public int insertRequest(RequestBean request) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql="";
		int insertCount=0;
		
		try {
			pstmt=con.prepareStatement("select max(num) from requestboard");	//현재보더중에 최대 값이 뭔지 찾는 것.
			rs=pstmt.executeQuery();
			
			if(rs.next()) 
				num = rs.getInt(1)+1;	//불러온 첫번째 열의 값+1
			else
				num=1;	//자료가 하나도 없을 경우엔 1을 주는 것. 처음에만 글등록한 경우에만 1을 주고, 그 다음부턴 if문이 실행되는 것.
			
			sql="insert into requestboard (num,subject,";
			sql+="content, file, re_seq, "+"re_lev, re_ref, readcount, "+"mem_id, date) values(?,?,?,?,?,?,?,?,?,now())";
			//sql="insert into board values(?,?,?,?,?,?,?,0,0,0,now());
			//now() 는 쿼리문 함수. 현재 시간이 들어옴.
			//주석처리된 sql문을 쓸때는 8~10 주석처리.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  num);
			pstmt.setString(2, request.getSubject());
			pstmt.setString(3, request.getContent());
			pstmt.setString(4, request.getFile()==null?"":request.getFile());	//파일이 없을 때 공백으로.
			//실제로 넘겨받는 값은 2,3,4로 3개
			pstmt.setInt(5, 0);		//re_seq. 글을 어떻게 정리할 것이냐. 정렬하는 것.
			pstmt.setInt(6, 0);		//re_lev. 들여쓰기.	답글은 따로 밑에 작성 따로 만들거기때문에 다 0으로.
			pstmt.setInt(7, num);	//re_ref. 기본키 값 num. 답글일 경우 기본키를 참조.(몇번 인덱스를 참조해서 거기에 답글을 달겠다...)
			pstmt.setInt(8, 0);	//조회수 0
			pstmt.setString(9, request.getMem_id());

			insertCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("boardInsert 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	//글 내용 보기
	public RequestBean selectArticle(int num) {	//BoardBean 하나만 가져오면 됨.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RequestBean requestBean = null;
		
		try {
			pstmt = con.prepareStatement("select * from requestboard where num = ? ");
			pstmt.setInt(1,  num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				requestBean = new RequestBean();
				requestBean.setNum(rs.getInt("num"));
				requestBean.setSubject(rs.getString("subject"));
				requestBean.setContent(rs.getString("content"));
				requestBean.setFile(rs.getString("file"));
				requestBean.setRe_ref(rs.getInt("re_ref"));
				requestBean.setRe_seq(rs.getInt("re_seq"));
				requestBean.setRe_lev(rs.getInt("re_lev"));
				requestBean.setRead_count(rs.getInt("readcount"));
				requestBean.setMem_id(rs.getString("mem_id"));
				requestBean.setDate(rs.getDate("date"));
				requestBean.setLikey(rs.getInt("likey"));
				//값 할당
			}
		}catch(Exception ex) {
			System.out.println("getDetail 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return requestBean;
	}
	
	//조회수 업데이트
	public int updateReadCount(int num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update requestboard set readcount = " + "readcount+1 where num = "+num;
		
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
	
	//글 수정
	public int updateArticle(RequestBean requestBean) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update requestboard set subject=?, content=? where num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, requestBean.getSubject());
			pstmt.setString(2, requestBean.getContent());
			pstmt.setInt(3, requestBean.getNum());
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("boardModify error :  " + e);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	//글삭제
		public int deleteArticle(int num) {
			PreparedStatement pstmt = null;
			String board_delete_sql ="delete from requestboard where num=?";
			int deleteCount=0;
			
			try {
				pstmt=con.prepareStatement(board_delete_sql);
				pstmt.setInt(1, num);
				deleteCount=pstmt.executeUpdate();
			}catch(Exception ex) {
				System.out.println("requestDelete 에러 : " + ex);
			}finally {
				close(pstmt);
			}
			return deleteCount;
		}
		
	//좋아요 
		public int likeyArticle(RequestBean requestBean) {
			PreparedStatement pstmt = null;
			String board_likey_sql ="update requestboard set likey=likey+1 where num=?";
			int likeyCount=0;
			
			try {
				pstmt=con.prepareStatement(board_likey_sql);
				pstmt.setInt(1, requestBean.getNum());
				likeyCount=pstmt.executeUpdate();
			}catch(Exception ex) {
				System.out.println("boardLikey 에러 : " + ex);
			}finally {
				close(pstmt);
			}
			return likeyCount;
		}

}
