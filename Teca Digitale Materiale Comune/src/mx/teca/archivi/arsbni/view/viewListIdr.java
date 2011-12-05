/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import mx.database.ConnectionPool;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata per estrarre una lista di Idr in un arco temporale
 * 
 * @author Massimiliano Randazzo
 *
 */
public class viewListIdr extends Query
{

	/**
	 * Costruttore
	 * 
	 * @param conn Questa variabile viene utilizzata per indicare il pool di connessioni con il database
	 */
	public viewListIdr(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * Questo metodo viene utilizzato per indicare la lista dei campi della select
	 * 
	 * @see mx.database.table.Query#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("LegNotBid", "TBLLEGNOT", "LEGNOTBID");
		this.addCampi("RisIdr", "TBLLEGNOTRIS", "RISIDR");
		this.addCampi("PieceGr", "TBLLEGNOTRIS", "PIECEGR");
		this.addCampi("PieceDt", "TBLLEGNOTRIS", "PIECEDT");
		this.addCampi("PieceIn", "TBLLEGNOTRIS", "PIECEIN");
		this.addCampi("NotDataMod", "TBLLEGNOTRIS", "NOTDATAMOD");
		this.addCampi("DateStart", "TBLLEGNOTRIS", "NOTDATAMOD", false, true, true);
		this.getCampo("DateStart").setTipoRicerca(">=");
		this.addCampi("DateEnd", "TBLLEGNOTRIS", "NOTDATAMOD", false, true, false);
		this.getCampo("DateEnd").setTipoRicerca("<=");
		this.addCampi("CodeTblOaiSet", "TBLOAISET", "CODE_TBLOAISET");
	}

	/**
	 * Questo metodo viene utilizzato per indicare le codnizione di From
	 * 
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
		this.setFrom("TBLLEGNOT,TBLLEGNOTRIS, TBLOAISET");
	}

	/**
	 * Questo metodo viene utilizzato per indicare la condizole Where
	 * 
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.addWhere("TBLLEGNOT.ID_TBLLEGNOT=TBLLEGNOTRIS.ID_TBLLEGNOT AND TBLLEGNOTRIS.ID_TBLOAISET=TBLOAISET.ID_TBLOAISET");
	}

}
