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
public class TblRisAudio extends Table
{

	/**
	 * Questa variabile viene utilizzata per loggare l'applicativi
	 */
	private static Logger log = new Logger(TblRisAudio.class, "teca.archivi.arsbni");

	public TblRisAudio(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * Costruttore
	 * 
	 * @param conn
	 */
	public TblRisAudio(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("risIdr", "TBLRISAUDIO", "RISIDR", true);
		this.addCampi("idTblAudio", "TBLRISAUDIO", "ID_TBLAUDIO", true);
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