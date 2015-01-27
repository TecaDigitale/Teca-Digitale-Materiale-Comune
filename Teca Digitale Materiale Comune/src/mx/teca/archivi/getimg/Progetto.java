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
 * Questa classe viene utilizzata per gestire la tabella progetto
 * @author Randazzo
 *
 */
public class Progetto extends TableNavigator
{

  /**
   * Questa variabile viene utilizzata per indicara un ultgeriore filtro nella ricerca.<BR>
   * 2= Visualizza tutti i progetto non chiusi
   */
  private String flag = "";
  
  /**
   * Costruttore con definizione del Pool di connessione e connesione preselezionata per le 
   * operazioni di modifica della tabella
   * @param conn Poll di connessioni con il database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   */
  public Progetto(ConnectionPool conn, MsSqlPool msp)
  {
    super(conn, msp);
  }
  
  /**
   * Costruttore con definizione del Pool di connessione e connesione preselezionata per le 
   * operazioni di modifica della tabella
   * @param conn Poll di connessioni con il database
   * @param msp Connessione da utilizzare nelle operazioni di modifica della tabella (insert, 
   * update, delete)
   * @param map Lista parametri da caricare
   */
  public Progetto(ConnectionPool conn, MsSqlPool msp, Map<Object, Object> map)
  {
    super(conn, msp, map);
  }

  /**
   * Costruttore con definizione del Pool di connessione e connesione preselezionata per le 
   * operazioni di modifica della tabella
   * @param conn Poll di connessioni con il database
   * @param map Lista parametri da caricare
   */
  public Progetto(ConnectionPool conn, Map<Object, Object> map)
  {
    super(conn, map);
  }

  /**
   * Costruttore
   */
  public Progetto()
  {
    super();
  }

  /**
   * Costruttore con la gestione del pool di connessioni
   * @param conn
   */
  public Progetto(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * Costruttore con relativa connessione pre selezionata al database
   * 
   * @param msp
   */
  public Progetto(MsSqlPool msp)
  {
    super(msp);
  }

  /**
   * Questo metodo viene utilizzato per eseguire la select sul database
   * @see mx.database.navigator.TableNavigator#startSelect()
   */
  public ResultSet startSelect()
  {
    if (!this.getCampo("nomeProgetto").isEmpty())
    {
      this.getCampo("nomeProgetto").setTipoRicerca("LIKE");
      this.setCampoValue("nomeProgetto",this.get("nomeProgetto")+"%");
    }
    if(flag.equals("2"))
      this.setWhere("DataChiusura IS NULL");
    

    return super.startSelect();
  }

  /**
   * Questa variabile viene utilizzata per indicara un ultgeriore filtro nella ricerca.<BR>
   * 2= Visualizza tutti i progetto non chiusi
   * @param flag The flag to set.
   */
  public void setFlag(String flag)
  {
    this.flag = flag;
  }

  /**
   * Questo metodo viene utilizzato per indicare la lista dei campi che compongono la 
   * tabella
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("idProgetto", "PROGETTO", "ID_PROGETTO", true);
    if (this.msp != null)
      this.getCampo("idProgetto").setGenID(new Contatori(this.msp), "PROGETTO");
    else
      this.getCampo("idProgetto").setGenID(new Contatori(this.conn), "PROGETTO");
    this.addCampi("nomeProgetto", "PROGETTO", "NOMEPROGETTO");
    this.addCampi("dataApertura", "PROGETTO", "DATAAPERTURA");
    this.addCampi("dataChiusura", "PROGETTO", "DATACHIUSURA");
    this.addCampi("note", "PROGETTO", "NOTE");
    this.addCampi("importArchivio", "PROGETTO", "IMPORTARCHIVIO");
    this.addCampi("importIntranet", "PROGETTO", "IMPORTINTRANET");
    this.addCampi("importInternet", "PROGETTO", "IMPORTINTERNET");
    this.addCampi("idTblOaiSet", "PROGETTO", "ID_TBLOAISET");
  }

  /**
   * Questo metodo viene utilizzato nelle pagine JSP per la volorizzazione dalla queryString
   * @param idProgetto
   */
  public void setIdProgetto(int idProgetto)
  {
    this.setCampoValue("idProgetto",idProgetto);
  }

  /**
   * Questo metodo viene utilizzato nelle pagine JSP per la volorizzazione dalla queryString
   * @param nomeProgetto
   */
  public void setNomeProgetto(String nomeProgetto)
  {
    this.setCampoValue("nomeProgetto",nomeProgetto);
  }

  /**
   * Questo metodo viene utilizzato nelle pagine JSP per la volorizzazione dalla queryString
   * @param note
   */
  public void setNote(String note)
  {
    this.setCampoValue("note", note);
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
