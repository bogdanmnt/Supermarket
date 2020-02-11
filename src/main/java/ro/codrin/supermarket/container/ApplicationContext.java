package ro.codrin.supermarket.container;

import ro.codrin.supermarket.dao.ClientDao;
import ro.codrin.supermarket.dao.FacturaDao;
import ro.codrin.supermarket.dao.ProducatorDao;
import ro.codrin.supermarket.dao.ProdusDao;
import ro.codrin.supermarket.dao.RaionDao;
import ro.codrin.supermarket.dao.SupermarketDao;
import ro.codrin.supermarket.dao.TipDao;
import ro.codrin.supermarket.dao.VanzareDao;

public class ApplicationContext {

	public static final ClientDao CLIENT_DAO = new ClientDao();
	public static final FacturaDao FACTURA_DAO = new FacturaDao();
	public static final ProducatorDao PRODUCATOR_DAO = new ProducatorDao();
	public static final ProdusDao PRODUS_DAO = new ProdusDao();
	public static final RaionDao RAION_DAO = new RaionDao();
	public static final SupermarketDao SUPERMARKET_DAO = new SupermarketDao();
	public static final TipDao TIP_DAO = new TipDao();
	public static final VanzareDao VANZARE_DAO = new VanzareDao();
	
}
