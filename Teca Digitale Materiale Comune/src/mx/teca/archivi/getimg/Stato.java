/**
 * 
 */
package mx.teca.archivi.getimg;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Column;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire la tabella dello stato
 * @author Randazzo
 *
 */
public class Stato extends Table
{

  /**
   * Costruttore con definizione del Pool di connessione e connesione preselezionata per le 
   * operazioni di modifica della tabella
   * @param conn Poll di connessioni con il database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Stato(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public Stato()
  {
    super();
  }

  /**
   * Costruttore con la gestione del Pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public Stato(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione della connessione selezionata
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Stato(MsSqlPool msp)
  {
    super(msp);
  }

  /**
   * Questo metodo viene utilizzato per indicare la lista dei campi della tabella
   * 
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idStato", "STATO", "ID_STATO", true);
    this.addCampi("descStato", "STATO", "DESC_STATO");
    this.getCampo("descStato").setOrderBy(1,Column.ORDERBY_CRES);
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
