/**
 * 
 */
package mx.teca.download.storage;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletResponse;

import mx.log4j.Logger;
import mx.teca.download.storage.exception.DownloadException;
import mx.teca.download.storage.ftpDataTransferListener.MonitorFTPDataTransferListener;
import mx.teca.download.storage.image.ConvertImg;
import mx.teca.download.storage.interfacce.IReadFileProtocol;

/**
 * Questa classe viene utilizzata per gestire il trasferimento via Socket
 * tramite il protocollo FTP di un File
 * 
 * @author Massimiliano Randazzo
 * 
 */
public class ReadFileProtocolFTP implements IReadFileProtocol {

	/**
	 * Questa variabile viene utilizzata per gestire il log dell'aplicazione
	 */
	private Logger log = new Logger(ReadFileProtocolFTP.class,
			"mx.teca.download.storage");

	/**
	 * Questa variabile viene utilizzata per indicare se la connessione con ftp
	 * &egrave; Ativa o Passiva
	 */
	private boolean connectionActive = false;

	/**
	 * Questa variabile viene utilizzata per indicare l'indirizzo del Host da
	 * contattare
	 */
	private String hostIp = "";

	/**
	 * Questa variabile viene utilizzata per indicare la porta del Host da
	 * contattare
	 */
	private int hostPorta = 22;

	/**
	 * Questa variabile viene utilizzata per indicare il Login del Host da
	 * contattare
	 */
	private String hostLogin = "";

	/**
	 * Questa variabile viene utilizzata per indicare la Password del Host da
	 * contattare
	 */
	private String hostPsw = "";

	/**
	 * Questa variabile viene utilizzata per indicare il TimeOut del protocollo
	 * Ftp
	 */
	private int timeOut = 10000;

	/**
	 * Questa variabile viene utilizzata per indicare se il trasferimento ftp
	 * del materiale avviene tramite un persorco assoluto nel file system oppure
	 * no
	 */
	private boolean absolutePath = true;

	/**
	 * Questa variabile viene utilizzata per indicare il nome del file dove
	 * loggare l'applicazione
	 */
	private String logFile = "logFtp.log";

	/**
	 * Costruttore
	 * 
	 * @param connectionActive
	 *            Variabile utilizzata per indicare se la connessione ftp
	 *            &egrave; Attiva o PAssiva
	 * @param hostIp
	 *            Variabile utilizzata per indicare l'indirizzo del Host da
	 *            contattare
	 * @param hostPorta
	 *            Variabile utilizzata per indicare la porta del Host da
	 *            contattare
	 * @param hostLogin
	 *            Variabile utilizzata per indicare il Login del Host da
	 *            contattare
	 * @param hostPsw
	 *            Variabile utilizzata per indicare la Password del Host da
	 *            contatare
	 * @param timeOut
	 *            Variabile utilizzata per indicaere il TimeOut del protocollo
	 *            Ftp
	 * @param absolutePath
	 *            Variabile utilizzata per indicare se il trasferimento ftp del
	 *            materiale avviene tramite un persorco assoluto nel file system
	 *            oppure no
	 * @param logFile
	 *            Variabile utilizzata per indicare il nome del file dove
	 *            loggare l'applicazione Ftp
	 */
	public ReadFileProtocolFTP(boolean connectionActive, String hostIp,
			int hostPorta, String hostLogin, String hostPsw, int timeOut,
			boolean absolutePath, String logFile) {
		this.connectionActive = connectionActive;
		this.hostIp = hostIp;
		this.hostPorta = hostPorta;
		this.hostLogin = hostLogin;
		this.hostPsw = hostPsw;
		this.timeOut = timeOut;
		this.absolutePath = absolutePath;
		this.logFile = logFile;
	}

