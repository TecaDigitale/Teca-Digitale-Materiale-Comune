/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.sql.ResultSet;

import mx.database.ConnectionPool;
import mx.database.table.Table;

/**
 * Questo metodo viene utilizzato per gestire l'accesso alla tabella IMGGROUP
 * @author Administrator
 *
 */
public class ImgGroup extends Table
{

	/**
	 * Costruttore
	 * @param conn
	 */
	public ImgGroup(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * Questo metodo viene utilizzato per indicare la lista dei campi che compongono la tabella
	 * 
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("IdImgGroup", "IMGGROUP", "ID_IMGGROUP", true);
		this.getCampo("idImgGroup").setGenID(new TblCont(conn), "IMGGROUP");
		this.addCampi("ScannerModel", "IMGGROUP", "SCANNER_MODEL");
		this.addCampi("FormatName", "IMGGROUP", "FORMAT_NAME");
		this.addCampi("CaptureSoftware", "IMGGROUP", "CAPTURE_SOFTWARE");
		this.addCampi("Ppi", "IMGGROUP", "PPI");
		this.addCampi("BitSample", "IMGGROUP", "BITSAMPLE");
	}

	/**
	 * Questo medoto viene invocato per indicare le azioni da eseguire dopo le operazioni di Insert o Update
	 * 
	 * @see mx.database.table.Table#postUpdate()
	 */
	protected void postUpdate()
	{
	}

	/**
	 * Questo medoto viene invocato per indicare le azioni da eseguire prima le operazioni di Insert o Update
	 * 
	 * @see mx.database.table.Table#preUpdate()
	 */
	protected void preUpdate()
	{
		if (this.getCampo("ScannerModel").isEmpty())
			this.setCampoValue("ScannerModel", "");
		if (this.getCampo("FormatName").isEmpty())
			this.setCampoValue("FormatName", "");
		if (this.getCampo("CaptureSoftware").isEmpty())
			this.setCampoValue("CaptureSoftware", "");
		if (this.getCampo("Ppi").isEmpty())
			this.setCampoValue("Ppi", 0);
		if (this.getCampo("BitSample").isEmpty())
			this.setCampoValue("BitSample", "");
	}

	/**
	 * Questo metodo viene utilizzato per estendere la classe di ricerca
	 * @see mx.database.table.DataSet#startSelect()
	 */
	public ResultSet startSelect()
	{
		return startSelect(false);
	}

	/**
	 * Questo metodo viene utilizzato per estendere la classe di ricerca
	 * @see mx.database.table.DataSet#startSelect()
	 */
	public ResultSet startSelect(boolean force)
	{
		if (!force)
			preUpdate();
		return super.startSelect();
	}
}
