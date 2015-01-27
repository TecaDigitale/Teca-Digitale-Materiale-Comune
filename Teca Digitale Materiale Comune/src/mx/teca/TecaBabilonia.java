/**
 * 
 */
package mx.teca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Questa classe viene utilizzata per la gestione del multi linguaggio del programma
 * 
 * @author Massimiliano Randazzo
 *
 */
public class TecaBabilonia
{

	/**
	 * Questa variabile viene utilizzata la path dove trovare i file richiesti
	 */
	public static String pathBabilonia="";

	/**
	 * Questa variavile viene utilizzata per gestire la lista dei file caricati
	 */
	private static Hashtable<String, Babilonia> babilonia = null;

	/**
	 * Questo metodo viene tilizzato per reprire le informazioni relativo al testo intressata per la lingua italiana
	 * 
	 * @param key Chiave di ricrca
	 * @return Valore individuato
	 */
	public static String getTesto(String key)
	{
		return TecaBabilonia.getTesto("it", key);
	}

	/**
	 * Questo metodo viene tilizzato per reprire le informazioni relativo al testo/lingua intressata
	 * 
	 * @param language lingua di riferimento
	 * @param key Chiave di ricrca
	 * @return Valore individuato
	 */
	public static String getTesto(String language, String key)
	{
		Babilonia babilonia = null;

		if (TecaBabilonia.babilonia == null)
			TecaBabilonia.babilonia = new Hashtable<String, Babilonia>();
		
		if (TecaBabilonia.babilonia.get(language)!= null)
			babilonia = (Babilonia) TecaBabilonia.babilonia.get(language);
		else
		{
			babilonia = new Babilonia(language);
			TecaBabilonia.babilonia.put(language, babilonia);
		}
		return babilonia.getTesto(key);
	}
}

class Babilonia
{

	/**
	 * Questa variabile viene utilizzata per loggare l'applicazione
	 */
	private static Logger log = Logger.getLogger(Babilonia.class);

	/**
	 * Questa variabile viene utilizzata per indicare la lingua relativa al file preso in oggetto
	 */
	private String language="it";

	/**
	 * Questa variabile viene utilizzata per indicare la lista dei testi disponibili
	 */
	private Hashtable<String, Object> htTesto = null;

	public Babilonia(String language)
	{
		File f = null;
		FileInputStream fis = null;
		Properties prop = null;
		String key = "";
		
		try
		{
			this.language=language;
			
			f = new File(TecaBabilonia.pathBabilonia+File.separator+"babilonia_"+language+".language");
			if (f.exists())
			{
				log.debug("File Babilon: "+f.getAbsolutePath());
				prop = new Properties();
				fis = new FileInputStream(f);
				prop.load(fis);
				htTesto = new Hashtable<String, Object>();
				for (Enumeration<Object> e = prop.keys(); e.hasMoreElements(); )
				{
					key = (String)e.nextElement();
					log.debug("Key: "+key+" -> "+prop.get(key));
					htTesto.put(key, prop.get(key));
				}
			}
			else
				log.error("Non risulta presente il file "+f.getAbsolutePath());
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
				if (fis != null)
					fis.close();
			}
			catch (IOException e)
			{
				log.error(e);
			}
		}
	}

	/**
	 * Questo metodo viene utilizzato per reperire la lingua di riferimento
	 * 
	 * @return Lingua di riferimnto
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * Questo metodo viene utilizzato per reperire le informazioni relative al testo
	 * 
	 * @param key chiave da ricercare
	 * @return Valore trovato
	 */
	public String getTesto(String key)
	{
	  String ris = "";
	  if (htTesto != null)
	  	if (htTesto.get(key)!= null)
	  		ris = (String)htTesto.get(key);
	  return ris;
	}
}