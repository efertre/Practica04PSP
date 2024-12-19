package controller;


import model.Asignatura;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CtrlDetalle {

    private Statement statement;
    private ResultSet resultSet;

    /**
     * Constructor que inicializa el Statement y ejecuta la consulta de asignaturas.
     * @throws SQLException si ocurre un error en la consulta.
     */
    public CtrlDetalle() throws SQLException {
        // Obtiene el Statement con capacidad de desplazamiento y actualización.
        statement = CtrlConexion.obtenerStatementDetalle();
        // Consulta inicial: selecciona todas las asignaturas.
        resultSet = statement.executeQuery("SELECT * FROM Asignatura");
    }

    /**
     * Verifica si hay registros en el ResultSet.
     * @return true si hay registros, false en caso contrario.
     * @throws SQLException si ocurre un error al consultar el ResultSet.
     */
    public boolean hayRegistros() throws SQLException {
        return resultSet.isBeforeFirst() || resultSet.isAfterLast() || resultSet.first();
    }

    /**
     * Avanza al siguiente registro.
     * @return true si hay un siguiente registro, false si no.
     * @throws SQLException si ocurre un error al avanzar.
     */
    public boolean avanzar() throws SQLException {
        return resultSet.next();
    }

    /**
     * Retrocede al registro anterior.
     * @return true si hay un registro anterior, false si no.
     * @throws SQLException si ocurre un error al retroceder.
     */
    public boolean retroceder() throws SQLException {
        return resultSet.previous();
    }

    /**
     * Mueve el cursor al primer registro.
     * @throws SQLException si ocurre un error al mover el cursor.
     */
    public void irAlPrimero() throws SQLException {
        resultSet.first();
    }

    /**
     * Mueve el cursor al último registro.
     * @throws SQLException si ocurre un error al mover el cursor.
     */
    public void irAlUltimo() throws SQLException {
        resultSet.last();
    }

    /**
     * Verifica si el cursor está en el primer registro.
     * @return true si está en el primer registro, false en caso contrario.
     * @throws SQLException si ocurre un error al verificar.
     */
    public boolean esPrimero() throws SQLException {
        return resultSet.isFirst();
    }

    /**
     * Verifica si el cursor está en el último registro.
     * @return true si está en el último registro, false en caso contrario.
     * @throws SQLException si ocurre un error al verificar.
     */
    public boolean esUltimo() throws SQLException {
        return resultSet.isLast();
    }

    /**
     * Devuelve un objeto Asignatura a partir del registro actual del ResultSet.
     * @return un objeto Asignatura con los datos del registro actual.
     * @throws SQLException si ocurre un error al leer los datos.
     */
    public Asignatura obtenerAsignaturaActual() throws SQLException {
        Asignatura asignatura = new Asignatura();
        asignatura.setCodigo(resultSet.getInt("codigo"));
        asignatura.setNombre(resultSet.getString("nombre"));
        asignatura.setNota(resultSet.getDouble("nota"));
        asignatura.setAlumnNumero(resultSet.getInt("alumno_numero"));
        return asignatura;
    }

    /**
     * Modifica la nota de la asignatura actual en la base de datos.
     * @param nuevaNota la nueva nota a establecer.
     * @throws SQLException si ocurre un error al actualizar la nota.
     */
    public void modificarNota(float nuevaNota) throws SQLException {
        resultSet.updateFloat("nota", nuevaNota);
        resultSet.updateRow();
    }

    /**
     * Cierra el ResultSet y el Statement.
     * @throws SQLException si ocurre un error al cerrar los recursos.
     */
    public void cerrar() throws SQLException {
        if (resultSet != null && !resultSet.isClosed()) {
            resultSet.close();
        }
        if (statement != null && !statement.isClosed()) {
            statement.close();
        }
    }
}
