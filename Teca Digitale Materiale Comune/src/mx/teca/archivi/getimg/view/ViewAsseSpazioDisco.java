/**
 * 
 */
package mx.teca.archivi.getimg.view;

import java.sql.ResultSet;
import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.navigator.QueryNavigator;

/**
 * Questa classe viene utilizzata per gestire la lista della tabella asseSpazioDisco
 * @author Randazzo
 *
 */
public class ViewAsseSpazioDisco extends QueryNavigator
{

  /**
   * Costruttore
   */
  public ViewAsseSpazioDisco()
  {
    super();
  }

  /**
   * Costruttore con la gestione del Pool di connessioni
   * 
   * @param conn
   *          Pool di connessioni
   */
  public ViewAsseSpazioDisco(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione del Pool di connessioni con lista dei parametri da caricare
   * @param conn Pool di connessioni con il Database
   * @param map Lista parametri da caricare
   */
  public ViewAsseSpazioDisco(ConnectionPool conn, Map map)
  {
    super(conn, map);
  }

  /**
   * Questo metodo viene utilizzato per indicare le condizione della form
   * 
   * @see mx.database.table.Query#defFrom()
   */
  protected void defFrom()
  {
    this.setFrom("ASSESPAZIODISCO");
    this
        .addFrom(conn.genJoinLeft("SERVER", "ASSESPAZIODISCO.ID_SERVER = SERVER.ID_SERVER"));
    this
    .addFrom(conn.genJoinLeft("PROGETTO", "ASSESPAZIODISCO.ID_PROGETTO = PROGETTO.ID_PROGETTO"));
    this
    .addFrom(conn.genJoinLeft("UTENTE", "ASSESPAZIODISCO.ID_UTENTE = UTENTE.ID_UTENTE"));
    this
    .addFrom(conn.genJoinLeft("SERVERNAME", "SERVER.ID_SERVERNAME = SERVERNAME.ID_SERVERNAME"));
  }

  /**
   * Questo metodo viene utilizzato per indicare le condizioni di where di
   * default della vista
   * 
   * @see mx.database.table.Query#defWhere()
   */
  protected void defWhere()
  {

  }

  /**
   * Quesgto metodo viene utilizzato per indicare i campi della vista
   * 
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idAsseSpazioDisco", "ASSESPAZIODISCO", "ID_ASSESPAZIODISCO");
    this.addCampi("idServer", "ASSESPAZIODISCO", "ID_SERVER");
    this.addCampi("idProgetto", "ASSESPAZIODISCO", "ID_PROGETTO");
    this.addCampi("idUtente", "ASSESPAZIODISCO", "ID_UTENTE");
    this.addCampi("loginFtp", "ASSESPAZIODISCO", "LOGINFTP");
    this.addCampi("passwordFtp", "ASSESPAZIODISCO", "PASSWORDFTP");
    this.addCampi("nomeCartella", "ASSESPAZIODISCO", "NOMECARTELLA");
    this.addCampi("note", "ASSESPAZIODISCO", "NOTE");
    this.addCampi("descrizione", "ASSESPAZIODISCO", "DESCRIZIONE");
    this.addCampi("idServerName", "SERVER", "ID_SERVERNAME");
    this.addCampi("pathAreaMemoria", "SERVER", "PATHAREAMEMORIA");
    this.addCampi("nomeProgetto", "PROGETTO", "NOMEPROGETTO");
    this.getCampo("nomeProgetto").setTipoRicerca("LIKE");
    this.addCampi("nome", "UTENTE", "NOME");
    this.addCampi("cognome", "UTENTE", "COGNOME");
    this.addCampi("indirizzoIp", "SERVERNAME", "INDIRIZZOIP");
  }

  
  /**
   * (non-Javadoc)
   * @see mx.database.navigator.QueryNavigator#startSelect()
   */
  public ResultSet startSelect()
  {
    if (!this.getCampo("nomeProgetto").isEmpty())
      this.setCampoValue("nomeProgetto",this.get("nomeProgetto")+"%");
    return super.startSelect();
  }

  /**
   * Questa funzione viene utilizzata dalle pagine Jsp per la valorizzazione del campi 
   * nomeProgetto
   * @param nomeProgetto nome id del progetto
   */
  public void setNomeProgetto(String nomeProgetto)
  {
    this.setCampoValue("nomeProgetto", nomeProgetto);
  }
}
