/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import mx.database.ConnectionPool;
import mx.database.MsSqlException;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * @author MRandazzo
 *
 */
public class TblAudio extends Table
{

	/**
	 * Questa variabile viene utilizzata per loggare l'applicativi
	 */
	private static Logger log = Logger.getLogger(TblAudio.class);

	public TblAudio(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * @param conn
	 */
	public TblAudio(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idTblAudio", "TBLAUDIO", "ID_TBLAUDIO", true);
		this.addCampi("hostId", "TBLAUDIO", "HOSTID");
		this.addCampi("mimeId", "TBLAUDIO", "MIMEID");
		this.addCampi("fruibId", "TBLAUDIO", "FRUIBID");
		this.addCampi("idAudioGroup", "TBLAUDIO", "ID_AUDIOGROUP");
		this.addCampi("audioPathName", "TBLAUDIO", "AUDIOPATHNAME");
		this.addCampi("audioMd5", "TBLAUDIO", "AUDIOMD5");
		this.addCampi("audioDate", "TBLAUDIO", "AUDIODATE");
		this.addCampi("audioSize", "TBLAUDIO", "AUDIOSIZE");
		this.addCampi("audioUsage", "TBLAUDIO", "AUDIOUSAGE");
		this.addCampi("audioDuration", "TBLAUDIO", "AUDIODURATION");
	}

	/**
	 * Questo metodo viene utilizzato per inserire / modificare il record nel database
	 * @see mx.database.table.Table#insert()
	 */
	public int insert() throws MsSqlException
	{
		TblAudio tblAudio = null;
		ResultSet rs = null;
		
		try
		{
			tblAudio = new TblAudio(conn);
			tblAudio.setCampoValue("idTblAudio", this.getCampoValue("idTblAudio"));
			
			rs = tblAudio.startSelect();
			if (rs.next())
				return super.update();
			else
				return super.insert();
		}
		catch (SQLException e)
		{
			log.error(e);
      throw new MsSqlException(e.getMessage(), e.getCause());
		}
		catch (MsSqlException e)
		{
			log.error(e);
			throw e;
		}
		finally
		{
			try
			{
				if (rs != null)
					rs.close();
				if (tblAudio != null)
					tblAudio.stopSelect();
			}
			catch (SQLException e)
			{
				log.error(e);
			}
		}
	}

	/**
	 * @see mx.database.table.Table#postUpdate()
	 */
	protected void postUpdate()
	{
	}

	/**
	 * @see mx.database.table.Table#preUpdate()
	 */
	protected void preUpdate()
	{
	}

}
