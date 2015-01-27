/**
 * 
 */
package mx.teca.extend;

import it.sbn.iccu.metaag1.Img;
import it.sbn.iccu.metaag1.Img.Altimg;
import it.sbn.iccu.metaag1.Metadigit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.database.ConnectionPool;
import mx.teca.archivi.arsbni.view.ViewListaIdrImg;
import mx.teca.interfacce.IExtendCheck;

import org.apache.log4j.Logger;

/**
 * Questa classe viene utilizzata per implementare delle personalizzazioni per la BNCF
 * 
 * @author Massimiliano Randazzo
 *
 */
public class CheckBncf implements IExtendCheck
{

	/**
	 * Questa variabile viene utilizzata per loggare l'applicazione
	 */
	private static Logger log = Logger.getLogger(CheckBncf.class);
	
	/**
	 * Costruttore
	 */
	public CheckBncf()
	{
	}

	/**
	 * Questo metodo viene utilizzato per verificare l'integrit� del file Mag
	 * 
	 * @param mag Questa variabile viene utilizzara per gestire il mag da verificare
	 * 
	 * @see mx.teca.interfacce.IExtendCheck#checkMag(mx.magEdit.mag.Mag)
	 */
	public void checkMag(Metadigit mag)
	{
		Img img = null;
		Altimg altImg = null;
		if (mag != null)
		{
			if (mag.getImg()!= null && mag.getImg().size()>0)
			{
				for (int x=0; x<mag.getImg().size(); x++)
				{
					img = (Img)mag.getImg().get(x);
					if (img.getUsage()!=null && img.getUsage().size()>0)
					{
						// Il campo Usage � valorizzato controllo se il file ha estensione imgf e in questo caso lo valorizzo a 5
						if (img.getFile().getHref().toLowerCase().endsWith(".imgf"))
							((Img)mag.getImg().get(x)).getUsage().set(0,"5");
					}
					else
					{
						// Il campo Usage non � valorizzato cerco di ricavare dati sufficenti per la valorizzazione
						if (img.getFile().getHref().toLowerCase().endsWith(".imgf"))
							((Img)mag.getImg().get(x)).getUsage().add("5");
						else if (img.getFile().getHref().toLowerCase().endsWith(".tif"))
							((Img)mag.getImg().get(x)).getUsage().add("1");
						else if (img.getFile().getHref().toLowerCase().endsWith(".jpg") && 
								((img.getPpi() != null && (img.getPpi().intValue()>0 && img.getPpi().intValue()>150)) ||
								 (img.getDpi() != null && (img.getDpi().intValue()>0 && img.getDpi().intValue()>150))) )
							((Img)mag.getImg().get(x)).getUsage().add("2");
						else
							((Img)mag.getImg().get(x)).getUsage().add("3");
					}
					if (((Img)mag.getImg().get(x)).getAltimg() != null && ((Img)mag.getImg().get(x)).getAltimg().size()>0)
					{
						for (int y=0; y<((Img)mag.getImg().get(x)).getAltimg().size();y++)
						{
							altImg = ((Altimg)((Img)mag.getImg().get(x)).getAltimg().get(y));
							if (altImg.getUsage()!=null && altImg.getUsage().size()>0)
							{
								// Il campo Usage � valorizzato controllo se il file ha estensione imgf e in questo caso lo valorizzo a 5
								if (altImg.getFile().getHref().toLowerCase().endsWith(".imgf"))
									((Altimg)((Img)mag.getImg().get(x)).getAltimg().get(y)).getUsage().set(0,"5");
							}
							else
							{
								// Il campo Usage non � valorizzato cerco di ricavare dati sufficenti per la valorizzazione
								if (altImg.getFile().getHref().toLowerCase().endsWith(".imgf"))
									((Altimg)((Img)mag.getImg().get(x)).getAltimg().get(y)).getUsage().add("5");
								else if (altImg.getFile().getHref().toLowerCase().endsWith(".tif"))
									((Altimg)((Img)mag.getImg().get(x)).getAltimg().get(y)).getUsage().add("1");
								else if (altImg.getFile().getHref().toLowerCase().endsWith(".jpg") && 
										((altImg.getPpi() != null && (altImg.getPpi().intValue()>0 && altImg.getPpi().intValue()>150)) ||
										 (altImg.getDpi() != null && (altImg.getDpi().intValue()>0 && altImg.getDpi().intValue()>150))) )
									((Altimg)((Img)mag.getImg().get(x)).getAltimg().get(y)).getUsage().add("2");
								else
									((Altimg)((Img)mag.getImg().get(x)).getAltimg().get(y)).getUsage().add("3");
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Questo metodo viene utilizzato per estendere laverifica sui codici Usage
	 * 
	 * @param usage Questa variabile viene utilizzata per indicare il livello di usabilit� del materiale
	 * @return indica il risultato della verifica
	 * @see mx.teca.interfacce.IExtendCheck#checkUsage(java.lang.String)
	 */
	public boolean checkUsage(String usage)
	{
		boolean ris = false;
		
		// Pubblico il materiale realtivo alle immagini imgf
		if (usage.equals("5"))
			ris = true;

		return ris;
	}

	/**
	 * Questo metodo viene utilizzato per testare il tipo di visualizzatore alternativo
	 * 
	 * @param response Questa variabile viene utilizzata per gestire le risposte verso il client
	 * @param request Questa variabile viene utilizzata per gestire le richieste del client
	 * @param pool Questa variabile viene utilizzata per il pool delle connessioni con il database
	 * @return indica il risultato della verifica
	 * 
	 * @see mx.teca.interfacce.IExtendCheck#checkViewer(javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpServletRequest, mx.database.ConnectionPool)
	 */
	public boolean checkViewer(HttpServletResponse response,
			HttpServletRequest request, ConnectionPool pool)
	{
//		Properties prop = null;
//		File f = null;
//		FileInputStream fis = null;
		ViewListaIdrImg viewImages = null;
		ResultSet rs = null;
		boolean ris = true;
		
		try
		{
			if (request.getParameter("RisIdrFiglio")!= null ||
					request.getParameter("RisIdr")!= null)
			{
				viewImages = new ViewListaIdrImg(pool);
				if (request.getParameter("RisIdrFiglio")!= null)
					viewImages.setCampoValue("RelRisIdrArrivo", request.getParameter("RisIdrFiglio"));
				else
					viewImages.setCampoValue("RelRisIdrArrivo", request.getParameter("RisIdr"));

				viewImages.setCampoValue("imgUsage", "5");
				rs = viewImages.startSelect();
				if (rs.next())
				{
					ris = false;
	/*
					f = new File(System.getProperty("catalina.base") + File.separator
							+ "Teca" + File.separator
							+ request.getContextPath().replace("/", "") + ".properties");
					if (f.exists())
					{
						fis = new FileInputStream(f);
						prop = new Properties();
						prop.load(fis);
						response.sendRedirect(prop.getProperty("xlImage.url")+rs.getString("imgPathName"));
						ris = false;
					}
	*/
				}
			}
		}
		/*
		catch (FileNotFoundException e)
		{
			log.error(e);
		}
		*/
		catch (SQLException e)
		{
			log.error(e);
		}
		/*
		catch (IOException e)
		{
			log.error(e);
		}
		*/
		finally
		{
			try
			{
//				if (fis != null)
//					fis.close();
				if (rs != null)
					rs.close();
				if (viewImages != null)
					viewImages.stopSelect();
			}
//			catch (IOException e)
//			{
//				log.error(e);
//			}
			catch (SQLException e)
			{
				log.error(e);
			}
		}
		return ris;
	}

	/**
	 * Questo metodo viene utilizzato per testare la fruibilit� del materiale
	 * 
	 * @param request Questa variabile viene utilizzata per gestire le richieste del client
	 * @param risIdr Identificativo della risorsa Digitale
	 * @param pool Questa variabile viene utilizzata per gestire il pool di connessioni con il database
	 * @return Risultato della verifica
	 * 
	 * @see mx.teca.interfacce.IExtendCheck#checkFruib(javax.servlet.http.HttpServletRequest)
	 */
	public boolean checkFruib(HttpServletRequest request, String risIdr, ConnectionPool pool, ServletContext application)
	{
		boolean ris =true;
		ViewListaIdrImg view = null;
		File f = null;
		FileInputStream fis = null;
		Properties prop = null;
		ResultSet rs = null;
		String fileName = "";

		try
		{
		  if (application.getInitParameter("nomeCatalogo")!=null && application.getInitParameter("nomeCatalogo").startsWith("file://"))
		    fileName = application.getInitParameter("nomeCatalogo").replace("file:///","");
		  else
		  {
	    	if (System.getProperty("catalina.base") != null)
	    		fileName = System.getProperty("catalina.base")+File.separator;
	    	else
	    		fileName = System.getProperty("jboss.server.home.dir")+File.separator;
		    if (application.getInitParameter("nomeCatalogo")==null)
		      fileName += "Teca";
		    else
		      fileName += application.getInitParameter("nomeCatalogo");
		  }
		  fileName += File.separator+request.getContextPath().replace("/","")+".properties";
/*
		  fileName = System.getProperty("catalina.base")+ File.separator;
	  	if (application.getInitParameter("nomeCatalogo")==null)
	  		fileName += "Teca";
	  	else
	  		fileName += application.getInitParameter("nomeCatalogo");
	  	fileName += File.separator + request.getContextPath().replace("/", "") + ".properties";
	  	*/
			f = new File(fileName);
			if (f.exists())
			{
				fis = new FileInputStream(f);
				prop = new Properties();
				prop.load(fis);

				view = new ViewListaIdrImg(pool);
				view.setCampoValue("RelRisIdrArrivo", risIdr);
				view.setCampoValue("fruiBid", prop.getProperty("checkFruib.fruib", "6"));
				view.setCampoValue("ImgUsage", "1");
				view.getCampo("ImgUsage").setTipoRicerca("<>");
				rs = view.startSelect();
				if (rs.next())
				{
					ris = checkIP(request.getRemoteAddr(), prop.getProperty("checkFruib.ip", "*.*.*.*"));
				}
			}
		}
		catch (FileNotFoundException e)
		{
			log.error(e);
		}
		catch (IOException e)
		{
			log.error(e);
		}
		catch (SQLException e)
		{
			log.error(e);
		}
		finally
		{
			try
			{
				if (fis != null)
					fis.close();
				if (rs != null)
					rs.close();
				if (view != null)
					view.stopSelect();
			}
			catch (IOException e)
			{
				log.error(e);
			}
			catch (SQLException e)
			{
				log.error(e);
			}
		}
		return ris;
	}

	/**
	 * Questo metodo viene utilizzato per testate l'indirizzo IP del client
	 * 
	 * @param ipSource Indirizzo IP del Client
	 * @param rangeIP Range di indirizzi abilitati
	 * @return Risultato verifica
	 */
	private boolean checkIP(String ipSource, String rangeIP)
	{
		boolean ris = false;
		String[] ipClient = null;
		String[] lista = null;
		String[] ipCheck = null;
		
		ipClient = ipSource.split("\\.");
		lista = rangeIP.split(",");
		for (int x = 0; x < lista.length; x++)
		{
			ipCheck = lista[x].split("\\.");
			if ((ipCheck[0].equals(ipClient[0]) || ipCheck[0].equals("*"))
					&& (ipCheck[1].equals(ipClient[1]) || ipCheck[1].equals("*"))
					&& (ipCheck[2].equals(ipClient[2]) || ipCheck[2].equals("*"))
					&& (ipCheck[3].equals(ipClient[3]) || ipCheck[3].equals("*")))
				ris = true;
		}
		return ris;
	}
}
