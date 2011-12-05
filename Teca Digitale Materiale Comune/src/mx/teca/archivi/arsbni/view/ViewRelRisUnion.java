/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import java.sql.ResultSet;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Query;

/**
 * Questa classe viene utilizzata per gestire la vista ViewRelRisunion
 * 
 * @author Massimiliano Randazzo
 * 
 */
public class ViewRelRisUnion extends Query
{

	/**
   * Costruttore
   * 
   * @param conn
   */
	public ViewRelRisUnion(ConnectionPool conn)
	{
		super(conn);
	}

	/**
   * Questo metodo viene utilizzato per indicare la condizione di From della
   * select
   * 
   * @see mx.database.table.Query#defFrom()
   */
	protected void defFrom()
	{
		this.setFrom("VIEWRELRISUNION");
	}

	/**
   * Questo metodo viene utilizzato per idnicare la condizione di where della
   * select
   * 
   * @see mx.database.table.Query#defWhere()
   */
	protected void defWhere()
	{
	}

	/**
   * @see mx.database.table.DataSet#defCampi()
   */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idr", "VIEWRELRISUNION", "IDR");
		this.addCampi("nota", "VIEWRELRISUNION", "NOTA");
		this.addCampi("mime", "VIEWRELRISUNION", "MIME");
		this.addCampi("seq", "VIEWRELRISUNION", "SEQ");
		this.addCampi("relRisIdr", "VIEWRELRISUNION", "RELRISIDR");
		this.addCampi("tipoRelId", "VIEWRELRISUNION", "TIPORELID");
	}

	/**
   * Questo metodo viene utilizzato per eseguire la ricerca
   * 
   * @see mx.database.table.DataSet#startSelect()
   */
	public ResultSet startSelect()
	{
		this.getCampo("seq").setOrderBy(1, Column.ORDERBY_CRES);
		return super.startSelect();
	}

}
