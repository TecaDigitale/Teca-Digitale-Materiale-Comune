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
public class AudioGroup extends Table
{

	/**
	 * @param conn
	 */
	public AudioGroup(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idAudioGroup", "AUDIOGROUP", "ID_AUDIOGROUP", true);
		this.getCampo("idAudioGroup").setGenID(new TblCont(conn), "AUDIOGROUP");
		this.addCampi("samplingFrequency", "AUDIOGROUP", "SAMPLINGFREQUENCY");
		this.addCampi("bitPerSample", "AUDIOGROUP", "BITPERSAMPLE");
		this.addCampi("bitRate", "AUDIOGROUP", "BITRATE");
		this.addCampi("formatName", "AUDIOGROUP", "FORMAT_NAME");
		this.addCampi("formatMime", "AUDIOGROUP", "FORMAT_MIME");
		this.addCampi("formatCompression", "AUDIOGROUP", "FORMAT_COMPRESSION");
		this.addCampi("formatChannel", "AUDIOGROUP", "FORMAT_CHANNEL");
		this.addCampi("sourceType", "AUDIOGROUP", "SOURCE_TYPE");
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
