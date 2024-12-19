package controller;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Asignatura;

public class CtrlResumen {

    private Statement statement;

    /**
     * Constructor que inicializa el Statement.
     * @throws SQLException si ocurre un error al obtener el Statement.
     */
    public CtrlResumen() throws SQLException {
        this.statement = CtrlConexion.obtenerStatementResumen();
    }

    /**
     * Devuelve una lista de objetos Asignatura asociados a un alumno.
     * @param aluNumero el número del alumno (FK en la tabla Asignatura).
     * @return una lista de asignaturas.
     * @throws SQLException si ocurre un error al realizar la consulta.
     */
    public List<Asignatura> obtenerAsignaturasDeAlumno(int aluNumero) throws SQLException {
        List<Asignatura> asignaturas = new ArrayList<>();
        String query = "SELECT * FROM Asignatura WHERE alumno_numero = " + aluNumero;
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Asignatura asignatura = new Asignatura();
            asignatura.setCodigo(resultSet.getInt("codigo"));
            asignatura.setNombre(resultSet.getString("nombre"));
            asignatura.setNota(resultSet.getDouble("nota"));
            asignatura.setAlumnNumero(resultSet.getInt("alumno_numero"));
            asignaturas.add(asignatura);
        }

        resultSet.close();
        return asignaturas;
    }

    /**
     * Calcula la nota media de las asignaturas de un alumno.
     * @param aluNumero el número del alumno (FK en la tabla Asignatura).
     * @return la nota media como un valor float.
     * @throws SQLException si ocurre un error al realizar la consulta.
     */
    public float calcularNotaMedia(int aluNumero) throws SQLException {
        String query = "SELECT AVG(nota) AS notaMedia FROM Asignatura WHERE alumno_numero = " + aluNumero;
        ResultSet resultSet = statement.executeQuery(query);

        float notaMedia = 0;
        if (resultSet.next()) {
            notaMedia = resultSet.getFloat("notaMedia");
        }

        resultSet.close();
        return notaMedia;
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