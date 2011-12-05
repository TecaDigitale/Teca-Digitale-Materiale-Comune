/**
 * 
 */
package mx.teca.download.storage.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.imageio.ImageIO;

import mx.log4j.Logger;

/**
 * Questa classe viene utilizzata per la Generazione delle immagini
 * @author MRandazzo
 *
 */
class GenerateImg
{

	/**
	 * Qusta variabile viene utilizzata per gestire il log dell'applicazione
	 */
	private Logger log = new  Logger(GenerateImg.class, "mx.teca.download.storage");

	/**
	 * Questa variabile viene utiluzzata per gestire il buffer dell'immagine
	 */
	private BufferedImage bi = null;

	/**
	 * Questa variabile viene utilizzata per indicare la posizione del cursore per la stampa del testo
	 */
	private int posTesto = 0;

	/**
	 * Costruttore
	 */
	public GenerateImg()
	{
		posTesto = 10;
	}

	/**
	 * Questo metodo viene utilizzato per la generazione della nuova immagine
	 * 
	 * @param width Larghezza
	 * @param height Altezza
	 * @param titleColor Colore del Titolo
	 * @param titleText Testo dele Titolo
	 * @param msgColor Colore del Testo
	 * @param msgText Testo
	 * @param outputStream Output dell'oggetto
	 */
	protected void createImage(int width, int height, Color titleColor, String titleText, Color msgColor, InputStream msgText, OutputStream outputStream)
	{
		BufferedReader br = null;
		InputStreamReader isr = null;
		String line = null;
		try
		{
			Graphics2D big = null;
			
			big = createImage(width, height);
			
			if (titleText != null && !titleText.trim().equals(""))
				writeTitle(big, titleColor, titleText);
			
			if (msgText != null )
			{
				isr = new InputStreamReader(msgText);
				br = new BufferedReader(isr);
				while((line=br.readLine())!=null)
					writeText(big, msgColor, line, new Font("TimesRoman",Font.PLAIN, 20));
			}
			
			ImageIO.write(bi, "JPEG", outputStream);
		}
		catch (IOException e)
		{
			log.error(e);
		}
	}

	/**
	 * Questo metodo viene utilizzato per la generazione della nuova immagine
	 * 
	 * @param width Larghezza
	 * @param height Altezza
	 * @param titleColor Colore del Titolo
	 * @param titleText Testo dele Titolo
	 * @param msgColor Colore del Testo
	 * @param msgText Testo
	 * @param outputStream Output dell'oggetto
	 */
	public void createImage(int width, int height, Color titleColor, String titleText, Color msgColor, String msgText, OutputStream outputStream)
	{
		try
		{
			Graphics2D big = null;
			
			big = createImage(width, height);
			
			if (titleText != null && !titleText.trim().equals(""))
				writeTitle(big, titleColor, titleText);
			
			if (msgText != null && !msgText.trim().equals(""))
				writeText(big, msgColor, msgText, null);
			
//			big.setTransform(AffineTransform.getScaleInstance((double)100/72, (double)100/72));
			ImageIO.write(bi, "JPEG", outputStream);
		}
		catch (IOException e)
		{
			log.error(e);
		}
	}

	/**
	 * Questo metodo viene utilizzato per disegnare la base dell'imamgine
	 * 
	 * @param width Larghezza
	 * @param height Altezza
	 * @return Immagine disegnata
	 */
	private Graphics2D createImage(int width, int height)
	{
		Graphics2D big = null;
		Rectangle2D singlePallet = null;

		bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		big = bi.createGraphics();
//		big.transform( AffineTransform.getScaleInstance((double)100/72, (double)100/72));
		big.setColor(Color.WHITE);
		big.fillRect(0, 0, bi.getWidth(), bi.getHeight());

		big.setColor(Color.BLACK);

		singlePallet = new Rectangle2D.Double(5,5, bi.getWidth()-10,bi.getHeight()-10);
		big.draw(singlePallet);

		return big;
	}

	/**
	 * Questo metodo viene utilizzato per disegnare il Titolo
	 * 
	 * @param big Immagine su cui applicare il titolo
	 * @param color Colore del Titolo
	 * @param line Testo del Titolo
	 */
	private void writeTitle(Graphics2D big, Color color, String line)
	{
		FontRenderContext fc = null; 
		Font font = null;

		font = new Font("TimesRoman",Font.BOLD, 60);

		fc =  big.getFontRenderContext();

		printLine(line, color, font, fc, big);
		posTesto+=10;
	}

