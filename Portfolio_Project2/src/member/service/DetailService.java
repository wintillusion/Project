package member.service;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.MemberDAO;
import member.vo.MemberBean;

public class DetailService {

	public MemberBean getArticle(String id) throws Exception {

		MemberBean article = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);

		article = memberDAO.selectMember(id);

		if (article != null) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return article;

	}

}
