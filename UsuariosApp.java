import java.util.Locale;
import java.util.Scanner;

//trabajo de: Tatiana Ramirez, Esteban peña
public class UsuariosApp {

    // Capacidad 
    private static final int CAPACIDAD = 30;

    // Arreglo
    private static final String[] nombres = new String[CAPACIDAD];
    private static final double[] salarios = new double[CAPACIDAD];
    private static final String[] cargos = new String[CAPACIDAD];
    private static final char[] sexos = new char[CAPACIDAD]; // 'M', 'F', 'O'

    
    private static int total = 0;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

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
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("==============================");
        System.out.println("   GESTIÓN DE USUARIOS (30)   ");
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
        if (total >= CAPACIDAD) {
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

        // Insertar
        nombres[total] = nombre;
        salarios[total] = salario;
        cargos[total] = cargo;
        sexos[total] = sSexo.charAt(0);
        total++;
        System.out.println("Usuario creado correctamente. Total ahora: " + total);
    }

    private static void listarUsuarios() {
        if (total == 0) {
            System.out.println("No hay usuarios para mostrar.");
            return;
        }
        System.out.printf("%n%-5s %-20s %-12s %-15s %-4s%n", "#", "NOMBRE", "SALARIO", "CARGO", "SEXO");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < total; i++) {
            System.out.printf("%-5d %-20s %-12.2f %-15s %-4s%n", i, nombres[i], salarios[i], cargos[i], sexos[i]);
        }
    }

    private static void buscarPorNombre(Scanner sc) {
        if (total == 0) {
            System.out.println("No hay usuarios para buscar.");
            return;
        }
        System.out.print("Ingrese parte o todo el nombre a buscar: ");
        String q = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        boolean encontrado = false;
        for (int i = 0; i < total; i++) {
            if (nombres[i].toLowerCase(Locale.ROOT).contains(q)) {
                if (!encontrado) {
                    System.out.printf("%n%-5s %-20s %-12s %-15s %-4s%n", "#", "NOMBRE", "SALARIO", "CARGO", "SEXO");
                    System.out.println("----------------------------------------------------------------");
                    encontrado = true;
                }
                System.out.printf("%-5d %-20s %-12.2f %-15s %-4s%n", i, nombres[i], salarios[i], cargos[i], sexos[i]);
            }
        }
        if (!encontrado) {
            System.out.println("Sin coincidencias.");
        }
    }

    private static void actualizarPorIndice(Scanner sc) {
        if (total == 0) {
            System.out.println("No hay usuarios para actualizar.");
            return;
        }
        int idx = pedirIndice(sc, "Índice a actualizar");
        if (idx == -1) return;

        System.out.println("Deje vacío un campo para mantener el valor actual.");

        System.out.print("Nombre (actual: " + nombres[idx] + "): ");
        String nuevoNombre = sc.nextLine().trim();
        if (!nuevoNombre.isEmpty()) nombres[idx] = nuevoNombre;

        System.out.print("Salario (actual: " + salarios[idx] + "): ");
        String sSalario = sc.nextLine().trim();
        if (!sSalario.isEmpty()) {
            Double val = parseDoubleSeguro(sSalario);
            if (val != null && val >= 0) salarios[idx] = val; else System.out.println("Salario no actualizado (valor inválido).");
        }

        System.out.print("Cargo (actual: " + cargos[idx] + "): ");
        String nuevoCargo = sc.nextLine().trim();
        if (!nuevoCargo.isEmpty()) cargos[idx] = nuevoCargo;

        System.out.print("Sexo (M/F/O) (actual: " + sexos[idx] + "): ");
        String sSexo = sc.nextLine().trim().toUpperCase(Locale.ROOT);
        if (!sSexo.isEmpty() && "MFO".indexOf(sSexo.charAt(0)) != -1) sexos[idx] = sSexo.charAt(0);

        System.out.println("Usuario actualizado.");
    }

    private static void eliminarPorIndice(Scanner sc) {
        if (total == 0) {
            System.out.println("No hay usuarios para eliminar.");
            return;
        }
        int idx = pedirIndice(sc, "Índice a eliminar");
        if (idx == -1) return;
        eliminarShift(idx);
        System.out.println("Usuario eliminado y lista corrida. Total ahora: " + total);
    }

    private static void eliminarPorNombre(Scanner sc) {
        if (total == 0) {
            System.out.println("No hay usuarios para eliminar.");
            return;
        }
        System.out.print("Nombre exacto a eliminar: ");
        String nombre = sc.nextLine().trim();
        int idx = indexOfNombre(nombre);
        if (idx == -1) {
            System.out.println("No se encontró el nombre especificado.");
            return;
        }
        eliminarShift(idx);
        System.out.println("Usuario eliminado y lista corrida. Total ahora: " + total);
    }

    //Utilidades

    private static void eliminarShift(int idx) {
        
        for (int i = idx; i < total - 1; i++) {
            nombres[i] = nombres[i + 1];
            salarios[i] = salarios[i + 1];
            cargos[i] = cargos[i + 1];
            sexos[i] = sexos[i + 1];
        }
        
        if (total > 0) {
            int last = total - 1;
            nombres[last] = null;
            salarios[last] = 0.0;
            cargos[last] = null;
            sexos[last] = '\0';
            total--;
        }
    }

    private static int indexOfNombre(String nombre) {
        for (int i = 0; i < total; i++) {
            if (nombres[i].equalsIgnoreCase(nombre)) return i;
        }
        return -1;
    }

    private static int pedirIndice(Scanner sc, String prompt) {
        System.out.print(prompt + " (0-" + (total - 1) + "): ");
        String sIdx = sc.nextLine().trim();
        try {
            int idx = Integer.parseInt(sIdx);
            if (idx < 0 || idx >= total) {
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