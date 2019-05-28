package admin.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeDAO;
import notice.vo.NoticeBean;


public class NoticeWriteProService {

	public boolean registNotice(NoticeBean noticeBean) throws Exception {

		boolean isWriteSuccess = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int insertCount = noticeDAO.insertNotice(noticeBean);

		if (insertCount > 0) { // dao에서 넘겨준 값이 0보다 크면 true로.
			commit(con);
			isWriteSuccess = true;
		} else {
			rollback(con);
		}

		close(con);
		return isWriteSuccess;
	}
}
