package principal;

import java.util.Scanner;

public class LoginUsuario {
    public static void main(String[] args) {
        String mail = "yaniscorrea@gmail.com";
        String pass = "12345";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su email: ");
        String inputEmail = scanner.nextLine();

        System.out.println("Ingrese su contraseña: ");
        String inputContrasena = scanner.nextLine();

        if (inputEmail.equals(mail)) {
            if (inputContrasena.equals(pass)) {
                System.out.println("Sesión iniciada");
            } else {
                System.out.println("Contraseña incorrecta");
            }
        } else {
            System.out.println("Usuario incorrecto");
        }
    }
}
