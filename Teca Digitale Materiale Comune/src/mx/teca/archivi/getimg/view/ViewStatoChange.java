/**
 * 
 */
package mx.teca.archivi.getimg.view;

import mx.database.ConnectionPool;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata pe gestire l'accesso alla tabella Stato_change
 * 
 * @author Massimiliano Randazzo
 *
 */
public class ViewStatoChange extends Query
{

	/**
	 * @param conn
	 */
	public ViewStatoChange(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * Questo metodo viene utilizzato per indicare i campi della tabella
	 * 
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idStatoPar", "STATO_CHANGE", "ID_STATOPAR");
		this.addCampi("StatoParDesc", "STATOPAR", "DESC_STATO");
		this.addCampi("idStatoDes", "STATO_CHANGE", "ID_STATODES");
		this.addCampi("StatoDesDesc", "STATODES", "DESC_STATO");
		this.addCampi("automatico", "STATO_CHANGE", "AUTOMATICO");
		this.addCampi("forceImp", "STATO_CHANGE", "FORCEIMP");
		this.addCampi("ShowEditMag", "STATOPAR", "SHOW_EDITMAG");
	}

	/**
	 * 
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
		this.setFrom("STATO_CHANGE, STATO STATOPAR, STATO STATODES");
	}

	/**
	 * 
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.setWhere("STATO_CHANGE.ID_STATOPAR=STATOPAR.ID_STATO AND STATO_CHANGE.ID_STATODES=STATODES.ID_STATO");
	}

}
