/**
 * 
 */
package mx.teca.archivi.arsbni;

import mx.database.ConnectionPool;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire la classe TblOaiToken del database
 * 
 * @author Massimiliano Randazzo
 *
 */
public class TblOaiToken extends Table
{

	/**
	 * Costruttore
	 * 
	 * @param arg0
	 */
	public TblOaiToken(ConnectionPool arg0)
	{
		super(arg0);
	}

	/**
	 * Questo metodo viene utilizzare per indicare le operazioni successive all'operazione di aggiornamento
	 * 
	 * @see mx.database.table.Table#postUpdate()
	 */
	protected void postUpdate()
	{
	}

	/**
	 * Questo metodo viene utilizzato per indicare le operazioni precedenti all'operazione di aggiornamento
	 * 
	 * @see mx.database.table.Table#preUpdate()
	 */
	protected void preUpdate()
	{
	}

	/**
	 * Questo metodo viene utilizzato per gestire la definizione dei campi della tabella
	 * 
	 * @see mx.database.table.DataSet#defCampi()
	 */
	public void defCampi()
	{
		this.setColKey(true);
		this.addCampi("IdTblOaiToken", "TBLOAITOKEN", "ID_TBLOAITOKEN", true);
		this.addCampi("dataDa", "TBLOAITOKEN", "DATA_DA");
		this.addCampi("DataA", "TBLOAITOKEN", "DATA_A");
		this.addCampi("fondo", "TBLOAITOKEN", "FONDO");
		this.addCampi("metaDataPrefix", "TBLOAITOKEN", "METADATAPREFIX");
		this.addCampi("posizione", "TBLOAITOKEN", "POSIZIONE");
		this.addCampi("dataMod", "TBLOAITOKEN", "DATAMOD");
	}
}
