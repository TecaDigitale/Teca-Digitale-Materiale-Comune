/**
 * 
 */
package mx.teca.archivi.getimg;

import mx.database.ConnectionPool;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per eseguire la Query per la tipologia delle immagini
 * @author Randazzo
 *
 */
public class TipoloCompr extends Table
{

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn
   */
  public TipoloCompr(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Gestione dei campi dellatabella
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idTipologiaImmagini", "TIPOLO_COMPR", "ID_TIPOLOGIAIMMAGINI", true);
    this.addCampi("idCompressione", "TIPOLO_COMPR", "ID_COMPRESSIONE", true);
  }

	protected void postUpdate()
	{
	}

	protected void preUpdate()
	{
	}

}
