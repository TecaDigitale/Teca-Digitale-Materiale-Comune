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
public class TlkpTypeDigit extends Table
{

	/**
	 * @param conn
	 */
	public TlkpTypeDigit(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("typedigitid", "TLKPTYPEDIGIT", "TYPEDIGITID",true);
		this.addCampi("typedigitdes", "TLKPTYPEDIGIT", "TYPEDIGITDES");
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
