package com.hackaboss.empleados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import logica.Empleado;
import persistencia.ControladoraPersistencia;
import persistencia.exceptions.NonexistentEntityException;

public class Empleados {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ControladoraPersistencia controladora = new ControladoraPersistencia();
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine());
            
            try {
                switch (opcion) {
                    case 1: altaEmpleado(); break;
                    case 2: bajaEmpleado(); break;
                    case 3: consultarEmpleados(); break;
                    case 4: modificarEmpleado(); break;
                    case 5: System.out.println("¡Hasta luego!"); break;
                    default: System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("\n╔════════════════════════╗");
        System.out.println("║     MENU PRINCIPAL     ║");
        System.out.println("╠════════════════════════╣");
        System.out.println("║ 1) ALTA               ║");
        System.out.println("║ 2) BAJA               ║");
        System.out.println("║ 3) CONSULTA           ║");
        System.out.println("║ 4) MODIFICACIONES     ║");
        System.out.println("║ 5) SALIR              ║");
        System.out.println("╚════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    private static void altaEmpleado() {
        System.out.println("\n=== ALTA DE EMPLEADO ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Salario: ");
        double salario = Double.parseDouble(scanner.nextLine());
        System.out.print("Fecha de inicio (dd/MM/yyyy): ");
        LocalDate fechaInicio = LocalDate.parse(scanner.nextLine(), dateFormatter);

        Empleado empleado = new Empleado(0, nombre, apellido, cargo, salario, fechaInicio);
        controladora.agregarEmpleado(empleado);
        System.out.println("Empleado agregado exitosamente");
    }

    private static void bajaEmpleado() throws NonexistentEntityException {
        System.out.println("\n=== BAJA DE EMPLEADO ===");
        System.out.print("ID del empleado a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        controladora.eliminarEmpleado(id);
        System.out.println("Empleado eliminado exitosamente");
    }

    private static void consultarEmpleados() {
        System.out.println("\n=== CONSULTA DE EMPLEADOS ===");
        System.out.println("1) Ver todos los empleados");
        System.out.println("2) Buscar por cargo");
        System.out.print("Seleccione una opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        if (opcion == 1) {
            List<Empleado> empleados = controladora.obtenerEmpleados();
            mostrarEmpleados(empleados);
        } else if (opcion == 2) {
            System.out.print("Ingrese el cargo a buscar: ");
            String cargo = scanner.nextLine();
            List<Empleado> empleados = controladora.buscarEmpleadosPorCargo(cargo);
            mostrarEmpleados(empleados);
        }
    }

    private static void modificarEmpleado() throws NonexistentEntityException {
        System.out.println("\n=== MODIFICAR EMPLEADO ===");
        System.out.print("ID del empleado a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Empleado empleado = controladora.obtenerEmpleado(id);
        if (empleado == null) {
            System.out.println("Empleado no encontrado");
            return;
        }

        System.out.println("Datos actuales: " + empleado);
        System.out.println("\nIngrese los nuevos datos (Enter para mantener el valor actual):");
        
        System.out.print("Nuevo nombre [" + empleado.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) empleado.setNombre(nombre);

        System.out.print("Nuevo apellido [" + empleado.getApellido() + "]: ");
        String apellido = scanner.nextLine();
        if (!apellido.isEmpty()) empleado.setApellido(apellido);

        System.out.print("Nuevo cargo [" + empleado.getCargo() + "]: ");
        String cargo = scanner.nextLine();
        if (!cargo.isEmpty()) empleado.setCargo(cargo);

        System.out.print("Nuevo salario [" + empleado.getSalario() + "]: ");
        String salarioStr = scanner.nextLine();
        if (!salarioStr.isEmpty()) empleado.setSalario(Double.parseDouble(salarioStr));

        System.out.print("Nueva fecha de inicio [" + empleado.getFechaInicio().format(dateFormatter) + "]: ");
        String fecha = scanner.nextLine();
        if (!fecha.isEmpty()) empleado.setFechaInicio(LocalDate.parse(fecha, dateFormatter));

        controladora.actualizarEmpleado(empleado);
        System.out.println("Empleado actualizado exitosamente");
    }

    private static void mostrarEmpleados(List<Empleado> empleados) {
        if (empleados.isEmpty()) {
            System.out.println("No se encontraron empleados");
            return;
        }

        System.out.println("\nLista de empleados:");
        System.out.println("------------------");
        for (Empleado emp : empleados) {
            System.out.println(emp);
        }
    }
}