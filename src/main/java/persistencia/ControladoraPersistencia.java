package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Empleado;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {
    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();

    public void agregarEmpleado(Empleado empleado) {
        empleadoJpa.create(empleado);
    }

    public void eliminarEmpleado(int id) throws NonexistentEntityException {
        empleadoJpa.destroy(id);
    }

    public void actualizarEmpleado(Empleado empleado) throws NonexistentEntityException {
        try {
            empleadoJpa.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Empleado> obtenerEmpleados() {
        return empleadoJpa.findEmpleadoEntities();
    }

    public Empleado obtenerEmpleado(int id) {
        return empleadoJpa.findEmpleado(id);
    }

    public List<Empleado> buscarEmpleadosPorCargo(String cargo) {
        return empleadoJpa.findEmpleadosByCargo(cargo);
    }
}


