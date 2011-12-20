/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata per estrarre la lista delle nomenclature delle immagini
 * 
 * @author Massimiliano Randazzo
 *
 */
public class ViewListaImg extends Query
{

	/**
	 * Costruttore
	 * @param conn
	 */
	public ViewListaImg(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * Questo metodo viene utilizzato per indicare la lista dei campi risultanti della select
	 * 
	 * @see mx.database.table.Query#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		
		this.addCampi("idr", "TBLRIS", "RISIDR");
		this.addCampi("nota", "TBLRIS", "RISNOTAPUB");
		this.addCampi("liv", "TBLRIS", "RISLIVELLO");
		this.addCampi("mime", "TBLIMG", "MIMEID");
		this.addCampi("imgUsage", "TBLIMG", "IMGUSAGE");
		this.addCampi("seq", "TBLRELRIS", "RELRISSEQUENZA");
		this.getCampo("seq").setOrderBy(1, Column.ORDERBY_CRES);
		this.addCampi("relrisidr", "TBLRELRIS", "RELRISIDRARRIVO");
		this.addCampi("RelRisIdrPartenza", "TBLRELRIS", "RELRISIDRPARTENZA");
		this.addCampi("tipoRelId", "TBLRELRIS", "TIPORELID");
		this.addCampi("imgPathName", "TBLIMG", "IMGPATHNAME");
		this.addCampi("imgLength", "TBLIMG", "IMGLENGTH");
		this.addCampi("imgWidth", "TBLIMG", "IMGWIDTH");
	}

	/**
	 * Questo metodo viene utilizzato per indicare la lista delle tabelle che compongono la select
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
		this.addFrom("TBLRELRIS,");
		this.addFrom("TBLRIS,");
		this.addFrom("TBLRISIMG,");
		this.addFrom("TBLIMG");
	}

	/**
	 * Questo metodo viene utilizzato per indicare le condizioni di where della select
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.addWhere("TBLRELRIS.RELRISIDRPARTENZA=TBLRIS.RISIDR AND ");
		this.addWhere("TBLRIS.RISIDR=TBLRISIMG.RISIDR AND ");
		this.addWhere("TBLRISIMG.ID_TBLIMG = TBLIMG.ID_TBLIMG"); 
	}

}
