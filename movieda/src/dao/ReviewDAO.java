package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import review.vo.ReviewBean;



public class ReviewDAO {
	
	DataSource ds;
	Connection con;
	private static ReviewDAO ReviewDAO;
	
	private ReviewDAO() {
		
	}
	
	public static ReviewDAO getInstance() {	
		if(ReviewDAO == null) {
			ReviewDAO = new ReviewDAO();	
		}
		return ReviewDAO;
	}
	
	public void setConnection(Connection con) {	
		this.con = con;
	}
	
	//글 갯수 구하기
	public int selectListCount(String sO, String sW) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select count(*) from reviewboard ";
		if(!sO.equals("")) {
			sql+="where "+sO+" like '%"+sW+"%' ";
		}

		try {
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("getListCount 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}

		return listCount;	
	}
	
	//글목록 보기
	public ArrayList<ReviewBean> selectArticleList(int page, int limit, String sO, String sW){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String board_list_sql="select * from reviewboard ";
		if(!sO.equals("")) {
			board_list_sql+="where "+sO+" like '%"+sW+"%' ";
		}
		board_list_sql+="order by re_ref desc, re_seq asc limit ?, ?";

		//		System.out.println("글목록보기 쿼리문 : " + board_list_sql);

		ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
		ReviewBean review = null;
		int startrow=(page-1)*limit;//10, 읽기 시작할 row번호	첫번째 시작 위치.
		
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1,  startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				review=new ReviewBean();	
				review.setNum(rs.getInt("num"));
				review.setSubject(rs.getString("subject"));
				review.setContent(rs.getString("content"));
				review.setFile(rs.getString("file"));
				review.setRe_ref(rs.getInt("re_ref"));
				review.setRe_seq(rs.getInt("re_seq"));
				review.setRe_lev(rs.getInt("re_lev"));
				review.setRead_count(rs.getInt("readcount"));
				review.setMem_id(rs.getString("mem_id"));
				review.setDate(rs.getDate("date"));
				review.setLikey(rs.getInt("likey"));
				reviewList.add(review);
			}
		}catch(Exception ex) {
			System.out.println("getReviewList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return reviewList;
	}
	
	//member_main
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = con.prepareStatement("select count(*) from reviewboard");	//전체 글 갯수 구하기
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
	public ArrayList<ReviewBean> memberArticleList(int page, int limit, String id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from reviewboard where mem_id=? order by re_ref desc, re_seq asc limit ?, ?";	//limit sql문 확인
													//order by 정렬을 re_ref로 desc(내림차순)정렬, 그다음 seq를 asc(오름차순)로 정렬.
													//최신글이 먼저 보이게하고, seq는 쓰여진 순서대로 보이게 하기 위해.
		ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
		ReviewBean review = null;
		int startrow=(page-1)*limit;//10, 읽기 시작할 row번호	첫번째 시작 위치.
		//System.out.println("DAO mem_id : " + id);
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setString(1, id);
			pstmt.setInt(2,  startrow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				review=new ReviewBean();		//BoardBean 객체를 만들고 ResultSet의 값들을 BoardBean에 값을 넘겨줌.
				review.setNum(rs.getInt("num"));
				review.setSubject(rs.getString("subject"));
				review.setContent(rs.getString("content"));
				review.setFile(rs.getString("file"));
				review.setRe_ref(rs.getInt("re_ref"));
				review.setRe_seq(rs.getInt("re_seq"));
				review.setRe_lev(rs.getInt("re_lev"));
				review.setRead_count(rs.getInt("readcount"));
				review.setMem_id(rs.getString("mem_id"));
				review.setDate(rs.getDate("date"));
				review.setLikey(rs.getInt("likey"));
				reviewList.add(review);
			}
		}catch(Exception ex) {
			System.out.println("getBoardList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return reviewList;
	}
	
	
	public ArrayList<ReviewBean> mainArticleList(int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from reviewboard order by re_ref desc, re_seq asc limit ?, ?";	//limit sql문 확인
													//order by 정렬을 re_ref로 desc(내림차순)정렬, 그다음 seq를 asc(오름차순)로 정렬.
													//최신글이 먼저 보이게하고, seq는 쓰여진 순서대로 보이게 하기 위해.
		ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
		ReviewBean review = null;
		int startrow=(page-1)*limit;//10, 읽기 시작할 row번호	첫번째 시작 위치.
		//System.out.println("DAO mem_id : " + id);
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1,  startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				review=new ReviewBean();		//BoardBean 객체를 만들고 ResultSet의 값들을 BoardBean에 값을 넘겨줌.
				review.setNum(rs.getInt("num"));
				review.setSubject(rs.getString("subject"));
				review.setContent(rs.getString("content"));
				review.setFile(rs.getString("file"));
				review.setRe_ref(rs.getInt("re_ref"));
				review.setRe_seq(rs.getInt("re_seq"));
				review.setRe_lev(rs.getInt("re_lev"));
				review.setRead_count(rs.getInt("readcount"));
				review.setMem_id(rs.getString("mem_id"));
				review.setDate(rs.getDate("date"));
				review.setLikey(rs.getInt("likey"));
				reviewList.add(review);
			}
		}catch(Exception ex) {
			System.out.println("getBoardList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return reviewList;
	}
	
	
	//글등록
	public int insertReview(ReviewBean review) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql="";
		int insertCount=0;
		
		try {
			pstmt=con.prepareStatement("select max(num) from reviewboard");	//현재보더중에 최대 값이 뭔지 찾는 것.
			rs=pstmt.executeQuery();
			
			if(rs.next()) 
				num = rs.getInt(1)+1;	//불러온 첫번째 열의 값+1
			else
				num=1;	//자료가 하나도 없을 경우엔 1을 주는 것. 처음에만 글등록한 경우에만 1을 주고, 그 다음부턴 if문이 실행되는 것.
			
			sql="insert into reviewboard (num,subject,";
			sql+="content, file, re_seq, "+"re_lev, re_ref, readcount, "+"mem_id, date) values(?,?,?,?,?,?,?,?,?,now())";
			//sql="insert into board values(?,?,?,?,?,?,?,0,0,0,now());
			//now() 는 쿼리문 함수. 현재 시간이 들어옴.
			//주석처리된 sql문을 쓸때는 8~10 주석처리.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  num);
			pstmt.setString(2, review.getSubject());
			pstmt.setString(3, review.getContent());
			pstmt.setString(4, review.getFile()==null?"":review.getFile());	//파일이 없을 때 공백으로.
			//실제로 넘겨받는 값은 2,3,4로 3개
			pstmt.setInt(5, 0);		//re_seq. 글을 어떻게 정리할 것이냐. 정렬하는 것.
			pstmt.setInt(6, 0);		//re_lev. 들여쓰기.	답글은 따로 밑에 작성 따로 만들거기때문에 다 0으로.
			pstmt.setInt(7, num);	//re_ref. 기본키 값 num. 답글일 경우 기본키를 참조.(몇번 인덱스를 참조해서 거기에 답글을 달겠다...)
			pstmt.setInt(8, 0);	//조회수 0
			pstmt.setString(9, review.getMem_id());

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
	public ReviewBean selectArticle(int num) {	//BoardBean 하나만 가져오면 됨.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewBean reviewBean = null;
		
		try {
			pstmt = con.prepareStatement("select * from reviewboard where num = ? ");
			pstmt.setInt(1,  num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reviewBean = new ReviewBean();
				reviewBean.setNum(rs.getInt("num"));
				reviewBean.setSubject(rs.getString("subject"));
				reviewBean.setContent(rs.getString("content"));
				reviewBean.setFile(rs.getString("file"));
				reviewBean.setRe_ref(rs.getInt("re_ref"));
				reviewBean.setRe_seq(rs.getInt("re_seq"));
				reviewBean.setRe_lev(rs.getInt("re_lev"));
				reviewBean.setRead_count(rs.getInt("readcount"));
				reviewBean.setMem_id(rs.getString("mem_id"));
				reviewBean.setDate(rs.getDate("date"));
				reviewBean.setLikey(rs.getInt("likey"));
				//값 할당
			}
		}catch(Exception ex) {
			System.out.println("get review Detail 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return reviewBean;
	}
	
	//조회수 업데이트
	public int updateReadCount(int num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update reviewboard set readcount = " + "readcount+1 where num = "+num;
		
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
	public int updateArticle(ReviewBean reviewBean) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update reviewboard set subject=?, content=? where num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reviewBean.getSubject());
			pstmt.setString(2, reviewBean.getContent());
			pstmt.setInt(3, reviewBean.getNum());
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("review modify error :  " + e);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	//글삭제
		public int deleteArticle(int num) {
			PreparedStatement pstmt = null;
			String board_delete_sql ="delete from reviewboard where num=?";
			int deleteCount=0;
			
			try {
				pstmt=con.prepareStatement(board_delete_sql);
				pstmt.setInt(1, num);
				deleteCount=pstmt.executeUpdate();
			}catch(Exception ex) {
				System.out.println("reviewdelete 에러 : " + ex);
			}finally {
				close(pstmt);
			}
			return deleteCount;
		}
		
	//좋아요 
		public int likeyArticle(ReviewBean reviewBean) {
			PreparedStatement pstmt = null;
			String board_likey_sql ="update reviewboard set likey=likey+1 where num=?";
			int likeyCount=0;
			
			try {
				pstmt=con.prepareStatement(board_likey_sql);
				pstmt.setInt(1, reviewBean.getNum());
				likeyCount=pstmt.executeUpdate();
			}catch(Exception ex) {
				System.out.println("review likey 에러 : " + ex);
			}finally {
				close(pstmt);
			}
			return likeyCount;
		}

}
