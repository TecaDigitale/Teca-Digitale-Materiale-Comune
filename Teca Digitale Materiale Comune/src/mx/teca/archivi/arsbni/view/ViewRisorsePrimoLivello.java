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
public class ViewRisorsePrimoLivello extends Query
{

	/**
	 * Codtruttore
	 */
	public ViewRisorsePrimoLivello(ConnectionPool conn)
	{
		super(conn);
	}

	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("relRisIdrArrivo", "TBLRELRIS1", "RELRISIDRARRIVO");
		this.addCampi("tipoRelId", "TBLRELRIS", "TIPORELID");
	}

	protected void defFrom()
	{
		this.setFrom("TBLRELRIS as TBLRELRIS1, TBLRELRIS");
	}

	protected void defWhere()
	{
		this.setWhere("TBLRELRIS1.RELRISIDRPARTENZA=TBLRELRIS.RELRISIDRARRIVO");
	}

}
