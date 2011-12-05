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
 * Questa classe viene utilizzata per eseguire una vista sulla tabella Server
 * @author Randazzo Massimiliano
 *
 */
public class ViewServer extends QueryNavigator
{

  /**
   * Costruttore
   */
  public ViewServer()
  {
    super();
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public ViewServer(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public ViewServer(ConnectionPool conn, Map map)
  {
    super(conn, map);
  }

  /**
   * Questo metodo viene utilizzato per indicare le condizioni di Form della vista
   * @see mx.database.table.Query#defFrom()
   */
  protected void defFrom()
  {
    this.setFrom("SERVER");
    this.addFrom(conn.genJoinLeft("SERVERNAME", "SERVER.ID_SERVERNAME = SERVERNAME.ID_SERVERNAME"));
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
    this.addCampi("idServer", "SERVER", "ID_SERVER");
    this.addCampi("pathAreaMemoria", "SERVER", "PATHAREAMEMORIA");
    this.addCampi("descServer", "SERVER","DESC_SERVER");
    this.addCampi("idServerName", "SERVER","ID_SERVERNAME");
    this.addCampi("indirizzoIp", "SERVERNAME","INDIRIZZOIP");
    this.addCampi("portaDemone", "SERVERNAME","PORTADEMONE");
  }

  /**
   * Questo metodo viene utilizzato per eseguire la ricerca della vista
   * @see mx.database.navigator.QueryNavigator#startSelect()
   */
  public ResultSet startSelect()
  {
    if (!this.getCampo("indirizzoIp").isEmpty())
    {
      this.getCampo("indirizzoIp").setTipoRicerca("LIKE");
      this.setCampoValue("indirizzoIp", this.get("indirizzoIp")+"%");
    }
      
    if (!this.getCampo("descServer").isEmpty())
    {
      this.getCampo("descServer").setTipoRicerca("LIKE");
      this.setCampoValue("descServer", this.get("descServer")+"%");
    }
    
    this.getCampo("indirizzoIp").setOrderBy(1, Column.ORDERBY_CRES);
    return super.startSelect();
  }
}
