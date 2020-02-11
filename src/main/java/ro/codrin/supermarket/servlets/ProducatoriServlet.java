package ro.codrin.supermarket.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.codrin.supermarket.container.ApplicationContext;
import ro.codrin.supermarket.dao.ProducatorDao;
import ro.codrin.supermarket.entitati.Producator;

public class ProducatoriServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		ProducatorDao dao = ApplicationContext.PRODUCATOR_DAO;
		List<Producator> producatori = dao.getProducatori();	
		
		request.setAttribute("producatori", producatori);
		
		// Aici serveltul face un forward al flow call-ului catre JSP
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/producatori.jsp");
		// si ii paseaza requestul si responsul 
		// (pe request se afla si atributul produse)
		requestDispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String denumire = request.getParameter("denumire");
		String adresa  = request.getParameter("adresa");
		
		ProducatorDao dao = ApplicationContext.PRODUCATOR_DAO;
		dao.adaugaProducator(denumire, adresa);
		
		doGet(request, response);
		
	}

}