	/**
	 * Questo metodo viene utilizzato per disegnare il testo della pagina
	 * 
	 * @param line Testo della pagina
	 * @param color Colore del testo 
	 * @param font Font del testo
	 * @param fc 
	 * @param big Imamgine su cui applicate il testo
	 */
	private void printLine(String line, Color color, Font font, FontRenderContext fc, Graphics2D big)
	{
		LineBreakMeasurer lbm = null;
		BufferedImage lineBuffer = null;
		TextLayout layout = null;
		Graphics2D g1 = null;
		int lineHeight = 0;

		if (line.equals(""))
			line = " ";
		lineHeight =  getLineHeigth(font, fc);
		
		lbm = new LineBreakMeasurer(getAci(line, color, font), fc);

		if (lbm!= null)
		{
		while (lbm.getPosition() < line.length()) 
		{
			lineBuffer = new BufferedImage((bi.getWidth()-10), lineHeight, 
					BufferedImage.TYPE_INT_RGB+BufferedImage.BITMASK);

			layout = lbm.nextLayout(bi.getWidth());

			g1 = lineBuffer.createGraphics();
			g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (layout != null)
				layout.draw(g1, 0, (int)layout.getAscent());
			else
				break;
			big.drawImage((BufferedImage)lineBuffer, 10,posTesto,null);
			posTesto += lineHeight;

		}
		}
	}

	/**
	 * Questo metodo viene utilizzato per calcolare l'occupazione del font che andremo a utilizzare
	 * 
	 * @param font Font del testo
	 * @param fc
	 * @return
	 */
	private int getLineHeigth(Font font, FontRenderContext fc)
	{
		Rectangle2D bounds = null;
		
		bounds = font.getStringBounds("test", fc);
		return (int)bounds.getHeight();
	}

	/**
	 * Questo metodo viene utilizzato per stampare il testo del messaggio
	 * 
	 * @param big Imamgine su cui aplicatte il testo
	 * @param color Colore del testo
	 * @param line Testo da stampare
	 */
	private void writeText(Graphics2D big, Color color, String line, Font font)
	{
		FontRenderContext fc = null; 
//		Font font = null;
		String[] st = null;

//		font = new Font("TimesRoman",Font.PLAIN, 30);
		if (font == null) 
			font = new Font("TimesRoman",Font.PLAIN, 30);

		fc =  big.getFontRenderContext();

		st = line.split("\r\n");
		for (int x=0; x<st.length; x++)
			printLine(st[x], color, font, fc, big);
	}

	private AttributedCharacterIterator getAci(String line, Color color, Font font)
	{
		AttributedCharacterIterator aci = null;
		AttributedString attribString = null;

			
		attribString = new AttributedString(line);

		attribString.addAttribute(TextAttribute.BACKGROUND , Color.WHITE);
		attribString.addAttribute(TextAttribute.FOREGROUND , color, 0, line.length());
		attribString.addAttribute(TextAttribute.FONT, font, 0, line.length());
		
		aci = attribString.getIterator();
		return aci;
	}

	
}


