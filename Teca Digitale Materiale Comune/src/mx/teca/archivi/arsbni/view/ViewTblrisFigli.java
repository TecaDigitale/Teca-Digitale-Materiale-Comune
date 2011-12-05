/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import java.sql.ResultSet;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Query;

/**
 * @author Randazzo
 *
 */
public class ViewTblrisFigli extends Query
{

  /**
   * @param conn
   */
  public ViewTblrisFigli(ConnectionPool conn)
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
    this.setWhere("TBLRIS.RISIDR = TBLRELRIS.RELRISIDRPARTENZA");
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idr", "TBLRELRIS", "RELRISIDRPARTENZA");
    this.addCampi("idrp", "TBLRELRIS", "RELRISIDRARRIVO", false, true, false);
    this.addCampi("seq", "TBLRELRIS", "RELRISSEQUENZA");
    this.getCampo("seq").setOrderBy(1, Column.ORDERBY_CRES);
    this.addCampi("nota", "TBLRIS", "RISNOTAPUB");
    this.addCampi("noteImg", "TBLRELRIS", "RELRISNOTE");
    this.addCampi("tipoRelId", "TBLRELRIS", "TIPORELID", false, true, false);
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.DataSet#startSelect()
   */
  public ResultSet startSelect()
  {
  	if (this.getCampo("tipoRelId").isEmpty())
  		this.setCampoValue("tipoRelId", 1);
  	return super.startSelect();
  }

}
