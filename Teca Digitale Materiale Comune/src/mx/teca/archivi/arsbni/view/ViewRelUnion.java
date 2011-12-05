/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import mx.database.ConnectionPool;
import mx.database.table.Query;

/**
 * @author MRandazzo
 *
 */
public class ViewRelUnion extends Query 
{

	public ViewRelUnion(ConnectionPool conn)
	{
		super(conn);
	}

	protected void defFrom()
	{
		this.setFrom("VIEWRELUNION");
		
	}

	protected void defWhere()
	{
		
	}

	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("id", "VIEWRELUNION", "ID");
		this.addCampi("des", "VIEWRELUNION", "DES");
		this.addCampi("direz", "VIEWRELUNION", "DIREZ");
		this.addCampi("nat", "VIEWRELUNION", "NAT");
		this.addCampi("relRisIdr", "VIEWRELUNION", "RELRISIDR");
	}

}
