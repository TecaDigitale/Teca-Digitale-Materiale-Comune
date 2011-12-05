/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import mx.database.ConnectionPool;
import mx.database.table.Query;

/**
 * @author Randazzo
 *
 */
public class ViewTblrisPadre extends Query
{

  /**
   * @param conn
   */
  public ViewTblrisPadre(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.Query#defFrom()
   */
  protected void defFrom()
  {
    this.setFrom("TBLRIS, TBLRELRIS");
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.Query#defWhere()
   */
  protected void defWhere()
  {
    this.setWhere("TBLRELRIS.TIPORELID = 1 and TBLRIS.RISIDR = TBLRELRIS.RELRISIDARRIVO");
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idr", "TBLRELRIS", "RELRISIDRPARTENZA", false, true, false);
    this.addCampi("idrp", "TBLRELRIS", "RELRISIDRARRIVO");
    this.addCampi("nota", "TBLRIS", "RISNOTAPUB");
  }

}
