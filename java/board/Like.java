package board;

public class Like {

	private String userId;
	private int articleNo;
	private String regDate;
	
	public Like(String userId, int articleNo, String regDate) {
		super();
		this.userId = userId;
		this.articleNo = articleNo;
		this.regDate = regDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
}
