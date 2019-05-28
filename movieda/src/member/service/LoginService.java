package member.service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.MemberDAO;
import member.vo.MemberBean;

public class LoginService {


public boolean isMemberLogin(String id,String password) throws Exception {
	
		boolean isLoginUser=false;
		Connection con=getConnection();
		MemberDAO memberDAO=MemberDAO.getInstance();
		memberDAO.setConnection(con);
		isLoginUser = memberDAO.ismemberbean(id, password);
		close(con);
		return isLoginUser;
	}
}
