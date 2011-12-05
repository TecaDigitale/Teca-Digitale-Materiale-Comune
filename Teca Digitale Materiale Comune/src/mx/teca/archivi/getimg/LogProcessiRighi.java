/**
 * 
 */
package mx.teca.archivi.getimg;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.navigator.TableNavigator;

/**
 * @author Randazzo
 *
 */
public class LogProcessiRighi extends TableNavigator
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
  public LogProcessiRighi(ConnectionPool conn, MsSqlPool msp, Map map)
  {
    super(conn, msp, map);
  }

  /**
   * Costruttore con la gestione del Pool di connessioni con lista dei parametri da caricare
   * @param conn Pool di connessioni con il Database
   * @param map Lista parametri da caricare
   */
  public LogProcessiRighi(ConnectionPool conn, Map map)
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
  public LogProcessiRighi(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public LogProcessiRighi()
  {
    super();
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public LogProcessiRighi(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con relativa connessione pre selezionata al database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public LogProcessiRighi(MsSqlPool msp)
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
    this.addCampi("idProcessiRighi", "LOGPROCESSIRIGHI", "ID_LOGPROCESSIRIGHI", true);
    if (this.msp != null)
      this.getCampo("idProcessiRighi").setGenID(new Contatori(this.msp), "LOGPROCESSIRIGHI");
    else
      this.getCampo("idProcessiRighi").setGenID(new Contatori(this.conn), "LOGPROCESSIRIGHI");
    this.addCampi("idlogProcessi", "LOGPROCESSIRIGHI", "ID_LOGPROCESSI");
    this.addCampi("tipoLog", "LOGPROCESSIRIGHI", "TIPOLOG");
    this.addCampi("tipoError", "LOGPROCESSIRIGHI", "TIPOERROR");
    this.addCampi("riga", "LOGPROCESSIRIGHI", "RIGA");
    this.addCampi("colonna", "LOGPROCESSIRIGHI", "COLONNA");
    this.addCampi("msgErr", "LOGPROCESSIRIGHI", "MSGERR");
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
