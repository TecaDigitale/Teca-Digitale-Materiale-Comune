/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.sql.ResultSet;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * @author MRandazzo
 *
 */
public class TblLegNotRis extends Table
{

	/**
	 * @param conn
	 */
	public TblLegNotRis(ConnectionPool conn)
	{
		super(conn);
	}

	public TblLegNotRis(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idTbllegNot", "TBLLEGNOTRIS", "ID_TBLLEGNOT", true);
		this.addCampi("risIdr", "TBLLEGNOTRIS", "RISIDR", true);
		this.addCampi("notLevel", "TBLLEGNOTRIS", "NOTLEVEL");
		this.addCampi("pieceGr", "TBLLEGNOTRIS", "PIECEGR");
		this.addCampi("pieceDt", "TBLLEGNOTRIS", "PIECEDT");
		this.addCampi("pieceIn", "TBLLEGNOTRIS", "PIECEIN");
		this.addCampi("fileMag", "TBLLEGNOTRIS", "FILEMAG");
		this.addCampi("hostId", "TBLLEGNOTRIS", "HOSTID");
		this.addCampi("notMd5", "TBLLEGNOTRIS", "NOTMD5");
		this.addCampi("notDataMod", "TBLLEGNOTRIS", "NOTDATAMOD");
		this.addCampi("idTblOaiSet", "TBLLEGNOTRIS", "ID_TBLOAISET");
	}

	protected void postUpdate()
	{
	}

	protected void preUpdate()
	{
	}

	public ResultSet startSelect()
	{
		return startSelect(true);
	}

	public ResultSet startSelect(boolean checkPieceNull)
	{
		if (checkPieceNull)
		{
			if (this.getCampoValue("pieceGr") == null)
			{
				this.getCampo("pieceGr").setTipoRicerca("is NULL");
				this.setCampoValue("pieceGr", "");
			}
			if (this.getCampoValue("pieceDt") == null)
			{
				this.getCampo("pieceDt").setTipoRicerca("is NULL");
				this.setCampoValue("pieceDt", "");
			}
			if (this.getCampoValue("pieceIn") == null)
			{
				this.getCampo("pieceIn").setTipoRicerca("is NULL");
				this.setCampoValue("pieceIn", "");
			}
		}
		return super.startSelect();
	}
	
	public void stopSelect()
	{
		super.stopSelect();
		this.getCampo("pieceGr").setTipoRicerca("=");
		this.getCampo("pieceDt").setTipoRicerca("=");
		this.getCampo("pieceIn").setTipoRicerca("=");
	}
}
