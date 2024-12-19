package controller;

import model.Alumno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    /**
     * Actualiza los datos de un alumno en la base de datos.
     * @param alumno el objeto Alumno con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException si ocurre un error durante la actualización.
     */
    public boolean actualizarAlumno(Alumno alumno) throws SQLException {
        String query = String.format(
                "UPDATE Alumno SET usuario = '%s', contrasena = '%s', fechaNacimiento = '%s', " +
                        "notaMedia = %.2f, imagen = ? WHERE numero = %d",
                alumno.getUsuario(),
                alumno.getContrasenia(),
                java.sql.Date.valueOf(alumno.getFechaNac()).toString(),
                alumno.getNotaMedia(),
                alumno.getNumero()
        );

        java.sql.PreparedStatement preparedStatement = CtrlConexion.obtenerConexion().prepareStatement(query);

        // Establece la imagen como parámetro (BLOB).
        preparedStatement.setBytes(1, alumno.getImg() );
        int filasAfectadas = preparedStatement.executeUpdate();
        preparedStatement.close();

        return filasAfectadas > 0;
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
