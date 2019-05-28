package admin.service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeDAO;
import notice.vo.NoticeBean;

public class NoticeModifyProService {
	public boolean modifyArticle(NoticeBean noticeBean) throws Exception {

		boolean isModifySuccess = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int updateCount = noticeDAO.modifyNotice(noticeBean);

		if (updateCount > 0) {
			commit(con);
			isModifySuccess = true; // 안넣어주면 수정실패라 뜨고 수정은 되는 기이한 현상이 발생하게 된다.
		} else {
			rollback(con);
		}

		close(con);
		return isModifySuccess;
	}
}
