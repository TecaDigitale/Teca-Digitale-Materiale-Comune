/*
 * Created on 23-gen-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mx.teca.archivi.getimg;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import util.Navigatore;
import mx.database.ConnectionPool;
import mx.database.MsSqlException;
import mx.database.MsSqlPool;
import mx.database.interfacce.IAccessTable;

/**
 * @author randazzo
 *
 */
public class ImpTmp extends Navigatore implements IAccessTable {

	private Logger log = Logger.getLogger(ImpTmp.class);

	/**
	 * Questa variabile viene utilizzata per indicare il bid della notizia
	 */
	private String bid = "";

	/**
	 * Questa variabile viene utilizzata per gestire il pool di connessione con
	 * il database
	 */
	private ConnectionPool conn;

	/**
	 * Questa variabile viene utilizzata per indicare la connessione assegnata
	 * all'utente
	 */
	private MsSqlPool msp;

	/**
	 * Questa variabile viene utilizzata per contenere il risultato della select
	 */
	private ResultSet rs;

	/**
	 * Costruttore
	 */
	public ImpTmp() {
		super();
	}

	/**
	 * 
	 * @see util.Navigatore#getQueryString()
	 */
	public String getQueryString() {
		return null;
	}

	/**
	 * 
	 * @see util.Navigatore#getHidden()
	 */
	public String getHidden() {
		return null;
	}

	/**
	 * Questo metodo viene utilizzato per estrarre i dati dalla tabella
	 * 
	 * @see mx.database.interfacce.IAccessTable#StartSelect()
	 */
	public ResultSet StartSelect() {
		String Sql = "";

		try {

			Sql = "select VIEW_FILEXML.* from VIEW_FILEXML, IMPTMP where VIEW_FILEXML.bid=IMPTMP.bid";

			msp = conn.getConn();

			rs = msp.StartSelect(Sql);
			this.setRecTot(this.recCount(rs));
			this.calcNum();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return rs;
	}

	/**
	 * Questo metodo viene utilizzato per terminare l'operazione di select dul
	 * database
	 * 
	 * @see mx.database.interfacce.IAccessTable#StopSelect()
	 */
	public void StopSelect() {
		try {
			if (rs != null)
				rs.close();
			if (rs != null)
				msp.StopSelect();
			if (msp != null)
				conn.releaseConn(msp);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * Questo metodo viene utilizzato per valorizzare il contenuto della tabella
	 * 
	 * @see mx.database.interfacce.IAccessTable#Insert()
	 */
	public void Insert() throws MsSqlException {
		MsSqlPool msp1 = null;
		String sql = "";
		try {
			msp1 = conn.getConn();
			sql = "insert into impTmp (bid) values ('" + bid.toUpperCase()
					+ "')";
			msp1.esegui(sql);
		} catch (MsSqlException e) {
			throw e;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new MsSqlException(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MsSqlException(e.getMessage());
		} finally {
			conn.releaseConn(msp1);
		}
	}

	/**
	 * Questo metodo viene utilizzato per cancellatre il contenuto di una
	 * tabella
	 */
	public void Delete() throws MsSqlException {
		MsSqlPool msp1 = null;
		String sql = "";
		try {
			msp1 = conn.getConn();
			sql = "delete from impTmp where bid='" + bid.toUpperCase() + "'";
			msp1.esegui(sql);
		} catch (MsSqlException e) {
			throw e;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new MsSqlException(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MsSqlException(e.getMessage());
		} finally {
			conn.releaseConn(msp1);
		}
	}

	/**
	 * Questo metodo viene utilizzato per Valorizzare la variabile utilizzata
	 * per la gestione delle connessioni con il database
	 * 
	 * @see mx.database.interfacce.IAccessTable#setConn(mx.database.ConnectionPool)
	 */
	public void setConn(ConnectionPool pool) {
		conn = pool;
	}

	/**
	 * Questo metodo viene utilizzato per indicare il bid della notizia
	 * 
	 * @param string
	 */
	public void setBid(String string) {
		bid = string;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.database.interfacce.IAccessTable#delete()
	 */
	public void delete() throws MsSqlException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.database.interfacce.IAccessTable#reset()
	 */
	public void reset() {

	}

}
