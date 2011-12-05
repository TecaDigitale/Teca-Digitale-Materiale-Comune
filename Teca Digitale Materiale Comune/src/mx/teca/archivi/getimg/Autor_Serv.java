/**
 * 
 */
package mx.teca.archivi.getimg;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlException;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per eseguire le operazioni sulla tabella Autor_Serv
 * @author Randazzo
 *
 */
public class Autor_Serv extends Table
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
  public Autor_Serv(ConnectionPool conn, MsSqlPool msp, Map map)
  {
    super(conn, msp, map);
  }

  /**
   * Costruttore con la gestione del Pool di connessioni con lista dei parametri da caricare
   * @param conn Pool di connessioni con il Database
   * @param map Lista parametri da caricare
   */
  public Autor_Serv(ConnectionPool conn, Map map)
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
  public Autor_Serv(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public Autor_Serv()
  {
    super();
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn Poll di connessioni con il da    catch (MsSqlException e)
    {
      throw e;
    }
tabase
   */
  public Autor_Serv(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con relativa connessione pre selezionata al database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Autor_Serv(MsSqlPool msp)
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
    this.addCampi("idServizio", "AUTOR_SERV", "ID_SERVIZIO");
    this.addCampi("idServizioOld", "AUTOR_SERV", "ID_SERVIZIO", true, true, false);
    this.addCampi("idAutorizzazione", "AUTOR_SERV", "ID_AUTORIZZAZIONE", true);
  }

  /**
   * Questo metodo viene utilizzato per la cancellazione di un record
   * @see mx.database.table.Table#delete()
   */
  public int delete() throws MsSqlException
  {
    this.getCampo("idServizio").setPrimaryKey(true);
    this.getCampo("idServizioOld").setPrimaryKey(false);
    return super.delete();
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
