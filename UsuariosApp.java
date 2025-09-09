// Nombres: Tatiana Ramirez, Esteban Peña 
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class UsuariosAppV2 {

    private static final int CAPACIDAD = 30;
    private static final List<Usuario> usuarios = new ArrayList<>(CAPACIDAD);

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner sc = new Scanner(System.in)) {
            boolean salir = false;
            while (!salir) {
                mostrarMenu();
                System.out.print("\nElige una opción: ");
                String opcion = sc.nextLine().trim();
                switch (opcion) {
                    case "1":
                        crearUsuario(sc);
                        break;
                    case "2":
                        listarUsuarios();
                        break;
                    case "3":
                        buscarPorNombre(sc);
                        break;
                    case "4":
                        actualizarPorIndice(sc);
                        break;
                    case "5":
                        eliminarPorIndice(sc);
                        break;
                    case "6":
                        eliminarPorNombre(sc);
                        break;
                    case "0":
                        salir = true;
                        System.out.println("Saliendo... ¡Gracias!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
                System.out.println();
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("==============================");
        System.out.println("    GESTIÓN DE USUARIOS (30)    ");
        System.out.println("==============================");
        System.out.println("1) Crear usuario");
        System.out.println("2) Listar usuarios");
        System.out.println("3) Buscar por nombre");
        System.out.println("4) Actualizar por índice");
        System.out.println("5) Eliminar por índice");
        System.out.println("6) Eliminar por nombre");
        System.out.println("0) Salir");
    }

    // CRUD

    private static void crearUsuario(Scanner sc) {
        if (usuarios.size() >= CAPACIDAD) {
            System.out.println("Capacidad máxima alcanzada (" + CAPACIDAD + ").");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Salario (ej. 1234.56): ");
        String sSalario = sc.nextLine().trim();
        Double salario = parseDoubleSeguro(sSalario);
        if (salario == null || salario < 0) {
            System.out.println("Salario inválido.");
            return;
        }

        System.out.print("Cargo: ");
        String cargo = sc.nextLine().trim();
        if (cargo.isEmpty()) {
            System.out.println("El cargo no puede estar vacío.");
            return;
        }

        System.out.print("Sexo (M/F/O): ");
        String sSexo = sc.nextLine().trim().toUpperCase(Locale.ROOT);
        if (sSexo.isEmpty() || "MFO".indexOf(sSexo.charAt(0)) == -1) {
            System.out.println("Sexo inválido. Use M, F u O.");
            return;
        }
        char sexo = sSexo.charAt(0);

        usuarios.add(new Usuario(nombre, salario, cargo, sexo));
        System.out.println("Usuario creado correctamente. Total ahora: " + usuarios.size());
    }

    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios para mostrar.");
            return;
        }
        System.out.printf("%n%-5s %-20s %-12s %-15s %-4s%n", "#", "NOMBRE", "SALARIO", "CARGO", "SEXO");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            System.out.printf("%-5d %-20s %-12.2f %-15s %-4s%n", i, u.getNombre(), u.getSalario(), u.getCargo(), u.getSexo());
        }
    }

    private static void buscarPorNombre(Scanner sc) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios para buscar.");
            return;
        }
        System.out.print("Ingrese parte o todo el nombre a buscar: ");
        String q = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        boolean encontrado = false;
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getNombre().toLowerCase(Locale.ROOT).contains(q)) {
                if (!encontrado) {
                    System.out.printf("%n%-5s %-20s %-12s %-15s %-4s%n", "#", "NOMBRE", "SALARIO", "CARGO", "SEXO");
                    System.out.println("----------------------------------------------------------------");
                    encontrado = true;
                }
                System.out.printf("%-5d %-20s %-12.2f %-15s %-4s%n", i, u.getNombre(), u.getSalario(), u.getCargo(), u.getSexo());
            }
        }
        if (!encontrado) {
            System.out.println("Sin coincidencias.");
        }
    }

    private static void actualizarPorIndice(Scanner sc) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios para actualizar.");
            return;
        }
        int idx = pedirIndice(sc, "Índice a actualizar");
        if (idx == -1) {
            return;
        }
        Usuario u = usuarios.get(idx);

        System.out.println("Deje vacío un campo para mantener el valor actual.");

        System.out.print("Nombre (actual: " + u.getNombre() + "): ");
        String nuevoNombre = sc.nextLine().trim();
        if (!nuevoNombre.isEmpty()) {
            u.setNombre(nuevoNombre);
        }

        System.out.print("Salario (actual: " + u.getSalario() + "): ");
        String sSalario = sc.nextLine().trim();
        if (!sSalario.isEmpty()) {
            Double val = parseDoubleSeguro(sSalario);
            if (val != null && val >= 0) {
                u.setSalario(val);
            } else {
                System.out.println("Salario no actualizado (valor inválido).");
            }
        }

        System.out.print("Cargo (actual: " + u.getCargo() + "): ");
        String nuevoCargo = sc.nextLine().trim();
        if (!nuevoCargo.isEmpty()) {
            u.setCargo(nuevoCargo);
        }

        System.out.print("Sexo (M/F/O) (actual: " + u.getSexo() + "): ");
        String sSexo = sc.nextLine().trim().toUpperCase(Locale.ROOT);
        if (!sSexo.isEmpty() && "MFO".indexOf(sSexo.charAt(0)) != -1) {
            u.setSexo(sSexo.charAt(0));
        }

        System.out.println("Usuario actualizado.");
    }

    private static void eliminarPorIndice(Scanner sc) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios para eliminar.");
            return;
        }
        int idx = pedirIndice(sc, "Índice a eliminar");
        if (idx == -1) {
            return;
        }
        usuarios.remove(idx);
        System.out.println("Usuario eliminado. Total ahora: " + usuarios.size());
    }

    private static void eliminarPorNombre(Scanner sc) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios para eliminar.");
            return;
        }
        System.out.print("Nombre exacto a eliminar: ");
        String nombre = sc.nextLine().trim();
        boolean removido = usuarios.removeIf(u -> u.getNombre().equalsIgnoreCase(nombre));
        if (removido) {
            System.out.println("Usuario eliminado. Total ahora: " + usuarios.size());
        } else {
            System.out.println("No se encontró el nombre especificado.");
        }
    }

    // Utilidades
    private static int pedirIndice(Scanner sc, String prompt) {
        System.out.print(prompt + " (0-" + (usuarios.size() - 1) + "): ");
        String sIdx = sc.nextLine().trim();
        try {
            int idx = Integer.parseInt(sIdx);
            if (idx < 0 || idx >= usuarios.size()) {
                System.out.println("Índice fuera de rango.");
                return -1;
            }
            return idx;
        } catch (NumberFormatException e) {
            System.out.println("Índice inválido.");
            return -1;
        }
    }

    private static Double parseDoubleSeguro(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

// Clase que representa un usuario
class Usuario {
    private String nombre;
    private double salario;
    private String cargo;
    private char sexo;

    public Usuario(String nombre, double salario, String cargo, char sexo) {
        this.nombre = nombre;
        this.salario = salario;
        this.cargo = cargo;
        this.sexo = sexo;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public char getSexo() { return sexo; }
    public void setSexo(char sexo) { this.sexo = sexo; }
}
