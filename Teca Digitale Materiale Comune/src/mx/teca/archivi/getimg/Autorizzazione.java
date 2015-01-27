/**
 * 
 */
package mx.teca.archivi.getimg;

import java.sql.ResultSet;
import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.navigator.TableNavigator;
import mx.database.table.Column;

/**
 * Questa classe viene utilizzata per la gestione della tabella Autorizzazione
 * @author Randazzo
 *
 */
public class Autorizzazione extends TableNavigator
{

  /**
   * Costruttore con definizione del Pool di connessione e connesione
   * preselezionata per le operazioni di modifica della tabella con lista dei parametri da caricare
   * 
   * @param conn
   *          Poll di connessioni con il database
   * @param msp
   *          Connessione da utilizzare nelle operazioni di modifica della
   *          tabella (insert, update, delete)
   * @param map Lista parametri da caricare
   */
  public Autorizzazione(ConnectionPool conn, MsSqlPool msp, Map<Object, Object> map)
  {
    super(conn, msp, map);
  }

  /**
   * Costruttore con la gestione del Pool di connessioni con lista dei parametri da caricare
   * @param conn Pool di connessioni con il Database
   * @param map Lista parametri da caricare
   */
  public Autorizzazione(ConnectionPool conn, Map<Object, Object> map)
  {
    super(conn, map);
  }

  /**
   * Costruttore con definizione del Pool di connessione e connesione preselezionata per le 
   * operazioni di modifica della tabella
   * @param conn Poll di connessioni con il database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Autorizzazione(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public Autorizzazione()
  {
    super();
  }

  /**
   * Costruttore con la gestione del Pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public Autorizzazione(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione della connessione selezionata
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Autorizzazione(MsSqlPool msp)
  {
    super(msp);
  }

  /**
   * Questo metodo viene utilizzato per indicare la lista dei campi che compongono la 
   * tabella
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idAutorizzazione", "AUTORIZZAZIONE", "ID_AUTORIZZAZIONE",true);
    if (this.msp != null)
      this.getCampo("idAutorizzazione").setGenID(new Contatori(this.msp), "AUTORIZZAZIONE");
    else
      this.getCampo("idAutorizzazione").setGenID(new Contatori(this.conn), "AUTORIZZAZIONE");
    this.addCampi("descAutorizzazione","AUTORIZZAZIONE","DESC_AUTORIZZAZIONE");
    this.addCampi("superUser","AUTORIZZAZIONE","SUPERUSER");
  }

  /**
   * Questo metodo viene utilizzato per eseguire la select sul database
   * @see mx.database.table.DataSet#startSelect()
   */
  public ResultSet startSelect()
  {
    if (!this.getCampo("descAutorizzazione").isEmpty())
    {
    	this.getCampo("descAutorizzazione").setTipoRicerca("like");
      this.setCampoValue("descAutorizzazione",this.getCampoValue("descAutorizzazione")+"%");
    }
    this.getCampo("descAutorizzazione").setOrderBy(1, Column.ORDERBY_CRES);
    return super.startSelect();
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
