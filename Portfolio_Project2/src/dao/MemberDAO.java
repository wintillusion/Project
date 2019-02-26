package dao;

import static db.JdbcUtil.close;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import member.vo.MemberBean;

public class MemberDAO {

	DataSource ds;
	Connection con;
	private static MemberDAO memberDAO;

	private MemberDAO() {
		// TODO Auto-generated constructor stub
	}

	public static MemberDAO getInstance() {
		if (memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 회원등록
	public int insertJoin(MemberBean memberbean) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String sql = "";

		try {

			sql = "insert into member (id, name, password, phone, postcode, address1, address2, email)";
			sql += "values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberbean.getId());
			pstmt.setString(2, memberbean.getName());
			pstmt.setString(3, memberbean.getPassword());
			pstmt.setString(4, memberbean.getPhone());
			pstmt.setString(5, memberbean.getPostcode());
			pstmt.setString(6, memberbean.getAddress1());
			pstmt.setString(7, memberbean.getAddress2());
			pstmt.setString(8, memberbean.getEmail());

			insertCount = pstmt.executeUpdate();	//insert 실행

		} catch (Exception ex) {
			System.out.println("회원가입 sql 에러 : " + ex);

		} finally {
			close(pstmt);
		}

		return insertCount;
	}
	
	//로그인
	public boolean ismemberbean(String id,String password) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from member where id=?";
		boolean login = false;
	
		try {
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			rs.next();
			if(password.equals(rs.getString("password"))) {
				login=true;
			}
		}catch (SQLException ex) {
			System.out.println("로그인 에러 : "+ex);
		} finally {
			close(pstmt);
		}
		return login;
	}
	
	//멤버 정보 보기
	public MemberBean selectMember(String id) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean memberBean = null;

		try {
			pstmt = con.prepareStatement("select * from member where id =?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("멤버정보보기 DAO : "+id);
			
			if (rs.next()) {
				memberBean = new MemberBean();
				memberBean.setId(rs.getString("id"));
				memberBean.setPassword(rs.getString("password"));
				memberBean.setName(rs.getString("name"));
				memberBean.setPhone(rs.getString("phone"));
				memberBean.setAddress1(rs.getString("address1"));
				memberBean.setAddress2(rs.getString("address2"));
				memberBean.setPostcode(rs.getString("postcode"));
				memberBean.setEmail(rs.getString("email"));
			}
		} catch (Exception ex) {
			System.out.println("MemberDetail 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberBean;
	}
	
	//회원정보수정
	public int updateMember(MemberBean memberbean) {

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update member set name=?, phone=?, postcode=?, address1=?, address2=?, email=? where id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberbean.getName());
			pstmt.setString(2, memberbean.getPhone());
			pstmt.setString(3, memberbean.getPostcode());
			pstmt.setString(4, memberbean.getAddress1());
			pstmt.setString(5, memberbean.getAddress2());
			pstmt.setString(6, memberbean.getEmail());
			pstmt.setString(7, memberbean.getId());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Modify 에러 " + e);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
/*	//멤버리스트
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement("select count(*) from member");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("MemberListCount 에러: " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<MemberBean> selectArticleList(int page, int limit) {

		ArrayList<MemberBean> articleList = new ArrayList<MemberBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String list_sql = "select * from member";
		
		 * select * from (select rownum rnum,a.* from((select * from member)a )) where
		 * rnum between ? and ?;
		 

		MemberBean member = null;
		
		 * int startrow = (page - 1) * limit; int endrow = startrow+limit-1;
		 

		try {
			pstmt = con.prepareStatement(list_sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new MemberBean();
				member.setId(rs.getString("id"));
				member.setPass(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setAge(rs.getInt("age"));
				member.setGender(rs.getString("gender"));
				member.setEmail(rs.getString("email"));
				articleList.add(member);
			}
		} catch (Exception ex) {
			System.out.println("MemberList 에러 :" + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}*/
	

	/*// 글 삭제
	public int deleteArticle(String id) {
		PreparedStatement pstmt = null;
		String board_delete_sql = "delete from member where id=?";
		int deleteCount = 0;

		try {
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}*/

	/*// 아이디 중복 확인
	public MemberBean checkArticle(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean memberBean = null;

		try {
			pstmt = con.prepareStatement("select * from member where id = ?"); // 값이 있다면 중복값존재, 없다면 id 생성가능
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memberBean = new MemberBean();
				memberBean.setId(rs.getString("id"));
			}

		} catch (Exception ex) {
			System.out.println("MemberDetail 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberBean;
	}없어도 작동 함.*/

}
