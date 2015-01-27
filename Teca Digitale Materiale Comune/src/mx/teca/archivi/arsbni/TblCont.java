/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import mx.database.ConnectionPool;
import mx.database.MsSqlException;
import mx.database.MsSqlPool;
import mx.database.table.Table;
import mx.database.table.interfacce.iGenID;

/**
 * Questa classe viene utilizzata per gestire l'accesso alla tabella TBLCONT
 * 
 * @author Randazzo
 *
 */
public class TblCont extends Table implements iGenID {

	private Logger log = Logger.getLogger(TblCont.class);

	/**
	 * Costruttore con definizione del Pool di connessione e connesione
	 * preselezionata per le operazioni di modifica della tabella con lista dei
	 * parametri da caricare
	 * 
	 * @param conn
	 *            Poll di connessioni con il database
	 * @param msp
	 *            Connessione da utilizzare nelle operazioni di modifica della
	 *            tabella (insert, update, delete)
	 * @param map
	 *            Lista parametri da caricare
	 */
	public TblCont(ConnectionPool conn, MsSqlPool msp, Map<Object, Object> map) {
		super(conn, msp, map);
	}

	/**
	 * Costruttore con la gestione del Pool di connessioni con lista dei
	 * parametri da caricare
	 * 
	 * @param conn
	 *            Pool di connessioni con il Database
	 * @param map
	 *            Lista parametri da caricare
	 */
	public TblCont(ConnectionPool conn, Map<Object, Object> map) {
		super(conn, map);
	}

	/**
	 * Costruttore con definizione del Pool di connessione e connesione
	 * preselezionata per le operazioni di modifica della tabella
	 * 
	 * @param conn
	 *            Poll di connessioni con il database
	 * @param msp
	 *            Connessione da utilizzare nelle operazioni di modifica della
	 *            tabella (insert, update, delete)
	 */
	public TblCont(ConnectionPool conn, MsSqlPool msp) {
		super(conn, msp);
	}

	/**
	 * Costruttore
	 */
	public TblCont() {
		super();
	}

	/**
	 * Costruttore con la gestione del Pool di connessioni
	 * 
	 * @param conn
	 *            Poll di connessioni con il database
	 */
	public TblCont(ConnectionPool conn) {
		super(conn);
	}

	/**
	 * Costruttore con la gestione della connessione selezionata
	 * 
	 * @param msp
	 *            Connessione da utilizzare nelle operazioni di modifica della
	 *            tabella (insert, update, delete)
	 */
	public TblCont(MsSqlPool msp) {
		super(msp);
	}

	/**
	 * Questo metodo viene utilizzato per indicare la lista dei campi che
	 * compongono la tabella
	 * 
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi() {
		this.setColKey(true);
		this.addCampi("contCodEnte", "TBLCONT", "CONTCODENTE", true);
		this.addCampi("contProg", "TBLCONT", "CONTPROG");
	}

	/**
	 * Questo metodo viene utilizzato per calcolare l'identificativo univoco per
	 * la chiave primaria
	 * 
	 * @param code
	 *            codice che indica la tabella per la quale generare il codice
	 * @return Identificativo calcolato
	 */
	public Integer genID(String code) {
		int ris = 0;
		ResultSet rs = null;
		try {
			this.setCampoValue("contProg", null);
			this.setCampoValue("contCodEnte", code);
			rs = startSelect();
			if (rs.next()) {
				ris = rs.getInt("contProg");
				ris++;
				this.setCampoValue("contProg", ris);
				this.update();
			} else {
				ris = 1;
				this.setCampoValue("contProg", ris);
				this.insert();
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} catch (MsSqlException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					this.stopSelect();
				}
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		}
		return new Integer(ris);
	}

	/**
	 * Questo metodo viene utilizzato per calcolare la chiave univoca per la
	 * chiave primaria
	 * 
	 * @param code
	 *            codice che indica la tabella per la quale generare il codice
	 * @param length
	 *            indica la lunghezza della chiave
	 * @return Chiave calcolata
	 */
	public String genKey(String code, int length) {
		String ris = "";
		ris = genID(code).toString();
		while ((ris.length() + code.length()) < length)
			// for(int x=0; x<(length-ris.length()); x++)
			ris = "0" + ris;
		return code + ris;
	}

	protected void postUpdate() {
	}

	protected void preUpdate() {
	}

}
