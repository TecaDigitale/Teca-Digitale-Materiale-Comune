/**
 * 
 */
package mx.teca.archivi.getimg;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per eseguire la Query per la compressione
 * @author Randazzo
 *
 */
public class Compressione extends Table
{

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn
   */
  public Compressione(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore
   */
  public Compressione()
  {
    super();
  }

  /**
   * Gestione dei campi dellatabella
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idCompressione", "COMPRESSIONE", "ID_COMPRESSIONE");
    this.addCampi("descCompressione", "COMPRESSIONE", "DESC_COMPRESSIONE");
    this.getCampo("descCompressione").setOrderBy(1,Column.ORDERBY_CRES);
  }

	protected void postUpdate()
	{
	}

	protected void preUpdate()
	{
	}

}
