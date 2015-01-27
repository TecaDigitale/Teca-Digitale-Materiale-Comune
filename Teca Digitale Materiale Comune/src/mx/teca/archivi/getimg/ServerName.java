/**
 * 
 */
package mx.teca.archivi.getimg;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Column;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire la tabella ServerName
 * 
 * @author Randazzo Massimiliano
 *
 */
public class ServerName extends Table
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
  public ServerName(ConnectionPool conn, MsSqlPool msp, Map<Object, Object> map)
  {
    super(conn, msp, map);
  }

  /**
   * Costruttore con definizione del Pool di connessione e connesione preselezionata per le 
   * operazioni di modifica della tabella
   * @param conn Poll di connessioni con il database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public ServerName(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public ServerName()
  {
    super();
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public ServerName(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con relativa connessione pre selezionata al database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public ServerName(MsSqlPool msp)
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
    this.addCampi("idServerName", "SERVERNAME", "ID_SERVERNAME", true);
    if (this.msp != null)
      this.getCampo("idServerName").setGenID(new Contatori(this.msp), "SERVERNAME");
    else
      this.getCampo("idServerName").setGenID(new Contatori(this.conn), "SERVERNAME");
    this.addCampi("indirizzoIp", "SERVERNAME", "INDIRIZZOIP");
    this.getCampo("indirizzoIp").setOrderBy(1, Column.ORDERBY_CRES);
    this.addCampi("portaDemone", "SERVERNAME", "PORTADEMONE");
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
