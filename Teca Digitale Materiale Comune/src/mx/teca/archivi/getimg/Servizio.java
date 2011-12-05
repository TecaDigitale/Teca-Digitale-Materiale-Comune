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
 * Questa classe viene utilizzata per gestire la tabella Servizi
 * @author Randazzo
 *
 */
public class Servizio extends Table
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
  public Servizio(ConnectionPool conn, MsSqlPool msp, Map map)
  {
    super(conn, msp, map);
  }

  /**
   * Costruttore con la gestione del Pool di connessioni con lista dei parametri da caricare
   * @param conn Pool di connessioni con il Database
   * @param map Lista parametri da caricare
   */
  public Servizio(ConnectionPool conn, Map map)
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
  public Servizio(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public Servizio()
  {
    super();
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public Servizio(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con relativa connessione pre selezionata al database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Servizio(MsSqlPool msp)
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
    this.addCampi("idServizio", "SERVIZIO", "ID_SERVIZIO", true);
    if (this.msp != null)
      this.getCampo("idServizio").setGenID(new Contatori(this.msp), "SERVIZIO");
    else
      this.getCampo("idServizio").setGenID(new Contatori(this.conn), "SERVIZIO");
    this.addCampi("codServizio", "SERVIZIO", "COD_SERVIZIO");
    this.addCampi("descServizio", "SERVIZIO", "DESC_SERVIZIO");
    this.getCampo("descServizio").setOrderBy(1, Column.ORDERBY_CRES);
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
