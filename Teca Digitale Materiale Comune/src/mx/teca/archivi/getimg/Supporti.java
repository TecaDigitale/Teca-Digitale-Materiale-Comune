/**
 * 
 */
package mx.teca.archivi.getimg;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire l'accesso lala tablla Supporti del database Gestionale
 * 
 * @author Massimiliano Randazzo
 *
 */
public class Supporti extends Table
{

	/**
	 * @param conn
	 */
	public Supporti(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * Questo metodo viene utilizzato per indicare la lista dei campi che compongo la tabella
	 * 
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idSupporto", "SUPPORTI", "ID_SUPPORTO", true);
		this.addCampi("descSupporto", "SUPPORTI", "DESC_SUPPORTO");
		this.getCampo("descSupporto").setOrderBy(Column.ORDERBY_DESC, 1);
	}

	/**
	 * Questo metodo viene utilizzato per indicare operazioni da eseguire dopo gli aggiornamenti della tabella
	 * 
	 * @see mx.database.table.Table#postUpdate()
	 */
	protected void postUpdate()
	{
	}

	/**
	 * Questo metodo viene utilizzato per indicare operazioni da eseguire prima gli aggiornamenti della tabella
	 * 
	 * @see mx.database.table.Table#preUpdate()
	 */
	protected void preUpdate()
	{
	}

}
