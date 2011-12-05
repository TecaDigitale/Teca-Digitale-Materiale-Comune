/**
 * 
 */
package mx.teca.archivi.getimg.view;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.navigator.QueryNavigator;

/**
 * Questa classe viene utilizzata per gestire una vista relativa al file Xml
 * 
 * @author Randazzo
 * 
 */
public class ViewFileXml extends QueryNavigator
{

  /**
   * Costruttore con la gestione del Pool di connessioni
   * 
   * @param conn
   *          Pool di connessioni
   */
  public ViewFileXml(ConnectionPool conn, Map map)
  {
    super(conn, map);
  }

  /**
   * Costruttore con la gestione del Pool di connessioni
   * 
   * @param conn
   *          Pool di connessioni
   */
  public ViewFileXml(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore
   */
  public ViewFileXml()
  {
    super();
  }

  /**
   * Questo metodo viene utilizzato per indicare le condizione della form
   * 
   * @see mx.database.table.Query#defFrom()
   */
  protected void defFrom()
  {
    this.setFrom("FILEXML");
    this
        .addFrom(conn.genJoinLeft("STATO", "FILEXML.ID_STATO = STATO.ID_STATO"));
    this.addFrom(conn.genJoinLeft("ASSESPAZIODISCO",
        "FILEXML.ID_ASSESPAZIODISCO = ASSESPAZIODISCO.ID_ASSESPAZIODISCO"));
    this
        .addFrom(conn
            .genJoinLeft("TIPOLOGIAMATERIALE",
                "FILEXML.ID_TIPOLOGIAMATERIALE = TIPOLOGIAMATERIALE.ID_TIPOLOGIAMATERIALE"));
    this.addFrom(conn.genJoinLeft("SERVER",
        "ASSESPAZIODISCO.ID_SERVER = SERVER.ID_SERVER"));
    this.addFrom(conn.genJoinLeft("SERVERNAME",
        "SERVER.ID_SERVERNAME = SERVERNAME.ID_SERVERNAME"));
    this.addFrom(conn.genJoinLeft("PROGETTO",
        "ASSESPAZIODISCO.ID_PROGETTO = PROGETTO.ID_PROGETTO"));
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
    this.addCampi("idFileXml", "FILEXML", "ID_FILEXML");
    this.addCampi("nomeFile", "FILEXML", "NOMEFILE");
    this.addCampi("idAsseSpazioDisco", "FILEXML", "ID_ASSESPAZIODISCO");
    this.addCampi("idStato", "FILEXML", "ID_STATO");
    this.addCampi("descStato", "STATO", "DESC_STATO");
    this.addCampi("idTipologiaMateriale", "FILEXML", "ID_TIPOLOGIAMATERIALE");
    this.addCampi("descTipologiaMateriale", "TIPOLOGIAMATERIALE",
        "DESC_TIPOLOGIAMATERIALE");
    this.addCampi("keyTipologiaMateriale", "TIPOLOGIAMATERIALE",
    "KEY_TIPOLOGIAMATERIALE");
    this.addCampi("supArchivio", "FILEXML", "SUP_ARCHIVIO");
    this.addCampi("munSupArchivio", "FILEXML", "NUMSUP_ARCHIVIO");
    this.addCampi("supIntranet", "FILEXML", "SUP_INTRANET");
    this.addCampi("munSupIntranet", "FILEXML", "NUMSUP_INTRANET");
    this.addCampi("supInternet", "FILEXML", "SUP_INTERNET");
    this.addCampi("munSupInternet", "FILEXML", "NUMSUP_INTERNET");
    this.addCampi("forceImp", "FILEXML", "FORCEIMP");
    this.addCampi("idServer", "ASSESPAZIODISCO", "ID_SERVER");
    this.addCampi("idProgetto", "ASSESPAZIODISCO", "ID_PROGETTO");
    this.addCampi("idUtente", "ASSESPAZIODISCO", "ID_UTENTE");
    this.addCampi("loginFtp", "ASSESPAZIODISCO", "LOGINFTP");
    this.addCampi("passwordFtp", "ASSESPAZIODISCO", "PASSWORDFTP");
    this.addCampi("nomeCartella", "ASSESPAZIODISCO", "NOMECARTELLA");
    this.addCampi("portaFtp", "ASSESPAZIODISCO", "PORTAFTP");
    this.addCampi("protocolloHost", "ASSESPAZIODISCO", "PROTOCOLLOHOST");
    this.addCampi("indirizzoIp", "SERVERNAME", "INDIRIZZOIP");
    this.addCampi("idServerName", "SERVER", "ID_SERVERNAME");
    this.addCampi("pathAreaMemoria", "SERVER", "PATHAREAMEMORIA");
    this.addCampi("descServer", "SERVER", "DESC_SERVER");
    this.addCampi("portaDemone", "SERVERNAME", "PORTADEMONE");
    this.addCampi("nomeProgetto", "PROGETTO", "NOMEPROGETTO");
    this.addCampi("dataChiusura", "PROGETTO", "DATACHIUSURA");
    this.addCampi("bid", "FILEXML", "BID");
    this.addCampi("idr", "FILEXML", "IDR");
    this.addCampi("importArchivio", "PROGETTO", "IMPORTARCHIVIO");
    this.addCampi("importIntranet", "PROGETTO", "IMPORTINTRANET");
    this.addCampi("importInternet", "PROGETTO", "IMPORTINTERNET");
    this.addCampi("idTblOaiSet", "PROGETTO", "ID_TBLOAISET");
  }

}
