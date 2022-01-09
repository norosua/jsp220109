package board;

public class Reply {
	
	private int parentNo;
	private String rBody;
	private String writer;
	private String regDate;

	public Reply(int parentNo, String rBody, String writer, String regDate) {
		super();
		this.parentNo = parentNo;
		this.rBody = rBody;
		this.writer = writer;
		this.regDate = regDate;
	}
	
	public int getParentNo() {
		return parentNo;
	}
	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	public String getrBody() {
		return rBody;
	}
	public void setrBody(String rBody) {
		this.rBody = rBody;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
