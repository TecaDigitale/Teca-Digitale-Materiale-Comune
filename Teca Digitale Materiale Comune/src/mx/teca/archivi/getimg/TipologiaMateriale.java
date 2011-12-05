/**
 * 
 */
package mx.teca.archivi.getimg;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Table;

/**
 * @author massi
 *
 */
public class TipologiaMateriale extends Table
{

	/**
	 * @param conn
	 */
	public TipologiaMateriale(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
    this.addCampi("idTipologiaMateriale", "TIPOLOGIAMATERIALE", "ID_TIPOLOGIAMATERIALE", true);
    this.addCampi("descTipologiaMateriale", "TIPOLOGIAMATERIALE", "DESC_TIPOLOGIAMATERIALE");
    this.getCampo("descTipologiaMateriale").setOrderBy(1,Column.ORDERBY_CRES);
    this.addCampi("keyTipologiaMateriale", "TIPOLOGIAMATERIALE", "KEY_TIPOLOGIAMATERIALE");
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
