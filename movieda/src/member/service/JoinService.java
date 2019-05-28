package member.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;
import java.sql.Connection;
import dao.MemberDAO;
import member.vo.MemberBean;



public class JoinService {

	
	public boolean isJoinWriter(MemberBean memberBean) throws Exception {
		
		boolean isLoginSuccess = false;
		Connection con=getConnection();
		MemberDAO memberDAO= MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int insertCount=memberDAO.insertJoin(memberBean);
		
		if(insertCount >0) {
			commit(con);
			isLoginSuccess=true;
			
		} else {
			rollback(con);
			
		}
		close(con);
		return isLoginSuccess;
	}
}
