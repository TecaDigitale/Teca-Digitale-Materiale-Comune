/**
 * 
 */
package mx.teca.archivi.getimg.view;

import java.util.Map;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata per indicare le caretteristiche delle imamgini
 * 
 * @author MRandazzo
 *
 */
public class ViewCaratImg extends Query
{

	/**
	 * @param conn
	 * @param map
	 */
	public ViewCaratImg(ConnectionPool conn, Map<Object, Object> map)
	{
		super(conn, map);
	}

	/**
	 * @param conn
	 * @param msp
	 */
	public ViewCaratImg(ConnectionPool conn, MsSqlPool msp)
	{
		super(conn, msp);
	}

	/**
	 * @param conn
	 */
	public ViewCaratImg(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @param msp
	 */
	public ViewCaratImg(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
		this.setFrom("CARAT_IMMAGINI, TIPOLO_COMPR, COMPRESSIONE, TIPOLOGIAIMMAGINI");

	}

	/**
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.setWhere("CARAT_IMMAGINI.ID_TIPOLOGIAIMMAGINI=TIPOLO_COMPR.ID_TIPOLOGIAIMMAGINI AND "+
				          "CARAT_IMMAGINI.ID_COMPRESSIONE=TIPOLO_COMPR.ID_COMPRESSIONE AND "+
				          "TIPOLO_COMPR.ID_TIPOLOGIAIMMAGINI=TIPOLOGIAIMMAGINI.ID_TIPOLOGIAIMMAGINI AND "+
				          "TIPOLO_COMPR.ID_COMPRESSIONE=COMPRESSIONE.ID_COMPRESSIONE");

	}

	/**
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idProgetto", "CARAT_IMMAGINI", "ID_PROGETTO");
		this.addCampi("risoluzione", "CARAT_IMMAGINI", "RISOLUZIONE");
		this.addCampi("tipo", "TIPOLOGIAIMMAGINI", "TIPO");
		this.addCampi("mimeType", "TIPOLOGIAIMMAGINI", "MIMETYPE");
		this.addCampi("descCompressione", "COMPRESSIONE", "DESC_COMPRESSIONE");

	}

}
