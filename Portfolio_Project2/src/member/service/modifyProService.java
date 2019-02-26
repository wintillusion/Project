package member.service;
import static db.JdbcUtil.*;
import java.sql.Connection;
import member.vo.MemberBean;
import dao.MemberDAO;

public class modifyProService {
	
	public boolean modifyArticle(MemberBean memberBean) throws Exception{
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int updateCount = memberDAO.updateMember(memberBean);
		
		if(updateCount >0) {
			commit(con);
			isModifySuccess=true;	//안넣어주면 수정실패라 뜨고 수정은 되는 기이한 현상이 발생하게 된다.
		}
		else {
			rollback(con);
		}
		
		close(con);
		return isModifySuccess;
	}

}
