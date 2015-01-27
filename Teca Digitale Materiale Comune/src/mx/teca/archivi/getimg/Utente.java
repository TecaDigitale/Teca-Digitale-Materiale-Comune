/**
 * 
 */
package mx.teca.archivi.getimg;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire la tabella Utente
 * @author Randazzo
 *
 */
public class Utente extends Table
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
  public Utente(ConnectionPool conn, MsSqlPool msp, Map<Object, Object> map)
  {
    super(conn, msp, map);
  }

  /**
   * Costruttore con la gestione del Pool di connessioni con lista dei parametri da caricare
   * @param conn Pool di connessioni con il Database
   * @param map Lista parametri da caricare
   */
  public Utente(ConnectionPool conn, Map<Object, Object> map)
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
  public Utente(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public Utente()
  {
    super();
  }

  /**
   * Costruttore con la gestione del Pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public Utente(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione della connessione selezionata
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Utente(MsSqlPool msp)
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
    this.addCampi("idUtente","UTENTE","ID_UTENTE",true);
    if (this.msp != null)
      this.getCampo("idUtente").setGenID(new Contatori(this.msp), "UTENTE");
    else
      this.getCampo("idUtente").setGenID(new Contatori(this.conn), "UTENTE");
    this.addCampi("login","UTENTE","LOGIN");
    this.addCampi("password","UTENTE","PASSWORD");
    this.addCampi("cognome","UTENTE","COGNOME");
    this.addCampi("nome","UTENTE","NOME");
    this.addCampi("idAutorizzazione","UTENTE","ID_AUTORIZZAZIONE");
    this.addCampi("dataAttivazione","UTENTE","DATAATTIVAZIONE");
    this.addCampi("dataDisattivazione","UTENTE","DATADISATTIVAZIONE");
    this.addCampi("email","UTENTE","EMAIL");
    this.addCampi("tipologiaEmail","UTENTE","TIPOLOGIAEMAIL");
    this.addCampi("azienda","UTENTE","AZIENDA");
    this.addCampi("telefono","UTENTE","TELEFONO");
    this.addCampi("superUser","UTENTE","SUPERUSER");
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
