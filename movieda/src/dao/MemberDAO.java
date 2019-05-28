package dao;

import static db.JdbcUtil.close;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import member.vo.MemberBean;
import util.SHA256Util;

public class MemberDAO {

	DataSource ds;
	Connection con;
	private static MemberDAO memberDAO;

	private MemberDAO() {
		// TODO Auto-generated constructor stub
	}

	public static MemberDAO getInstance() {
		if (memberDAO == null) {
			memberDAO = new MemberDAO(); //null일때만 생성. 작동하는 객체가 이미 있으면 생성시키지 않음. 한번 생성하면 계속 쓰고, 그다음부터 다시 만들지 않아서 리소스 절약이 가능.
		}
		return memberDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 회원등록
	public int insertJoin(MemberBean memberBean) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String sql = "";
		/*String test="123";
		SHA256Util.testSHA256(test);*/

		try {

			sql = "insert into member (id, name, password, phone, postcode, address1, address2, email)";
			sql += "values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getId());
			pstmt.setString(2, memberBean.getName());
			pstmt.setString(3, SHA256Util.testSHA256(memberBean.getPassword()));	//비밀번호 암호화
			pstmt.setString(4, memberBean.getPhone());
			pstmt.setString(5, memberBean.getPostcode());
			pstmt.setString(6, memberBean.getAddress1());
			pstmt.setString(7, memberBean.getAddress2());
			pstmt.setString(8, memberBean.getEmail());
			
			insertCount = pstmt.executeUpdate();	//insert 실행
			System.out.println(memberBean.getId() +"의 비밀번호 : "+SHA256Util.testSHA256(memberBean.getPassword()));

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
		//	System.out.println("멤버정보보기 DAO : "+id);
			
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
	public int updateMember(MemberBean memberBean) {

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update member set name=?, phone=?, postcode=?, address1=?, address2=?, email=? where id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getName());
			pstmt.setString(2, memberBean.getPhone());
			pstmt.setString(3, memberBean.getPostcode());
			pstmt.setString(4, memberBean.getAddress1());
			pstmt.setString(5, memberBean.getAddress2());
			pstmt.setString(6, memberBean.getEmail());
			pstmt.setString(7, memberBean.getId());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Modify 에러 " + e);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	//비밀번호 변경
	public int updatepass(MemberBean memberBean) {

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update member set password=? where id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getPassword());
			pstmt.setString(2, memberBean.getId());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Pass Modify 에러 " + e);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	//계정 삭제
	public int deleteMember(String id) {
		PreparedStatement pstmt = null;
		String delete_sql = "delete from member where id=?";
		int deleteCount = 0;

		try {
			pstmt = con.prepareStatement(delete_sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	//멤버리스트
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
	//멤버 리스트
	public ArrayList<MemberBean> selectArticleList(int page, int limit) {

		ArrayList<MemberBean> articleList = new ArrayList<MemberBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String list_sql = "select * from member";
		/*
		 * select * from (select rownum rnum,a.* from((select * from member)a )) where
		 * rnum between ? and ?;
		 */

		MemberBean member = null;
		/*
		 * int startrow = (page - 1) * limit; int endrow = startrow+limit-1;
		 */

		try {
			pstmt = con.prepareStatement(list_sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new MemberBean();
				member.setId(rs.getString("id"));
				
				member.setName(rs.getString("name"));
				
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
	}
}
