/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import mx.database.ConnectionPool;
import mx.database.table.Query;

/**
 * @author MRandazzo
 *
 */
public class ViewRisorseSecondoLivello extends Query
{

	/**
	 * Codtruttore
	 */
	public ViewRisorseSecondoLivello(ConnectionPool conn)
	{
		super(conn);
	}

	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("relRisIdrArrivo", "TBLRELRIS2", "RELRISIDRARRIVO");
		this.addCampi("tipoRelId", "TBLRELRIS", "TIPORELID");
	}

	protected void defFrom()
	{
		this.setFrom("TBLRELRIS as TBLRELRIS2, TBLRELRIS as TBLRELRIS1, TBLRELRIS");
	}

	protected void defWhere()
	{
		this.setWhere("TBLRELRIS2.RELRISIDRPARTENZA=TBLRELRIS1.RELRISIDRARRIVO AND TBLRELRIS1.RELRISIDRPARTENZA=TBLRELRIS.RELRISIDRARRIVO");
	}

}
