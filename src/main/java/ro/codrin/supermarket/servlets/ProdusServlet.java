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
import ro.codrin.supermarket.dao.ProdusDao;
import ro.codrin.supermarket.dao.RaionDao;
import ro.codrin.supermarket.dao.TipDao;
import ro.codrin.supermarket.entitati.Producator;
import ro.codrin.supermarket.entitati.Produs;
import ro.codrin.supermarket.entitati.Raion;
import ro.codrin.supermarket.entitati.Tip;

public class ProdusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdusDao produsDao = ApplicationContext.PRODUS_DAO;
		List<Produs> produse = produsDao.getProduse(null);	
		request.setAttribute("produse", produse);
		
		TipDao tipDao = ApplicationContext.TIP_DAO;
		List<Tip> tipuri = tipDao.getTipuri();	
		System.out.println(tipuri);
		request.setAttribute("tipuri", tipuri);
		
		ProducatorDao producatorDao = ApplicationContext.PRODUCATOR_DAO;
		List<Producator> producatori = producatorDao.getProducatori();
		System.out.println(tipuri);
		request.setAttribute("producatori", producatori);
		
		
		// Aici serveltul face un forward al flow call-ului catre JSP
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/produse.jsp");
		// si ii paseaza requestul si responsul 
		// (pe request se afla si atributul produse)
		requestDispatcher.forward(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String denumireProdus = request.getParameter("denumire");
		String pretProdus = request.getParameter("pret");
		String stocProdus = request.getParameter("stoc");
		String tipId = request.getParameter("tipId");
		String producatorId = request.getParameter("producatorId");
		ProdusDao dao = new ProdusDao();
		dao.adaugaProdus(denumireProdus,pretProdus,stocProdus,tipId,producatorId);
		
		doGet(request, response);
	}

}
