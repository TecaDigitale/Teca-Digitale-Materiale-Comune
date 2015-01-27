/**
 * 
 */
package mx.teca.archivi.getimg;

import java.sql.ResultSet;
import java.util.GregorianCalendar;
import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlException;
import mx.database.MsSqlPool;
import mx.database.navigator.TableNavigator;
import mx.database.table.Column;

/**
 * @author Randazzo
 *
 */
public class LogProcessi extends TableNavigator
{

  /**
   * Costruttore con definizione del Pool di connessione e connesione preselezionata per le 
   * operazioni di modifica della tabella
   * @param conn Poll di connessioni con il database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public LogProcessi(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public LogProcessi(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public LogProcessi(ConnectionPool conn, Map<Object, Object> map)
  {
    super(conn, map);
  }

  /**
   * Questo metodo viene utilizzato per indicare la lista dei campi che compongono la 
   * tabella
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idLogProcessi", "LOGPROCESSI", "ID_LOGPROCESSI", true);
    if (this.msp != null)
      this.getCampo("idLogProcessi").setGenID(new Contatori(this.msp), "LOGPROCESSI");
    else
      this.getCampo("idLogProcessi").setGenID(new Contatori(this.conn), "LOGPROCESSI");
    this.addCampi("tipoLog", "LOGPROCESSI", "TIPOLOG");
    this.addCampi("numError", "LOGPROCESSI", "NUMERROR");
    this.addCampi("numWarning", "LOGPROCESSI", "NUMWARNING");
    this.addCampi("dataStart", "LOGPROCESSI", "DATASTART");
    this.addCampi("dataStop", "LOGPROCESSI", "DATASTOP");
    this.addCampi("idFileXml", "LOGPROCESSI", "ID_FILEXML");
  }

  /**
   * Questo metodo viene utilizzato per eseguire la ricerca nella tabella
   * 
   * @see mx.database.table.DataSet#startSelect()
   */
  public ResultSet startSelect()
  {
    this.getCampo("dataStart").setOrderBy(Column.ORDERBY_DESC,1);
    return super.startSelect();
  }

  /**
   * Questo metodo viene utilizzato per chiudere l'attivit� aggiorndo il record della tabella logProcessi aggiornando
   * il campo dataStop
   *
   */
  public void closeAttivita() throws MsSqlException
  {
    this.setCampoValue("dataStop", new GregorianCalendar());
    this.update();
  }

	/**
	 * @see mx.database.table.Table#insert()
	 */
	public int insert() throws MsSqlException
	{
    this.setCampoValue("dataStart", new GregorianCalendar());
		return super.insert();
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
