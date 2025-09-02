import java.util.Scanner;

public class HeladeriaCandi {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int total = 0;

        System.out.println("*********************");
        System.out.println("* Heladeria Candi *");
        System.out.println("*********************");
        System.out.println("        MENU         ");
        System.out.println("1. Cono sencillo");
        System.out.println("2. Cono doble");
        System.out.println("3. Cono especial");
        System.out.println("4. Banana Split");
        System.out.println("0. Terminar");
        System.out.println("*********************");

        System.out.print("Seleccione una opción: ");
        int opcion = entrada.nextInt();

        if (opcion == 1) {
            total = 2500;
            System.out.println("Has elegido: 1. Cono sencillo");
            System.out.println("Precio: $2.500");
            System.out.println("Descripción: Helado refrescante con un solo sabor.");
        } else if (opcion == 2) {
            total = 4500;
            System.out.println("Has elegido: 2. Cono doble");
            System.out.println("Precio: $4.500");
            System.out.println("Descripción: Helado refrescante con dos sabores.");
        } else if (opcion == 3) {
            total = 4800;
            System.out.println("Has elegido: 3. Cono especial");
            System.out.println("Precio: $4.800");
            System.out.println("Descripción: Helado refrescante con cuatro sabores.");
        } else if (opcion == 4) {
            total = 8500;
            System.out.println("Has elegido: 4. Banana split");
            System.out.println("Precio: $8.500");
            System.out.println("Descripción: Helado refrescante con bolas de helado de vainilla, chocolate con leche y fresa, servidos en hilera.");
        } else if (opcion == 0) {
            System.out.println("¡Gracias por su visita!");
        } else {
            System.out.println("Opción inválida.");
        }

        if (total > 0) {
            System.out.println("\nTotal a pagar: $" + total);
        }

        entrada.close();
    }
}