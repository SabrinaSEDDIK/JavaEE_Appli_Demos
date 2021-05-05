package fr.eni.javaee.module3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDelegantResponseAUneJSP
 */
@WebServlet("/modules/module3/ServletDelegantResponseAUneJSP")
public class ServletDelegantResponseAUneJSP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		//Première manière d'aller vers la JSP
		//rd = request.getRequestDispatcher("/WEB-INF/modules/module3/demonstrations/premiereJSPDansWEB-INF.jsp");
		
		//Deuxième manière (on pourrait aussi utiliser la même URL qu'au-dessus)
		//rd = this.getServletContext().getRequestDispatcher("/modules/module3/demonstrations/secondePremiereJSP");
		
		//Troisième manière
		rd = this.getServletContext().getNamedDispatcher("PremiereJSPDansWEB_INF");
		
		//Dans les 3 cas, terminer par :
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
