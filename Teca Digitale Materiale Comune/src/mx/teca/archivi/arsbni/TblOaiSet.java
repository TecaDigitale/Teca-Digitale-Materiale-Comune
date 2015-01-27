/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.sql.ResultSet;
import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.navigator.TableNavigator;

/**
 * Questa classe viene utilizzata per gestire l'accesso alla tabella TblOaiSet del database Teca
 * 
 * @author Massimiliano Randazzo
 *
 */
public class TblOaiSet extends TableNavigator
{

	/**
	 * Costruttore
	 *  
	 * @param conn
	 */
	public TblOaiSet(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * Costruttore
	 *  
	 * @param conn
	 */
	public TblOaiSet(ConnectionPool conn, Map<Object, Object> map)
	{
		super(conn, map);
	}

	/**
	 * Questo metodo viene utilizzato per definire il campi della tabella
	 * 
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idTblOaiSet", "TBLOAISET", "ID_TBLOAISET", true);
		if (msp != null)
			this.getCampo("idTblOaiSet").setGenID(new TblCont(msp), "TBLOAISET");
		else
			this.getCampo("idTblOaiSet").setGenID(new TblCont(conn), "TBLOAISET");
		this.addCampi("CodeTblOaiSet", "TBLOAISET", "CODE_TBLOAISET");
		this.addCampi("DescrTblOaiSet", "TBLOAISET", "DESCR_TBLOAISET");
		this.addCampi("UrlTblOaiSet", "TBLOAISET", "URL_TBLOAISET");
		this.addCampi("Accesso", "TBLOAISET", "ACCESSO");
		this.addCampi("Available", "TBLOAISET", "AVAILABLE");
	}

	/**
	 * Questo metodo viene utilizzato per eseguire la ricerca sul Database
	 * 
	 * @see mx.database.navigator.TableNavigator#startSelect()
	 */
	public ResultSet startSelect()
	{
    if (!this.getCampo("CodeTblOaiSet").isEmpty())
    {
      this.getCampo("CodeTblOaiSet").setTipoRicerca("LIKE");
      this.setCampoValue("CodeTblOaiSet", this.get("CodeTblOaiSet")+"%");
    }
    if (!this.getCampo("DescrTblOaiSet").isEmpty())
    {
      this.getCampo("DescrTblOaiSet").setTipoRicerca("LIKE");
      this.setCampoValue("DescrTblOaiSet", this.get("DescrTblOaiSet")+"%");
    }
		return super.startSelect();
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
