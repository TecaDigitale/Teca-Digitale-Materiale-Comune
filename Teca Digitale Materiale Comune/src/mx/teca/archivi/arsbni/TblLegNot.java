/**
 * 
 */
package mx.teca.archivi.arsbni;

import java.sql.ResultSet;

import mx.database.ConnectionPool;
import mx.database.MsSqlPool;
import mx.database.table.Table;
import mx.utility.ConvertText;

/**
 * @author MRandazzo
 *
 */
public class TblLegNot extends Table
{

	private Object autore = null;
	
	private Object titolo = null;

	private Object notePub = null;
	
	private Object autoreKey = null;
	
	private Object titoloKey = null;

	private boolean imgPresenti = true;

	/**
	 * 
	 * @param msp
	 */
	public TblLegNot(MsSqlPool msp)
	{
		super(msp);
	}

	/**
	 * @param conn
	 */
	public TblLegNot(ConnectionPool conn)
	{
		super(conn);
	}

	/**
	 * @see mx.database.table.DataSet#defCampi()
	 */
	protected void defCampi()
	{
		this.setColKey(true);
		this.addCampi("idTblLegNot", "TBLLEGNOT", "ID_TBLLEGNOT", true);
		this.addCampi("legNotBid", "TBLLEGNOT", "LEGNOTBID");
		this.addCampi("legNotSegna", "TBLLEGNOT", "LEGNOTSEGNA");
		this.addCampi("legNotInv", "TBLLEGNOT", "LEGNOTINV");
		this.addCampi("typeDigitId", "TBLLEGNOT", "TYPEDIGITID");
		this.addCampi("tmpAutore", "TBLLEGNOT", "TMPAUTORE");
		this.addCampi("tmpTitolo", "TBLLEGNOT", "TMPTITOLO");
		this.addCampi("tmpNotePubblicazione", "TBLLEGNOT", "TMPNOTE_PUBBLICAZIONE");
		this.addCampi("tmpChiaveAutore", "TBLLEGNOT", "TMPCHIAVE_AUTORE");
		this.addCampi("tmpChiaveTitolo", "TBLLEGNOT", "TMPCHIAVE_TITOLO");
		this.addCampi("legNotBni", "TBLLEGNOT", "LEGNOTBNI");
		this.addCampi("legNotBidOld", "TBLLEGNOT", "LEGNOTBID",false,true, false);
	}

	/**
	 * Questo metodo viene utilizzato per verificare la validità del contenuto di alcuni campi
	 *
	 */
	protected void preUpdate()
	{
		if (this.getCampoValue("legNotSegna") == null && imgPresenti)
		{
			this.getCampo("legNotSegna").setTipoRicerca("is NULL");
			this.setCampoValue("legNotSegna", "");
		}
		if (this.getCampoValue("legNotInv") == null && imgPresenti)
		{
			this.getCampo("legNotInv").setTipoRicerca("is NULL");
			this.setCampoValue("legNotInv", "");
		}
		if (this.getCampoValue("tmpAutore") != null)
			this.setCampoValue("tmpChiaveAutore", ConvertText.conveVar(this.get("tmpAutore")));
		if (this.getCampoValue("tmpTitolo") != null)
			this.setCampoValue("tmpChiaveTitolo", ConvertText.conveVar(this.get("tmpTitolo")));

		autore = this.getCampoValue("tmpAutore");
		if (this.getCampoValue("tmpAutore") != null && this.get("tmpAutore").length()>80)
			this.setCampoValue("tmpAutore", this.get("tmpAutore").substring(0,80));

		notePub = this.getCampoValue("tmpNotePubblicazione");
		if (this.getCampoValue("tmpNotePubblicazione") != null && this.get("tmpNotePubblicazione").length()>80)
			this.setCampoValue("tmpNotePubblicazione", this.get("tmpNotePubblicazione").substring(0,80));
		
		autoreKey = this.getCampoValue("tmpChiaveAutore");
		if (this.getCampoValue("tmpChiaveAutore") != null && this.get("tmpChiaveAutore").length()>80)
			this.setCampoValue("tmpChiaveAutore", this.get("tmpChiaveAutore").substring(0,80));

		titolo = this.getCampoValue("tmpTitolo");
		if (this.getCampoValue("tmpTitolo") != null && this.get("tmpTitolo").length()>80)
			this.setCampoValue("tmpTitolo", this.get("tmpTitolo").substring(0,80));

		titoloKey = this.getCampoValue("tmpChiaveTitolo");
		if (this.getCampoValue("tmpChiaveTitolo") != null && this.get("tmpChiaveTitolo").length()>80)
			this.setCampoValue("tmpChiaveTitolo", this.get("tmpChiaveTitolo").substring(0,80));
	}
	
	protected void postUpdate()
	{
		this.getCampo("legNotSegna").setTipoRicerca("=");
		this.getCampo("legNotInv").setTipoRicerca("=");
		this.setCampoValue("tmpAutore", autore);
		this.setCampoValue("tmpNotePubblicazione", notePub);
		this.setCampoValue("tmpChiaveAutore", autoreKey);
		this.setCampoValue("tmpTitolo", titolo);
		this.setCampoValue("tmpChiaveTitolo", titoloKey);
	}
	
	public ResultSet startSelect()
	{
		return startSelect(true);
	}

	public ResultSet startSelect(boolean imgPresenti)
	{
		this.imgPresenti = imgPresenti;
		preUpdate();
		return super.startSelect();
	}
	
	public void stopSelect()
	{
		super.stopSelect();
		postUpdate();
	}
}
