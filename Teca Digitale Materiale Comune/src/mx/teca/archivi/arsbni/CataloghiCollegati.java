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
public class CataloghiCollegati extends Table
{

	/**
	 * @param conn
	 */
	public CataloghiCollegati(ConnectionPool conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("legNotSegnaOri", "CATALOGHICOLLEGATI", "LEGNOTSEGNAORI", true);
		this.addCampi("legNotBidDes", "CATALOGHICOLLEGATI", "LEGNOTBIDDES", false);
		this.addCampi("relRisSequenza", "CATALOGHICOLLEGATI", "RELRISSEQUENZA", false);
		this.addCampi("linkCollegati", "CATALOGHICOLLEGATI", "LINKCOLLEGATI", false);
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
