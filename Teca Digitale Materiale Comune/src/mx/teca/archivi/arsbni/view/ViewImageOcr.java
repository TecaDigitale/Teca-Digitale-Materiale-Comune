/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import mx.database.ConnectionPool;
import mx.database.table.Query;

/**
 * @author MRandazzo
 *
 */
public class ViewImageOcr extends Query
{

	public ViewImageOcr(ConnectionPool pool)
	{
		super(pool);
	}

	/**
	 * @see mx.database.table.Query#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("risIdr", "TBLRISIMG", "RISIDR", true);
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
	}

	/**
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
		this.setFrom("TBLRISIMG, TBLOCR");
	}

	/**
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.setWhere("TBLRISIMG.ID_TBLIMG=TBLOCR.ID_TBLIMG");
	}

}
