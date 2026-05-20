package principal;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginUsuario {
    static ArrayList<Usuario> admins = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Admin ya existentes

        admins.add(new Usuario("Yanis", "Correa", "yaniscorrea@gmail.com", "Uruguay", "ADMIN", "12345"));
        admins.add(new Usuario("Leonardo", "Pérez", "leonardoperez@gmail.com", "Uruguay", "ADMIN", "12345"));

        int opcion;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Login");
            System.out.println("2. Registro de administrador");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    loginUsuario();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 3);
    }

    // Login
    public static void loginUsuario() {
        System.out.println("=== Login ===");
        System.out.print("Email: ");
        String inputEmail = scanner.nextLine();
        System.out.print("Contraseña: ");
        String inputContrasena = scanner.nextLine();

        boolean acceso = false;
        for (Usuario u : admins) {
            if (u.email.equals(inputEmail) && u.password.equals(inputContrasena)) {
                System.out.println("Login exitoso");
                acceso = true;
                break;
            }
        }

        if (!acceso) {
            System.out.println("Credenciales incorrectas");
        }
    }

    // Registro de nuevo admin
    public static void registrarUsuario() {
        System.out.println("=== Registro de Administrador ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Validar si ya existe
        for (Usuario u : admins) {
            if (u.email.equals(email)) {
                System.out.println("Error: el usuario ya existe.");
                return;
            }
        }

        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Repetir contraseña: ");
        String repetir = scanner.nextLine();

        if (!password.equals(repetir)) {
            System.out.println("Error: las contraseñas no coinciden.");
            return;
        }

        System.out.print("País: ");
        String pais = scanner.nextLine();

        Usuario nuevo = new Usuario(nombre, apellido, email, pais, "ADMIN", password);
        admins.add(nuevo);
        System.out.println("Usuario registrado correctamente.");
    }
}
