package Util;

import java.sql.Date;

public class Datas {
	public Date converteData(String data) 
	{
		return (java.sql.Date.valueOf(data));
	}
}
