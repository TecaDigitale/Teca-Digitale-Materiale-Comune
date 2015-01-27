/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire l'accesso alla tabella TLKPMIME de database della Teca
 * 
 * @author Massimiliano Randazzo
 *
 */
public class Tlkpmime extends Table
{

	/**
	 * @param conn
	 * @param msp
	 * @param map
	 */
	public Tlkpmime(ConnectionPool conn, MsSqlPool msp, Map<Object, Object> map)
	{
		super(conn, msp, map);
	}

	/**
	 * @param conn
	 * @param map
	 */
	public Tlkpmime(ConnectionPool conn, Map<Object, Object> map)
	{
		super(conn, map);
	}

	/**
	 * @param conn
	 * @param msp
	 */
	public Tlkpmime(ConnectionPool conn, MsSqlPool msp)
	{
		super(conn, msp);
	}

	/**
	 * @param conn
	 */
	public Tlkpmime(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @param msp
	 */
	public Tlkpmime(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("mimeId", "TLKPMIME", "MIMEID", true);
		this.addCampi("mimeDes", "TLKPMIME", "MIMEDES");
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
