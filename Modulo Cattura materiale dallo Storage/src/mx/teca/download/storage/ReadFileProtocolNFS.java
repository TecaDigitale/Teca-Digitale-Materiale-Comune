/**
 * 
 */
package mx.teca.download.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletResponse;

import mx.log4j.Logger;
import mx.teca.download.storage.exception.DownloadException;
import mx.teca.download.storage.image.ConvertImg;
import mx.teca.download.storage.interfacce.IReadFileProtocol;

/**
 * Questa classe vine utilizzata per gestire il traferimento via Socker tramite il protocollo NFS
 * 
 * @author Massimiliano Randazzo
 *
 */
public class ReadFileProtocolNFS implements IReadFileProtocol
{

	/**
	 * Qusta variabile viene utilizzata per gestire il log dell'applicazione
	 */
	private Logger log = new  Logger(ReadFileProtocolNFS.class, "mx.teca.download.storage");

	/**
	 * Costruttore
	 */
	public ReadFileProtocolNFS()
	{
	}

	/**
	 * @throws DownloadException 
	 * @see mx.teca.download.storage.interfacce.IReadFileProtocol#readSocket(java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpServletResponse)
	 */
	public void readSocket(String contentType, String pathSource,
			String fileSource, HttpServletResponse response, OutputStream outputStream) throws DownloadException
	{
		if (contentType.equals("text/xml"))
			readSocketXml(contentType, pathSource, fileSource, response, outputStream);
		else if (contentType.equals("OCR-IMG"))
			readSocketOcr(pathSource, fileSource, response, outputStream);
		else
			readSocketImg(contentType, pathSource, fileSource, response, outputStream);
	}

	/**
	 * Questo metodo viene 
	 * @param pathSource
	 * @param fileSource
	 * @param response
	 * @throws DownloadException 
	 */
	public void readSocketOcr(String pathSource,
			String fileSource, HttpServletResponse response, OutputStream outputStream) throws DownloadException
	{
		File f = null;
		FileInputStream fiis = null;

		try
		{
			log.debug("readSocketOcr");
			if (response != null)
				response.setHeader("Content-Type", "image/jpeg");

			f = new File(pathSource+File.separator+fileSource);
			if (f.exists())
			{
				
				log.debug("File: "+f.getAbsolutePath());
				fiis = new FileInputStream(f);
				if (response != null)
					ConvertImg.convertToImg(fiis, response.getOutputStream());
				else
					ConvertImg.convertToImg(fiis, outputStream);

				log.debug("File: "+f.getAbsolutePath()+" len: "+f.length());
			}
			else
				throw new DownloadException((String)Download.listaParam.get("imgError.NFS.ocr.fileNotFound.title.color", "RED"),
						(String)Download.listaParam.get("imgError.NFS.ocr.fileNotFound.title", "Messaggio di Errore"), 
						(String)Download.listaParam.get("imgError.NFS.ocr.fileNotFound.testo.color", "BLACK"), 
						(String)Download.listaParam.get("imgError.NFS.imocrage.fileNotFound.testo", "Non \u00E8 possibile reperire l'oggetto digitale richiesto"));
		}
		catch (FileNotFoundException e)
		{
			log.error(e);
		}
		catch (DownloadException e) 
		{
			throw e;
		}
		catch (IOException e)
		{
			log.error(e);
			throw new DownloadException((String)Download.listaParam.get("imgError.NFS.ocr.fileReadError.title.color", "RED"),
					(String)Download.listaParam.get("imgError.NFS.ocr.fileReadError.title", "Messaggio di Errore"), 
					(String)Download.listaParam.get("imgError.NFS.ocr.fileReadError.testo.color", "BLACK"), 
					(String)Download.listaParam.get("imgError.NFS.ocr.fileReadError.testo", "Problemi nella lettura dell'oggetto digitale richiesto"));
		}
		finally
		{
			try
			{
				if (fiis != null)
					fiis.close();
			}
			catch (IOException e)
			{
				log.error(e);
			}
		}
	}

