/**
 * 
 */
package mx.teca.archivi.getimg;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per eseguire la Query per la tipologia delle immagini
 * @author Randazzo
 *
 */
public class TipologiaImmagini extends Table
{

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn
   */
  public TipologiaImmagini(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore
   */
  public TipologiaImmagini()
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
    this.addCampi("idTipologiaImmagini", "TIPOLOGIAIMMAGINI", "ID_TIPOLOGIAIMMAGINI");
    this.addCampi("tipo", "TIPOLOGIAIMMAGINI", "TIPO");
    this.getCampo("tipo").setOrderBy(1,Column.ORDERBY_CRES);
    this.addCampi("mimeType", "TIPOLOGIAIMMAGINI", "MIMETYPE");
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
