/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Query;

/**
 * @author MRandazzo
 *
 */
public class ViewTblRelRisUnion extends Query
{

	/**
	 * @param conn
	 * @param map
	 */
	public ViewTblRelRisUnion(ConnectionPool conn, Map map)
	{
		super(conn, map);
	}

	/**
	 * @param conn
	 * @param msp
	 */
	public ViewTblRelRisUnion(ConnectionPool conn, MsSqlPool msp)
	{
		super(conn, msp);
	}

	/**
	 * @param conn
	 */
	public ViewTblRelRisUnion(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @param msp
	 */
	public ViewTblRelRisUnion(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
    this.setFrom("VIEWRELRISUNION");
	}

	/**
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
	}

	/**
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idr", "VIEWRELRISUNION", "IDR");
		this.addCampi("nota", "VIEWRELRISUNION", "NOTA");
		this.addCampi("liv", "VIEWRELRISUNION", "LIV");
		this.addCampi("mime", "VIEWRELRISUNION", "MIME");
		this.addCampi("seq", "VIEWRELRISUNION", "SEQ");
		this.addCampi("relRisIdr", "VIEWRELRISUNION", "RELRISIDR");
		this.addCampi("tipoRelId", "VIEWRELRISUNION", "TIPORELID");
	}

}
