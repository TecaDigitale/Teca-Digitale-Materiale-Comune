/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata per reperire le informazioni delle imamgini per la stru
 * 
 * @author Massimiliano Randazzo
 *
 */
public class ViewStru extends Query
{

	/**
	 * @param conn
	 */
	public ViewStru(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @param msp
	 */
	public ViewStru(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * @param conn
	 * @param map
	 */
	public ViewStru(ConnectionPool conn, Map<Object, Object> map)
	{
		super(conn, map);
	}

	/**
	 * @param conn
	 * @param msp
	 */
	public ViewStru(ConnectionPool conn, MsSqlPool msp)
	{
		super(conn, msp);
	}

	/**
	 * @see mx.database.table.Query#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idr", "TBLRIS", "RISIDR");
		this.addCampi("nota", "TBLRIS", "RISNOTAPUB");
		this.addCampi("liv", "TBLRIS", "RISLIVELLO");
		this.addCampi("seq", "TBLRELRIS", "RELRISSEQUENZA");
		this.addCampi("relrisidr", "TBLRELRIS", "RELRISIDRARRIVO");
		this.addCampi("tiporelid", "TBLRELRIS", "TIPORELID");
	}

	/**
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
//		this.setFrom("TBLRIS, TBLIMG, TBLRELRIS, TBLRISIMG");
    this.setFrom("TBLRELRIS");
    this.addFrom(conn.genJoinLeft("TBLRIS", "TBLRIS.RISIDR = TBLRELRIS.RELRISIDRPARTENZA"));
    this.addFrom(conn.genJoinLeft("TBLRISIMG", "TBLRIS.RISIDR = TBLRISIMG.RISIDR"));
    this.addFrom(conn.genJoinLeft("TBLIMG", "TBLRISIMG.ID_TBLIMG = TBLIMG.ID_TBLIMG"));
	}

	/**
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
//		this.setWhere("TBLRELRIS.RELRISIDRPARTENZA = TBLRIS.RISIDR and " +
//				          "TBLRIS.RISIDR = TBLRISIMG.RISIDR and " +
//				          "TBLRISIMG.ID_TBLIMG = TBLIMG.ID_TBLIMG");
	}

}
