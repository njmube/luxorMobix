package com.luxsoft.cfdi;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.util.Assert;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class QRCodeTest {
	
	
	public static void main(String[] args) throws Exception{
		String qq="?re=ISA870406UF4&rr=PMA971112U22&tt=174000&id,AF68185F-47CA-4EF8-8711-2BADDC840081";
		Image img=ImageIO.read(QRCode.from(qq).to(ImageType.GIF).withSize(250, 250).file());
		System.out.println("Image: "+img);
		
		File file=new File("web-app/reports/prueba.jrxml");
		Assert.isTrue(file.exists());
		
		JasperReport report=JasperCompileManager.compileReport("web-app/reports/CFDI.jrxml");
		Map params=new HashMap();
		params.put("QR_CODE", img);
		JasperPrint print=JasperFillManager.fillReport(report, params,new JREmptyDataSource());
		byte[] pdf=JasperExportManager.exportReportToPdf(print);
		
		JasperViewer.viewReport(print, true);
		
		
	}

}
