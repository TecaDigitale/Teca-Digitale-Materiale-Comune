/**
 * 
 */
package mx.teca.archivi.arsbni;

import mx.database.ConnectionPool;
import mx.database.table.Table;

/**
 * @author MRandazzo
 *
 */
public class TmpFileXml extends Table
{

	public TmpFileXml(ConnectionPool pool)
	{
		super(pool);
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("risIdr", "TMPFILEXML", "RISIDR", true);
		this.addCampi("riga", "TMPFILEXML", "RIGA", true);
		this.addCampi("singleTree", "TMPFILEXML", "SINGLETREE", true);
		this.addCampi("fileXml", "TMPFILEXML", "FILEXML");
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
