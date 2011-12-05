/**
 * 
 */
package mx.teca.archivi.getimg;

import java.sql.ResultSet;
import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.navigator.TableNavigator;

/**
 * Questa classe viene utilizzata per gestire l'acesso alla tabella per l'assegnazione dello
 * Spazio disco
 * @author Randazzo
 *
 */
public class AsseSpazioDisco extends TableNavigator
{

  public AsseSpazioDisco(ConnectionPool conn, Map map)
  {
    super(conn, map);
    // TODO Auto-generated constructor stub
  }

  /**
   * Costruttore con definizione del Pool di connessione e connesione preselezionata per le 
   * operazioni di modifica della tabella
   * @param conn Poll di connessioni con il database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public AsseSpazioDisco(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public AsseSpazioDisco()
  {
    super();
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn
   */
  public AsseSpazioDisco(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione della connessione selezionata
   * @param msp
   */
  public AsseSpazioDisco(MsSqlPool msp)
  {
    super(msp);
  }

  /**
   * Gestione dei campi dellatabella
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idAsseSpazioDisco", "ASSESPAZIODISCO", "ID_ASSESPAZIODISCO", true);
    if (msp!=null)
      this.getCampo("idAsseSpazioDisco").setGenID(new Contatori(msp),"ASSESPAZIODISCO");
    else
      this.getCampo("idAsseSpazioDisco").setGenID(new Contatori(conn),"ASSESPAZIODISCO");
    this.addCampi("idServer", "ASSESPAZIODISCO", "ID_SERVER");
    this.addCampi("idProgetto", "ASSESPAZIODISCO", "ID_PROGETTO");
    this.addCampi("idUtente", "ASSESPAZIODISCO", "ID_UTENTE");
    this.addCampi("loginFtp", "ASSESPAZIODISCO", "LOGINFTP");
    this.addCampi("passwordFtp", "ASSESPAZIODISCO", "PASSWORDFTP");
    this.addCampi("nomeCartella", "ASSESPAZIODISCO", "NOMECARTELLA");
    this.addCampi("note", "ASSESPAZIODISCO", "NOTE");
    this.addCampi("descrizione", "ASSESPAZIODISCO", "DESCRIZIONE");
    this.addCampi("portaFtp", "ASSESPAZIODISCO", "PORTAFTP");
    this.addCampi("protocolloHost", "ASSESPAZIODISCO", "PROTOCOLLOHOST");
  }

	protected void postUpdate()
	{
	}

	protected void preUpdate()
	{
	}

	public ResultSet startSelect(boolean oldVersionDB)
	{
		if (oldVersionDB)
		{
			this.removeCampo("protocolloHost");
			this.removeCampo("portaFtp");
		}
		return super.startSelect();
	}
}
