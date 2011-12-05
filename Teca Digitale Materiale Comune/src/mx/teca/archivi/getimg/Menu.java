/**
 * 
 */
package mx.teca.archivi.getimg;

import mx.database.ConnectionPool;
import mx.database.table.Table;

/**
 * @author MRandazzo
 *
 */
public class Menu extends Table
{

	/**
	 * @param conn
	 */
	public Menu(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idMenu", "MENU", "ID_MENU", true);
		this.addCampi("idMenuPadre", "MENU", "ID_MENUPADRE");
		this.addCampi("ordine", "MENU", "ORDINE");
		this.addCampi("titolo", "MENU", "TITOLO");
		this.addCampi("descrizione", "MENU", "DESCRIZIONE");
		this.addCampi("link", "MENU", "LINK");
		this.addCampi("autorizzazione", "MENU", "AUTORIZZAZIONE");
	}

	/**
	 * @see mx.database.table.Table#postUpdate()
	 */
	protected void postUpdate()
	{
	}

	/**
	 * @see mx.database.table.Table#preUpdate()
	 */
	protected void preUpdate()
	{
	}

}
