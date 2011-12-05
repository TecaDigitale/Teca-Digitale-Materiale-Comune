/**
 * 
 */
package mx.teca.archivi.arsbni;

import mx.database.ConnectionPool;
import mx.database.table.Table;

/**
 * @author MRandazzo
 *
 */
public class VideoGroup extends Table
{

	/**
	 * @param conn
	 */
	public VideoGroup(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idVideoGroup", "VIDEOGROUP", "ID_VIDEOGROUP", true);
		this.getCampo("idVideoGroup").setGenID(new TblCont(conn), "VIDEOGROUP");
		this.addCampi("formatName", "VIDEOGROUP", "FORMAT_NAME");
		this.addCampi("formatMime", "VIDEOGROUP", "FORMAT_MIME");
		this.addCampi("formatVideo", "VIDEOGROUP", "FORMAT_VIDEO");
		this.addCampi("formatEncodeType", "VIDEOGROUP", "FORMAT_ENCODETYPE");
		this.addCampi("formatStreamType", "VIDEOGROUP", "FORMAT_STREAMTYPE");
		this.addCampi("formatCodec", "VIDEOGROUP", "FORMAT_CODEC");
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
