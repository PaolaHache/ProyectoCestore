package principal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaUsuarios {
    private ArrayList<Usuario> usuarios;
    private Scanner scanner;

    public SistemaUsuarios() {
        usuarios = new ArrayList<>();
        scanner = new Scanner(System.in);

        usuarios.add(new Admin("Yanis", "Correa", "yaniscorrea@gmail.com", "Uruguay", "12345"));
        usuarios.add(new Admin("Leonardo", "Pérez", "leonardoperez@gmail.com", "Uruguay", "12345"));
        usuarios.add(new Tester("Paola", "Holzmann", "paola291187@gmail.com", "Uruguay", "Abcde"));
    }

    public void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Login");
            System.out.println("2. Registro de administrador");
            System.out.println("3. Salir");
            System.out.println("4. Listar usuarios");
            System.out.println("5. Buscar usuario");
            System.out.print("Seleccione una opción válida: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opción inválida. Ingrese un número.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1 -> loginUsuario();
                case 2 -> registrarAdmin();
                case 3 -> System.out.println("Salida exitosa...");
                case 4 -> listarUsuarios();
                case 5 -> buscarUsuario();
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 3);
    }

    private void loginUsuario() {
        System.out.println("=== Login ===");
        System.out.print("Email: ");
        String inputEmail = scanner.nextLine();
        System.out.print("Contraseña: ");
        String inputContrasena = scanner.nextLine();

        Usuario u = buscarPorEmail(inputEmail);

        if (u != null && u.getPassword().equals(inputContrasena)) {
            System.out.println("Login exitoso");
            u.mostrarInfo();
            u.accionEspecial();

            if (u instanceof Admin) {
                menuAdmin();
            }
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    // Solo un Admin autenticado puede registrar Testers
    private void menuAdmin() {
        int opcion = 0;
        do {
            System.out.println("\n--- Panel de administrador ---");
            System.out.println("1. Registrar tester");
            System.out.println("2. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opción inválida.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1 -> registrarTester();
                case 2 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 2);
    }

    private void registrarAdmin() {
        System.out.println("=== Registro de Administrador ===");
        String[] datos = pedirDatosBasicos();
        if (datos == null) return;

        usuarios.add(new Admin(datos[0], datos[1], datos[2], datos[3], datos[4]));
        System.out.println("Administrador registrado correctamente.");
    }

    private void registrarTester() {
        System.out.println("=== Registro de Tester ===");
        String[] datos = pedirDatosBasicos();
        if (datos == null) return;

        Tester nuevo = new Tester(datos[0], datos[1], datos[2], datos[3], datos[4]);
        nuevo.setNivelTester(elegirNivelTester());
        usuarios.add(nuevo);
        System.out.println("Tester registrado correctamente.");
    }

    // Devuelve {nombre, apellido, email, pais, password} o null si hubo error
    private String[] pedirDatosBasicos() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (buscarPorEmail(email) != null) {
            System.out.println("Error: el usuario ya existe.");
            return null;
        }

        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Repetir contraseña: ");
        String repetir = scanner.nextLine();
        if (!password.equals(repetir)) {
            System.out.println("Error: las contraseñas no coinciden.");
            return null;
        }

        System.out.print("País: ");
        String pais = scanner.nextLine();

        return new String[]{nombre, apellido, email, pais, password};
    }

    private String elegirNivelTester() {
        int opcion = 0;
        do {
            System.out.println("Nivel de tester:");
            System.out.println("1. Junior");
            System.out.println("2. Senior");
            System.out.println("3. Líder");
            System.out.print("Seleccione: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opción inválida.");
                scanner.nextLine();
                opcion = 0;
            }
        } while (opcion < 1 || opcion > 3);

        return switch (opcion) {
            case 1 -> "Junior";
            case 2 -> "Senior";
            default -> "Líder";
        };
    }

    private void listarUsuarios() {
        System.out.println("=== Lista de Usuarios ===");
        for (Usuario u : usuarios) {
            u.mostrarInfo();
        }
    }

    private void buscarUsuario() {
        System.out.print("Ingrese email a buscar: ");
        String email = scanner.nextLine();

        Usuario u = buscarPorEmail(email);
        if (u != null) {
            System.out.println("Usuario encontrado:");
            u.mostrarInfo();
        } else {
            System.out.println("No se encontró usuario con ese email.");
        }
    }

    private Usuario buscarPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
}