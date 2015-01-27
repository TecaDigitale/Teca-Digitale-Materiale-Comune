/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import mx.database.ConnectionPool;
import mx.database.MsSqlException;
import mx.database.MsSqlPool;
import mx.database.table.Table;

/**
 * Questa classe viene utilizzata per gestire l'accesso alla tabella TblRelRis dell'archivio Teca
 * 
 * @author Massimiliano Randazzo
 *
 */
public class TblRelRis extends Table
{

	public TblRelRis(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * Questa variabile viene utilizzata per loggare l'applicativi
	 */
	private static Logger log = Logger.getLogger(TblRelRis.class);

	/**
	 * @param conn
	 * @param msp
	 */
	public TblRelRis(ConnectionPool conn, MsSqlPool msp)
	{
		super(conn, msp);
	}

	/**
	 * @param conn
	 */
	public TblRelRis(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("relRisidrPartenza", "TBLRELRIS", "RELRISIDRPARTENZA", true);
		this.addCampi("relRisidrArrivo", "TBLRELRIS", "RELRISIDRARRIVO", true);
		this.addCampi("tipoRelId", "TBLRELRIS", "TIPORELID");
		this.addCampi("relRisSequenza", "TBLRELRIS", "RELRISSEQUENZA");
		this.addCampi("relRisNote", "TBLRELRIS", "RELRISNOTE");
	}

	public ResultSet startSelect()
	{
    // TODO: Da implementare meglio
		return super.startSelect();
		/*
		String sql = "";
		
		try
		{
			if(Relazione.getNat().equals("G"))
			{
				if (Relazione.getDir().equals("AP"))
				{
					sql = sql + "tblRelRis, tblRis WHERE tblRelRis.RelRisIdrPartenza=tblRis.RisIdr ";
					sql = sql + "AND tblRelRis.RelRisIdrArrivo = '" + idr + "'";
				}
				else
				{
					sql = sql + "tblRelRis, tblRis WHERE tblRelRis.RelRisIdrArrivo=tblRis.RisIdr ";
					sql = sql + "AND tblRelRis.RelRisIdrPartenza = '" + idr + "'";
				}
				sql = sql + " AND tblRelRis.TipoRelId = " + Relazione.getTipo();
				sql = sql + " ORDER BY tblRelRis.RelRisSequenza";
				
				sql="SELECT tblRis.RisIdr IDR,tblRis.RisNotaPub NOTA,tblRis.RisLivello LIV,tblRis.Mimeid MIME,tblRelRis.RelRisSequenza SEQ FROM " + sql;
			}
			else
			{
				
				sql = "from ViewRelRisUnion where RelRisIdr = '" + idr + "' and TipoRelId = " + Relazione.getTipo();
				
				sql="SELECT * FROM " + sql + " ORDER BY SEQ"; 	
			}
			msp = conn.getConn();

			rs = msp.StartSelect(sql);
			this.setRecTot(this.recCount(rs));
		}
		catch (SQLException e)
		{
			SendLog.ErrorLog(this.getClass().getName()+".StartSelect","SQLException",e);
		}
		catch (Exception e)
		{
			SendLog.ErrorLog(this.getClass().getName()+".StartSelect","Exception",e);
		}
		return rs;
*/
		// TODO Auto-generated method stub
//		return super.startSelect();
	}

	/**
	 * Questo metodo viene utilizzato per inserire / modificare il record nel database
	 * @see mx.database.table.Table#insert()
	 */
	public int insertForce() throws MsSqlException
	{
		return super.insert();
	}

	/**
	 * Questo metodo viene utilizzato per inserire / modificare il record nel database
	 * @see mx.database.table.Table#insert()
	 */
	public int insert() throws MsSqlException
	{
		TblRelRis tblRelRis = null;
		ResultSet rs = null;
		
		try
		{
			tblRelRis = new TblRelRis(conn);
			tblRelRis.setCampoValue("relRisidrPartenza", this.getCampoValue("relRisidrPartenza"));
			tblRelRis.setCampoValue("relRisidrArrivo", this.getCampoValue("relRisidrArrivo"));
			
			rs = tblRelRis.startSelect();
			if (rs.next())
				return super.update();
			else
				return super.insert();
		}
		catch (SQLException e)
		{
			log.error(e);
      throw new MsSqlException(e.getMessage(), e.getCause());
		}
		catch (MsSqlException e)
		{
			log.error(e);
			throw e;
		}
		finally
		{
			try
			{
				if (rs != null)
					rs.close();
				if (tblRelRis != null)
					tblRelRis.stopSelect();
			}
			catch (SQLException e)
			{
				log.error(e);
			}
		}
	}

	protected void postUpdate()
	{
		// TODO Auto-generated method stub
		
	}

	protected void preUpdate()
	{
		// TODO Auto-generated method stub
		
	}

}
