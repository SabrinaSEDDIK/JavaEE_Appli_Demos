package fr.eni.javaee.module4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ServletTestPolConnexion
 */
@WebServlet("/modules/module4/ServletTestPoolConnexion")
public class ServletTestPoolConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			Context context = new InitialContext();
			//Recherche de la DataSource
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
			//Demande une connexion. La méthode getConnection met la dde en attente
			//tant qu'il n'y a pas de connexion dispo dans le pool
			Connection cnx = dataSource.getConnection();
			out.print("La connexion est "+(cnx.isClosed()?"fermée":"ouverte")+".");
			//Libérer la connexion qd on en n'a plus besoin
			cnx.close(); //connexion nn fermée mais remise dans le pool
		
		} catch (NamingException | SQLException ex) {
			ex.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.println("Une erreur est survenue lors de l'utilisation de la base de données : " + ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
