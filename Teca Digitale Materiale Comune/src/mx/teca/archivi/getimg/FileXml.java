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
 * Questa classe viene utilizzata per gestire la tabella FileXml
 * 
 * @author Randazzo
 *
 */
public class FileXml extends Table
{

	private boolean insAvanzamento = true;

	public FileXml(ConnectionPool conn, MsSqlPool msp, Map<Object, Object> map)
	{
		super(conn, msp, map);
	}

	public FileXml(ConnectionPool conn, Map<Object, Object> map)
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
  public FileXml(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }

  /**
   * Costruttore
   */
  public FileXml()
  {
    super();
  }

  /**
   * Costruttore con la gestione del Pool di connessioni
   * @param conn Poll di connessioni con il database
   */
  public FileXml(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con la gestione della connessione selezionata
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public FileXml(MsSqlPool msp)
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
    this.addCampi("idFileXml", "FILEXML", "ID_FILEXML", true);
    if (msp!=null)
      this.getCampo("idFileXml").setGenID(new Contatori(msp),"FILEXML");
    else
      this.getCampo("idFileXml").setGenID(new Contatori(conn),"FILEXML");
    this.addCampi("nomeFile", "FILEXML", "NOMEFILE");
    this.addCampi("idAsseSpazioDisco", "FILEXML", "ID_ASSESPAZIODISCO");
    this.addCampi("idStato", "FILEXML", "ID_STATO");
    this.addCampi("idTipologiaMateriale", "FILEXML", "ID_TIPOLOGIAMATERIALE");
    this.addCampi("supArchivio", "FILEXML", "SUP_ARCHIVIO");
    this.addCampi("numSupArchivio", "FILEXML", "NUMSUP_ARCHIVIO");
    this.addCampi("supIntranet", "FILEXML", "SUP_INTRANET");
    this.addCampi("numSupIntranet", "FILEXML", "NUMSUP_INTRANET");
    this.addCampi("supInternet", "FILEXML", "SUP_INTERNET");
    this.addCampi("numSupInternet", "FILEXML", "NUMSUP_INTERNET");
    this.addCampi("forceImp", "FILEXML", "FORCEIMP");
    this.addCampi("bid", "FILEXML", "BID");
    this.addCampi("idr", "FILEXML", "IDR");
  }

  /**
   * Questo metodo viene utilizzato per eseguire l'inserimento nella tabella
   * @see mx.database.table.Table#insert()
   */
  public int insert() throws MsSqlException
  {
    int ris = 0;
    Avanzamento avanzamento = null;
    
    ris = super.insert();
    if (insAvanzamento)
    {
      avanzamento = new Avanzamento(conn, msp);
      avanzamento.setCampoValue("idFileXml",this.getCampoValue("idFileXml"));
      avanzamento.setCampoValue("idStato",this.getCampoValue("idStato"));
      ris += avanzamento.insert();
    }
    return ris;
  }

  /**
   * Questo metodo viene utilizzato per aggiornare lo stato della richiesta
   * @return Numero record aggiornae inseriti
   * @throws MsSqlException
   */
  public int updateStato() throws MsSqlException
  {
    int ris = 0;
    Avanzamento avanzamento = null;
    
    ris = super.update();
    if (insAvanzamento)
    {
      avanzamento = new Avanzamento(conn, msp);
      avanzamento.setCampoValue("idFileXml",this.getCampoValue("idFileXml"));
      avanzamento.setCampoValue("idStato",this.getCampoValue("idStato"));
      ris += avanzamento.insert();
    }
    return ris;
  }

  /**
   * Questo metodo viene utilizzato mettere il flag di pubblicato a tutte le
   * immagini
   */
  public void pubAllImg() throws MsSqlException
  {
    this.esegui("update FileXml set id_Stato='PUB' where id_Stato='FLU'");
  }

  public String printSup(String tipo, String supValue, int numSupValue)
  {
  	String ris = "";
  	if (supValue == null)
  		supValue = "";
  	ris = "<td class=\"testo\">Sup. "+tipo+":</td>";
  	ris += "<td class=\"testo\">";
  	ris += "<select name=\"sup"+tipo+"\" class=\"testo\">";
    ris += "<option value=\"\">Selezionare</option>";
  	ris += "<option value=\"C\""+(supValue.equals("C")?" selected":"")+">Cd-Rom</option>";
  	ris += "<option value=\"D\""+(supValue.equals("D")?" selected":"")+">Dvd</option>";
  	ris += "<option value=\"H\""+(supValue.equals("H")?" selected":"")+">HDD</option>";
  	ris += "</select>";
  	ris += "</td>";
  	ris += "<td class=\"testo\">Num.:</td>";
  	ris += "<td class=\"testo\">";
  	ris += "<input type=\"text\" name=\"numSup"+tipo+"\" value=\""+numSupValue+"\">";
  	ris += "</td>";
  	return ris;
  }

	protected void postUpdate()
	{
	}

	protected void preUpdate()
	{
	}

	public void setInsAvanzamento(boolean insAvanzamento)
	{
		this.insAvanzamento = insAvanzamento;
	}
}
