/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.sql.ResultSet;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * @author Randazzo
 *
 */
public class Tblris extends Table
{

	public Tblris(MsSqlPool msp)
	{
		super(msp);
	}

	private Object note = null;
	
	/**
   * Costruttore
   * @param conn
   */
  public Tblris(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("risidr", "TBLRIS", "RISIDR", true);
    this.addCampi("risNotaPub", "TBLRIS", "RISNOTAPUB");
    this.addCampi("risLivello", "TBLRIS", "RISLIVELLO");
    this.addCampi("bid", "","", false, false, false);
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.DataSet#startSelect()
   */
  public ResultSet startSelect()
  {
    if (this.getCampoValue("bid") != null)
    {
      this.setWhere("TBLRIS.RISIDR in (select TBLRELRIS.RELRISIDRPARTENZA " +
                                        "from TBLRELRIS, TBLLEGNOT " +
                                       "where TBLLEGNOT.LEGNOTBID = '" + ((String)this.getCampoValue("bid")).toUpperCase() + "' and "+
                                             "TBLRELRIS.RELRISIDRARRIVO = TBLLEGNOT.RIDIDR)");

    }
    return super.startSelect();
  }
	
	protected void preUpdate()
	{
		note = this.getCampoValue("risNotaPub");
		if (this.get("risNotaPub").length()>255)
			this.setCampoValue("risNotaPub", this.get("risNotaPub").subSequence(0, 255));
	}
	
	protected void postUpdate()
	{
		this.setCampoValue("risNotaPub", note);
	}
}
