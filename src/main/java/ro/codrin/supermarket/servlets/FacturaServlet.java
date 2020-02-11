package ro.codrin.supermarket.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.codrin.supermarket.container.ApplicationContext;
import ro.codrin.supermarket.dao.FacturaDao;
import ro.codrin.supermarket.dao.ProdusDao;
import ro.codrin.supermarket.dao.SupermarketDao;
import ro.codrin.supermarket.dao.VanzareDao;
import ro.codrin.supermarket.dto.ProdusDePeFactura;
import ro.codrin.supermarket.entitati.Factura;
import ro.codrin.supermarket.entitati.Produs;
import ro.codrin.supermarket.entitati.Supermarket;
import ro.codrin.supermarket.util.Utils;

/**
 * Servlet implementation class FacturaServlet
 */
public class FacturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		FacturaDao facturaDao = ApplicationContext.FACTURA_DAO;
		List<Factura> facturi = facturaDao.getFacturi();	
		request.setAttribute("facturi", facturi);
		
		SupermarketDao supermarketDao = ApplicationContext.SUPERMARKET_DAO;
		List<Supermarket> supermarkete = supermarketDao.getSupermarkete();	
		System.out.println(supermarkete);
		request.setAttribute("supermarkete", supermarkete);
		
		// Aici serveltul face un forward al flow call-ului catre JSP
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/facturi.jsp");
		// si ii paseaza requestul si responsul 
		// (pe request se afla si atributul produse)
		requestDispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		Aici trebuie creata o factura care sa contina toate produsele cumparate (vanzarile) 
//		In servletulFActura in doPost cream facutura o factura goala. 

		FacturaDao facturaDao = ApplicationContext.FACTURA_DAO;
		facturaDao.adaugaFactura(Utils.SUPERMARKET_ID);
		
		int codUltimaFactura = facturaDao.getCodFacturaDeLaUltimaFactura();

//		trebuie adaugate in baza de date toate vanzarile pentru fiecare produs.	
//		luam idurile produselor din cosul de cumparaturi care se afla pe sesiune.
//		o bucla for pentru fiecare produs de pe sesiune.
//				
//		Nota: e un pic de lucru la cantitate. 
		
//		La fiecare produs trebuie verificat stocul.
//		SELECT stoc from Produs WHERE produs_id = ....
//		
//		
//		CAnd se creaza vanzarile se adauga codul facuturii.
		
		
		VanzareDao vanzareDao = ApplicationContext.VANZARE_DAO;
		//vanzareDao.adaugaVanzare(produsId, Utils.CLIENT_ID, codUltimaFactura, cantitate)
		
		HttpSession session = request.getSession();
		List<Integer> cos = (ArrayList<Integer>)session.getAttribute("cosCumparaturi");
		
		HashMap<Integer, Integer> produsIdsSiCantitati = new HashMap<>();
		//session.getAtribute
		for(int produsId : cos) {
			// in cos avem produsele: 5, 104, 34, 1302, 34, 56, 5, 34.
			// hashMapul va contine perechile:
			// 5,2
			// 104, 1
			// 1302, 1
			// 34, 3
			// 56, 1
			// Cum functioneaza metoda put:
			// Daca cheia nu exista, adauga cheia si valoarea. 
			// Daca cheia exista deja, inlocuieste valoarea cu noua valoare.
			
			//produsIdsSiCantitati.put(2, 2);
			//produsIdsSiCantitati.put(3, 1);
			
			if(produsIdsSiCantitati.containsKey(produsId)) {
				int cantitate = produsIdsSiCantitati.get(produsId);
				produsIdsSiCantitati.put(produsId, ++cantitate);
			}
			else
				produsIdsSiCantitati.put(produsId, 1);
		}
		
		// In momentul de fata avem un hashmap care contine toate produsele din cosul de cumparaturi
		// cu cheile: produsId si valorile: cantitati.
		// Trebuie verificat ca cantitatile sa fie mai mici sau egale decat stocul, pentru fiecare produs.
		ProdusDao produsDao = new ProdusDao();
		for(Integer key : produsIdsSiCantitati.keySet()) {
			Integer cantitateaProdusuluiDinCos = produsIdsSiCantitati.get(key);
			Integer stoculProdusuluiDinCos = produsDao.getStoc(key); 
			
			if(cantitateaProdusuluiDinCos > stoculProdusuluiDinCos) {
				// TODO returnam un mesaj de anulare a facturarii
				request.setAttribute("e", "Ne pare rau, intre timp altcineva a cumparat un produs. "
						+ "Cantitatea produsului cu id-ul " + key + " din cosul de cumparatori "
								+ "este mai mica decat stocul.");
				
				// Aici serveltul face un forward al flow call-ului catre JSP
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/cos.jsp");
				// si ii paseaza requestul si responsul 
				// (pe request se afla si atributul produse)
				requestDispatcher.forward(request, response);	
			}	
		}		
		
		vanzareDao.adaugaVanzare(produsIdsSiCantitati, Utils.CLIENT_ID, codUltimaFactura);

//		Se sterg toate produsele din cosul de cumparaturi. list.clear()
		cos.clear();		
		
//		Pretul total al facturii:  Trebuie obtinut pretul fiecarui produs din baza de date.		
//		//SELECT ..... FROM vanzare
//		UPDATE facturi SET prettotal = ... WHERE factura_id = ....
//		Ultimul lucru: trebuie scazut stocul.

		Set<Integer> productIds = produsIdsSiCantitati.keySet();
		
		List<Produs> produseleDinBazaDeDatePuseInCos = produsDao.getProduse(productIds);
		double pretTotalFactura = 0;
		for (Produs produs : produseleDinBazaDeDatePuseInCos) {
			// pretTtoal = pretTotal + pretProdus * Cantitate.
			pretTotalFactura = pretTotalFactura + produs.getPret() * produsIdsSiCantitati.get(produs.getId());
		}
		
//		UPDATE facturi SET prettotal = ... WHERE factura_id = ....
		
		facturaDao.setPretTotal(codUltimaFactura,pretTotalFactura);
		
		
//		Ultimul lucru: trebuie scazut stocul pentru fiecare produs din cosul din cumparaturi.
		for(Produs produs : produseleDinBazaDeDatePuseInCos) {
			int stoculVechiAlProdusului = produs.getStoc();
			int idProdus = produs.getId();
			int cantitate = produsIdsSiCantitati.get(idProdus);
			int stoculNouAlProduslui = stoculVechiAlProdusului - cantitate;
			produsDao.setStocNou(idProdus, stoculNouAlProduslui);
		}
		
 //		returnam factura.jsp

		// ne trebuie toate vanzarile care au acest cod de factura.
		List<ProdusDePeFactura> produseVandute = vanzareDao.getToateVanzarileDeLaOFactura(codUltimaFactura);
		
		request.setAttribute("produseVandute", produseVandute);
		request.setAttribute("codFactura", codUltimaFactura);
		
		String pretTotalFacturaFormatatLaDouaZecimale = String.format("%.2f", pretTotalFactura);
		request.setAttribute("pretTotal", pretTotalFacturaFormatatLaDouaZecimale);

		
		// Aici serveltul face un forward al flow call-ului catre JSP
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/factura.jsp");
		// si ii paseaza requestul si responsul 
		// (pe request se afla si atributul produse)
		requestDispatcher.forward(request, response);		
		
	}

}
