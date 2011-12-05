/**
 * 
 */
package mx.teca.archivi.arsbni;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire l'acesso alal tabella Biblioteche
 * 
 * @author Massimiliano Randazzo
 *
 */
public class Biblioteche extends Table
{

	/**
	 * Costruttore
	 * 
	 * @param conn Pool di connessioni con il database
	 */
	public Biblioteche(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * Queto metod viene utilizzato per indicare la lista dei campi della tabella
	 * 
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("Sigla", "BIBLIOTECHE", "SIGLA", true);
		this.addCampi("Descrizione", "BIBLIOTECHE", "DESCRIZIONE");
		this.getCampo("Descrizione").setOrderBy(Column.ORDERBY_CRES, 1);
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
