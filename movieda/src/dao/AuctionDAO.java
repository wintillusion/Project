package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import auction.vo.AuctionBean;
import auction.vo.AuctionInfoBean;
import auction.vo.StockBean;
import request.vo.RequestBean;
public class AuctionDAO {
	
	DataSource ds;
	Connection con;
	private static AuctionDAO auctionDAO;
	
	private AuctionDAO() {
		
	}
	
	public static AuctionDAO getInstance() {	
		if(auctionDAO == null) {
			auctionDAO = new AuctionDAO();	
		}
		return auctionDAO;
	}
	
	public void setConnection(Connection con) {	
		this.con = con;
	}
	
	//경매 글 갯수 구하기
	public int selectListCount(String sO, String sW) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select count(*) from auction ";
		if(!sO.equals("")) {
			sql+="where "+sO+" like '%"+sW+"%' ";
		}
//		System.out.println("글 갯수 : " + sql);
		try {
			
			pstmt = con.prepareStatement(sql);	//전체 글 갯수 구하기
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount=rs.getInt(1); //값이 있으면 넘겨줌
			}
		}catch(Exception ex) {
			System.out.println("getAuctionListCount error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}

		return listCount;	//값이 없으면 0 값을 넘김.
	}
	
	
	//경매글리스트
	public ArrayList<AuctionBean> selectAuctionList(int page, int limit, String sO, String sW){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//String board_list_sql="select * from board order by BOARD_RE_REF desc, BOARD_RE_SEQ asc limit ?, ?";	//limit sql문 확인
													//order by 정렬을 re_ref로 desc(내림차순)정렬, 그다음 seq를 asc(오름차순)로 정렬.
													//최신글이 먼저 보이게하고, seq는 쓰여진 순서대로 보이게 하기 위해.
		
		String auction_list_sql="select * from auction ";
		if(!sO.equals("")) {
			auction_list_sql+="where "+sO+" like '%"+sW+"%' ";
		}
		auction_list_sql+="order by num desc";

		//		System.out.println("글목록보기 쿼리문 : " + board_list_sql);

		ArrayList<AuctionBean> auctionList = new ArrayList<AuctionBean>();
		AuctionBean auction = null;
		
		
		try {
			pstmt = con.prepareStatement(auction_list_sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				auction=new AuctionBean();		//BoardBean 객체를 만들고 ResultSet의 값들을 BoardBean에 값을 넘겨줌.
				auction.setNum(rs.getInt("num"));
				auction.setSubject(rs.getString("subject"));
				auction.setContent(rs.getString("content"));
				auction.setFstmoney(rs.getInt("fstmoney"));
				auction.setInstmoney(rs.getInt("instmoney"));
				auction.setFile(rs.getString("file"));
				auction.setMem_id(rs.getString("mem_id"));
				auction.setEdate(rs.getDate("edate"));
				auction.setNdate(rs.getDate("ndate"));
				auction.setWriter(rs.getString("writer"));
				auctionList.add(auction);
			}
		}catch(Exception ex) {
			System.out.println("getAuctionList error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return auctionList;
	}
	
	//main auctionList image; 진행중인 경매
	public ArrayList<AuctionBean> selectAuctionList(int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String auction_list_sql="select * from auction where auction_code=0";

		ArrayList<AuctionBean> auctionList = new ArrayList<AuctionBean>();
		AuctionBean auction = null;
		
		
		try {
			pstmt = con.prepareStatement(auction_list_sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				auction=new AuctionBean();		//BoardBean 객체를 만들고 ResultSet의 값들을 BoardBean에 값을 넘겨줌.
				auction.setNum(rs.getInt("num"));
				auction.setSubject(rs.getString("subject"));
				auction.setContent(rs.getString("content"));
				auction.setFstmoney(rs.getInt("fstmoney"));
				auction.setInstmoney(rs.getInt("instmoney"));
				auction.setFile(rs.getString("file"));
				auction.setMem_id(rs.getString("mem_id"));
				auction.setEdate(rs.getDate("edate"));
				auction.setNdate(rs.getDate("ndate"));
				auction.setWriter(rs.getString("writer"));
				auctionList.add(auction);
			}
		}catch(Exception ex) {
			System.out.println("getAuctionList error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return auctionList;
	}
	

	
	//경매 등록. 경매등록은 admin 계정만 등록이 가능하도록 action에서 설정해두기.
	public int insertAuction(AuctionBean auction, StockBean stockBean) {			
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int num = 0;
		String sql="";
		String sql2="";
		int insertCount=0;
		
		try {
			pstmt=con.prepareStatement("select max(num) from auction");	//현재보더중에 최대 값이 뭔지 찾는 것.stock의 기본키는 자동으로 auction 기본키와 같아짐.
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) 
				num = rs.getInt(1)+1;	//불러온 첫번째 열의 값+1
			else 
				num=1;	//자료가 하나도 없을 경우엔 1을 주는 것. 처음에만 글등록한 경우에만 1을 주고, 그 다음부턴 if문이 실행되는 것.
			
			sql="insert into auction (num,subject,";
			sql+="ndate, edate, fstmoney, "+"instmoney, content, file, "+"auction_code, mem_id, writer) values(?,?,now(),?,?,?,?,?,?,?,?)";
			sql2="insert into stock(num, product, stock_code, auction_num) value(?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  num);		//경매 번호
			pstmt.setString(2, auction.getSubject());	//경매 타이틀
			pstmt.setDate(3, auction.getEdate());		//종료일
			pstmt.setInt(4, auction.getFstmoney());		//시작가
			pstmt.setInt(5, auction.getFstmoney());	// 현재 입찰 가격(update될 가격)	처음 등록할때는 fstmoney로 올려주기.
			pstmt.setString(6, auction.getContent());	//경매 내용
			pstmt.setString(7, auction.getFile()==null?"":auction.getFile());	//파일이 없을 때 공백으로. +이미지 파일의 경우 detail에도 뜨도록 설정해두기.
			pstmt.setInt(8, auction.getAuction_code());	//경매 상태 코드. 
			pstmt.setString(9, auction.getMem_id());	//현재 입찰자 입찰자 id.
			pstmt.setString(10, auction.getWriter());	//작성자 id
			
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, num);
			pstmt2.setString(2, stockBean.getProduct());
			pstmt2.setInt(3, stockBean.getStock_code());
			pstmt2.setInt(4, num);
			
			insertCount=pstmt.executeUpdate();
			insertCount=pstmt2.executeUpdate();
		}catch(Exception ex){
			System.out.println("auctionInsert error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
			close(pstmt2);
		}
		return insertCount;
	}
	
	
	//경매 내용 보기
	public AuctionBean selectAuction(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctionBean auctionBean = null;
		
		try {
			pstmt = con.prepareStatement("select * from auction where num = ? ");
			pstmt.setInt(1,  num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				auctionBean = new AuctionBean();
				auctionBean.setNum(rs.getInt("num"));
				auctionBean.setSubject(rs.getString("subject"));
				auctionBean.setContent(rs.getString("content"));
				auctionBean.setFile(rs.getString("file"));
				auctionBean.setAuction_code(rs.getInt("auction_code"));
				auctionBean.setFstmoney(rs.getInt("fstmoney"));
				auctionBean.setInstmoney(rs.getInt("instmoney"));
				auctionBean.setMem_id(rs.getString("mem_id"));
				auctionBean.setEdate(rs.getDate("edate"));
				auctionBean.setNdate(rs.getDate("ndate"));
				auctionBean.setWriter(rs.getString("writer"));
				
			}
		}catch(Exception ex) {
			System.out.println("Auction Detail error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return auctionBean;
	}
	
	public StockBean selectStock(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StockBean stockBean = null;
		
		try {
			pstmt = con.prepareStatement("select * from stock where auction_num = ? ");
			pstmt.setInt(1,  num);	//옥션 번호.
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				stockBean = new StockBean();
				stockBean.setNum(rs.getInt("num"));
				stockBean.setProduct(rs.getString("product"));
				stockBean.setAuction_num(rs.getInt("auction_num"));
				
			}
		}catch(Exception ex) {
			System.out.println("stock Detail error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return stockBean;
	}
	
	
	public ArrayList<AuctionInfoBean> auctionInfoList(int num){
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String auction_list_sql="select * from auctioninfo ";
		auction_list_sql+="where order_num=? order by ndate desc";

		ArrayList<AuctionInfoBean> auctionInfoList = new ArrayList<AuctionInfoBean>();
		AuctionInfoBean auctioninfo = null;
		
		
		try {
			pstmt = con.prepareStatement(auction_list_sql);
			pstmt.setInt(1,  num);	//옥션 번호.
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				auctioninfo=new AuctionInfoBean();		
				auctioninfo.setNum(rs.getInt("num"));
				auctioninfo.setMem_id(rs.getString("mem_id"));
				auctioninfo.setMymoney(rs.getInt("mymoney"));
				auctioninfo.setOrder_num(rs.getInt("order_num"));
				auctionInfoList.add(auctioninfo);
			}
		}catch(Exception ex) {
			System.out.println("getAuctioninfoList error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return auctionInfoList;
	}
	


	//경매글의 입찰자 내용, 입찰가격 갱신, 경매인포등록
	public int updateAuction(AuctionBean auctionBean, AuctionInfoBean auctioninfo) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int num = 0;
		ResultSet rs = null;
		String sql="update auction set mem_id=?, instmoney=? where num=?";
		
		
		String sql2="";
		sql2="insert into auctioninfo (num, order_num,";
		sql2+="mymoney, mem_id, ndate) values(?,?,?,?,now())";
		try {
			
			pstmt2=con.prepareStatement("select max(num) from auctioninfo");
			rs=pstmt2.executeQuery();
			
			if(rs.next()) 
				num = rs.getInt(1)+1;	//불러온 첫번째 열의 값+1
			else
				num=1;	//자료가 하나도 없을 경우엔 1을 주는 것. 처음에만 글등록한 경우에만 1을 주고, 그 다음부턴 if문이 실행되는 것.
			
			
			pstmt = con.prepareStatement(sql);
			pstmt2 = con.prepareStatement(sql2);
			pstmt.setString(1, auctionBean.getMem_id());
			pstmt.setInt(2, auctionBean.getInstmoney());
			pstmt.setInt(3, auctionBean.getNum());
			pstmt2.setInt(1,  num);		//경매 번호
			pstmt2.setInt(2, auctioninfo.getOrder_num());	//경매 번호
			pstmt2.setInt(3, auctioninfo.getMymoney());		//종료일
			pstmt2.setString(4, auctioninfo.getMem_id());		//시작가
			updateCount = pstmt.executeUpdate();
			updateCount += pstmt2.executeUpdate();
		}catch(Exception e) {
			System.out.println("auctionUpdate error :  " + e);
		}finally {
			close(pstmt);
			close(pstmt2);
		}
		return updateCount;
	}
	

	public int selectListCount() {		//auctionInfo 정보 다 가져오기. 이건 admin에 적용해서 다 볼수있도록 할 것.
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = con.prepareStatement("select count(*) from auctioninfo");	//본인 옥션 정보가져오기.
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount=rs.getInt(1); //값이 있으면 넘겨줌
			}
		}catch(Exception ex) {
			System.out.println("getListCount 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}

		return listCount;	//값이 없으면 0 값을 넘김.
	}
	//본인 경매이력보기.
	public ArrayList<AuctionInfoBean> memberAuctionList(int page, int limit, String id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String auction_list_sql="select * from auctioninfo where mem_id=?";
													//본인이 넣은 가장 높은 금액만 보이도록.
		//select max(mymoney), num, order_id, mymoney, order_num, auction_code from auctioninfo where order_id=?
		ArrayList<AuctionInfoBean> auctionInfoList = new ArrayList<AuctionInfoBean>();
		AuctionInfoBean auctionInfo = null;
		
		try {
			pstmt = con.prepareStatement(auction_list_sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				auctionInfo=new AuctionInfoBean();		//BoardBean 객체를 만들고 ResultSet의 값들을 BoardBean에 값을 넘겨줌.
				auctionInfo.setNum(rs.getInt("num"));
				auctionInfo.setMem_id(rs.getString("mem_id"));
				auctionInfo.setMymoney(rs.getInt("mymoney"));
				auctionInfo.setOrder_num(rs.getInt("order_num"));
				
				auctionInfoList.add(auctionInfo);
			}
		}catch(Exception ex) {
			System.out.println("getMyAuctionInfo error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return auctionInfoList;
	}
	
	//경매 종료
	public int endAuction(AuctionBean auctionBean) {
		PreparedStatement pstmt = null;
	
		String sql="";
		int insertCount=0;
		
		try {
			sql="update auction set instmoney=?, mem_id=?, auction_code=? where num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, auctionBean.getInstmoney());
			pstmt.setString(2, auctionBean.getMem_id());
			pstmt.setInt(3, auctionBean.getAuction_code());
			pstmt.setInt(4, auctionBean.getNum());
			
			insertCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("경매종료info업데이트 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	//옥션 삭제
	public int deleteAuction(int num) {
		PreparedStatement pstmt = null;
		String auction_delete_sql ="delete from auction where num=?";
		int deleteCount=0;
		
		try {
			pstmt=con.prepareStatement(auction_delete_sql);
			pstmt.setInt(1, num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("auction delete error : " + ex);
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}
		
	public ArrayList<AuctionBean> EndAuctionList(int page, int limit, String id){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from auction where mem_id=? and auction_code=1 order by num desc";
												
		ArrayList<AuctionBean> auctionEndList = new ArrayList<AuctionBean>();
		AuctionBean auction = null;
		
		
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				auction = new AuctionBean();
				auction.setNum(rs.getInt("num"));
				auction.setSubject(rs.getString("subject"));
				auction.setContent(rs.getString("content"));
				auction.setFstmoney(rs.getInt("fstmoney"));
				auction.setInstmoney(rs.getInt("instmoney"));
				auction.setFile(rs.getString("file"));
				auction.setMem_id(rs.getString("mem_id"));
				auction.setEdate(rs.getDate("edate"));
				auction.setNdate(rs.getDate("ndate"));
				auction.setWriter(rs.getString("writer"));
				auctionEndList.add(auction);
			}
		}catch(Exception ex) {
			System.out.println("getBoardList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return auctionEndList;
	}
	//입금
	public int depositAuction(AuctionBean auctionBean) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update auction set auction_code=? where num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, auctionBean.getAuction_code());
			pstmt.setInt(2, auctionBean.getNum());
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("auctionDeposit error :  " + e);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public int depositStock(StockBean stockBean) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update stock set stock_code=?, auction_num=? where num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, stockBean.getStock_code());
			pstmt.setInt(2, stockBean.getAuction_num());
			pstmt.setInt(3, stockBean.getNum());
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("stock error :  " + e);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public int modifyAuction(AuctionBean auctionBean) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update auction set subject=?, content=?, edate=?, fstmoney=?, instmoney=?, mem_id=?, writer=? where num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auctionBean.getSubject());
			pstmt.setString(2, auctionBean.getContent());
			pstmt.setDate(3, auctionBean.getEdate());
			pstmt.setInt(4, auctionBean.getFstmoney());
			pstmt.setInt(5, auctionBean.getInstmoney());
			pstmt.setString(6, auctionBean.getMem_id());
			pstmt.setString(7, auctionBean.getWriter());
			pstmt.setInt(8, auctionBean.getNum());
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("auctionModify error :  " + e);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	
	public int modifyStock(StockBean stockBean) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update stock set product=? where auction_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stockBean.getProduct());
			pstmt.setInt(2, stockBean.getAuction_num());
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("stockModify error :  " + e);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	

}
