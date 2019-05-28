package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;
import comment.vo.CommentBean;
import request.vo.RequestBean;


public class CommentDAO {
	DataSource ds;
	Connection con;
	private static CommentDAO commentDAO;

	private CommentDAO() {
		// TODO Auto-generated constructor stub
	}

	public static CommentDAO getInstance() {
		if (commentDAO == null) {
			commentDAO = new CommentDAO();
		}
		return commentDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int insertReplyComment(CommentBean comment) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_max_sql="select max(num) from comment";
		String sql="";
		int num=0;
		int insertCount=0;
		int re_ref=comment.getRe_ref();
		int re_lev=comment.getRe_lev();
		int re_seq=comment.getRe_seq();
		
		try {
			pstmt=con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())num=rs.getInt(1)+1;
			else num=1;
			sql="update comment set re_seq=re_seq+1 where re_ref=? ";
			sql+="and re_seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			int updateCount=pstmt.executeUpdate();
			
			if(updateCount>0) {
				commit(con);
			}
			
			re_seq=re_seq+1;
			re_lev=re_lev+1;
			sql="insert into comment(num, content, comment_board,";
			sql+="mem_id, comment_code,date,re_seq,re_lev,re_ref) ";
			sql+="values(?,?,?,?,?,now(),?,?,?)";
			
			//System.out.println(sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1 , num);
			pstmt.setString(2, comment.getContent());
			pstmt.setInt(3, comment.getComment_board());
			pstmt.setString(4, comment.getMem_id());
			pstmt.setInt(5, 1);
			pstmt.setInt(6, re_seq);
			pstmt.setInt(7, re_lev);
			pstmt.setInt(8, re_ref);
			insertCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("commentReply 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = con.prepareStatement("select count(*) from comment");	//전체 글 갯수 구하기
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount=rs.getInt(1); //값이 있으면 넘겨줌
			}
		}catch(Exception ex) {
			System.out.println("getCommentListCount 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}

		return listCount;	//값이 없으면 0 값을 넘김.
	}
	
	public ArrayList<CommentBean> selectCommentList(int page, int limit, int comment_board){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from comment where comment_board=? and comment_code=1 order by re_ref desc, re_seq asc limit ?, ?";	//limit sql문 확인
													//order by 정렬을 re_ref로 desc(내림차순)정렬, 그다음 seq를 asc(오름차순)로 정렬.
													//최신글이 먼저 보이게하고, seq는 쓰여진 순서대로 보이게 하기 위해.
		ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();
		CommentBean comment = null;
		int startrow=(page-1)*limit;//10, 읽기 시작할 row번호	첫번째 시작 위치.
		
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, comment_board);
			pstmt.setInt(2,  startrow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				comment=new CommentBean();		
				comment.setNum(rs.getInt("num"));
				comment.setContent(rs.getString("content"));
				comment.setComment_board(rs.getInt("comment_board"));
				comment.setMem_id(rs.getString("mem_id"));
				comment.setComment_code(rs.getInt("comment_code"));
				comment.setDate(rs.getDate("date"));
				comment.setRe_seq(rs.getInt("re_seq"));
				comment.setRe_lev(rs.getInt("re_lev"));
				comment.setRe_ref(rs.getInt("re_ref"));
				commentList.add(comment);
			}
		}catch(Exception ex) {
			System.out.println("getCommentList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return commentList;
	}
}
