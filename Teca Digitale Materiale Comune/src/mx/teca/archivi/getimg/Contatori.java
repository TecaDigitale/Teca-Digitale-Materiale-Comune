/**
 * 
 */
package mx.teca.archivi.getimg;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import mx.database.ConnectionPool;
import mx.database.MsSqlException;
import mx.database.MsSqlPool;
import mx.database.table.Table;
import mx.database.table.interfacce.iGenID;

/**
 * @author Randazzo
 *
 */
public class Contatori extends Table implements iGenID {

	private Logger log = Logger.getLogger(Contatori.class);

	/**
	 * Costruttore con la gestione del pool di connessione
	 * 
	 * @param conn
	 */
	public Contatori(ConnectionPool conn) {
		super(conn);
	}

	/**
	 * Costruttore con la gestione della connessione pre selezionata
	 * 
	 * @param msp
	 */
	public Contatori(MsSqlPool msp) {
		super(msp);
	}

	/**
	 * Costruttore
	 */
	public Contatori() {
		super();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see mx.database.table.interfacce.iGenID#genID(java.lang.String)
	 */
	public Integer genID(String code) {
		int ris = 0;
		ResultSet rs = null;
		try {
			this.setCampoValue("tabella", code);
			rs = startSelect();
			if (rs.next()) {
				ris = rs.getInt("ultimoValore");
				ris++;
				this.setCampoValue("ultimoValore", ris);
				this.update();
			} else {
				ris = 1;
				this.setCampoValue("ultimoValore", ris);
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
	 * Questo metodo viene utilizzato per indicare i campi della tabella
	 * 
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi() {
		this.setColKey(true);
		this.addCampi("tabella", "CONTATORI", "TABELLA", true);
		this.addCampi("ultimoValore", "CONTATORI", "ULTIMOVALORE");
	}

	public String genKey(String code, int length) {
		return null;
	}

	protected void postUpdate() {
	}

	protected void preUpdate() {
	}

}
