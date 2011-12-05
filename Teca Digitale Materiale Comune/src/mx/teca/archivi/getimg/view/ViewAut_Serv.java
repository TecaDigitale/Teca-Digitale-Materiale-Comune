/**
 * 
 */
package mx.teca.archivi.getimg.view;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.navigator.QueryNavigator;

/**
 * Questa classe viene utilizzata per eseguire le ricerca sulla tabella Aut_Serv e correlate
 * @author Randazzo
 *
 */
public class ViewAut_Serv extends QueryNavigator
{

  /**
   * Costruttore
   */
  public ViewAut_Serv()
  {
    super();
  }

  /**
   * @param conn
   */
  public ViewAut_Serv(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * @param conn
   * @param map
   */
  public ViewAut_Serv(ConnectionPool conn, Map map)
  {
    super(conn, map);
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.Query#defFrom()
   */
  protected void defFrom()
  {
    this.setFrom("AUTOR_SERV");
    this.addFrom(conn.genJoinLeft("AUTORIZZAZIONE", "AUTOR_SERV.ID_AUTORIZZAZIONE = AUTORIZZAZIONE.ID_AUTORIZZAZIONE"));
    this.addFrom(conn.genJoinLeft("SERVIZIO", "AUTOR_SERV.ID_SERVIZIO = SERVIZIO.ID_SERVIZIO"));
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.Query#defWhere()
   */
  protected void defWhere()
  {
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("descAutorizzazione", "AUTORIZZAZIONE", "DESC_AUTORIZZAZIONE");
    this.addCampi("idAutorizzazione", "AUTOR_SERV", "ID_AUTORIZZAZIONE");
    this.addCampi("idServizio", "AUTOR_SERV","ID_SERVIZIO");
    this.addCampi("codServizio", "SERVIZIO", "COD_SERVIZIO");
    this.addCampi("descServizio", "SERVIZIO", "DESC_SERVIZIO");
  }

}
