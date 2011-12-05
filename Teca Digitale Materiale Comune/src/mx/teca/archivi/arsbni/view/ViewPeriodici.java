/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import java.sql.ResultSet;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Query;

/**
 * Questa calsse viene utilizzata per la ricerca dei periodici 
 * 
 * @author massi
 *
 */
public class ViewPeriodici extends Query
{

	/**
	 * @param conn
	 */
	public ViewPeriodici(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Query#defCampi()
	 */
	protected void defCampi()
	{
		this.setFrom("TBLRELRIS, TBLLEGNOTRIS");
	}

	/**
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
		this.setWhere("TBLRELRIS.RELRISIDRPARTENZA = TBLLEGNOTRIS.RISIDR");
	}

	/**
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.setColKey(true);
		this.addCampi("relRisIdrArrivo", "TBLRELRIS", "RELRISIDRARRIVO");
		this.addCampi("tipoRelId", "TBLRELRIS", "TIPORELID");
		this.addCampi("risIdr", "TBLLEGNOTRIS", "RISIDR");
		this.addCampi("notLevel", "TBLLEGNOTRIS", "NOTLEVEL");
		this.addCampi("pieceGr", "TBLLEGNOTRIS", "PIECEGR");
		this.addCampi("pieceDt", "TBLLEGNOTRIS", "PIECEDT");
		this.addCampi("pieceIn", "TBLLEGNOTRIS", "PIECEIN");
		this.addCampi("idTblLegNot", "TBLLEGNOTRIS", "ID_TBLLEGNOT");
	}

	/**
	 * Questo metodo viene utilizzato per eseguire la ricerca
	 * 
	 * @see mx.database.table.DataSet#startSelect()
	 */
	public ResultSet startSelect()
	{
		this.getCampo("pieceIn").setOrderBy(Column.ORDERBY_CRES,1);
		return super.startSelect();
	}

}
