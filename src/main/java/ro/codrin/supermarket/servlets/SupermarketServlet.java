package ro.codrin.supermarket.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.codrin.supermarket.container.ApplicationContext;
import ro.codrin.supermarket.dao.RaionDao;
import ro.codrin.supermarket.dao.SupermarketDao;
import ro.codrin.supermarket.entitati.Supermarket;

/**
 * Servlet implementation class SupermarketServlet
 */
public class SupermarketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SupermarketDao dao = ApplicationContext.SUPERMARKET_DAO;
		List<Supermarket> supermarkete = dao.getSupermarkete(); 
		
		request.setAttribute("supermarkete", supermarkete);
		
		// Aici serveltul face un forward al flow call-ului catre JSP
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/supermarkete.jsp");
		// si ii paseaza requestul si responsul 
		// (pe request se afla si atributul produse)
		requestDispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String locatie = request.getParameter("locatie");
		
		SupermarketDao dao = ApplicationContext.SUPERMARKET_DAO;
		dao.adaugaLocatie(locatie);

		doGet(request, response);
	}

}
