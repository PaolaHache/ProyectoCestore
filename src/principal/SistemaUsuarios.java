package principal;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaUsuarios {
    private ArrayList<Usuario> usuarios;
    private Scanner scanner;

    public SistemaUsuarios() {
        usuarios = new ArrayList<>();
        scanner = new Scanner(System.in);

        // Usuarios precargados
        usuarios.add(new Usuario("Yanis", "Correa", "yaniscorrea@gmail.com", "Uruguay", "ADMIN", "12345"));
        usuarios.add(new Usuario("Leonardo", "Pérez", "leonardoperez@gmail.com", "Uruguay", "ADMIN", "12345"));
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Login");
            System.out.println("2. Registro de administrador");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción válida: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> loginUsuario();
                case 2 -> registrarUsuario();
                case 3 -> System.out.println("Salida exitosa del sistema...");
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

        boolean acceso = false;
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(inputEmail) && u.getPassword().equals(inputContrasena)) {
                System.out.println("Login exitoso");
                u.mostrarInfo();
                acceso = true;
                break;
            }
        }

        if (!acceso) {
            System.out.println("Credenciales incorrectas");
        }
    }

    private void registrarUsuario() {
        System.out.println("=== Registro de Administrador ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Validar si ya existe
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
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
        usuarios.add(nuevo);
        System.out.println("Usuario registrado correctamente.");
    }
}
