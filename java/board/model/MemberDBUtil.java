package board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import board.member.Member;

public class MemberDBUtil {
	
	public void updateData(String sql) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			
			conn = getConnection();
			stmt = conn.createStatement();
			System.out.println("sql : " + sql);
			stmt.executeUpdate(sql);
			close(conn, stmt);
			
		} catch (Exception e) {
			System.out.println("접속 시도중 문제 발생!!");
			close(conn, stmt);
		}
	}
	
	public Member getData(String sql) {
		ArrayList<Member> memberList = getDataList(sql);
		if(memberList.size() > 0) {
			return memberList.get(0);	
		}
		return null;
	}
	
	public ArrayList<Member> getDataList(String sql) {
		
		ArrayList<Member> memberList = new ArrayList<>();

		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			stmt = conn.createStatement();
			System.out.println("sql : " + sql);
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				int idx = rs.getInt("idx");
				String title = rs.getString("loginId");
				String body = rs.getString("loginPw");
				String writer = rs.getString("nickname");
				String regDate = rs.getString("regDate");
				
				Member m = new Member(idx, title, writer, body, regDate);				
				memberList.add(m);
			}
			close(conn, stmt, rs);
				
	} catch (Exception e) {
		System.out.println("접속 시도중 문제 발생!!");
		close(conn, stmt, rs);
	}
		
		return memberList;
}
		
		
		
	
//	public Article getArticleById(int id) {
//		
//		Article article = null;
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		
//		//11월 28일차 수업
//		//자바명령어나 기능키로 사용하지 않는 특수문자는 2번 중복으로 입력하면 문자로 기능
//		//
//		
//		try {
//			// %s = String
//			// %d = int
//
//			conn = getConnection();
//			stmt = conn.createStatement();
//			String sql = "SELECT * FROM article WHERE idx = " + id;
//			
//			System.out.println("sql : " + sql);
//			rs = stmt.executeQuery(sql);
//			
//			while(rs.next()) { // 다음 로우로 이동 다음이 있으면 true 반환, 없으면 false 반환
//				
//				int idx = rs.getInt("idx");
//				String title = rs.getString("title");
//				String writer = rs.getString("nickname");
//				String body = rs.getString("body");
//				String regDate = rs.getString("regDate");
//				//날짜계산이 필요한 경우에는 스트링이 아닌 데이터로 받을것.
//				
//				article = new Article(idx, title, writer, body, regDate);
//			}
//						
//
//			close(conn, stmt, rs);
//			
//			
//		} catch (Exception e) {
//			System.out.println("접속 시도중 문제 발생!!");
//			close(conn, stmt, rs);
//		}
//		
//		return article;
//	}
	
//	public ArrayList<Article> getArticleList() {
//		
//		ArrayList<Article> articleList = new ArrayList<>();
//		
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		
//		try {
//
//			conn = getConnection();
//			stmt = conn.createStatement();
//			String sql = "SELECT * FROM article";
//			
//			System.out.println("sql : " + sql);
//			rs = stmt.executeQuery(sql);
//			
//			while(rs.next()) { // 다음 로우로 이동 다음이 있으면 true 반환, 없으면 false 반환
//				
//				int idx = rs.getInt("idx");
//				String title = rs.getString("title");
//				String body = rs.getString("body");
//				String writer = rs.getString("nickname");
//				String regDate = rs.getString("regDate");
//				//날짜계산이 필요한 경우에는 스트링이 아닌 데이터로 받을것.
//				
//				Article a = new Article(idx, title, body, writer, regDate);				
//				articleList.add(a);
//			}
//						
//
//			close(conn, stmt, rs);
//			
//			
//		} catch (Exception e) {
//			System.out.println("접속 시도중 문제 발생!!");
//			close(conn, stmt, rs);
//		}
//		
//		return articleList;
//	}
	
//	public void updateArticle(Article a) {
//		
//		Connection conn = null;
//		Statement stmt = null;
//
//		try {
//
//			conn = getConnection();
//			stmt = conn.createStatement();
//
//			//String sql = "UPDATE article SET title = '" + a.getTitle() + "' , `body` = '" + a. getBody()+ "' WHERE idx = " + a.getNo();
//
//			String sqlOrigin = """
//					
//					UPDATE article
//					SET title = '%s' ,
//					`body` = '%s'
//					WHERE idx = %d
//					
//					""";
//
//			String sql = String.format(sqlOrigin, a.getTitle(), a.getBody(), a.getNo());
//			
//			System.out.println("sql : " + sql);
//			stmt.executeUpdate(sql);
//
//			close(conn, stmt);
//
//		} catch (Exception e) {
//			System.out.println("접속 시도중 문제 발생!!");
//			close(conn, stmt); 
//		}
//	}

//	public void insertArticle(Article a) {
//		
//
//		Connection conn = null;
//		Statement stmt = null;
//
//		try {
//
//			conn = getConnection();
//			stmt = conn.createStatement();
//
//			//String sql = "INSERT INTO article SET title = '" + a.getTitle() +"', `body` = '" + a.getBody() + "', nickname = '" + a.getWriter() + "', regDate = '" + a.getRegDate() + "'";
//			String sqlOrigin = """
//					INSERT INTO article 
//					SET title = '%s', 
//					`body` = '%s', 
//					nickname = '%s', 
//					regDate = '%s'
//					""";
//			String sql = String.format(sqlOrigin, a.getTitle(), a.getBody(), a.getWriter(), a.getRegDate());
//					
//			System.out.println("sql : " + sql);
//			stmt.executeUpdate(sql);
//
//			close(conn, stmt);
//
//		} catch (Exception e) {
//			System.out.println("접속 시도중 문제 발생!!");
//			close(conn, stmt);
//		}
//	}
	
	
	private void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs) {
		
		try {
			
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			
		}
		
		} catch (Exception e) {
			System.out.println("자원을 해제하는 중 문제 발생");
		}
		
}
	
		
	
	
	
	
	

	public static Connection getConnection() {

		String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
		String user = "root";
		String pass = "";

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("접속 시도중 문제 발생!!");
		}

		return conn;
}

	
		
	
}
