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
public class ViewListaIdrImg extends Query
{

	/**
	 * Costruttore
	 * @param arg0 Questa variabile viene utilizzata per indicare il pool di connessione
	 */
	public ViewListaIdrImg(ConnectionPool arg0)
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
		this.setFrom("TBLLEGNOT, TBLLEGNOTRIS, TBLRELRIS, TBLRISIMG, TBLIMG, TBLRIS");
	}

	/**
	 * Questo metodo viene utilizzato per indicare la condizione di where della ricerca
	 * 
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.addWhere("TBLLEGNOT.ID_TBLLEGNOT = TBLLEGNOTRIS.ID_TBLLEGNOT AND ");
		this.addWhere("TBLLEGNOTRIS.RISIDR = TBLRELRIS.RELRISIDRARRIVO AND ");
		this.addWhere("TBLRELRIS.RELRISIDRPARTENZA = TBLRISIMG.RISIDR AND ");
		this.addWhere("TBLRISIMG.ID_TBLIMG = TBLIMG.ID_TBLIMG AND ");
		this.addWhere("TBLRELRIS.RELRISIDRPARTENZA = TBLRIS.RISIDR");
	}

	/**
	 * @see mx.database.table.Query#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("legNotBid", "TBLLEGNOT", "LEGNOTBID");
		this.addCampi("tmpAutore", "TBLLEGNOT", "TMPAUTORE");
		this.addCampi("tmpTitolo", "TBLLEGNOT", "TMPTITOLO");
		this.addCampi("tmpNotePubblicazione", "TBLLEGNOT", "TMPNOTE_PUBBLICAZIONE");
		this.addCampi("risIdr", "TBLLEGNOTRIS", "RISIDR");
		this.addCampi("pieceGr", "TBLLEGNOTRIS", "PIECEGR");
		this.addCampi("PieceDt", "TBLLEGNOTRIS", "PIECEDT");
		this.addCampi("PieceIn", "TBLLEGNOTRIS", "PIECEIN");
		this.addCampi("RelRisIdrArrivo", "TBLRELRIS", "RELRISIDRARRIVO");
		this.addCampi("RelRisIdrPartenza", "TBLRELRIS", "RELRISIDRPARTENZA");
 		this.addCampi("RelRisSequenza", "TBLRELRIS", "RELRISSEQUENZA");
 		this.addCampi("tiporelid", "TBLRELRIS", "TIPORELID");
 		this.addCampi("risNotaPub", "TBLRIS", "RISNOTAPUB");
 		this.addCampi("ImgUsage", "TBLIMG", "IMGUSAGE");
		this.addCampi("imgPathName", "TBLIMG", "IMGPATHNAME");
		this.addCampi("idTblImg", "TBLIMG", "ID_TBLIMG");
		this.addCampi("fruiBid", "TBLIMG", "FRUIBID");
		this.addCampi("imgLength", "TBLIMG", "IMGLENGTH");
		this.addCampi("imgWidth", "TBLIMG", "IMGWIDTH");
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
		this.getCampo("ImgUsage").setOrderBy(Column.ORDERBY_CRES, 3);
		return super.startSelect();
	}
}
