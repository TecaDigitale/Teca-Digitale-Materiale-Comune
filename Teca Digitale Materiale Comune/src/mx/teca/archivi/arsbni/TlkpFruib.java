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
public class TlkpFruib extends Table
{

	/**
	 * @param conn
	 */
	public TlkpFruib(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("fruibid", "TLKPFRUIB", "FRUIBID",true);
		this.addCampi("fruibdes", "TLKPFRUIB", "FRUIBDES");
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
