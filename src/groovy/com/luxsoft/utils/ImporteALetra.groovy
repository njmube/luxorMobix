package com.luxsoft.utils

import java.math.BigDecimal;
import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;




class ImporteALetra {
	

	
	private int flag;
	private int numero;
	private String importe_parcial;
	private String num;
	private String num_letra;
	private String num_letras;
	private String num_letram;
	private String num_letradm;
	private String num_letracm;
	private String num_letramm;
	private String num_letradmm;
	
	private ImporteALetra(){
		numero = 0;
		flag=0;
	}
	private ImporteALetra(int n){
		numero = n;
		flag=0;
	}

	
	private String unidad(def numero){
		int amount=new Integer((int) numero);
		switch (amount){
		case 9:
				num = "NUEVE";
				break;
		case 8:
				num = "OCHO";
				break;
		case 7:
				num = "SIETE";
				break;
		case 6:
				num = "SEIS";
				break;
		case 5:
				num = "CINCO";
				break;
		case 4:
				num = "CUATRO";
				break;
		case 3:
				num = "TRES";
				break;
		case 2:
				num = "DOS";
				break;
		case 1:
				if (flag == 0)
					num = "UNO";
				else
					num = "UN";
				break;
		case 0:
				num = "";
				break;
		}
		return num;
	}
	
	private String decena(def numero){
	
		if (numero >= 90 && numero < 100)
		{
			num_letra = "NOVENTA ";
			if (numero > 90)
				num_letra = num_letra.concat("Y ").concat(unidad(numero - 90));
		}
		else if (numero >= 80 && numero < 90)
		{
			num_letra = "OCHENTA ";
			if (numero > 80)
				num_letra = num_letra.concat("Y ").concat(unidad(numero - 80));
		}
		else if (numero >= 70 && numero < 80)
		{
			num_letra = "SETENTA ";
			if (numero > 70)
				num_letra = num_letra.concat("Y ").concat(unidad(numero - 70));
		}
		else if (numero >= 60 && numero < 70)
		{
			num_letra = "SESENTA ";
			if (numero > 60)
				num_letra = num_letra.concat("Y ").concat(unidad(numero - 60));
		}
		else if (numero >= 50 && numero < 60)
		{
			num_letra = "CINCUENTA ";
			if (numero > 50)
				num_letra = num_letra.concat("Y ").concat(unidad(numero - 50));
		}
		else if (numero >= 40 && numero < 50)
		{
			num_letra = "CUARENTA ";
			if (numero > 40)
				num_letra = num_letra.concat("Y ").concat(unidad(numero - 40));
		}
		else if (numero >= 30 && numero < 40)
		{
			num_letra = "TREINTA ";
			if (numero > 30)
				num_letra = num_letra.concat("Y ").concat(unidad(numero - 30));
		}
		else if (numero >= 20 && numero < 30)
		{
			if (numero == 20)
				num_letra = "VEINTE ";
			else
				num_letra = "VEINTI".concat(unidad(numero - 20));
		}
		else if (numero >= 10 && numero < 20)
		{
			int amount=new Integer((int) numero);
			switch (amount){
			case 10:

				num_letra = "DIEZ ";
				break;

			case 11:

				num_letra = "ONCE ";
				break;

			case 12:

				num_letra = "DOCE ";
				break;

			case 13:

				num_letra = "TRECE ";
				break;

			case 14:

				num_letra = "CATORCE ";
				break;

			case 15:
			
				num_letra = "QUINCE ";
				break;
			
			case 16:
			
				num_letra = "DIECISEIS ";
				break;
			
			case 17:
			
				num_letra = "DIECISIETE ";
				break;
			
			case 18:
			
				num_letra = "DIECIOCHO ";
				break;
			
			case 19:
			
				num_letra = "DIECINUEVE ";
				break;
			
			}
		}
		else
			num_letra = unidad(numero);

	return num_letra;
	}

