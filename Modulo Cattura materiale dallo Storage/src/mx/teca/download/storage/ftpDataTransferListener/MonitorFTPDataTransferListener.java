package mx.teca.download.storage.ftpDataTransferListener;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import mx.log4j.Logger;

public class MonitorFTPDataTransferListener implements FTPDataTransferListener {

	private Logger log = new Logger(MonitorFTPDataTransferListener.class, "mx.teca.download.ftpDataTransferListener");

	private String url = null;
	
	private long byteTrasf = 0l;

	public MonitorFTPDataTransferListener(String hostIp,
			int hostPorta, boolean absolutePath, String pathSource,
			String fileSource){
		url = hostIp;
		url += ":"+hostPorta;
		if (absolutePath)
			url += "/";
		url += pathSource;
		url += "/";
		url += fileSource;
	}
	@Override
	public void aborted() {
		log.error(url+" - Connessione Abbortita");
	}

	@Override
	public void completed() {
		log.info(url+" - Fine trasferimento");
	}

	@Override
	public void failed() {
		log.info(url+" - Operazione fallita");
	}

	@Override
	public void started() {
		log.info(url+" - Inizio trasferimento");
	}

	@Override
	public void transferred(int byteTrasf) {
		this.byteTrasf += byteTrasf;
		log.info(url+" - Trasferiti "+this.byteTrasf+" byte");
	}

}
