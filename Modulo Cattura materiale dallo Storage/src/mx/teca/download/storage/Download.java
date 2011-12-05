/**
 * 
 */
package mx.teca.download.storage;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import mx.log4j.Logger;
import mx.teca.download.storage.exception.DownloadException;
import mx.teca.download.storage.interfacce.IReadFileProtocol;
import mx.util.MxHashtable;


/**
 * @author massi
 *
 */
public class Download
{

	/**
	 * Questa variabile viene utilizzata per gestire il log dell'aplicazione
	 */
	private Logger log = new Logger(Download.class, "mx.teca.download.storage");

	/**
	 * Questa variabile viene utilizzata per indicare se il percorso da calcolare  Assoluto o relativo
	 */
	private boolean absolutePath = false;

	/**
	 * Questo metodo viene utilizzato per indicare la larghezza dell'imamgine in caso di Errore
	 */
	public static int width = 794;

	/**
	 * Questo metodo viene utilizzato per indicare l'altezza dell'imamgine in caso di Errore
	 */
	public static int height = 1123;

	/**
	 * Questa variabile viene utilizzata per indicare la lista dei parametri dell'applicazione
	 */
	public static MxHashtable listaParam = new MxHashtable();

	private boolean connectionActive = false;
	private int timeOut = 10;
	private String ftpLog = "";
	private HttpServletResponse response = null;
	private OutputStream outputStream = null;

	/**
	 * 
	 */
	public Download(boolean absolutePath, boolean connectionActive, int timeOut, String ftpLog, HttpServletResponse response)
	{
		this.absolutePath = absolutePath;
		this.connectionActive = connectionActive;
		this.timeOut = timeOut;
		this.ftpLog = ftpLog;
		this.response = response;
	}

	/**
	 * 
	 */
	public Download(boolean absolutePath, boolean connectionActive, int timeOut, String ftpLog, OutputStream outputStream)
	{
		this.absolutePath = absolutePath;
		this.connectionActive = connectionActive;
		this.timeOut = timeOut;
		this.ftpLog = ftpLog;
		this.outputStream = outputStream;
	}
	
	/**
	 * Questo metodo viene utilizzato per eseguire la procedura di Download
	 * 
	 * @param hostProt
	 * @param hostServerPath
	 * @param hostPathDisco
	 * @param fileDig
	 * @param hostIp
	 * @param hostPorta
	 * @param hostLogin
	 * @param hostPsw
	 * @param mimeType
	 * @throws DownloadException 
	 */
	public void download(String hostProt, String hostServerPath, String hostPathDisco, String fileDig, String hostIp, int hostPorta, 
			String hostLogin, String hostPsw, String mimeType) throws DownloadException
	{
		String pathSource = "";
		String fileSource = "";
		IReadFileProtocol readFile = null;

		try
		{
			log.debug("absolutePath: "+absolutePath);
			log.debug("hostProt: " + hostProt);
			log.debug("hostServerPath: " + hostServerPath);
			log.debug("hostPathDisco: " + hostPathDisco);
			log.debug("fileDig: " + fileDig);
			log.debug("hostIp: " + hostIp);
			log.debug("hostPorta: " + hostPorta);
			log.debug("hostLogin: " + hostLogin);
			log.debug("hostPsw: " + hostPsw);
			
			pathSource = "";
			if (absolutePath || hostProt.equals("NFS"))
			{
				pathSource = hostServerPath;
				if (pathSource.trim().length()>0)
				{
					if (pathSource.startsWith("./"))
						pathSource = pathSource.substring(1);
					else if (!pathSource.startsWith("/"))
						pathSource = "/"+pathSource;
					if (!pathSource.endsWith("/"))
						pathSource += "/";
				}
				else
					pathSource = "/";
			}
			else
				pathSource = "/";

			if (!hostPathDisco.trim().equals(""))
			{
				if (hostPathDisco.trim().startsWith("./"))
					pathSource += hostPathDisco.trim().substring(2);
				else if (hostPathDisco.trim().startsWith("/"))
					pathSource += hostPathDisco.trim().substring(1);
				else
					pathSource += hostPathDisco.trim();

				if (!pathSource.endsWith("/"))
					pathSource += "/";
			}

			pathSource = pathSource.replace("\\", "/");

			if (!fileDig.trim().equals(""))
			{
				fileSource = fileDig.trim();
				fileSource = fileSource.replace("\\", "/");

				if (fileSource.startsWith("./"))
					fileSource = fileSource.substring(2);

				if (fileSource.lastIndexOf("/") > -1)
				{
					if (!pathSource.endsWith("/"))
						pathSource += "/";
					pathSource += fileSource.substring(0, fileSource.lastIndexOf("/"));
					fileSource = fileSource.substring(fileSource.lastIndexOf("/") + 1);
				}

				if (hostProt.equals("NFS"))
					readFile = new ReadFileProtocolNFS();
				else
					readFile = new ReadFileProtocolFTP(connectionActive, hostIp, hostPorta, hostLogin, hostPsw, timeOut, absolutePath, ftpLog);
				readFile.readSocket(mimeType,pathSource, fileSource, response, outputStream);
			}
			else
				throw new DownloadException((String)Download.listaParam.get("imgError.fileMag.title.color", "RED"),
						(String)Download.listaParam.get("imgError.fileMag.title", "Messaggio di Errore"), 
						(String)Download.listaParam.get("imgError.fileMag.testo.color", "BLACK"), 
						(String)Download.listaParam.get("imgError.fileMag.testo", "Risorsa Digitale non definita"));
		}
		catch (DownloadException e)
		{
			throw e;
		}
	}
}
