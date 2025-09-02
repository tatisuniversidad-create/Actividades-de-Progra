import java.util.Scanner;
// integranteas Tatiana Ramirez y Esteban Peña
public class MenuFinal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        String proteina = "", leguminosas = "", ensalada = "", acompañamiento = "";
        int PrecioProt = 0, PrecioLeg = 0, PrecioEns = 0, PrecioAcom = 0;

        do {
            System.out.println("\n¡===== CORRIENTAZO =====!");
            System.out.println("*1) Proteína           *");
            System.out.println("*2) Leguminosas        *");
            System.out.println("*3) Ensalada           *");
            System.out.println("*4) Acompañamiento     *");
            System.out.println("*5) Total del pedido   *");
            System.out.println("*6) Salir              *");
            System.out.println("=========================");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: // PROTEÍNA
                    System.out.println("===== PROTEÍNA ========");
                    System.out.println("1) Pollo (6000)");
                    System.out.println("2) Carne de res (7000)");
                    System.out.println("3) Carne de cerdo (6500)");
                    System.out.println("4) Pescado (8000)");
                    int OpcionProt = sc.nextInt();
                    switch (OpcionProt) {
                        case 1:
                            proteina = "Pollo";
                            PrecioProt = 6000;
                            break;
                        case 2:
                            proteina = "Carne de res";
                            PrecioProt = 7000;
                            break;
                        case 3:
                            proteina = "Carne de cerdo";
                            PrecioProt = 6500;
                            break;
                        case 4:
                            proteina = "Pescado";
                            PrecioProt = 8000;
                            break;
                        default:
                            System.out.println("Opción no válida");
                    }
                    break;

                case 2: // LEGUMINOSAS
                    System.out.println("===== LEGUMINOSAS =====");
                    System.out.println("1) Frijol (2000)");
                    System.out.println("2) Lentejas (1800)");
                    System.out.println("3) Garbanzos (2200)");
                    System.out.println("4) Arvejas (2000)");
                    int OpcionLeg = sc.nextInt();
                    switch (OpcionLeg) {
                        case 1:
                            leguminosas = "Frijol";
                            PrecioLeg = 2000;
                            break;
                        case 2:
                            leguminosas = "Lentejas";
                            PrecioLeg = 1800;
                            break;
                        case 3:
                            leguminosas = "Garbanzos";
                            PrecioLeg = 2200;
                            break;
                        case 4:
                            leguminosas = "Arvejas";
                            PrecioLeg = 2000;
                            break;
                        default:
                            System.out.println("Opción no válida");
                    }
                    break;

                case 3: // ENSALADA
                    System.out.println("===== ENSALADA ========");
                    System.out.println("1) Lechuga y tomate (1500)");
                    System.out.println("2) Ensalada de aguacate (2500)");
                    System.out.println("3) Verduras cocidas (2000)");
                    System.out.println("4) Ensalada dulce (3000)");
                    int OpcionEns = sc.nextInt();
                    switch (OpcionEns) {
                        case 1:
                            ensalada = "Lechuga y tomate";
                            PrecioEns = 1500;
                            break;
                        case 2:
                            ensalada = "Ensalada de aguacate";
                            PrecioEns = 2500;
                            break;
                        case 3:
                            ensalada = "Verduras cocidas";
                            PrecioEns = 2000;
                            break;
                        case 4:
                            ensalada = "Ensalada dulce";
                            PrecioEns = 3000;
                            break;
                        default:
                            System.out.println("Opción no válida");
                    }
                    break;

                case 4: // ACOMPAÑAMIENTO
                    System.out.println("=== ACOMPAÑAMIENTO ====");
                    System.out.println("1) Papa (1000)");
                    System.out.println("2) Plátano (1200)");
                    System.out.println("3) Arepa (1000)");
                    System.out.println("4) Yuca (1200)");
                    int OpcionAcom = sc.nextInt();
                    switch (OpcionAcom) {
                        case 1:
                            acompañamiento = "Papa";
                            PrecioAcom = 1000;
                            break;
                        case 2:
                            acompañamiento = "Plátano";
                            PrecioAcom = 1200;
                            break;
                        case 3:
                            acompañamiento = "Arepa";
                            PrecioAcom = 1000;
                            break;
                        case 4:
                            acompañamiento = "Yuca";
                            PrecioAcom = 1200;
                            break;
                        default:
                            System.out.println("Opción no válida");
                    }
                    break;

                case 5: // TOTAL
                    int total = PrecioProt + PrecioLeg + PrecioEns + PrecioAcom;
                    System.out.println("\n===== SU PEDIDO =====");
                    System.out.println("Proteína: " + proteina);
                    System.out.println("Leguminosas: " + leguminosas);
                    System.out.println("Ensalada: " + ensalada);
                    System.out.println("Acompañamiento: " + acompañamiento);
                    System.out.println("💰 Precio total: " + total);
                    break;

                case 6: // SALIR
                    System.out.println("Saliendo del menú...");
                    break;

                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 6);

        sc.close();
    }
}