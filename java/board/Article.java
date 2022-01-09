package board;

public class Article {
	
	private int no;
	private String title;
	private int memberIdx;	
	private String writer;
	private String body;
	private String regDate;
	
	public Article(int no, String title, int memberIdx, String writer, String body, String regDate) {
		this.no = no;
		this.title = title;
		this.memberIdx = memberIdx;
		this.writer = writer;
		this.body = body;
		this.regDate = regDate;
	}
	
	public Article(int no, String title, String body, int memberIdx) {
		this.no = no;
		this.title = title;
		this.memberIdx = memberIdx;
		this.body = body;
	}
	
	public Article(String title, String body, int memberIdx) {
		this.title = title;
		this.body = body;
		this.memberIdx = memberIdx;
	}
	
	//접근제어자가 없으면 다른 폴더에서는 사용할 수 없다.

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}


	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
