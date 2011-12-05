/**
 * 
 */
package mx.teca.archivi.arsbni;

import mx.database.ConnectionPool;
import mx.database.table.Table;

/**
 * @author massi
 *
 */
public class TlkpTipoRel extends Table
{

	/**
	 * @param conn
	 */
	public TlkpTipoRel(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("tiporelid", "TLKPTIPOREL", "TIPORELID",true);
		this.addCampi("tiporeldesap", "TLKPTIPOREL", "TIPORELDESAP");
		this.addCampi("tiporeldespa", "TLKPTIPOREL", "TIPORELDESPA");
		this.addCampi("tiporelnat", "TLKPTIPOREL", "TIPORELNAT");
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
