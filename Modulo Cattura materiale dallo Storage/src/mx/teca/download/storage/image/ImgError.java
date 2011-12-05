/**
 * 
 */
package mx.teca.download.storage.image;

import java.awt.Color;
import java.io.OutputStream;

import mx.teca.download.storage.Download;


/**
 * Questa classe viene utilizzata la generazione di un file imamgine da un file di testo o Stringa
 * 
 * @author Massimiliano Randazzo
 *
 */
public class ImgError extends GenerateImg
{
	
	/**
	 * Costruttore
	 */
	public ImgError()
	{
		super();
	}

	public static void imgErr(String msgText, OutputStream outputStream)
	{
		imgErr(Color.BLACK, msgText, outputStream);
	}

	public static void imgErr(Color msgColor, String msgText, OutputStream outputStream)
	{
		imgErr(null, null, msgColor, msgText, outputStream);
	}

	public static void imgErr(String titleText, String msgText, OutputStream outputStream)
	{
		imgErr(titleText, Color.BLACK, msgText, outputStream);
	}

	public static void imgErr(String titleText, Color msgColor, String msgText, OutputStream outputStream)
	{
		imgErr(Color.RED, titleText, msgColor, msgText, outputStream);
	}

	public static void imgErr(String titleColor, String titleText, String msgColor, String msgText, OutputStream outputStream)
	{
		imgErr(getColor(titleColor), titleText, getColor(msgColor), msgText, outputStream);
	}

	private static Color getColor(String color)
	{
		Color cColor = Color.BLACK;
		
		if (color.equalsIgnoreCase("RED"))
			cColor = Color.RED;
		else if (color.equalsIgnoreCase("BLACK"))
			cColor = Color.BLACK;
		else if (color.equalsIgnoreCase("BLUE"))
			cColor = Color.BLUE;
		else if (color.equalsIgnoreCase("CYAN"))
			cColor = Color.CYAN;
		else if (color.equalsIgnoreCase("DARK_GRAY"))
			cColor = Color.DARK_GRAY;
		else if (color.equalsIgnoreCase("GRAY"))
			cColor = Color.GRAY;
		else if (color.equalsIgnoreCase("GREEN"))
			cColor = Color.GREEN;
		else if (color.equalsIgnoreCase("LIGHT_GRAY"))
			cColor = Color.LIGHT_GRAY;
		else if (color.equalsIgnoreCase("MAGENTA"))
			cColor = Color.MAGENTA;
		else if (color.equalsIgnoreCase("ORANGE"))
			cColor = Color.ORANGE;
		else if (color.equalsIgnoreCase("PINK"))
			cColor = Color.PINK;
		else if (color.equalsIgnoreCase("WHITE"))
			cColor = Color.WHITE;
		else if (color.equalsIgnoreCase("YELLOW"))
			cColor = Color.YELLOW;
		return cColor;
	}

	public static void imgErr(Color titleColor, String titleText, Color msgColor, String msgText, OutputStream outputStream)
	{
		imgErr(Download.width, 
				Download.height, 
				titleColor, 
				titleText, 
				msgColor, 
				msgText, 
				outputStream);
	}

	public static void imgErr(int width, int height, Color titleColor, String titleText, Color msgColor, String msgText, OutputStream outputStream)
	{
		ImgError createImg = null;
		createImg =  new ImgError();
		createImg.createImage(width, height, titleColor, titleText, msgColor, msgText, outputStream);
	}
}
