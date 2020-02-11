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
import ro.codrin.supermarket.dao.TipDao;
import ro.codrin.supermarket.entitati.Raion;
import ro.codrin.supermarket.entitati.Tip;

/**
 * Servlet implementation class TipuriServlet
 */
public class TipuriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TipDao tipDao = ApplicationContext.TIP_DAO;
		List<Tip> tipuri = tipDao.getTipuri();	
		request.setAttribute("tipuri", tipuri);
		
		RaionDao raionDao = ApplicationContext.RAION_DAO;
		List<Raion> raioane = raionDao.getRaioane();	
		System.out.println(raioane);
		request.setAttribute("raioane", raioane);
		
		// Aici serveltul face un forward al flow call-ului catre JSP
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/tipuri.jsp");
		// si ii paseaza requestul si responsul 
		// (pe request se afla si atributul produse)
		requestDispatcher.forward(request, response);	
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String denumireTip = request.getParameter("denumire");
		String raionId = request.getParameter("raionId");
		TipDao dao = ApplicationContext.TIP_DAO;
		dao.adaugaTip(denumireTip, raionId);
		
		doGet(request, response);
	}

}
