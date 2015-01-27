/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire l'accesso alla tabella TLKPHOST
 * @author Randazzo
 *
 */
public class TlkpHost extends Table
{

  /**
   * Costruttore con definizione del Pool di connessione e connesione
   * preselezionata per le operazioni di modifica della tabella con lista dei parametri da caricare
   * 
   * @param conn
   *          Poll di connessioni con il database
   * @param msp
   *          Connessione da utilizzare nelle operazioni di modifica della
   *          tabella (insert, update, delete)
   * @param map Lista parametri da caricare
   */
  public TlkpHost(ConnectionPool conn, MsSqlPool msp, Map<Object, Object> map)
  {
    super(conn, msp, map);
  }

  /**
   * Costruttore con la gestione del Pool di connessioni con lista dei parametri da caricare
   * @param conn Pool di connessioni con il Database
   * @param map Lista parametri da caricare
   */
  public TlkpHost(ConnectionPool conn, Map<Object, Object> map)
  {
    super(conn, map);
  }

  /**
   * Costruttore con definizione del Pool di connessione e connesione preselezionata per le 
   * operazioni di modifica della tabella
   * @param conn Poll di connessioni con il database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public TlkpHost(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public TlkpHost()
  {
    super();
  }

  /**
   * Costruttore con la gestione del Pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public TlkpHost(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione della connessione selezionata
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public TlkpHost(MsSqlPool msp)
  {
    super(msp);
  }

  /**
   * Questo metodo viene utilizzato per indicare la lista dei campi che compongono la 
   * tabella
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("hostId", "TLKPHOST", "HOSTID",true);
    if (this.msp != null)
      this.getCampo("hostId").setGenID(new TblCont(this.msp), "HOST");
    else
      this.getCampo("hostId").setGenID(new TblCont(this.conn), "HOST");
    this.addCampi("hostProt","TLKPHOST","HOSTPROT");
    this.addCampi("hostIp","TLKPHOST","HOSTIP");
    this.addCampi("hostPorta","TLKPHOST","HOSTPORTA");
    this.addCampi("hostServerPath", "TLKPHOST", "HOSTSERVERPATH");
    this.addCampi("hostPathDisco", "TLKPHOST", "HOSTPATHDISCO");
    this.addCampi("hostLogin","TLKPHOST","HOSTLOGIN");
    this.addCampi("hostPsw","TLKPHOST","HOSTPSW");
  }

	/**
	 * Questo metodo viene utilizzata per indicare la path della Base
	 * 
	 */
	public String getBasePath(String fileImg)
	{
		String ris = "";
		
		ris = this.get("hostServerPath");
		if (!ris.endsWith("/"))
			ris += "/";
		
		if (this.get("hostPathDisco").startsWith("./"))
			ris += this.get("hostPathDisco").substring(2);
		else if (this.get("hostPathDisco").startsWith("/"))
			ris += this.get("hostPathDisco").substring(1);
		else
			ris += this.get("hostPathDisco");

		if (!ris.endsWith("/"))
			ris += "/";

		if (fileImg.startsWith("./"))
			ris += fileImg.substring(2);
		else if (fileImg.startsWith("/"))
			ris += fileImg.substring(1);
		else
			ris += fileImg;

		return ris;
	}

	protected void postUpdate()
	{
		// TODO Auto-generated method stub
		
	}

	protected void preUpdate()
	{
		// TODO Auto-generated method stub
		
	}
  
}
