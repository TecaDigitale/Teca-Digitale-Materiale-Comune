/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata per estrarre tutte le imamgini relative alle imamgini
 * 
 * @author Massiniliano Randazzo
 *
 */
public class ViewImages extends Query
{

	/**
	 * @param conn
	 */
	public ViewImages(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
		this.setFrom("TBLRIS, " +
				"TBLRISIMG, " +
				"TLKPHOST, " +
				"TBLIMG " +
				conn.genJoinLeft("TLKPMIME", "TBLIMG.MIMEID = TLKPMIME.MIMEID")+" "+
				conn.genJoinLeft("TBLOCR", "TBLOCR.ID_TBLIMG=TBLIMG.ID_TBLIMG")+" "+
				conn.genJoinLeft("OCRGROUP", "OCRGROUP.ID_OCRGROUP=TBLOCR.ID_OCRGROUP")+" "+
				conn.genJoinLeft("TLKPHOST AS TLKPHOSTOCR", "TLKPHOSTOCR.HOSTID=TBLOCR.HOSTID"));
	}

	/**
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.setWhere("TBLRIS.RISIDR = TBLRISIMG.RISIDR");
		this.addWhere("AND TBLRISIMG.ID_TBLIMG = TBLIMG.ID_TBLIMG");
    this.addWhere("AND TBLIMG.HOSTID = TLKPHOST.HOSTID");
//    this.addWhere("AND TBLIMG.MIMEID = TLKPMIME.MIMEID");
	}

	/**
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("risIdr", "TBLRIS", "RISIDR"); //idr
		this.addCampi("risNotaPub", "TBLRIS", "RISNOTAPUB");
		this.addCampi("risLivello", "TBLRIS", "RISLIVELLO");  //livello
		this.addCampi("idTblImg", "TBLIMG", "ID_TBLIMG");
		this.addCampi("hostId", "TBLIMG", "HOSTID");
		this.addCampi("mimeId", "TBLIMG", "MIMEID");
		this.addCampi("fruiBid", "TBLIMG", "FRUIBID");
		this.addCampi("imgPathName", "TBLIMG", "IMGPATHNAME"); //"path"
		this.addCampi("imgMd5", "TBLIMG", "IMGMD5"); //md5
		this.addCampi("imgFormato", "TBLIMG", "IMGFORMATO"); //formato
		this.addCampi("imgData", "TBLIMG", "IMGDATA");
		this.addCampi("imgSize", "TBLIMG", "IMGSIZE"); //size
		this.addCampi("imgUsage", "TBLIMG", "IMGUSAGE");
		this.getCampo("imgUsage").setOrderBy(1, Column.ORDERBY_CRES);
    this.addCampi("hostProt", "TLKPHOST", "HOSTPROT"); //"prot"
    this.addCampi("hostIp", "TLKPHOST", "HOSTIP"); //"ip"
    this.addCampi("mimeDes", "TLKPMIME", "MIMEDES"); //"mime"
    this.addCampi("hostPorta", "TLKPHOST", "HOSTPORTA");
    this.addCampi("hostServerPath", "TLKPHOST", "HOSTSERVERPATH");
    this.addCampi("hostPathDisco", "TLKPHOST", "HOSTPATHDISCO");
    this.addCampi("hostLogin", "TLKPHOST", "HOSTLOGIN");
    this.addCampi("hostPsw", "TLKPHOST", "HOSTPSW");
    this.addCampi("idImgGroup", "TBLIMG", "ID_IMGGROUP");
    this.addCampi("imgLength", "TBLIMG", "IMGLENGTH");
    this.addCampi("imgWidth", "TBLIMG", "IMGWIDTH");

    this.addCampi("idTblOcr", "TBLOCR", "ID_TBLOCR");	//
		this.addCampi("idOcrGroup", "TBLOCR", "ID_OCRGROUP");//
		this.addCampi("formatName", "OCRGROUP", "FORMAT_NAME");
		this.addCampi("softwareOcr", "OCRGROUP", "SOFTWARE_OCR");
		this.addCampi("sequenceNumber", "TBLOCR", "SEQUENCENUMBER");	//
		this.addCampi("nomenclature", "TBLOCR", "NOMENCLATURE");	//
		this.addCampi("ocrUsage", "TBLOCR", "OCRUSAGE");	//
		this.addCampi("ocrPathName", "TBLOCR", "OCRPATHNAME");	//
		this.addCampi("ocrMd5", "TBLOCR", "OCRMD5");	//
		this.addCampi("ocrSize", "TBLOCR", "OCRSIZE");	//
		this.addCampi("ocrData", "TBLOCR", "OCRDATA");	//
		this.addCampi("hostIdOcr", "TBLOCR", "HOSTID");	//

    this.addCampi("hostProtOcr","TLKPHOSTOCR","HOSTPROT");
    this.addCampi("hostIpOcr","TLKPHOSTOCR","HOSTIP");
    this.addCampi("hostPortaOcr","TLKPHOSTOCR","HOSTPORTA");
    this.addCampi("hostServerPathOcr", "TLKPHOSTOCR", "HOSTSERVERPATH");
    this.addCampi("hostPathDiscoOcr", "TLKPHOSTOCR", "HOSTPATHDISCO");
    this.addCampi("hostLoginOcr","TLKPHOSTOCR","HOSTLOGIN");
    this.addCampi("hostPswOcr","TLKPHOSTOCR","HOSTPSW");
	}

}
