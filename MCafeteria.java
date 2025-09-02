import java.util.Scanner;

public class MCafeteria {

    public static void main(String[] args) {
        int opcion;
        do {
            opcion = menuPrincipal();
            if (opcion == 1) {
                int subOpcionProducto;
                do {
                    subOpcionProducto = menuProducto();
                    if (subOpcionProducto == 1) {
                        System.out.println("Ha seleccionado Cafe Latte");
                    }
                } while (subOpcionProducto != 2); // Volver al menu principal del producto
            } else if (opcion == 2) {
                int subOpcionCompras;
                do {
                    subOpcionCompras = menuCompras();
                    if (subOpcionCompras == 1) {
                        System.out.println("Ha comprado 1 unidad");
                    } else if (subOpcionCompras == 2) {
                        System.out.println("Ha comprado 2 unidades");
                    }
                } while (subOpcionCompras != 3); // Volver al menu principal de compras
            } else if (opcion == 3) {
                System.out.println("Programa terminado");
            }
        } while (opcion != 3);
    }

    public static int menuPrincipal() {
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;
        System.out.println("Menu");
        System.out.println("1. Menu producto");
        System.out.println("2. Menu compras");
        System.out.println("3. Terminal");
        System.out.println("Cual es su opcion?");
        opcion = entrada.nextInt();
        return opcion;
    }

    public static int menuProducto() {
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;
        System.out.println("Menu producto");
        System.out.println("1. Cafe Latte");
        System.out.println("2. Volver al menu principal");
        System.out.println("Cual es su opcion?");
        opcion = entrada.nextInt();
        return opcion;
    }

    public static int menuCompras() {
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;
        System.out.println("Menu compras");
        System.out.println("1. Comprar 1 unidad");
        System.out.println("2. Comprar 2 unidades");
        System.out.println("3. Volver al menu principal");
        System.out.println("Cual es su opcion?");
        opcion = entrada.nextInt();
        return opcion;
    }
}