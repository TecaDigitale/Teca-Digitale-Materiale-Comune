/**
 * 
 */
package mx.teca.archivi.getimg;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.navigator.TableNavigator;

/**
 * Questa Tabella viene utilizzata per gestire la tabella Prog_Utenti
 * @author Randazzo
 *
 */
public class Prog_Utenti extends TableNavigator
{

  /**
   * Costruttore con definizione del Pool di connessione e connesione preselezionata per le 
   * operazioni di modifica della tabella
   * @param conn Poll di connessioni con il database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Prog_Utenti(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public Prog_Utenti()
  {
    super();
  }

  /**
   * Costruttore con la gestione del Pool di Connessioni
   * @param conn
   */
  public Prog_Utenti(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione della connessione precedentemente selezionata
   * @param msp
   */
  public Prog_Utenti(MsSqlPool msp)
  {
    super(msp);
  }

  /**
   *Questo metodo viene utilizzato per indicare i campi di ricerca
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
  	this.setColKey(true);
    this.addCampi("idProgetto", "PROG_UTENTI", "ID_PROGETTO", true);
    this.addCampi("idUtente", "PROG_UTENTI", "ID_UTENTE", true);

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
