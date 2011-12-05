/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Query;

/**
 * @author massi
 *
 */
public class ViewListaImages extends Query
{

	/**
	 * @param conn
	 */
	public ViewListaImages(ConnectionPool conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see mx.database.table.Query#defCampi()
	 */
	protected void defCampi()
	{
		this.setFrom("TBLRISIMG, TBLIMG");
	}

	/**
	 * @see mx.database.table.Query#defFrom()
	 */
	protected void defFrom()
	{
		this.addWhere("TBLRISIMG.ID_TBLIMG = TBLIMG.ID_TBLIMG");
	}

	/**
	 * @see mx.database.table.Query#defWhere()
	 */
	protected void defWhere()
	{
		this.setColKey(true);
		this.addCampi("risIdr", "TBLRISIMG", "RISIDR"); //idr
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
		this.getCampo("imgUsage").setOrderBy(Column.ORDERBY_CRES,1);
    this.addCampi("idImgGroup", "TBLIMG", "ID_IMGGROUP");
    this.addCampi("imgLength", "TBLIMG", "IMGLENGTH");
    this.addCampi("imgWidth", "TBLIMG", "IMGWIDTH");
    this.addCampi("hostId", "TBLIMG", "HOSTID");
    this.addCampi("mimeId", "TBLIMG", "MIMEID");
	}

}