/*

	public void createImage()
	{
		MyRende myRende = null;
		Graphics2D g = null;
		Graphics2D g1 = null;
		String line = "Questa e' una prova di inserimento di un testo con l'interruzione automatica della riga.\nRiga 2.\nRiga 3 - s asd  m k am, ak ,ma k amd nka ddnqw m, kd m, k m k m nak dmnq mnd mndkl jkd ask djka da sdkas ja dn akl djka danb da j n bdas jak jkabmda kld jk dasmn bdklas da d nkjdldjkqw ";

		Font font = null;
		FontRenderContext fc; 
		
		try {
		font = new Font("TimesRoman",Font.PLAIN, (int)20);
		} catch (Exception e)
		{
		System.out.println("Error opening font file : "+e.getMessage());

		} 
		try
		{
			myRende = new MyRende();
			BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
			Graphics2D big = bi.createGraphics();
			big.setColor(Color.WHITE);
			big.fillRect(0, 0, bi.getWidth(), bi.getHeight());

			big.setColor(Color.BLACK);

			Rectangle2D singlePallet = new Rectangle2D.Double(2,2,196,196);
			big.setFont(new Font("TimesRoman",Font.PLAIN, 9));
			big.drawString("xxx", 5, 15);
			big.setFont(new Font("TimesRoman", Font.BOLD, 10));
			big.drawString("Single Pallet Lane", 5, 30);

			fc =  big.getFontRenderContext();
			
			AttributedString attribString = new AttributedString(line);

			attribString.addAttribute(TextAttribute.BACKGROUND , Color.WHITE);
			attribString.addAttribute(TextAttribute.FOREGROUND , Color.BLACK, 0, line.length());
			attribString.addAttribute(TextAttribute.FONT, font, 0, line.length());
			AttributedCharacterIterator aci = attribString.getIterator();
			LineBreakMeasurer lbm = new LineBreakMeasurer(aci, fc);

			int pos = 0;
			int width = 150;
			int lineHeight = 0;
			BufferedImage tempBuffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
			g = tempBuffer.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(font);
			fc = g.getFontRenderContext();
			Rectangle2D bounds = font.getStringBounds("test", fc);

			float wrappingWidth = width;
			lineHeight = (int)bounds.getHeight(); 
			int lineCnt=5;
			int lintCount =0;
			try
			{
			while (( pos = lbm.getPosition()) < line.length()) 
			{
				lintCount++;
				System.out.println("pos: "+pos);
				BufferedImage lineBuffer = new BufferedImage(width, lineHeight, BufferedImage.TYPE_INT_RGB+BufferedImage.BITMASK);
				((Graphics2D)lineBuffer.getGraphics()).setBackground(Color.YELLOW);
				((Graphics2D)lineBuffer.getGraphics()).setColor(Color.WHITE);
				g1 = lineBuffer.createGraphics();
				System.out.println("Transp: "+lineBuffer.getTransparency());
				g1.setBackground(Color.WHITE);
//				g1.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OVER, 1f));
				g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				TextLayout layout = lbm.nextLayout(wrappingWidth);
				int y = (int)layout.getAscent();
				System.out.println(y+" - "+width+" - "+layout.getCharacterCount());
				layout.draw(g1, 0, y);
//				layout.draw(g1, 0, y);
				big.drawImage((BufferedImage)lineBuffer,5,30+(lineHeight*lintCount),null);
				lineCnt += lineHeight;
			}
			}
			catch (NullPointerException e)
			{
			}
			ImageIO.write(bi, "JPEG", new File("myFile.jpg"));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  private BufferedImage createImageEmpty() {
    BufferedImage bim;

    Color[] colors = { Color.red, Color.blue, Color.yellow, };

    int width = 8, height = 8;
    bim = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = bim.createGraphics();
    for (int i = 0; i < width; i++) {
      g2.setPaint(colors[(i / 2) % colors.length]);
      g2.drawLine(0, i, i, 0);
      g2.drawLine(width - i, height, width, height - i);
    }
    return bim;
  }	
	public static void main(String[] args)
	{
		CreateImage createImg = null;
		
		createImg = new CreateImage();
		createImg.createImage();
	}
	class MyRende implements RenderedImage
	{

		public WritableRaster copyData(WritableRaster raster)
		{
			System.out.println("copyData("+raster+")");
			return null;
		}

		public ColorModel getColorModel()
		{
			System.out.println("getColorModel()");
			return null;
		}

		public Raster getData()
		{
			System.out.println("getData()");
			return null;
		}

		public Raster getData(Rectangle rect)
		{
			System.out.println("getData("+rect+")");
			return null;
		}

		public int getHeight()
		{
			System.out.println("getHeight()");
			return 0;
		}

		public int getMinTileX()
		{
			System.out.println("getMinTileX()");
			return 0;
		}

		public int getMinTileY()
		{
			System.out.println("getMinTileY()");
			return 0;
		}

		public int getMinX()
		{
			System.out.println("getMinX()");
			return 0;
		}

		public int getMinY()
		{
			System.out.println("getMinY()");
			return 0;
		}

		public int getNumXTiles()
		{
			System.out.println("getNumXTiles()");
			return 0;
		}

		public int getNumYTiles()
		{
			System.out.println("getNumYTiles()");
			return 0;
		}

		public Object getProperty(String name)
		{
			System.out.println("getProperty("+name+")");
			return null;
		}

		public String[] getPropertyNames()
		{
			System.out.println("getPropertyNames()");
			return null;
		}

		public SampleModel getSampleModel()
		{
			System.out.println("getSampleModel()");
			return null;
		}

		public Vector getSources()
		{
			System.out.println("getSources()");
			return null;
		}

		public Raster getTile(int tileX, int tileY)
		{
			System.out.println("getTile("+tileX+", "+tileY+")");
			return null;
		}

		public int getTileGridXOffset()
		{
			System.out.println("getTileGridXOffset()");
			return 0;
		}

		public int getTileGridYOffset()
		{
			System.out.println("getTileGridYOffset()");
			return 0;
		}

		public int getTileHeight()
		{
			System.out.println("getTileHeight()");
			return 0;
		}

		public int getTileWidth()
		{
			System.out.println("getTileWidth()");
			return 0;
		}

		public int getWidth()
		{
			System.out.println("getWidth()");
			return 0;
		}
		
	}
*/

