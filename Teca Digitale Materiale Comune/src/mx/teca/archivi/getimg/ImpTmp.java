/*
 * Created on 23-gen-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mx.teca.archivi.getimg;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.Navigatore;
import util.SendLog;
import mx.database.ConnectionPool;
import mx.database.MsSqlException;
import mx.database.MsSqlPool;
import mx.database.interfacce.IAccessTable;

/**
 * @author randazzo
 *
 */
public class ImpTmp extends Navigatore implements IAccessTable
{

	/**
	 * Questa variabile viene utilizzata per indicare il bid della notizia
	 */
	private String bid = "";

	/**
	 * Questa variabile viene utilizzata per gestire il pool di connessione con il database
	 */
	private ConnectionPool conn;
	
	/**
	 * Questa variabile viene utilizzata per indicare la connessione assegnata all'utente
	 */
	private MsSqlPool msp;
	
	/**
	 * Questa variabile viene utilizzata per contenere il risultato della select
	 */
	private ResultSet rs;

	/**
	 * Costruttore
	 */
	public ImpTmp()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see util.Navigatore#getQueryString()
	 */
	public String getQueryString()
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see util.Navigatore#getHidden()
	 */
	public String getHidden()
	{
		return null;
	}

	/**
	 * Questo metodo viene utilizzato per estrarre i dati dalla tabella
	 * 
	 * @see mx.database.interfacce.IAccessTable#StartSelect()
	 */
	public ResultSet StartSelect()
	{
		String Sql = "";
		
		try
		{
			
			Sql ="select view_filexml.* from view_filexml, impTmp where view_filexml.bid=impTmp.bid";

			msp = conn.getConn();

			rs = msp.StartSelect(Sql);
			this.setRecTot(this.recCount(rs));
			this.calcNum();
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
	}

	/**
	 * Questo metodo viene utilizzato per terminare l'operazione di select dul database
	 * 
	 * @see mx.database.interfacce.IAccessTable#StopSelect()
	 */
	public void StopSelect()
	{
		try
		{
			if (rs != null) rs.close();
			if (rs != null) msp.StopSelect();
			if (msp != null) conn.releaseConn(msp);
		}
		catch (SQLException e)
		{
			SendLog.ErrorLog(this.getClass().getName()+".StopSelect","SQLException",e);
		}
	}

	/**
	 * Questo metodo viene utilizzato per valorizzare il contenuto della tabella 
	 * @see mx.database.interfacce.IAccessTable#Insert()
	 */
	public void Insert() throws MsSqlException
	{
		MsSqlPool msp1 = null;
		String sql = "";
		try
		{
			msp1 = conn.getConn();
			sql = "insert into impTmp (bid) values ('" + bid.toUpperCase() + "')";
			msp1.esegui(sql);
		}
		catch (MsSqlException e)
		{
			throw e;
		}
		catch (SQLException e)
		{
			SendLog.ErrorLog(this.getClass().getName()+".Insert","SQLException",e);
			throw new MsSqlException(e.getMessage());
		}
		catch (Exception e)
		{
			SendLog.ErrorLog(this.getClass().getName()+".Insert","Exception",e);
			throw new MsSqlException(e.getMessage());
		}
		finally
		{
			conn.releaseConn(msp1);
		}
	}

	/**
	 * Questo metodo viene utilizzato per cancellatre il contenuto di una tabella
	 */
	public void Delete() throws MsSqlException
	{
		MsSqlPool msp1 = null;
		String sql = "";
		try
		{
			msp1 = conn.getConn();
			sql = "delete from impTmp where bid='" + bid.toUpperCase() + "'";
			msp1.esegui(sql);
		}
		catch (MsSqlException e)
		{
			throw e;
		}
		catch (SQLException e)
		{
			SendLog.ErrorLog(this.getClass().getName()+".Delete","SQLException",e);
			throw new MsSqlException(e.getMessage());
		}
		catch (Exception e)
		{
			SendLog.ErrorLog(this.getClass().getName()+".Delete","Exception",e);
			throw new MsSqlException(e.getMessage());
		}
		finally
		{
			conn.releaseConn(msp1);
		}
	}

	/**
	 * Questo metodo viene utilizzato per Valorizzare la variabile utilizzata per la gestione delle connessioni con il 
	 * database
	 * @see mx.database.interfacce.IAccessTable#setConn(mx.database.ConnectionPool)
	 */
	public void setConn(ConnectionPool pool)
	{
		conn = pool;
	}
	
	/**
	 * Questo metodo viene utilizzato per indicare il bid della notizia
	 * @param string
	 */
	public void setBid(String string)
	{
		bid = string;
	}

	/* (non-Javadoc)
	 * @see mx.database.interfacce.IAccessTable#delete()
	 */
	public void delete() throws MsSqlException
	{
		
	}

	/* (non-Javadoc)
	 * @see mx.database.interfacce.IAccessTable#reset()
	 */
	public void reset()
	{
		
	}

}
