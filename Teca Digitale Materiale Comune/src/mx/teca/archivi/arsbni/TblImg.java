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
 * Questa classe viene utilizzata per gestire l'accesso alal tabella TblImg del database teca
 * 
 * @author Massimiliano Randazzo
 *
 */
public class TblImg extends Table
{

	/**
	 * Questa variabile viene utilizzata per loggare l'applicativi
	 */
	private static Logger log = Logger.getLogger(TblImg.class);

	/**
	 * Costruttore
	 * @param conn
	 */
	public TblImg(ConnectionPool conn)
	{
		super(conn);
	}

	public TblImg(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idTblImg", "TBLIMG", "ID_TBLIMG", true);
		this.addCampi("hostId", "TBLIMG", "HOSTID");
		this.addCampi("mimeId", "TBLIMG", "MIMEID");
		this.addCampi("fruibId", "TBLIMG", "FRUIBID");
		this.addCampi("idImgGroup", "TBLIMG", "ID_IMGGROUP");
		this.addCampi("imgPathName", "TBLIMG", "IMGPATHNAME");
		this.addCampi("imgMd5", "TBLIMG", "IMGMD5");
		this.addCampi("imgFormato", "TBLIMG", "IMGFORMATO");
		this.addCampi("imgData", "TBLIMG", "IMGDATA");
		this.addCampi("imgSize", "TBLIMG", "IMGSIZE");
		this.addCampi("imgUsage", "TBLIMG", "IMGUSAGE");
		this.addCampi("imgLength", "TBLIMG", "IMGLENGTH");
		this.addCampi("imgWidth", "TBLIMG", "IMGWIDTH");
		this.addCampi("imgLengthConv", "TBLIMG", "IMGLENGTH_CONV");
		this.addCampi("imgWidthConv", "TBLIMG", "IMGWIDTH_CONV");
	}

	/**
	 * Questo metodo viene utilizzato per inserire / modificare il record nel database
	 * @see mx.database.table.Table#insert()
	 */
	public int insert() throws MsSqlException
	{
		TblImg tblImg = null;
		ResultSet rs = null;
		
		try
		{
			tblImg = new TblImg(conn);
			tblImg.setCampoValue("idTblImg", this.getCampoValue("idTblImg"));
			
			rs = tblImg.startSelect();
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
				if (tblImg != null)
					tblImg.stopSelect();
			}
			catch (SQLException e)
			{
				log.error(e);
			}
		}
	}

	protected void postUpdate()
	{
	}

	protected void preUpdate()
	{
	}
}
