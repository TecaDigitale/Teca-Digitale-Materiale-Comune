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
public class TblVideo extends Table
{

	/**
	 * Questa variabile viene utilizzata per loggare l'applicativi
	 */
	private static Logger log = Logger.getLogger(TblVideo.class);

	/**
	 * @param conn
	 */
	public TblVideo(ConnectionPool conn)
	{
		super(conn);
	}

	public TblVideo(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idTblVideo", "TBLVIDEO", "ID_TBLVIDEO", true);
		this.addCampi("hostId", "TBLVIDEO", "HOSTID");
		this.addCampi("mimeId", "TBLVIDEO", "MIMEID");
		this.addCampi("fruibId", "TBLVIDEO", "FRUIBID");
		this.addCampi("idVideoGroup", "TBLVIDEO", "ID_VIDEOGROUP");
		this.addCampi("videoPathName", "TBLVIDEO", "VIDEOPATHNAME");
		this.addCampi("videoMd5", "TBLVIDEO", "VIDEOMD5");
		this.addCampi("videoDate", "TBLVIDEO", "VIDEODATE");
		this.addCampi("videoSize", "TBLVIDEO", "VIDEOSIZE");
		this.addCampi("videoUsage", "TBLVIDEO", "VIDEOUSAGE");
		this.addCampi("videoDuration", "TBLVIDEO", "VIDEODURATION");
	}

	/**
	 * Questo metodo viene utilizzato per inserire / modificare il record nel database
	 * @see mx.database.table.Table#insert()
	 */
	public int insert() throws MsSqlException
	{
		TblVideo tblVideo = null;
		ResultSet rs = null;
		
		try
		{
			tblVideo = new TblVideo(conn);
			tblVideo.setCampoValue("idTblVideo", this.getCampoValue("idTblVideo"));
			
			rs = tblVideo.startSelect();
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
				if (tblVideo != null)
					tblVideo.stopSelect();
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