	/**
	 * Questo metodo viene utilizzato per leggere il File via Socket e
	 * trasmetterlo al client
	 * 
	 * @param contentType
	 *            Tipologia del materiale da inviare
	 * @param pathSource
	 *            Path del client dove risiede il materiale
	 * @param fileSource
	 *            File del materiale
	 * @param response
	 *            Connessione del client a cui mandare il materiale
	 * @throws DownloadException
	 * 
	 * @see mx.teca.download.storage.interfacce.IReadFileProtocol#readSocket(java.lang.String,
	 *      java.lang.String, java.lang.String,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void readSocket(String contentType, String pathSource,
			String fileSource, HttpServletResponse response,
			OutputStream outputStream) throws DownloadException {
		GregorianCalendar gcStart = new GregorianCalendar();
		GregorianCalendar gcStop = null;
		FTPClient client = null;
		ByteArrayOutputStream baos = null;
		MonitorFTPDataTransferListener monitor = null;
		long dif = 0;
		String errTitleColor = null;
		String errTitleText = null;
		String errMsgColor = null;
		String errMsgText = null;
		String url = null;

		url = hostIp;
		url += ":"+hostPorta;
		if (absolutePath)
			url += "/";
		url += pathSource;
		url += "/";
		url += fileSource;

		try {
			client = new FTPClient();

			log.debug("contentType: "+contentType);
			if (response != null)
			{
				if (contentType.equals("OCR-IMG"))
					response.setHeader("Content-Type", "image/jpeg");
				else
					response.setHeader("Content-Type", contentType);
			}

			if (hostPorta==22){
				// Eseguo la connessione SFTP
				log.debug("Inizializzo il protocollo per una connessione SFTP");
				client.setSecurity(FTPClient.SECURITY_FTPS);
			}

			if (connectionActive)
				client.setPassive(false);
			else
				client.setPassive(true);

			// Apro la connessione con il Server
			log.debug("Apro la connessione con il server "+hostIp+":"+hostPorta);
			client.connect(hostIp, hostPorta);

			// Eseguo il login verso il Server
			log.debug("Eseguo la connessione login: "+hostLogin+" pwd:"+hostPsw);
			client.login(hostLogin, hostPsw);

			log.debug("absolutePath: "+absolutePath);
			if (absolutePath)
				client.changeDirectory("/");
			
			log.debug("cambio cartella: "+ pathSource);
			client.changeDirectory(pathSource);

			monitor = new MonitorFTPDataTransferListener(hostIp, hostPorta, absolutePath, pathSource, fileSource);
			if (contentType.equals("OCR-IMG"))
			{
				baos = new ByteArrayOutputStream();
				client.download(fileSource, baos, 0, monitor);
				baos.flush();
				if (response != null)
					ConvertImg.convertToImg(baos.toString(), response.getOutputStream());
				else
					ConvertImg.convertToImg(baos.toString(), outputStream);
			}
			else
			{
				if (response != null)
					client.download(fileSource, response.getOutputStream(), 0, monitor);
				else
					client.download(fileSource, outputStream, 0, monitor);
			}

		} catch (IllegalStateException e) {
			log.error(url+" IllegalStateException ",e);
			if (contentType.equals("OCR-IMG")){
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.ocr.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.ocr.title","Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.ocr.testo","Problemi nell'accesso allo storage del materiale Multimediale");
			} else{
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.image.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.image.title", "Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.image.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.image.testo", "Problemi nell'accesso allo storage del materiale Multimediale");
			}
			errMsgText += "\n IllegalStateException";
			errMsgText += e.getMessage();
			
			throw new DownloadException(errTitleColor, errTitleText, errMsgColor, errMsgText);
		} catch (IOException e) {
			log.error(url+" IOException ",e);
			if (contentType.equals("OCR-IMG")){
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.ocr.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.ocr.title","Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.ocr.testo","Problemi nell'accesso allo storage del materiale Multimediale");
			} else{
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.image.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.image.title", "Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.image.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.image.testo", "Problemi nell'accesso allo storage del materiale Multimediale");
			}
			errMsgText += "\n IOException";
			errMsgText += e.getMessage();
			
			throw new DownloadException(errTitleColor, errTitleText, errMsgColor, errMsgText);
		} catch (FTPIllegalReplyException e) {
			log.error(url+" FTPIllegalReplyException ",e);
			if (contentType.equals("OCR-IMG")){
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.ocr.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.ocr.title","Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.ocr.testo","Problemi nell'accesso allo storage del materiale Multimediale");
			} else{
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.image.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.image.title", "Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.image.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.image.testo", "Problemi nell'accesso allo storage del materiale Multimediale");
			}
			errMsgText += "\n FTPIllegalReplyException";
			errMsgText += e.getMessage();
			
			throw new DownloadException(errTitleColor, errTitleText, errMsgColor, errMsgText);
		} catch (FTPException e) {
			log.error(url+" FTPException ",e);
			if (contentType.equals("OCR-IMG")){
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.ocr.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.ocr.title","Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.ocr.testo","Problemi nell'accesso allo storage del materiale Multimediale");
			} else{
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.image.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.image.title", "Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.image.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.image.testo", "Problemi nell'accesso allo storage del materiale Multimediale");
			}
			errMsgText += "\n FTPException";
			errMsgText += e.getMessage();
			
			throw new DownloadException(errTitleColor, errTitleText, errMsgColor, errMsgText);
		} catch (FTPDataTransferException e) {
			log.error(url+" FTPDataTransferException ",e);
			if (contentType.equals("OCR-IMG")){
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.ocr.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.ocr.title","Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.ocr.testo","Problemi nell'accesso allo storage del materiale Multimediale");
			} else{
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.image.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.image.title", "Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.image.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.image.testo", "Problemi nell'accesso allo storage del materiale Multimediale");
			}
			errMsgText += "\n FTPDataTransferException";
			errMsgText += e.getMessage();
			
			throw new DownloadException(errTitleColor, errTitleText, errMsgColor, errMsgText);
		} catch (FTPAbortedException e) {
			log.error(url+" FTPAbortedException ",e);
			if (contentType.equals("OCR-IMG")){
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.ocr.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.ocr.title","Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.ocr.testo","Problemi nell'accesso allo storage del materiale Multimediale");
			} else{
				errTitleColor = (String) Download.listaParam.get("imgError.FTP.image.title.color", "RED");
				errTitleText = (String) Download.listaParam.get("imgError.FTP.image.title", "Messaggio di Errore");
				errMsgColor = (String) Download.listaParam.get("imgError.FTP.image.testo.color", "BLACK");
				errMsgText = (String) Download.listaParam.get("imgError.FTP.image.testo", "Problemi nell'accesso allo storage del materiale Multimediale");
			}
			errMsgText += "\n FTPAbortedException";
			errMsgText += e.getMessage();
			
			throw new DownloadException(errTitleColor, errTitleText, errMsgColor, errMsgText);
		} finally {
			try {
				if (client != null) {
					// Eseguo la disconnessione con il Server
					client.disconnect(true);
				}
			} catch (IllegalStateException e) {
				log.error(url+" IllegalStateException Disconnect ",e);
				if (contentType.equals("OCR-IMG")){
					errTitleColor = (String) Download.listaParam.get("imgError.FTP.ocr.title.color", "RED");
					errTitleText = (String) Download.listaParam.get("imgError.FTP.ocr.title","Messaggio di Errore");
					errMsgColor = (String) Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK");
					errMsgText = (String) Download.listaParam.get("imgError.FTP.ocr.testo","Problemi nell'accesso allo storage del materiale Multimediale");
				} else{
					errTitleColor = (String) Download.listaParam.get("imgError.FTP.image.title.color", "RED");
					errTitleText = (String) Download.listaParam.get("imgError.FTP.image.title", "Messaggio di Errore");
					errMsgColor = (String) Download.listaParam.get("imgError.FTP.image.testo.color", "BLACK");
					errMsgText = (String) Download.listaParam.get("imgError.FTP.image.testo", "Problemi nell'accesso allo storage del materiale Multimediale");
				}
				errMsgText += "\n IllegalStateException";
				errMsgText += e.getMessage();
				
				throw new DownloadException(errTitleColor, errTitleText, errMsgColor, errMsgText);
			} catch (IOException e) {
				log.error(url+" IOException Disconnect ",e);
				if (contentType.equals("OCR-IMG")){
					errTitleColor = (String) Download.listaParam.get("imgError.FTP.ocr.title.color", "RED");
					errTitleText = (String) Download.listaParam.get("imgError.FTP.ocr.title","Messaggio di Errore");
					errMsgColor = (String) Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK");
					errMsgText = (String) Download.listaParam.get("imgError.FTP.ocr.testo","Problemi nell'accesso allo storage del materiale Multimediale");
				} else{
					errTitleColor = (String) Download.listaParam.get("imgError.FTP.image.title.color", "RED");
					errTitleText = (String) Download.listaParam.get("imgError.FTP.image.title", "Messaggio di Errore");
					errMsgColor = (String) Download.listaParam.get("imgError.FTP.image.testo.color", "BLACK");
					errMsgText = (String) Download.listaParam.get("imgError.FTP.image.testo", "Problemi nell'accesso allo storage del materiale Multimediale");
				}
				errMsgText += "\n IOException";
				errMsgText += e.getMessage();
				
				throw new DownloadException(errTitleColor, errTitleText, errMsgColor, errMsgText);
			} catch (FTPIllegalReplyException e) {
				log.error(url+" FTPIllegalReplyException Disconnect ",e);
				if (contentType.equals("OCR-IMG")){
					errTitleColor = (String) Download.listaParam.get("imgError.FTP.ocr.title.color", "RED");
					errTitleText = (String) Download.listaParam.get("imgError.FTP.ocr.title","Messaggio di Errore");
					errMsgColor = (String) Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK");
					errMsgText = (String) Download.listaParam.get("imgError.FTP.ocr.testo","Problemi nell'accesso allo storage del materiale Multimediale");
				} else{
					errTitleColor = (String) Download.listaParam.get("imgError.FTP.image.title.color", "RED");
					errTitleText = (String) Download.listaParam.get("imgError.FTP.image.title", "Messaggio di Errore");
					errMsgColor = (String) Download.listaParam.get("imgError.FTP.image.testo.color", "BLACK");
					errMsgText = (String) Download.listaParam.get("imgError.FTP.image.testo", "Problemi nell'accesso allo storage del materiale Multimediale");
				}
				errMsgText += "\n FTPIllegalReplyException";
				errMsgText += e.getMessage();
				
				throw new DownloadException(errTitleColor, errTitleText, errMsgColor, errMsgText);
			} catch (FTPException e) {
				log.error(url+" FTPException Disconnect ",e);
				if (contentType.equals("OCR-IMG")){
					errTitleColor = (String) Download.listaParam.get("imgError.FTP.ocr.title.color", "RED");
					errTitleText = (String) Download.listaParam.get("imgError.FTP.ocr.title","Messaggio di Errore");
					errMsgColor = (String) Download.listaParam.get("imgError.FTP.ocr.testo.color", "BLACK");
					errMsgText = (String) Download.listaParam.get("imgError.FTP.ocr.testo","Problemi nell'accesso allo storage del materiale Multimediale");
				} else{
					errTitleColor = (String) Download.listaParam.get("imgError.FTP.image.title.color", "RED");
					errTitleText = (String) Download.listaParam.get("imgError.FTP.image.title", "Messaggio di Errore");
					errMsgColor = (String) Download.listaParam.get("imgError.FTP.image.testo.color", "BLACK");
					errMsgText = (String) Download.listaParam.get("imgError.FTP.image.testo", "Problemi nell'accesso allo storage del materiale Multimediale");
				}
				errMsgText += "\n FTPException";
				errMsgText += e.getMessage();
				
				throw new DownloadException(errTitleColor, errTitleText, errMsgColor, errMsgText);
			}
		}
		// if (connectionActive)
		// ReadSocketActive.readSocketActive(contentType, pathSource,
		// fileSource, response, connectionActive,
		// timeOut, logFile, hostIp, hostPorta, hostLogin, hostPsw,
		// absolutePath, outputStream);
		// else
		// ReadSocketPassive.readSocketPassive(contentType, pathSource,
		// fileSource, response, hostIp, hostPorta,
		// hostLogin, hostPsw, outputStream);
		gcStop = new GregorianCalendar();
		dif = gcStop.getTimeInMillis() - gcStart.getTimeInMillis();
		if (dif > 5000) {
			log.warn("File: " + pathSource + "/" + fileSource
					+ " scaricato in " + dif + " millisec");
		}
	}

}
