package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.Article;
import board.model.SqlMapper;


@WebServlet("/article")
public class ArticleServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			

			SqlMapper mapper = new SqlMapper();
			
			String action = request.getParameter("action");
			
			if(action.equals("list")) {
			 	ArrayList<Article> articles = mapper.getArticleList();
			 	request.setAttribute("articles", articles);
			 	
			 	// JSP에 데이터 보내기
			 	
			 	RequestDispatcher rd = request.getRequestDispatcher("board/list.jsp");
			 	rd.forward(request, response);

			}
			if(action.equals("add")) {
				RequestDispatcher rd = request.getRequestDispatcher("board/addForm.jsp");
			 	rd.forward(request, response);			
			}
			else if(action.equals("doAdd")) {
				
				String title = request.getParameter("title");
				String body = request.getParameter("body");
				
				Article a = new Article(title, body,1);
				
				mapper.insertArticle(a);

//			 	ArrayList<Article> articles = mapper.getArticleList();
//			 	request.setAttribute("articles", articles);
//				
//			 	RequestDispatcher rd = request.getRequestDispatcher("board/list.jsp");
//			 	rd.forward(request, response);
				
				response.sendRedirect("/article?action=list");
			}
			else if(action.equals("detail")) {
				
				int idx = Integer.parseInt(request.getParameter("idx"));
			
				Article article = mapper.getArticleById(idx);
				
				request.setAttribute("article", article);
				
				RequestDispatcher rd = request.getRequestDispatcher("board/detail.jsp");
			 	rd.forward(request, response);
			}
			else if(action.equals("doDelete")) {

				int idx = Integer.parseInt(request.getParameter("idx"));
				
				mapper.deleteArticle(idx);
				
				response.sendRedirect("/article?action=list");
				
			}
			
			else if(action.equals("update")) {
				
				int idx = Integer.parseInt(request.getParameter("idx"));
				Article article = mapper.getArticleById(idx);
				request.setAttribute("article", article);
				
				RequestDispatcher rd = request.getRequestDispatcher("board/updateForm.jsp");
			 	rd.forward(request, response);
			}
			
			else if(action.equals("doUpdate")) {
				
				int idx = Integer.parseInt(request.getParameter("idx"));				
				String title = request.getParameter("title");
				String body = request.getParameter("body");
				
				Article a = new Article(idx, title, body,1);
				
				mapper.updateArticle(a);
				
				response.sendRedirect("/article?action=detail&idx=" + idx);
			}
		 	
	}
}