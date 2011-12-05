/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import mx.database.ConnectionPool;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata per ricavare le informazioni relative all'audio
 * 
 * @author massi
 *
 */
public class ViewVideo extends Query
{

	/**
	 * Costruttore
	 * 
	 * @param conn
	 */
	public ViewVideo(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Query#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("risNotaPub", "TBLRIS", "RISNOTAPUB");
		this.addCampi("risIdr", "TBLRIS", "RISIDR");
		this.addCampi("mimeType", "TLKPMIME", "MIMEDES");
	}

	/**
	 * 
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
		this.setFrom("TBLRIS, TBLRISVIDEO, TBLVIDEO, TLKPMIME");
	}

	/**
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.setWhere("TBLRIS.RISIDR=TBLRISVIDEO.RISIDR AND "+
				"TBLRISVIDEO.ID_TBLVIDEO=TBLVIDEO.ID_TBLVIDEO AND "+
				"TBLVIDEO.MIMEID=TLKPMIME.MIMEID");
	}

}
