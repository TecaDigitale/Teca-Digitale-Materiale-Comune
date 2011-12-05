/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.database.ConnectionPool;
import mx.database.MsSqlException;
import mx.database.MsSqlPool;
import mx.database.table.Table;
import mx.log4j.Logger;

/**
 * @author MRandazzo
 *
 */
public class TblRisVideo extends Table
{

	/**
	 * Questa variabile viene utilizzata per loggare l'applicativi
	 */
	private static Logger log = new Logger(TblRisVideo.class, "teca.archivi.arsbni");

	/**
	 * Costruttore
	 * 
	 * @param conn
	 */
	public TblRisVideo(ConnectionPool conn)
	{
		super(conn);
	}

	public TblRisVideo(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("risIdr", "TBLRISVIDEO", "RISIDR", true);
		this.addCampi("idTblVideo", "TBLRISVIDEO", "ID_TBLVIDEO", true);
	}

	/**
	 * Questo metodo viene utilizzato per inserire / modificare il record nel database
	 * @see mx.database.table.Table#insert()
	 */
	public int insert() throws MsSqlException
	{
		ResultSet rs = null;
		int ris = 0;
		try
		{
			rs = this.startSelect();
			if (!rs.next())
				ris = super.insert();
		}
		catch (SQLException e)
		{
			log.error(e);
		}
		catch (MsSqlException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (rs !=  null)
				{
					rs.close();
					this.stopSelect();
				}
			}
			catch (SQLException e)
			{
				log.error(e);
			}
		}
		return ris;
	}

	protected void postUpdate()
	{
	}

	protected void preUpdate()
	{
	}

}
