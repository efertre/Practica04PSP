package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CtrlConexion {

    private static Connection conexion;

    // Credenciales y datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3308/paulema";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    // Método para obtener la conexión
    public static Connection obtenerConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                // Registrar el driver si es necesario
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establecer la conexión
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            } catch (ClassNotFoundException e) {
                throw new SQLException("No se encontró el driver JDBC para MySQL", e);
            }
        }
        return conexion;
    }

    /**
     * Método para cerrar la conexión a la base de datos.
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
     * @return un `Statement` con capacidad de desplazamiento.
     * @throws SQLException si ocurre un error al crear el `Statement`.
     */
    public static Statement obtenerStatementDetalle() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            obtenerConexion();
        }
        return conexion.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );
    }

    /**
     * Devuelve un `Statement` para operar en el panel "resumen".
     * @return un `Statement` simple para consultas rápidas.
     * @throws SQLException si ocurre un error al crear el `Statement`.
     */
    public static Statement obtenerStatementResumen() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            obtenerConexion();
        }
        return conexion.createStatement(
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY
        );
    }
}
