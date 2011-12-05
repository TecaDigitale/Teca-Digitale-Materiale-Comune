/**
 * 
 */
package mx.teca.archivi.getimg.view;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata per eseguire la vista tra la tabella utenti e prog_utenti
 * @author Randazzo
 *
 */
public class ViewProgUtenti extends Query
{

  public ViewProgUtenti(ConnectionPool conn, Map map)
  {
    super(conn, map);
    // TODO Auto-generated constructor stub
  }

  /**
   * Costruttore con definizione del Pool di Connessione
   * @param conn
   */
  public ViewProgUtenti(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore
   */
  public ViewProgUtenti()
  {
    super();
  }

  /**
   * Metodo utilizzato per la definizione le condizioni del form
   * @see mx.database.table.Query#defFrom()
   */
  protected void defFrom()
  {
    this.setFrom("UTENTE, PROG_UTENTI");

  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.Query#defWhere()
   */
  protected void defWhere()
  {
    this.setWhere("UTENTE.ID_UTENTE = PROG_UTENTI.ID_UTENTE");
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idUtente", "UTENTE", "ID_UTENTE");
    this.addCampi("login", "UTENTE","LOGIN");
    this.addCampi("pasword", "UTENTE","PASSWORD");
    this.addCampi("cognome","UTENTE","COGNOME");
    this.addCampi("nome", "UTENTE", "NOME");
    this.addCampi("idAutorizzazione","UTENTE","ID_AUTORIZZAZIONE");
    this.addCampi("dataAttivazione", "UTENTE","DATAATTIVAZIONE");
    this.addCampi("dataDisattivazione", "UTENTE","DATADISATTIVAZIONE");
    this.addCampi("email","UTENTE","EMAIL");
    this.addCampi("tipologiaEmail","UTENTE","TIPOLOGIAEMAIL");
    this.addCampi("idProgetto","PROG_UTENTI","ID_PROGETTO");
    this.setCampoTipoRicerca("tipologiaEmail"," in ");
  }

}
