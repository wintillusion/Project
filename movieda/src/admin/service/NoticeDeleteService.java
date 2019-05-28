package admin.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeDAO;

public class NoticeDeleteService {

	
	public boolean removeNotice(int num) throws Exception{
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int deleteCount = noticeDAO.deleteNotice(num);
		
		if(deleteCount>0) {
			commit(con);
			isRemoveSuccess=true;
		}else {
			rollback(con);
		}
		close(con);
		return isRemoveSuccess;
	}

}
