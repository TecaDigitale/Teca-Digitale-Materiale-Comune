/**
 * 
 */
package mx.teca.archivi.getimg;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire l'accesso alla tabella avanzamento
 * 
 * @author Randazzo
 *
 */
public class Avanzamento extends Table
{

  /**
   * Costruttore con definizione del Pool di connessione e connesione preselezionata per le 
   * operazioni di modifica della tabella
   * @param conn Poll di connessioni con il database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Avanzamento(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public Avanzamento()
  {
    super();
  }

  /**
   * Costruttore con la gestione del Pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public Avanzamento(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione della connessione selezionata
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Avanzamento(MsSqlPool msp)
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
    this.addCampi("idAvanzamento", "AVANZAMENTO", "ID_AVANZAMENTO", true);
    if (msp!=null)
      this.getCampo("idAvanzamento").setGenID(new Contatori(msp),"AVANZAMENTO");
    else
      this.getCampo("idAvanzamento").setGenID(new Contatori(conn),"AVANZAMENTO");
    this.addCampi("idFileXml", "AVANZAMENTO", "ID_FILEXML");
    this.addCampi("idStato", "AVANZAMENTO", "ID_STATO");
    this.addCampi("data", "AVANZAMENTO", "DATA");
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