	private String centena(def numero){
		if (numero >= 100)
		{
			if (numero >= 900 && numero < 1000)
			{
				num_letra = "NOVECIENTOS ";
				if (numero > 900)
					num_letra = num_letra.concat(decena(numero - 900));
			}
			else if (numero >= 800 && numero < 900)
			{
				num_letra = "OCHOCIENTOS ";
				if (numero > 800)
					num_letra = num_letra.concat(decena(numero - 800));
			}
			else if (numero >= 700 && numero < 800)
			{
				num_letra = "SETECIENTOS ";
				if (numero > 700)
					num_letra = num_letra.concat(decena(numero - 700));
			}
			else if (numero >= 600 && numero < 700)
			{
				num_letra = "SEISCIENTOS ";
				if (numero > 600)
					num_letra = num_letra.concat(decena(numero - 600));
			}
			else if (numero >= 500 && numero < 600)
			{
				num_letra = "QUINIENTOS ";
				if (numero > 500)
					num_letra = num_letra.concat(decena(numero - 500));
			}
			else if (numero >= 400 && numero < 500)
			{
				num_letra = "CUATROCIENTOS ";
				if (numero > 400)
					num_letra = num_letra.concat(decena(numero - 400));
			}
			else if (numero >= 300 && numero < 400)
			{
				num_letra = "TRESCIENTOS ";
				if (numero > 300)
					num_letra = num_letra.concat(decena(numero - 300));
			}
			else if (numero >= 200 && numero < 300)
			{
				num_letra = "DOSCIENTOS ";
				if (numero > 200)
					num_letra = num_letra.concat(decena(numero - 200));
			}
			else if (numero >= 100 && numero < 200)
			{
				if (numero == 100)
					num_letra = "CIEN ";
				else
					num_letra = "CIENTO ".concat(decena(numero - 100));
			}
		}
		else
			num_letra = decena(numero);
		
		return num_letra;
	}

	private String miles(int numero){
		if (numero >= 1000 && numero <2000){
			num_letram = ("MIL ").concat(centena(numero%1000));
		}
		if (numero >= 2000 && numero <10000){
			flag=1;
			num_letram = unidad(numero/1000).concat(" MIL ").concat(centena(numero%1000));
		}
		if (numero < 1000)
			num_letram = centena(numero);
		
		return num_letram;
	}

	private String decmiles(int numero){
		if (numero == 10000)
			num_letradm = "DIEZ MIL ";
		if (numero > 10000 && numero <20000){
			flag=1;
			num_letradm = decena(numero/1000).concat("MIL ").concat(centena(numero%1000));
		}
		if (numero >= 20000 && numero <100000){
			flag=1;
			num_letradm = decena(numero/1000).concat(" MIL ").concat(miles(numero%1000));
		}
		
		
		if (numero < 10000)
			num_letradm = miles(numero);
		
		return num_letradm;
	}

	private String cienmiles(int numero){
		if (numero == 100000)
			num_letracm = "CIEN MIL";
		if (numero >= 100000 && numero <1000000){
			flag=1;
			num_letracm = centena(numero/1000).concat(" MIL ").concat(centena(numero%1000));
		}
		if (numero < 100000)
			num_letracm = decmiles(numero);
		return num_letracm;
	}

	private String millon(int numero){
		if (numero >= 1000000 && numero <2000000){
			flag=1;
			num_letramm = ("UN MILLON ").concat(cienmiles(numero%1000000));
		}
		if (numero >= 2000000 && numero <10000000){
			flag=1;
			num_letramm = unidad(numero/1000000).concat(" MILLONES ").concat(cienmiles(numero%1000000));
		}
		if (numero < 1000000)
			num_letramm = cienmiles(numero);
		
		return num_letramm;
	}
	
	private String decmillon(int numero){
		if (numero == 10000000)
			num_letradmm = "DIEZ MILLONES";
		if (numero > 10000000 && numero <20000000){
			flag=1;
			num_letradmm = decena(numero/1000000).concat("MILLONES ").concat(cienmiles(numero%1000000));
		}
		if (numero >= 20000000 && numero <100000000){
			flag=1;
			num_letradmm = decena(numero/1000000).concat(" MILLONES ").concat(millon(numero%1000000));
		}
		
		
		if (numero < 10000000)
			num_letradmm = millon(numero);
		
		return num_letradmm;
	}

	
	public String convertirLetras(final BigDecimal importe){
		println "El importe a convertir en letras es: " +importe
		double  val1=importe.abs().doubleValue();
		int entero=importe.intValue();
		double cent=val1-(double)entero;
		long valor=Math.round(cent*100);
		final String svalor=String.valueOf(valor);
		final String ok=StringUtils.leftPad(svalor,2,'0');
		num_letras = decmillon(importe.intValue());
		num_letras+=" PESOS {0}/100 M.N.";
		println "Importe con letra  " +num_letras
		return MessageFormat.format(num_letras, ok);
	}
	
	private static ImporteALetra INSTANCE;
	
	public static String aLetra(final BigDecimal importe){
		if(INSTANCE==null)
			INSTANCE=new ImporteALetra();
		return INSTANCE.convertirLetras(importe);
	}


}
