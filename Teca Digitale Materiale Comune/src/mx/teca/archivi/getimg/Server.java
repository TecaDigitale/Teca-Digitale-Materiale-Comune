/**
 * 
 */
package mx.teca.archivi.getimg;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire la tabella Server
 * @author Randazzo
 *
 */
public class Server extends Table
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
  public Server(ConnectionPool conn, MsSqlPool msp, Map<Object, Object> map)
  {
    super(conn, msp, map);
  }

  /**
   * Costruttore con la gestione del Pool di connessioni con lista dei parametri da caricare
   * @param conn Pool di connessioni con il Database
   * @param map Lista parametri da caricare
   */
  public Server(ConnectionPool conn, Map<Object, Object> map)
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
  public Server(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public Server()
  {
    super();
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public Server(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con relativa connessione pre selezionata al database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Server(MsSqlPool msp)
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
    this.addCampi("idServer", "SERVER", "ID_SERVER", true);
    if (this.msp != null)
      this.getCampo("idServer").setGenID(new Contatori(this.msp), "SERVER");
    else
      this.getCampo("idServer").setGenID(new Contatori(this.conn), "SERVER");
    this.addCampi("pathAreaMemoria", "SERVER", "PATHAREAMEMORIA");
    this.addCampi("descServer", "SERVER", "DESC_SERVER");
    this.addCampi("idServerName", "SERVER", "ID_SERVERNAME");
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
