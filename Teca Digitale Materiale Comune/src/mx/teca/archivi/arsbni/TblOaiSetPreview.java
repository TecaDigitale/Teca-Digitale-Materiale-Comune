/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.navigator.TableNavigator;

/**
 * Questa classe viene utilizzata per gestire l'accesso alla tabella TblOaiRequest del database Teca
 * 
 * @author Massimiliano Randazzo
 *
 */
public class TblOaiSetPreview extends TableNavigator
{

	/**
	 * Costruttore
	 *  
	 * @param conn
	 */
	public TblOaiSetPreview(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * Costruttore
	 *  
	 * @param conn
	 */
	public TblOaiSetPreview(ConnectionPool conn, Map map)
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
		this.addCampi("idTblOaiSet", "TBLOAISETPREVIEW", "ID_TBLOAISET", true);
		this.addCampi("idPreview", "TBLOAISETPREVIEW", "ID_PREVIEW",true);
	}

	protected void postUpdate()
	{
	}

	protected void preUpdate()
	{
	}

}
