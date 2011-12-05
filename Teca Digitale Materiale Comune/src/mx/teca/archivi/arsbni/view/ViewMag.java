/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import mx.database.ConnectionPool;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata per gestire la ricerca della posizione fisica
 * del File Mag
 * 
 * @author Massimiliano Randazzo
 * 
 */
public class ViewMag extends Query
{

	/**
   * Costruttore
   * 
   * @param conn
   *          Questa variabile viene utilizzata per indicare il Pool di
   *          connessioni con il Database
   */
	public ViewMag(ConnectionPool conn)
	{
		super(conn);
	}

	/**
   * Questo metodo viene utilizzato per indicare la lista dei campi che
   * compongono la ricerca
   * 
   * @see mx.database.table.Query#defCampi()
   */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("hostProt", "TLKPHOST", "HOSTPROT");
		this.addCampi("hostIp", "TLKPHOST", "HOSTIP");
		this.addCampi("hostPorta", "TLKPHOST", "HOSTPORTA");
		this.addCampi("hostServerPath", "TLKPHOST", "HOSTSERVERPATH");
		this.addCampi("hostPathDisco", "TLKPHOST", "HOSTPATHDISCO");
		this.addCampi("hostLogin", "TLKPHOST", "HOSTLOGIN");
		this.addCampi("hostPsw", "TLKPHOST", "HOSTPSW");
		this.addCampi("fileMag", "TBLLEGNOTRIS", "FILEMAG");
		this.addCampi("risIdr", "TBLLEGNOTRIS", "RISIDR");
	}

	/**
   * Questo metodo viene utilizzato per indicare la lista delle tabelle
   * coinvolte nella select
   * 
   * @see mx.database.table.Query#defFrom()
   */
	protected void defFrom()
	{
		this.setFrom("TBLLEGNOTRIS, TLKPHOST");
	}

	/**
   * Questo metodo viene utilizzato per indicare la condizione di where della
   * select
   * 
   * @see mx.database.table.Query#defWhere()
   */
	protected void defWhere()
	{
		this.setWhere("TBLLEGNOTRIS.HOSTID=TLKPHOST.HOSTID");
	}

}
