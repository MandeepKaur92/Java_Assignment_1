package com.kn.pp.mj.week3.assignment1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			String n = request.getParameter("username");
			String p = request.getParameter("userpass");
			out.print("Hello!  " + n + "  ");

			HttpSession session = request.getSession();
			session.setAttribute("uname", n);

			// check validation of password means enter password by user is correct or not
			if (p.equals("s") && n.equals("mandeep")) {
				Cookie ck = new Cookie("uname", n);// creating cookie object
				response.addCookie(ck);// adding cookie in the response
				out.print(ck.getValue());

				RequestDispatcher rd = request.getRequestDispatcher("UserServlet");
				rd.forward(request, response);

			} else {

				out.print("Sorry UserName or Password Error!"); // if password is not correct then this message show
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp"); // here request is send for page
																					// login.jsp
				rd.include(request, response); // here the content of a resource is include in the response.

			}

			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
