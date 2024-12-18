package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import dbm.Config;

public  class CtrlConexion {
	
	private static Connection conn = null;
	
	private   CtrlConexion() throws Exception{
		new Config();
	}
	
	public static Connection conectar() throws Exception {
		if (conn == null) {
			new CtrlConexion();
		}
		conn = DriverManager.getConnection(Config.url, Config.userName, Config.userPass);
		return conn;
	}
	
	
	public static void cerrar() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ignore) {};
	}
	
	
	
	
	
}
