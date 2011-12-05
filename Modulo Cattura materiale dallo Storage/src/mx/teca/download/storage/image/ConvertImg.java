/**
 * 
 */
package mx.teca.download.storage.image;

import java.awt.Color;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Questa classe viene utilizzata la generazione di un file imamgine da un file di testo o Stringa
 * 
 * @author Massimiliano Randazzo
 *
 */
public class ConvertImg extends GenerateImg
{
	
	/**
	 * Costruttore
	 */
	public ConvertImg()
	{
		super();
	}

	/**
	 * Questo metodo viene utilizzato per la conversione di un testo in imamgine
	 * 
	 * @param msgText Testo da convertire 
	 * @param outputStream Output dell'imamgine
	 */
	public static void convertToImg(String msgText, OutputStream outputStream)
	{
		convertToImg(Color.BLACK, msgText, outputStream);
	}

	/**
	 * Questo metodo viene utilizzato per la conversione di un testo in imamgine
	 * 
	 * @param msgColor Colore del Testo
	 * @param msgText Testo da convertire 
	 * @param outputStream Output dell'imamgine
	 */
	public static void convertToImg(Color msgColor, String msgText, OutputStream outputStream)
	{
		convertToImg(794, 2300, msgColor, msgText, outputStream);
	}

	/**
	 * Questo metodo viene utilizzato per la conversione di un testo in imamgine
	 * 
	 * @param width Larghezza dell'imamgine
	 * @param height Altezza dell'immagine
	 * @param msgColor Colore del Testo
	 * @param msgText Testo da convertire 
	 * @param outputStream Output dell'imamgine
	 */
	public static void convertToImg(int width, int height, Color msgColor, String msgText, OutputStream outputStream)
	{
		ConvertImg createImg = null;
		createImg =  new ConvertImg();
		createImg.createImage(width, height, null, null, msgColor, msgText, outputStream);
	}

	/**
	 * Questo metodo viene utilizzato per la conversione di un testo in imamgine
	 * 
	 * @param msgText Testo da convertire 
	 * @param outputStream Output dell'imamgine
	 */
	public static void convertToImg(InputStream msgText, OutputStream outputStream)
	{
		convertToImg(Color.BLACK, msgText, outputStream);
	}

	/**
	 * Questo metodo viene utilizzato per la conversione di un testo in imamgine
	 * 
	 * @param msgColor Colore del Testo
	 * @param msgText Testo da convertire 
	 * @param outputStream Output dell'imamgine
	 */
	public static void convertToImg(Color msgColor, InputStream msgText, OutputStream outputStream)
	{
		convertToImg(794, 2300, msgColor, msgText, outputStream);
	}

	/**
	 * Questo metodo viene utilizzato per la conversione di un testo in imamgine
	 * 
	 * @param width Larghezza dell'imamgine
	 * @param height Altezza dell'immagine
	 * @param msgColor Colore del Testo
	 * @param msgText Testo da convertire 
	 * @param outputStream Output dell'imamgine
	 */
	public static void convertToImg(int width, int height, Color msgColor, InputStream msgText, OutputStream outputStream)
	{
		ConvertImg createImg = null;
		createImg =  new ConvertImg();
		createImg.createImage(width, height, null, null, msgColor, msgText, outputStream);
	}
}
