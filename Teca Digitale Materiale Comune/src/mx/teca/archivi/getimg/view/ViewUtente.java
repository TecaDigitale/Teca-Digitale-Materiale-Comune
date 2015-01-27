/**
 * 
 */
package mx.teca.archivi.getimg.view;

import java.sql.ResultSet;
import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.navigator.QueryNavigator;
import mx.database.table.Column;

/**
 * Questa classe viene utilizzata per eseguire le query sulla tabella Utente
 * @author Randazzo
 *
 */
public class ViewUtente extends QueryNavigator
{

  String flagChiusura = "";
  
  /**
   * Costruttore
   */
  public ViewUtente()
  {
    super();
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public ViewUtente(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn Poll di connessioni con il database
   * @param map
   */
  public ViewUtente(ConnectionPool conn, Map<Object, Object> map)
  {
    super(conn, map);
  }

  /**
   * Questo metodo viene utilizzato per indicare le condizioni di Form della vista
   * @see mx.database.table.Query#defFrom()
   */
  protected void defFrom()
  {
    this.setFrom("UTENTE");
    this.addFrom(conn.genJoinLeft("AUTORIZZAZIONE", "AUTORIZZAZIONE.ID_AUTORIZZAZIONE = UTENTE.ID_AUTORIZZAZIONE"));
  }

  /**
   * Questo metodo viene utilizzato per indicare le condizioni di Where della vista di Default
   * @see mx.database.table.Query#defWhere()
   */
  protected void defWhere()
  {
  }

  /**
   * Questo metodo viene utilizzato per indicare la lista dei campi che compongono la 
   * tabella
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idAutorizzazione", "AUTORIZZAZIONE", "ID_AUTORIZZAZIONE");
    this.addCampi("descAutorizzazione", "AUTORIZZAZIONE","DESC_AUTORIZZAZIONE");
    this.addCampi("superUser", "AUTORIZZAZIONE", "SUPERUSER");
    this.addCampi("idUtente", "UTENTE", "ID_UTENTE");
    this.addCampi("login", "UTENTE", "LOGIN");
    this.addCampi("password", "UTENTE", "PASSWORD");
    this.addCampi("cognome", "UTENTE", "COGNOME");
    this.addCampi("nome", "UTENTE", "NOME");
    this.addCampi("dataAttivazione", "UTENTE", "DATAATTIVAZIONE");
    this.addCampi("dataDisattivazione", "UTENTE", "DATADISATTIVAZIONE");
    this.addCampi("email", "UTENTE", "EMAIL");
    this.addCampi("tipologiaEmail", "UTENTE", "TIPOLOGIAEMAIL");
    this.addCampi("azienda", "UTENTE", "AZIENDA");
    this.addCampi("telefono", "UTENTE", "TELEFONO");
  }

  /**
   * Questo metodo viene utilizzato per eseguire l'operazione di Select
   * @see mx.database.navigator.QueryNavigator#startSelect()
   */
  public ResultSet startSelect()
  {
    if (!this.getCampo("cognome").isEmpty())
    {
      this.getCampo("cognome").setTipoRicerca("LIKE");
      this.setCampoValue("cognome",this.getCampoValue("cognome")+"%");
    }
    
    if (!this.getCampo("nome").isEmpty())
    {
      this.getCampo("nome").setTipoRicerca("LIKE");
      this.setCampoValue("nome",this.getCampoValue("nome")+"%");
    }
      
    if (!this.getCampo("login").isEmpty())
    {
      this.getCampo("login").setTipoRicerca("LIKE");
      this.setCampoValue("login",this.getCampoValue("login")+"%");
    }

    if (this.intValue("idAutorizzazione")==0)
      this.setCampoValue("idAutorizzazione",null);

    if(flagChiusura.equals("2"))
      this.setWhere("DATADISATTIVAZIONE IS NULL");

    this.getCampo("cognome").setOrderBy(1,Column.ORDERBY_CRES);
    this.getCampo("nome").setOrderBy(2,Column.ORDERBY_CRES);
    return super.startSelect();
  }

  /**
   * @param flagChiusura The flagChiusura to set.
   */
  public void setFlagChiusura(String flagChiusura)
  {
    this.flagChiusura = flagChiusura;
  }

}
