package board.model;

import java.util.ArrayList;

import board.Article;
import board.member.Member;

public class SqlMapper {

	ArticleDBUtil articleDB = new ArticleDBUtil();
	MemberDBUtil memberDB = new MemberDBUtil();
	
	public ArrayList<Article> getSearchedList(String keyword) {
		String sql = """
				
				SELECT *
				FROM article
				WHERE title LIKE '%%%s%%'
				
				""";
		
		sql = String.format(sql, keyword);
		return articleDB.getDataList(sql);
	}
	
	public ArrayList<Article> getArticleList() {
		String sql = """
				SELECT *
				FROM article
				""";
		ArrayList<Article> articleList = articleDB.getDataList(sql);
		return articleList;
	}
	
	public Article getArticleById(int id) {
		String sql = """
				
				SELECT *
				FROM article
				WHERE idx = %d
				
				""";
		
		sql = String.format(sql, id);
		return articleDB.getData(sql);
		
	}
	
	public void updateArticle(Article a) {
		String sql = """
				
				UPDATE article
				SET title = '%s',
				`body` = '%s'
				WHERE idx = %d
				
				""";
		
		sql = String.format(sql, a.getTitle(), a.getBody(), a.getNo());
		articleDB.updateData(sql);
	}
	
	public void insertArticle(Article a) {
		String sql = """
				
				INSERT INTO article
				SET title = '%s',
				`body` = '%s',
				nickname = '%s',
				regDate = NOW()
				
				""";
		
		sql = String.format(sql, a.getTitle(), a.getBody(), a.getWriter());
		articleDB.updateData(sql);
		
	}
	
	public void deleteArticle(int id) {
		String sql = """
				
				DELETE
				FROM article
				WHERE idx = %d
				
				""";
		
		sql = String.format(sql, id);
		articleDB.updateData(sql);
	}
	
	public void insertMember(Member m) {
		String sql = """
				
				INSERT INTO `member`
				SET loginID = '%s',
				loginPw = '%s',
				nickname = '%s',
				regDate = NOW()
				
				""";
		sql = String.format(sql,  m.getLoginId(), m.getLoginPw(), m.getNickname());
		articleDB.updateData(sql);				
	}
	
	public Member getMemberLoginIdAndLoginPw(String loginId, String loginPw) {
		String sql = """
				
				SELECT *
				FROM `member`
				WHERE loginId = '%s'
				AND loginPw = '%s'
				
				""";
		
		sql = String.format(sql, loginId, loginPw);
		return memberDB.getData(sql);
		
	}
}
