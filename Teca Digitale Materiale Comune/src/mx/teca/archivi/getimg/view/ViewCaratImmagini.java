/**
 * 
 */
package mx.teca.archivi.getimg.view;

import mx.database.ConnectionPool;
import mx.database.navigator.QueryNavigator;

/**
 * Questa classe viene utilizzata per gestire la lista della tabella carat_Immagini
 * @author Randazzo
 *
 */
public class ViewCaratImmagini extends QueryNavigator
{

  /**
   * Costruttore
   */
  public ViewCaratImmagini()
  {
    super();
  }

  /**
   * Costruttore con la gestione del Pool di connessioni
   * 
   * @param conn
   *          Pool di connessioni
   */
  public ViewCaratImmagini(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Questo metodo viene utilizzato per indicare le condizione della form
   * 
   * @see mx.database.table.Query#defFrom()
   */
  protected void defFrom()
  {
    this.setFrom("CARAT_IMMAGINI");
    this
        .addFrom(conn.genJoinLeft("COMPRESSIONE", "CARAT_IMMAGINI.ID_COMPRESSIONE = COMPRESSIONE.ID_COMPRESSIONE"));
    this
    .addFrom(conn.genJoinLeft("TIPOLOGIAIMMAGINI", "CARAT_IMMAGINI.ID_TIPOLOGIAIMMAGINI = TIPOLOGIAIMMAGINI.ID_TIPOLOGIAIMMAGINI"));
  }

  /**
   * Questo metodo viene utilizzato per indicare le condizioni di where di
   * default della vista
   * 
   * @see mx.database.table.Query#defWhere()
   */
  protected void defWhere()
  {

  }

  /**
   * Quesgto metodo viene utilizzato per indicare i campi della vista
   * 
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idCaratImmagini", "CARAT_IMMAGINI", "ID_CARAT_IMMAGINI");
    this.addCampi("idProgetto", "CARAT_IMMAGINI", "ID_PROGETTO");
    this.addCampi("risoluzione", "CARAT_IMMAGINI", "RISOLUZIONE");
    this.addCampi("idTipologiaImmagini", "CARAT_IMMAGINI", "ID_TIPOLOGIAIMMAGINI");
    this.addCampi("idCompressione", "CARAT_IMMAGINI", "ID_COMPRESSIONE");
    this.addCampi("note", "CARAT_IMMAGINI", "NOTE");
    this.addCampi("descCompressione", "COMPRESSIONE", "DESC_COMPRESSIONE");
    this.addCampi("tipo", "TIPOLOGIAIMMAGINI", "TIPO");
  }

  /**
   * Questa funzione viene utilizzata dalle pagine Jsp per la valorizzazione del campi 
   * id_Progetto
   * @param idProgetto codice id del progetto
   */
  public void setIdProgetto(int idProgetto)
  {
    this.setCampoValue("idProgetto", idProgetto);
  }
}
