/**
 * 
 */
package mx.teca.archivi.arsbni;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire l'accesso alla tabella TblOcr del
 * database
 * 
 * @author Massimiliano Randazzo
 * 
 */
public class TblOcr extends Table
{

	/**
   * Costruttore
   * 
   * @param conn
   */
	public TblOcr(ConnectionPool conn)
	{
		super(conn);
	}

	public TblOcr(MsSqlPool msp)
	{
		super(msp);
	}

	/**
   * Questo metodo viene utilizzato per definire i campi da utilizzare nella
   * query
   * 
   * @see mx.database.table.Table#defCampi()
   */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idTblOcr", "TBLOCR", "ID_TBLOCR", true);	//
		this.addCampi("idTblImg", "TBLOCR", "ID_TBLIMG");//
		this.addCampi("idOcrGroup", "TBLOCR", "ID_OCRGROUP");//
		this.addCampi("sequenceNumber", "TBLOCR", "SEQUENCENUMBER");	//
		this.addCampi("nomenclature", "TBLOCR", "NOMENCLATURE");	//
		this.addCampi("ocrUsage", "TBLOCR", "OCRUSAGE");	//
		this.addCampi("ocrPathName", "TBLOCR", "OCRPATHNAME");	//
		this.addCampi("ocrMd5", "TBLOCR", "OCRMD5");	//
		this.addCampi("ocrSize", "TBLOCR", "OCRSIZE");	//
		this.addCampi("ocrData", "TBLOCR", "OCRDATA");	//
		this.addCampi("hostId", "TBLOCR", "HOSTID");	//
		this.addCampi("risIdr", "", "", false, false, false);
//		this.addCampi("ocrNote", "TBLOCR", "OCRNOTE");	//
	}

	/**
   * Questo metodo viene utilizzato per implementare i controlli da eseguire
   * prima dell'esecuzuibne delle operazioni di insert e update
   * 
   * @see mx.database.table.Table#postUpdate()
   */
	protected void postUpdate()
	{

	}

	/**
   * Questo metodo viene utilizzato per implementare i controlli da eseguire
   * dopo dell'esecuzuibne delle operazioni di insert e update
   * 
   * @see mx.database.table.Table#preUpdate()
   */
	protected void preUpdate()
	{

	}

}
