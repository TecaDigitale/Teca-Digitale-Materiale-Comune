/**
 * 
 */
package mx.teca.download.storage.interfacce;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import mx.teca.download.storage.exception.DownloadException;

/**
 * Questa interfaccia viene utilizzata per gestire i diversi protocolli per la lettura dei file 
 * 
 * @author Massimiliano Randazzo
 *
 */
public interface IReadFileProtocol
{

	/**
	 * Questo metodo viene utilizzato per leggere le informazioni via socket
	 * 
	 * @param contentType Content Type del file da trasmettere
	 * @param pathSource Path relativo alla posizione del file
	 * @param fileSource Nome del file
	 * @param response sochek dove trasmettere il file
	 * @throws DownloadException 
	 */
	public void readSocket(String contentType, String pathSource, String fileSource,
			HttpServletResponse response, OutputStream outputStream) throws DownloadException;
}
