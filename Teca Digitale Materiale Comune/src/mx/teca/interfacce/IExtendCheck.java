/**
 * 
 */
package mx.teca.interfacce;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.database.ConnectionPool;
import mx.magEdit.mag.Mag;

/**
 * Questa interfaccia verrà utilizzata per persolizzare le installazioni implementando dei controlli aggiuntivi
 * 
 * @author Massimiliano Randazzo
 *
 */
public interface IExtendCheck
{

	/**
	 * Questo metodo viene utilizzato per fare delle verifiche aggiuntive sul file Mag
	 * 
	 * @param mag File Mag da verificare
	 */
	public abstract void checkMag(Mag mag);

	/**
	 * Questo metodo viene utilizzato per esterndere la verifica sugli usage
	 * 
	 * @param usage Codice Usage da verificare
	 * @return risultato della verifica
	 */
	public abstract boolean checkUsage(String usage);

	/**
	 * Questo metodo viene utilizzato per testare il tipo di visualizzatore alternativo
	 * 
	 * @param response Questa variabile viene utilizzata per gestire le risposte verso il client
	 * @param request Questa variabile viene utilizzata per gestire le richieste del client
	 * @param pool Questa variabile viene utilizzata per il pool delle connessioni con il database
	 * @return risultato della verifica
	 */
	public abstract boolean checkViewer(HttpServletResponse response, HttpServletRequest request, ConnectionPool pool);

	/**
	 * Questo metodo viene utilizzato per testare la fruibilità del materiale
	 * 
	 * @param request Questa variabile viene utilizzata per gestire le richieste del client
	 * @param risIdr Identificativo della risorsa Digitale
	 * @param pool Questa variabile viene utilizzata per gestire il pool di connessioni con il database
	 * @return Risultato della verifica
	 */
	public abstract boolean checkFruib(HttpServletRequest request, String risIdr, ConnectionPool pool, ServletContext application);
	
}
