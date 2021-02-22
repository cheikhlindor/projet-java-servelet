package fr.eni.encheresprojetjls.servlets;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletDeconnection
 */
@WebServlet("/ServletDeconnection")
public class ServletDeconnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// fermeture de la session et retour en mode non connecté
		HttpSession session = request.getSession();
		session.invalidate();

		response.sendRedirect("ServletPageEncheres1");
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/Page_Encheres1.jsp");
//		rd.forward(request, response);
//	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		doGet(request, response);
	}

}
