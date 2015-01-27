/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import java.sql.ResultSet;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata per estrarre la lista degli IDR relativamente a un determinato Mag
 * 
 * @author Massimiliano Randazzo
 *
 */
public class ViewListaIdrAudioSpogli extends Query
{

	/**
	 * Costruttore
	 * @param arg0 Questa variabile viene utilizzata per indicare il pool di connessione
	 */
	public ViewListaIdrAudioSpogli(ConnectionPool arg0)
	{
		super(arg0);
	}

	/**
	 * Questo metodo viene utilizzato per definire la condizione di from della ricerca
	 * 
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
		this.setFrom("TBLLEGNOT, TBLLEGNOTRIS, TBLRELRIS, TBLRISAUDIO, TBLAUDIO, TBLRELRIS TBLRELRISSPOGLI");
	}

	/**
	 * Questo metodo viene utilizzato per indicare la condizione di where della ricerca
	 * 
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.addWhere("TBLRELRISSPOGLI.RELRISIDRPARTENZA=TBLRELRIS.RELRISIDRARRIVO AND ");
		this.addWhere("TBLLEGNOT.ID_TBLLEGNOT = TBLLEGNOTRIS.ID_TBLLEGNOT AND ");
		this.addWhere("TBLLEGNOTRIS.RISIDR = TBLRELRIS.RELRISIDRARRIVO AND ");
		this.addWhere("TBLRELRIS.RELRISIDRPARTENZA = TBLRISAUDIO.RISIDR AND ");
		this.addWhere("TBLRISAUDIO.ID_TBLAUDIO = TBLAUDIO.ID_TBLAUDIO");
	}

	/**
	 * @see mx.database.table.Query#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("legNotBid", "TBLLEGNOT", "LEGNOTBID");
		this.addCampi("pieceGr", "TBLLEGNOTRIS", "PIECEGR");
		this.addCampi("PieceDt", "TBLLEGNOTRIS", "PIECEDT");
		this.addCampi("PieceIn", "TBLLEGNOTRIS", "PIECEIN");
		this.addCampi("RelRisIdrArrivo", "TBLRELRISSPOGLI", "RELRISIDRARRIVO");
		this.addCampi("RelRisIdrPartenza", "TBLRELRIS", "RELRISIDRPARTENZA");
 		this.addCampi("RelRisSequenza", "TBLRELRIS", "RELRISSEQUENZA");
 		this.addCampi("audioUsage", "TBLAUDIO", "AUDIOUSAGE");
		this.addCampi("audioPathName", "TBLAUDIO", "AUDIOPATHNAME");
		this.addCampi("idTblAudio", "TBLAUDIO", "ID_TBLAUDIO");
		this.addCampi("fruiBid", "TBLAUDIO", "FRUIBID");
	}

	/**
	 * Questo metodo viene utilizzato per estendere le funzionalità si partenza della select
	 * 
	 * @see mx.database.table.DataSet#startSelect()
	 */
	public ResultSet startSelect()
	{
		this.getCampo("RelRisIdrPartenza").setOrderBy(Column.ORDERBY_CRES, 1);
		this.getCampo("RelRisSequenza").setOrderBy(Column.ORDERBY_CRES, 2);
		this.getCampo("audioUsage").setOrderBy(Column.ORDERBY_CRES, 3);
		return super.startSelect();
	}
}
