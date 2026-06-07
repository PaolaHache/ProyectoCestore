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

        // Precargados
        usuarios.add(new Admin("Yanis", "Correa", "yaniscorrea@gmail.com", "Uruguay", "12345"));
        usuarios.add(new Admin("Leonardo", "Pérez", "leonardoperez@gmail.com", "Uruguay", "12345"));
        usuarios.add(new Tester("Paola", "Holzmann", "paola291187@gmail.com", "Uruguay", "Abcde"));
    }

    public void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Login");
            System.out.println("2. Registro de usuario");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción válida: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opción inválida. Ingrese un número.");
                scanner.nextLine(); // limpiar buffer
                continue;
            }

            switch (opcion) {
                case 1 -> loginUsuario();
                case 2 -> registrarUsuario();
                case 3 -> System.out.println("Salida exitosa...");
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
        if (!acceso) System.out.println("Credenciales incorrectas.");
    }

    private void registrarUsuario() {
        System.out.println("=== Registro de Usuario ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

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

        System.out.print("Tipo de usuario (1=Admin, 2=Tester): ");
        int tipo;
        try {
            tipo = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Tipo inválido. Se registrará como Tester por defecto.");
            scanner.nextLine();
            tipo = 2;
        }

        Usuario nuevo;
        if (tipo == 1) {
            nuevo = new Admin(nombre, apellido, email, pais, password);
        } else if (tipo == 2) {
            nuevo = new Tester(nombre, apellido, email, pais, password);
        } else {
            System.out.println("Tipo inválido. Se registrará como Tester por defecto.");
            nuevo = new Tester(nombre, apellido, email, pais, password);
        }

        usuarios.add(nuevo);
        System.out.println("Usuario registrado correctamente.");
    }
}