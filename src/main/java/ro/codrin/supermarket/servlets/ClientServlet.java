package ro.codrin.supermarket.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.codrin.supermarket.container.ApplicationContext;
import ro.codrin.supermarket.dao.ClientDao;
import ro.codrin.supermarket.dao.RaionDao;
import ro.codrin.supermarket.entitati.Client;
import ro.codrin.supermarket.entitati.Producator;

/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClientDao dao = ApplicationContext.CLIENT_DAO;
		List<Client> clienti = dao.getClienti();	
		
     	request.setAttribute("clienti", clienti);
		
		// Aici serveltul face un forward al flow call-ului catre JSP
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/clienti.jsp");
		// si ii paseaza requestul si responsul 
		// (pe request se afla si atributul produse)
		requestDispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nume = request.getParameter("nume");
		String prenume = request.getParameter("prenume");
		
		ClientDao dao = new ClientDao();
		dao.adaugaClient(nume,prenume);
		doGet(request, response);
	}

}