	public void readSocketImg(String contentType, String pathSource,
			String fileSource, HttpServletResponse response, OutputStream outputStream) throws DownloadException
	{
		File f = null;
		FileImageInputStream fiis = null;
		byte[] bbuf = new byte[1000000]; 
		int len = -1;
		int tot = 0;

		try
		{
			log.debug("readSocketImg");
			log.info("Content-Type: "+contentType);
			if (response != null)
				response.setHeader("Content-Type", contentType);

			f = new File(pathSource+File.separator+fileSource);
			if (f.exists())
			{
				
				log.info("File: "+f.getAbsolutePath());
				fiis = new FileImageInputStream(f);

				while ((len = fiis.read(bbuf))>-1)
				{
					tot +=len;
					log.debug("Len: "+len);
					log.debug("Len: "+bbuf.length);
					if (response != null)
						response.getOutputStream().write(bbuf,0,len);
					else
						outputStream.write(bbuf,0,len);
				}
				log.debug("File: "+f.getAbsolutePath()+" len: "+f.length()+" - "+tot);
			}
			else
				throw new DownloadException((String)Download.listaParam.get("imgError.NFS.image.fileNotFound.title.color", "RED"),
						(String)Download.listaParam.get("imgError.NFS.image.fileNotFound.title", "Messaggio di Errore"), 
						(String)Download.listaParam.get("imgError.NFS.image.fileNotFound.testo.color", "BLACK"), 
						(String)Download.listaParam.get("imgError.NFS.image.fileNotFound.testo", "Non \u00E8 possibile reperire l'oggetto digitale richiesto"));
		}
		catch (FileNotFoundException e)
		{
			log.error(e);
		}
		catch (DownloadException e)
		{
			throw e;
		}
		catch (IOException e)
		{
			if (!e.getClass().getName().equals("org.apache.catalina.connector.ClientAbortException"))
			{
				log.error(e);
				throw new DownloadException((String)Download.listaParam.get("imgError.NFS.image.fileReadError.title.color", "RED"),
						(String)Download.listaParam.get("imgError.NFS.image.fileReadError.title", "Messaggio di Errore"), 
						(String)Download.listaParam.get("imgError.NFS.image.fileReadError.testo.color", "BLACK"), 
						(String)Download.listaParam.get("imgError.NFS.image.fileReadError.testo", "Problemi nella lettura dell'oggetto digitale richiesto"));
			}
		}
		finally
		{
			try
			{
				if (fiis != null)
					fiis.close();
			}
			catch (IOException e)
			{
				log.error(e);
			}
		}
	}
	
	public void readSocketXml(String contentType, String pathSource,
			String fileSource, HttpServletResponse response, OutputStream outputStream)
	{
		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		OutputStreamWriter osw = null;
		char[] cbuf = new char[100000];
		int len=-1;

		try
		{
			log.debug("readSocketXml");
			if (response != null)
				response.setHeader("Content-Type", contentType);
			
			//response.setHeader("Content-Disposition", "attachment;filename=test.csv");

			f = new File(pathSource+File.separator+fileSource);
			if (f.exists())
			{
				
				log.debug("File: "+f.getAbsolutePath());
				fr = new FileReader(f);
				
				br = new BufferedReader(fr);
				if (response != null)
					osw = new OutputStreamWriter(response.getOutputStream());
				else
					osw = new OutputStreamWriter(outputStream);
					
				while ((len=br.read(cbuf))>-1)
				{
					log.debug("Len: "+len);
					log.debug("Len: "+cbuf.length);
					osw.write(cbuf,0,len);
				}
			}
			else
				log.error("Impossibile trovare il File: "+f.getAbsolutePath());
		}
		catch (FileNotFoundException e)
		{
			log.error(e);
		}
		catch (IOException e)
		{
			log.error(e);
		}
		finally
		{
			try
			{
				if (osw != null)
				{
					osw.flush();
//					osw.close();
				}
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			}
			catch (IOException e)
			{
				log.error(e);
			}
		}
	}

}
