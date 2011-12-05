/**
 * 
 */
package mx.teca.download.storage.socketFtp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import mx.ftp.std.ClientFtp;
import mx.log4j.Logger;
import mx.socket.client.exception.ClientException;
import mx.teca.download.storage.Download;
import mx.teca.download.storage.exception.DownloadException;
import mx.teca.download.storage.image.ConvertImg;

/**
 * @author massi
 *
 */
public class ReadSocketPassive
{

	/**
	 * Questa variabile viene utilizzata per gestire il log dell'aplicazione
	 */
	private static Logger log = new Logger(ReadSocketPassive.class, "mx.teca.download.storage");

	/**
	 * 
	 */
	public ReadSocketPassive()
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
	public static void readSocketPassive(String contentType, String pathSource, String fileSource,
			HttpServletResponse response, String hostIp, int hostPorta, String hostLogin,
			String hostPsw, OutputStream outputStream) throws DownloadException
	{
		ClientFtp clientFtp = null;
		ByteArrayOutputStream baos = null;

		try
		{
			clientFtp = new ClientFtp();
			clientFtp.connect(hostIp, hostPorta, hostLogin, hostPsw);
			if (response != null)
			{
  			if (contentType.equals("OCR-IMG"))
  				response.setHeader("Content-Type", "image/jpeg");
  			else
  				response.setHeader("Content-Type", contentType);
			}

			clientFtp.changeDir(pathSource);
			clientFtp.bin();
			if (contentType.equals("OCR-IMG"))
			{
				baos = new ByteArrayOutputStream();
  			clientFtp.get(fileSource, baos);
				baos.flush();
				if (response != null)
					ConvertImg.convertToImg(baos.toString(), response.getOutputStream());
				else
					ConvertImg.convertToImg(baos.toString(), outputStream);
			}
			else
			{
				if (response != null)
    			clientFtp.get(fileSource, response.getOutputStream());
				else
    			clientFtp.get(fileSource, outputStream);
			}
		}
		catch (ClientException e)
		{
			if (contentType.equals("OCR-IMG"))
				throw new DownloadException((String)Download.listaParam.get("imgError.FTP.ocr.title.color", "RED"),
						(String)Download.listaParam.get("imgError.FTP.ocr.title", "Messaggio di Errore"), 
						(String)Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK"), 
						(String)Download.listaParam.get("imgError.FTP.ocr.testo", "Problemi nell'accesso allo storage del materiale Multimediale"));
			else
				throw new  DownloadException((String)Download.listaParam.get("imgError.FTP.image.title.color", "RED"),
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
			if (clientFtp != null)
				clientFtp.disconnect();
		}
	}
	
}
