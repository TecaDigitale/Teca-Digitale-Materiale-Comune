/**
 * 
 */
package mx.teca.download.storage.image.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import mx.teca.download.storage.image.ImgError;

/**
 * @author MRandazzo
 *
 */
public class CreateImageTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		File f = null;
		FileOutputStream fos = null;

		try
		{
			f = new File("msgError.jpg");
			fos = new FileOutputStream(f);

			ImgError.imgErr("Messaggio di Errore", "Questo \u00e8 un messaggio di Errore di prova", fos);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (fos != null)
				{
					fos.flush();
					fos.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}
