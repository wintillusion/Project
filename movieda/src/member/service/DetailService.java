package member.service;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.MemberDAO;
import member.vo.MemberBean;

public class DetailService {

	public MemberBean getMemberBean(String id) throws Exception {

		MemberBean memberBean = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);

		memberBean = memberDAO.selectMember(id);
		//System.out.println("Detail service : " + id);
		if (memberBean != null) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return memberBean;

	}

}
