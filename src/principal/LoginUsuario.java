package principal;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginUsuario {
    public static void main(String[] args) {
        // Lista de administradores
        ArrayList<Usuario> admins = new ArrayList<>();
        admins.add(new Usuario("Yanis", "Correa", "yaniscorrea@gmail.com", "Uruguay", "ADMIN", "12345"));
        admins.add(new Usuario("Leonardo", "Pérez", "leonardoperez@gmail.com", "Uruguay", "ADMIN", "12345"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su email: ");
        String inputEmail = scanner.nextLine();

        System.out.println("Ingrese su contraseña: ");
        String inputContrasena = scanner.nextLine();

        boolean acceso = false;
        for (Usuario u : admins) {
            if (u.email.equals(inputEmail) && u.password.equals(inputContrasena)) {
                System.out.println("Sesión iniciada correctamente");
                acceso = true;
                break;
            }
        }

        if (!acceso) {
            System.out.println("Credenciales incorrectas");
        }
    }
}
