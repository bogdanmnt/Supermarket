package ro.codrin.supermarket.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import ro.codrin.supermarket.container.ApplicationContext;
import ro.codrin.supermarket.dao.ProducatorDao;
import ro.codrin.supermarket.dao.ProdusDao;
import ro.codrin.supermarket.dao.TipDao;
import ro.codrin.supermarket.entitati.Producator;
import ro.codrin.supermarket.entitati.Produs;
import ro.codrin.supermarket.entitati.Tip;

/**
 * Servlet implementation class CosCumparaturiServlet
 */
public class CosCumparaturiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	// afiseaza continutul cosului de cumparaturi
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Integer> cos = (ArrayList<Integer>)session.getAttribute("cosCumparaturi");
		request.setAttribute("cosJsp", cos);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/cos.jsp");
		// si ii paseaza requestul si responsul 
		// (pe request se afla si atributul produse)
		requestDispatcher.forward(request, response);
	}

	// adauga produs in cos
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// obtinem parametrul idProdusParam de pe request
		Integer id = Integer.parseInt(request.getParameter("idProdusParam"));
		
		// obtinem ArrayListul CosDeCumparaturi de pe Sesiune
		// Daca nu e creat, il cream acum
		HttpSession session = request.getSession();
		List<Integer> cos = (ArrayList<Integer>)session.getAttribute("cosCumparaturi");
		if (cos == null) {
			cos = new ArrayList<Integer>();
		}
		
		// Verificam stocul. Daca e mai mare decat 1, 
		// adaugam produsul (id-ul) in arrayList
		ProdusDao produsDao = ApplicationContext.PRODUS_DAO;
		if (produsDao.verifyThatStocIsGreaterThanZero(id)) {
			cos.add(id);
			// Reactualizam Cosul de cumparaturi pe Sesiune
			session.setAttribute("cosCumparaturi", cos);

			// Afisam un mesaj cum ca produsul a fost adaugat in cos.
			// Sau ca produsul nu e pe stoc
			request.setAttribute("adaugat", "Produsul cu id " + id + " a fost adaugat in cos");
		} else {
			request.setAttribute("neadaugat", "Produsul cu id " + id + " nu este pe stoc");
		}
		
		List<Produs> produse = produsDao.getProduse(null);	
		request.setAttribute("produse", produse);
		
		TipDao tipDao = ApplicationContext.TIP_DAO;
		List<Tip> tipuri = tipDao.getTipuri();	
		System.out.println(tipuri);
		request.setAttribute("tipuri", tipuri);
		
		ProducatorDao producatorDao = ApplicationContext.PRODUCATOR_DAO;
		List<Producator> producatori = producatorDao.getProducatori();
		System.out.println(producatori);
		request.setAttribute("producatori", producatori);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/produse.jsp");
		// si ii paseaza requestul si responsul 
		// (pe request se afla si atributul produse)
		requestDispatcher.forward(request, response);
		
		
		// In momentul in care un user intra pe un site nou, 
		// automat se creaza o sesiune pentru el. Cand serverul returneaza pagina
		// ii adauga un cookie (String) in browser.
		// Cand userul navigheaza pe o alta pagina, 
		// la fiecare request browserul va trimite si cookie-ul specific site-ului
		// In acest mod, serverul il recunoaste pe user dupa cookie.
		
		// Sesiunea este unica pentru fiecare user.
		// request.getSession()  va returna mereu acelasi obiect sesiune pentru fiecare user
		
		// Serverele web sunt STATELESS. (adica nu STATEFUL)
		// STATELESS: nu le intereseaza cine le apeleaza. Nu tin minte.
		// Adica nu se pastreaza o conexiune deschisa pentru fiecare request.
		// Serverele web primesc un request si raspund cu o pagina (un response)
		// Daca s-ar pastra conexiunile deschise cu toti userii, serverele ar ramane fara memorie.
		
		// Serverele recunosc userul dupa cookie, nu tin conexiunea deschisa.
		
		
	}

}
