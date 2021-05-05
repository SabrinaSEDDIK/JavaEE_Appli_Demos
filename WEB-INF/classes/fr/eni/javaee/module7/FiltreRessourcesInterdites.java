package fr.eni.javaee.module7;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltreRessourcesInterdites
 */
@WebFilter(urlPatterns="/modules/module7/*",
dispatcherTypes= {
		DispatcherType.REQUEST,
		DispatcherType.FORWARD,
		DispatcherType.INCLUDE,
		DispatcherType.ERROR
})
public class FiltreRessourcesInterdites implements Filter {

   
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest= (HttpServletRequest) request;
		if(!httpRequest.getServletPath().toLowerCase().contains("interdit")) {
			chain.doFilter(request, response); //laissons passer la requête vers la ressource qui est autorisée
		}else {
			//renvoyons une 403 à l'utilisateur
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

		// pass the request along the filter chain
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
