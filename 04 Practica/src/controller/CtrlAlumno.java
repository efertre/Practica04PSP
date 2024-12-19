package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Alumno;

public class CtrlAlumno {

    private Statement statement;

    /**
     * Constructor que inicializa el Statement.
     * @throws SQLException si ocurre un error al obtener el Statement.
     */
    public CtrlAlumno() throws SQLException {
        this.statement = CtrlConexion.obtenerStatementResumen(); // Usa el Statement compartido para consultas simples.
    }

    /**
     * Obtiene un objeto Alumno desde la base de datos basado en el número de alumno.
     * @param numero el número del alumno (PK en la tabla Alumno).
     * @return un objeto Alumno.
     * @throws SQLException si ocurre un error al realizar la consulta.
     */
    public Alumno obtenerAlumnoPorNumero(int numero) throws SQLException {
        String query = "SELECT * FROM Alumno WHERE numero = " + numero;
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            Alumno alumno = new Alumno();
            alumno.setNumero(resultSet.getInt("numero"));
            alumno.setUsuario(resultSet.getString("usuario"));
            alumno.setContrasenia(resultSet.getString("contrasenia"));
            alumno.setFechaNac(resultSet.getDate("fecha_nacimiento").toLocalDate());
            alumno.setNotaMedia(resultSet.getDouble("nota_media"));
            alumno.setImg(resultSet.getBytes("imagen")); 
            resultSet.close();
            return alumno;
        } else {
            resultSet.close();
            return null; // Si no encuentra el alumno.
        }
    }

    public boolean actualizarNotaMedia(Alumno alumno) throws SQLException {
        
        String query = "UPDATE Alumno SET nota_media = ? WHERE numero = ?";

        // Prepara la consulta
        try (PreparedStatement preparedStatement = CtrlConexion.obtenerConexion().prepareStatement(query)) {
            // Establece los parámetros
            preparedStatement.setDouble(1, alumno.getNotaMedia());  // Establece la nueva nota media
            preparedStatement.setInt(2, alumno.getNumero());        // Establece el número del alumno

            // Ejecuta la actualización
            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;  // Si se actualizó al menos una fila, la actualización fue exitosa
        }
    }


   

    /**
     * Cierra el Statement.
     * @throws SQLException si ocurre un error al cerrar el Statement.
     */
    public void cerrar() throws SQLException {
        if (statement != null && !statement.isClosed()) {
            statement.close();
        }
    }
}
