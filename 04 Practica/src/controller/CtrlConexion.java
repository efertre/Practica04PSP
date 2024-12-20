package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CtrlConexion {

	private static Connection conexion;

	// Credenciales y datos de conexión (Dani si lo quieres probar aquí tienes que
	// cambiar los credenciales a los de tu base de datos)
	private static final String URL = "jdbc:mysql://localhost:3308/paulema";
	private static final String USUARIO = "root";
	private static final String CONTRASENA = "";

	// Método para obtener la conexión
	public static Connection obtenerConexion() throws SQLException {
		if (conexion == null || conexion.isClosed()) {
			// Establecer la conexión
			conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		}
		return conexion;
	}

	/**
	 * Método para cerrar la conexión a la base de datos.
	 * 
	 * @throws SQLException si ocurre un error al cerrar la conexión.
	 */
	public static void cerrarConexion() throws SQLException {
		if (conexion != null && !conexion.isClosed()) {
			conexion.close();
			System.out.println("Conexión cerrada correctamente.");
		}
	}

	/**
	 * Devuelve un `Statement` para operar en el panel "detalle".
	 * 
	 * @return un `Statement` con capacidad de desplazamiento (hacia delante y hacia
	 *         atrás), y permite actualizar desde el propio ResultSet.
	 * @throws SQLException si ocurre un error al crear el `Statement`.
	 */
	public static Statement obtenerStatementDetalle() throws SQLException {
		if (conexion == null || conexion.isClosed()) {
			obtenerConexion();
		}
		return conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}

	/**
	 * Devuelve un `Statement` para operar en el panel "resumen".
	 * 
	 * @return un `Statement` simple para consultas rápidas (permite desplazar solo
	 *         hacia delante y está disponible solo en modo lectura desde el ResultSet).
	 * @throws SQLException si ocurre un error al crear el `Statement`.
	 */
	public static Statement obtenerStatementResumen() throws SQLException {
		if (conexion == null || conexion.isClosed()) {
			obtenerConexion();
		}
		return conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
	}
}
