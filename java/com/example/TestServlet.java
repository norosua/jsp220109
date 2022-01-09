package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
//		System.out.println("hello!!!!");
		
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=utf-8");
		
		
		
		
		
		
		
		
		//브라우저로 출력하기
//		Scanner sc = new Scanner(System.in);
		String dan = request.getParameter("dan");
		String limit = request.getParameter("limit");
		
		int ndan = Integer.parseInt(dan);
		

		int nlimit = Integer.parseInt(limit);
		
		PrintWriter out = response.getWriter();
		for(int i = 1; i <= nlimit; i++) {

			out.println(ndan + " X " + i + " = " + ndan * i + "<br>");
						
		}

		PrintWriter in = response.getWriter();
		in.println("시발");
		
	}

}
