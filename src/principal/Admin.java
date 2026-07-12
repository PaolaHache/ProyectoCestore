package principal;

import excepciones.DatosInvalidosException;

public class Admin extends Usuario {

    public Admin(String nombre, String apellido, String email, String pais, String password)
            throws DatosInvalidosException {
        super(nombre, apellido, email, pais, password);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("ADMIN: " + getNombre() + " " + getApellido()
                + " - " + getEmail() + " | País: " + getPais());
    }

    @Override
    public void accionEspecial() {
        System.out.println("Gestionando usuarios y configuraciones...");
    }
}