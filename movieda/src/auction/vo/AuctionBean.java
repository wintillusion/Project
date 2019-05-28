package auction.vo;

import java.sql.Date;

public class AuctionBean {
	private int num;
	private String subject;
	private Date ndate;
	private Date edate;
	private int fstmoney;
	private int instmoney;
	private String content;
	private String file;
	private int auction_code;
	private String mem_id;
	private String writer;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getNdate() {
		return ndate;
	}
	public void setNdate(Date ndate) {
		this.ndate = ndate;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public int getFstmoney() {
		return fstmoney;
	}
	public void setFstmoney(int fstmoney) {
		this.fstmoney = fstmoney;
	}
	public int getInstmoney() {
		return instmoney;
	}
	public void setInstmoney(int instmoney) {
		this.instmoney = instmoney;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getAuction_code() {
		return auction_code;
	}
	public void setAuction_code(int auction_code) {
		this.auction_code = auction_code;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	

}
