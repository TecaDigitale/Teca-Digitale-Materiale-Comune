/**
 * 
 */
package mx.teca.archivi.arsbni.view;

import java.sql.ResultSet;

import mx.database.ConnectionPool;
import mx.database.table.Column;
import mx.database.table.Query;

/**
 * @author Randazzo
 *
 */
public class ViewTbllegnotTblris extends Query
{

  /**
   * @param conn
   */
  public ViewTbllegnotTblris(ConnectionPool conn)
  {
    super(conn);
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.Query#defFrom()
   */
  protected void defFrom()
  {
    this.setFrom("TBLLEGNOT, TBLRIS, TBLLEGNOTRIS");
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.Query#defWhere()
   */
  protected void defWhere()
  {
  	this.setWhere("TBLLEGNOT.ID_TBLLEGNOT=TBLLEGNOTRIS.ID_TBLLEGNOT");
  	this.addWhere("AND TBLLEGNOTRIS.RISIDR=TBLRIS.RISIDR");
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.DataSet#defCampi()
   */
  protected void defCampi()
  {
    this.setColKey(true);
    this.addCampi("legnotBid", "TBLLEGNOT", "LEGNOTBID");
    this.getCampo("legnotBid").setOrderBy(1, Column.ORDERBY_CRES);
    this.addCampi("autore", "TBLLEGNOT", "TMPAUTORE");
    this.addCampi("titolo", "TBLLEGNOT", "TMPTITOLO");
    this.addCampi("notePubblicazione", "TBLLEGNOT", "TMPNOTE_PUBBLICAZIONE");
    this.addCampi("legNotSegna", "TBLLEGNOT", "LEGNOTSEGNA");
    this.addCampi("legNotInv", "TBLLEGNOT", "LEGNOTINV");
    this.addCampi("idTblLegNot", "TBLLEGNOT", "ID_TBLLEGNOT");
    this.addCampi("risidr", "TBLRIS", "RISIDR");
    this.addCampi("risnotapub", "TBLRIS", "RISNOTAPUB");
    this.addCampi("pieceGr", "TBLLEGNOTRIS", "PIECEGR");
    this.addCampi("pieceDt", "TBLLEGNOTRIS", "PIECEDT");
    this.addCampi("pieceIn", "TBLLEGNOTRIS", "PIECEIN");
    this.addCampi("notDataMod", "TBLLEGNOTRIS", "NOTDATAMOD");
    this.addCampi("idTblOaiSet", "TBLLEGNOTRIS", "ID_TBLOAISET");
    this.addCampi("hostId", "TBLLEGNOTRIS", "HOSTID");
    this.addCampi("fileMag", "TBLLEGNOTRIS", "FILEMAG");
    this.addCampi("imgformato", "TBLIMG", "IMGFORMATO", false, false, false);
    this.addCampi("fruibid", "TBLIMG", "FRUIBID", false, false,false);
    this.addCampi("typedigitid", "TBLLEGNOT", "TYPEDIGITID", false, false, true);
  }

  /**
   * (non-Javadoc)
   * @see mx.database.table.DataSet#startSelect()
   */
  public ResultSet startSelect()
  {
    if (this.getCampoValue("imgformato")!=null && (!this.getCampoValue("imgformato").equals("")))
    {
    	this.addFrom(", TBLRISIMG, TBLIMG");
      this.addWhere("AND TBLRIS.RISIDR=TBLRISIMG.RISIDR");
      this.addWhere("AND TBLRISIMG.ID_TBLIMG=TBLIMG.ID_TBLIMG");
      this.getCampo("imgformato").setView(true);
      this.getCampo("imgformato").setWhere(true);
      this.getCampo("fruibid").setView(true);
      this.getCampo("fruibid").setWhere(true);
    }
    
    return super.startSelect();
  }

}
