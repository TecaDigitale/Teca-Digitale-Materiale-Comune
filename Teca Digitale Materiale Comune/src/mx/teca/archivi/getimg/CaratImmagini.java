/**
 * 
 */
package mx.teca.archivi.getimg;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * @author Administrator
 *
 */
public class CaratImmagini extends Table
{

	/**
	 * @param conn
	 */
	public CaratImmagini(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @param conn
	 */
	public CaratImmagini(ConnectionPool conn, MsSqlPool msp)
	{
		super(conn,msp);
	}

	/**
	 * @see mx.database.table.Table#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idCaratImmagini", "CARAT_IMMAGINI", "ID_CARAT_IMMAGINI", true);
    if (msp!=null)
      this.getCampo("idCaratImmagini").setGenID(new Contatori(msp),"CARAT_IMMAGINI");
    else
      this.getCampo("idCaratImmagini").setGenID(new Contatori(conn),"CARAT_IMMAGINI");
		this.addCampi("idProgetto", "CARAT_IMMAGINI", "ID_PROGETTO");
		this.addCampi("risoluzione", "CARAT_IMMAGINI", "RISOLUZIONE");
		this.addCampi("idTipologiaImmagini", "CARAT_IMMAGINI", "ID_TIPOLOGIAIMMAGINI");
		this.addCampi("idCompressione", "CARAT_IMMAGINI", "ID_COMPRESSIONE");
		this.addCampi("note", "CARAT_IMMAGINI", "NOTE");
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
