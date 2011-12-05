/**
 * 
 */
package mx.teca.download.storage.exception;

/**
 * Questa classe viene utilizzata per gestire gli errori della procedura di Download
 * 
 * @author Massimiliano Randazzo
 * @version $Id: DownloadException.java,v 1.1 2011-02-15 20:27:34 massi Exp $
 */
public class DownloadException extends Exception
{

	/**
	 * Questa variabile viene utilizzato per indicare il Serial Versio UID
	 */
	private static final long serialVersionUID = -5890574566584309489L;

	/**
	 * Questa variabile viene utilizzato per indicare il colore del titolo
	 */
	private String titleColor = "";

	/**
	 * Questa variabile viene utilizzato per indicare il testo del titolo
	 */
	private String titleMessage = "";

	/**
	 * Questa variabile viene utilizzata per indicare il colore del messaggio
	 */
	private String testoColor = "";

	/**
	 * Questa variabile viene utilizzata per indicare il testo del messaggio
	 */
	private String testoMessage = "";

	/**
	 * 
	 */
	public DownloadException(String titleColor, String titleMessage, String testoColor, String testoMessage)
	{
		this.titleColor = titleColor;
		this.titleMessage = titleMessage;
		this.testoColor = testoColor;
		this.testoMessage = testoMessage;
	}

	/**
	 * @return the titleColor
	 */
	public String getTitleColor()
	{
		return titleColor;
	}

	/**
	 * @return the titleMessage
	 */
	public String getTitleMessage()
	{
		return titleMessage;
	}

	/**
	 * @return the testoColor
	 */
	public String getTestoColor()
	{
		return testoColor;
	}

	/**
	 * @return the testoMessage
	 */
	public String getTestoMessage()
	{
		return testoMessage;
	}

}
