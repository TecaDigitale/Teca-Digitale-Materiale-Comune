/**
 * 
 */
package mx.teca.download.storage.socketFtp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import mx.ftp.ClientFtp;
import mx.ftp.ClientFtpException;
import mx.log4j.Logger;
import mx.teca.download.storage.Download;
import mx.teca.download.storage.exception.DownloadException;
import mx.teca.download.storage.image.ConvertImg;

/**
 * @author massi
 *
 */
public class ReadSocketActive
{

	/**
	 * Questa variabile viene utilizzata per gestire il log dell'aplicazione
	 */
	private static Logger log = new Logger(ReadSocketActive.class, "mx.teca.download.storage");

	/**
	 * 
	 */
	public ReadSocketActive()
	{
	}

	/**
   * Questo metodo viene utilizzato per leggere il File via Socket e
   * trasmetterlo al client
   * 
   * @param contentType
   *          Tipologia del materiale da inviare
   * @param pathSource
   *          Path del client dove risiede il materiale
   * @param fileSource
   *          File del materiale
   * @param response
   *          Connessione del client a cui mandare il materiale 
	 * @throws DownloadException 
   */
	public static  void readSocketActive(String contentType, String pathSource, String fileSource,
			HttpServletResponse response, boolean connectionActive, int timeOut, String logFile,
			String hostIp, int hostPorta, String hostLogin, String hostPsw, boolean absolutePath, 
			OutputStream outputStream) throws DownloadException
	{
		ClientFtp clientFtp = null;
		ByteArrayOutputStream baos = null;
		try
		{
			clientFtp = new ClientFtp();
			log.debug("contentType: "+contentType);
			if (response != null)
			{
				if (contentType.equals("OCR-IMG"))
					response.setHeader("Content-Type", "image/jpeg");
				else
					response.setHeader("Content-Type", contentType);
			}

			clientFtp.setConnectionActive(connectionActive);
 
			log.debug("timeOut: "+timeOut);
			clientFtp.setTimeOut(timeOut);
			
			log.debug("logFile: "+logFile);
			clientFtp.setLogFile(logFile);

			clientFtp.setLogAppend(false);

			log.debug("hostIp: "+hostIp);
			clientFtp.setIndirizzoIP(hostIp);

			log.debug("hostPorta: "+hostPorta);
			clientFtp.setPorta(hostPorta);

			log.debug("hostLogin: "+hostLogin);
			clientFtp.setLogin(hostLogin);

			log.debug("hostPsw: "+hostPsw);
			clientFtp.setPassword(hostPsw);

			log.debug("absolutePath: "+absolutePath);
			if (absolutePath)
				clientFtp.setPathRoot("/");
			
			clientFtp.open();
			if (contentType.equals("OCR-IMG"))
			{
				baos = new ByteArrayOutputStream();
				clientFtp.recieve(baos, pathSource, fileSource);
				baos.flush();
				if (response != null)
					ConvertImg.convertToImg(baos.toString(), response.getOutputStream());
				else
					ConvertImg.convertToImg(baos.toString(), outputStream);
			}
			else
			{
				if (response != null)
  				clientFtp.recieve(response.getOutputStream(), pathSource, fileSource);
				else
  				clientFtp.recieve(outputStream, pathSource, fileSource);
			}
		}
		catch (ClientFtpException e)
		{
			log.error(e);
			if (contentType.equals("OCR-IMG"))
				throw new DownloadException((String)Download.listaParam.get("imgError.FTP.ocr.title.color", "RED"),
						(String)Download.listaParam.get("imgError.FTP.ocr.title", "Messaggio di Errore"), 
						(String)Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK"), 
						(String)Download.listaParam.get("imgError.FTP.ocr.testo", "Problemi nell'accesso allo storage del materiale Multimediale"));
			else
				throw new DownloadException((String)Download.listaParam.get("imgError.FTP.image.title.color", "RED"),
						(String)Download.listaParam.get("imgError.FTP.image.title", "Messaggio di Errore"), 
						(String)Download.listaParam.get("imgError.FTP.image.testo.color", "BLACK"), 
						(String)Download.listaParam.get("imgError.FTP.image.testo", "Problemi nell'accesso allo storage del materiale Multimediale"));
		}
		catch (IOException e)
		{
			log.error(e); 
		}
		finally
		{
			if (clientFtp!= null)
				clientFtp.close();
		}
		
	}

}
