package ro.codrin.supermarket.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.codrin.supermarket.container.ApplicationContext;
import ro.codrin.supermarket.dao.ProducatorDao;
import ro.codrin.supermarket.dao.RaionDao;
import ro.codrin.supermarket.entitati.Raion;

/**
 * Servlet implementation class RaionServlet
 */
public class RaionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RaionDao dao = ApplicationContext.RAION_DAO;
		List<Raion> raioane = dao.getRaioane();	
		
		request.setAttribute("raioane", raioane);
		
		// Aici serveltul face un forward al flow call-ului catre JSP
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/raioane.jsp");
		// si ii paseaza requestul si responsul 
		// (pe request se afla si atributul produse)
		requestDispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String denumire = request.getParameter("denumire");
		
		RaionDao dao = ApplicationContext.RAION_DAO;
		dao.adaugaRaion(denumire);
		
		doGet(request, response);
	}
}
