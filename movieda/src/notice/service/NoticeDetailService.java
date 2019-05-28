package notice.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeDAO;
import notice.vo.NoticeBean;

public class NoticeDetailService {

	public NoticeBean getArticle(int num) throws Exception {

		NoticeBean noticeBean = null;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int updateCount = noticeDAO.updateReadCount(num);

		if (updateCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		noticeBean = noticeDAO.selectNotice(num);
		close(con);
		return noticeBean;
	}

}
