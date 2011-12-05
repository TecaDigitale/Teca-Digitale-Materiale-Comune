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
public class ViewAudio extends Query
{

	/**
	 * Costruttore
	 * 
	 * @param conn
	 */
	public ViewAudio(ConnectionPool conn)
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
		this.setFrom("TBLRIS, TBLRISAUDIO, TBLAUDIO, TLKPMIME");
	}

	/**
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.setWhere("TBLRIS.RISIDR=TBLRISAUDIO.RISIDR AND "+
				"TBLRISAUDIO.ID_TBLAUDIO=TBLAUDIO.ID_TBLAUDIO AND "+
				"TBLAUDIO.MIMEID=TLKPMIME.MIMEID");
	}

}
