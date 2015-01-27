/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.navigator.TableNavigator;

/**
 * Questa classe viene utilizzata per gestire l'accesso alla tabella TblOainotizia del database Teca
 * 
 * @author Massimiliano Randazzo
 *
 */
public class TblOaiNotizia extends TableNavigator
{

	/**
	 * Costruttore
	 *  
	 * @param conn
	 */
	public TblOaiNotizia(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * Costruttore
	 *  
	 * @param conn
	 */
	public TblOaiNotizia(ConnectionPool conn, Map<Object, Object> map)
	{
		super(conn, map);
	}

	/**
	 * Questo metodo viene utilizzato per definire il campi della tabella
	 * 
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idTblLegNot", "TBLOAINOTIZIA", "ID_TBLLEGNOT", true);
		this.addCampi("risIdr", "TBLOAINOTIZIA", "RISIDR",true);
		this.addCampi("sequenza", "TBLOAINOTIZIA", "SEQUENZA");
		this.addCampi("idTblOaiSet", "TBLOAINOTIZIA", "ID_TBLOAISET",true);
		this.addCampi("notDataMod", "TBLOAINOTIZIA", "NOTDATAMOD");
		this.addCampi("stato", "TBLOAINOTIZIA", "STATO");
		this.addCampi("descrizione", "TBLOAINOTIZIA", "DESCRIZIONE");
	}

	protected void postUpdate()
	{
	}

	protected void preUpdate()
	{
	}

}
