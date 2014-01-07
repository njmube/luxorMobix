package com.luxsoft.utils

class Rounding {
	
	public static BigDecimal round(BigDecimal val,int n) {
		return val.setScale(n, BigDecimal.ROUND_HALF_UP);
	}

}
