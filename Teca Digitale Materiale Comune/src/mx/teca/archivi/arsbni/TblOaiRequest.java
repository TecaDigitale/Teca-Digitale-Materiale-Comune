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
public class TblOaiRequest extends TableNavigator
{

	/**
	 * Costruttore
	 *  
	 * @param conn
	 */
	public TblOaiRequest(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * Costruttore
	 *  
	 * @param conn
	 */
	public TblOaiRequest(ConnectionPool conn, Map map)
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
		this.addCampi("idTblOaiRequest", "TBLOAIREQUEST", "ID_TBLOAIREQUEST", true);
		this.addCampi("da", "TBLOAIREQUEST", "DA");
		this.addCampi("a", "TBLOAIREQUEST", "A");
		this.addCampi("cursore", "TBLOAIREQUEST", "CURSORE");
		this.addCampi("collezione", "TBLOAIREQUEST", "COLLEZIONE");
		this.addCampi("tipo", "TBLOAIREQUEST", "TIPO");
	}

	protected void postUpdate()
	{
	}

	protected void preUpdate()
	{
	}

}
