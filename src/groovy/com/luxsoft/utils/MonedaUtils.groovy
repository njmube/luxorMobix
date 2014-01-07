package com.luxsoft.utils

import java.math.RoundingMode;

class MonedaUtils {
	
	public final static Currency PESOS;
	public final static Currency DOLARES;
	public final static Currency EUROS;
	
	static{
		Locale mx=new Locale("es","mx");
		PESOS=Currency.getInstance(mx);
		DOLARES=Currency.getInstance(Locale.US);
		EUROS=Currency.getInstance("EUR");
	}
	
	public static final BigDecimal IVA=0.16;
	
	/**
	 * Calcula el impuesto a la tasa indicada. La tasa es indicada en entreo de 1 a 100
	 * 
	 * @param importe
	 * @param tasa en formato
	 * @return
	 */
	public static final BigDecimal calcularImpuesto(BigDecimal importe,BigDecimal tasa){
		tasa=tasa.divide(100,RoundingMode.HALF_EVEN)
		return importe.multiply(tasa)
	}
	
	public static final BigDecimal calcularImpuesto(BigDecimal importe){
		return importe.multiply(IVA)
	}
	
	
	public static final BigDecimal calcularTotal(BigDecimal importe){
		return importe.add(calcularImpuesto(importe));
	}
	
	
	
	
	static final BigDecimal aplicarDescuentosEnCascada(final BigDecimal importe,BigDecimal... descuentos){
		BigDecimal neto=importe;
		for(BigDecimal dd:descuentos){
			if(dd==null)continue;
			if(dd.doubleValue()>0){
				BigDecimal d=BigDecimal.valueOf(dd);
				BigDecimal descuento=neto.multiply(d);
				descuento=descuento.divide(BigDecimal.valueOf(100d));
				neto=neto.subtract(descuento).setScale(2, RoundingMode.HALF_EVEN);
			}
		}
		return neto;
	}
	
	
	public static final BigDecimal calcularImporteDelTotal(BigDecimal total){
		BigDecimal val=BigDecimal.valueOf(1).add(IVA);
		BigDecimal importe=total.divide(val,2,RoundingMode.HALF_EVEN);
		return importe;
	}
	
	
	public static final BigDecimal calcularImporteDelTotal(BigDecimal total,BigDecimal tasaIva){
		BigDecimal val=BigDecimal.valueOf(1).add(tasaIva);
		BigDecimal importe=total.divide(val,2,RoundingMode.HALF_EVEN);
		return importe;
	}
	
	
	public static final BigDecimal aplicarDescuentosEnCascadaSinRedondeo(
			final BigDecimal importe,double... descuentos){
		BigDecimal neto=importe;
		for(double dd:descuentos){
			if(dd>0){
				BigDecimal d=BigDecimal.valueOf(dd);
				BigDecimal descuento=neto.multiply(d);
				descuento=descuento.divide(BigDecimal.valueOf(100d));
				neto=neto.subtract(descuento).setScale(6, RoundingMode.HALF_EVEN);
			}
		}
		return neto;
	}
	
	
	

}
