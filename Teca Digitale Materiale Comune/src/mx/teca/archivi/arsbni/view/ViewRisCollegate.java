/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import java.sql.ResultSet;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata per verificare se esistono risorse collegate all'identificativo selezionato
 * 
 * @author Massimiliano Randazzo
 *
 */
public class ViewRisCollegate extends Query
{

	/**
	 * Costruttore
	 * @param conn
	 */
	public ViewRisCollegate(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
		this.setFrom("TBLRELRIS, TBLLEGNOTRIS, TBLRIS, TBLLEGNOT");
	}

	/**
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.setWhere("TBLRELRIS.RELRISIDRPARTENZA = TBLLEGNOTRIS.RISIDR AND");
		this.addWhere("TBLRELRIS.RELRISIDRARRIVO = TBLRIS.RISIDR AND");
		this.addWhere("TBLLEGNOT.ID_TBLLEGNOT = TBLLEGNOTRIS.ID_TBLLEGNOT");
	}

	/**
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("relRisIdrArrivo", "TBLRELRIS", "RELRISIDRARRIVO");
		this.addCampi("tipoRelId", "TBLRELRIS", "TIPORELID");
		this.addCampi("risIdr", "TBLLEGNOTRIS", "RISIDR");
		this.addCampi("notLevel", "TBLLEGNOTRIS", "NOTLEVEL");
		this.addCampi("pieceGr", "TBLLEGNOTRIS", "PIECEGR");
		this.addCampi("pieceDt", "TBLLEGNOTRIS", "PIECEDT");
		this.addCampi("pieceIn", "TBLLEGNOTRIS", "PIECEIN");
		this.addCampi("risNotaPub", "TBLLEGNOT", "TMPTITOLO");
	}

	/**
	 * Questo metodo viene utilizzato per eseguire la ricerca
	 * 
	 * @see mx.database.table.DataSet#startSelect()
	 */
	public ResultSet startSelect()
	{
		this.getCampo("risNotaPub").setOrderBy(Column.ORDERBY_CRES,1);
		this.getCampo("pieceIn").setOrderBy(Column.ORDERBY_CRES,2);
		return super.startSelect();
	}

}
