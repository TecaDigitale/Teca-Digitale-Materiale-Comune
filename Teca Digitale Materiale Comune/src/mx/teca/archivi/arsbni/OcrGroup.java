/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.sql.ResultSet;

import mx.database.ConnectionPool;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire l'accesso alla tabella OcrGroup
 * 
 * @author Massimiliano Randazzo
 *
 */
public class OcrGroup extends Table
{

	/**
	 * Costruttore 
	 * @param conn
	 */
	public OcrGroup(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * Questo metodo viene utilizzato per indicare le operazioni da eseguire prima della modifica della tabella
	 * 
	 * @see mx.database.table.Table#postUpdate()
	 */
	protected void postUpdate()
	{

	}

	/**
	 * Questo metodo viene utilizzato per indicare le operazioni da eseguire dopo la modifica della tabella
	 * @see mx.database.table.Table#preUpdate()
	 */
	protected void preUpdate()
	{
		if (this.getCampo("softwareOcr").isEmpty())
			this.setCampoValue("softwareOcr", "");
	}

	/**
	 * Questo metodo viene utilizzato per definire la lista dei campi che compongono la tabella
	 * 
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idOcrGroup", "OCRGROUP", "ID_OCRGROUP", true);
		this.getCampo("idOcrGroup").setGenID(new TblCont(conn), "OCRGROUP");
		this.addCampi("formatName", "OCRGROUP", "FORMAT_NAME");
		this.addCampi("softwareOcr", "OCRGROUP", "SOFTWARE_OCR");
	}

	/**
	 * Questo metodo viene utilizzato per estendere la classe di ricerca
	 * @see mx.database.table.DataSet#startSelect()
	 */
	public ResultSet startSelect()
	{
		preUpdate();
		return super.startSelect();
	}
}
