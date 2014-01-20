package com.luxsoft.cfdi

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.MessageFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.apache.commons.lang.exception.ExceptionUtils;



class QRCodeUtils {
	
	public static  generarQR(Comprobante cfdi) {
		try {
			TimbreFiscal timbre=new TimbreFiscal(cfdi)
			String pattern="?re={0}&rr={1}&tt={2,number,##########.######}&id,{3}"
			String qq=MessageFormat.format(pattern, cfdi.getEmisor().getRfc(),cfdi.getReceptor().getRfc(),cfdi.getTotal(),timbre.UUID)
			println 'Generando QCode para: '+qq
			File file=QRCode.from(qq).to(ImageType.GIF).withSize(250, 250).file()
			//BufferedImage img=ImageIO.read(file);
			return file.toURI().toURL()
			 //return ImageIO.read(QRCode.from(qq).to(ImageType.GIF).withSize(250, 250).file())
			//ImageIcon img=new ImageIcon(file.readBytes())
			//return new ByteArrayInputStream(file.readBytes())
			//return img
			
		} catch (Exception e) {
			throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e),e);
		}
		
	}
	
	public static getQCode(Comprobante cfdi){
		TimbreFiscal timbre=new TimbreFiscal(cfdi);
		BigDecimal total=cfdi.getTotal();
		String pattern="?re={0}&rr={1}&tt={2,number,##########.######}&id,{3}";
		String qq=MessageFormat.format(pattern, cfdi.getEmisor().getRfc(),cfdi.getReceptor().getRfc(),cfdi.getTotal(),timbre.UUID);
		println 'Generando QCode para: '+qq
		return QRCode.from(qq).withSize(250,205).stream()
	}

}
