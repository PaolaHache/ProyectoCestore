package principal;

import excepciones.DatosInvalidosException;

public abstract class Usuario {
    private String nombre;
    private String apellido;
    private String email;
    private String pais;
    private String password;

    public Usuario(String nombre, String apellido, String email, String pais, String password)
            throws DatosInvalidosException {
        Validador.validarCampoObligatorio(nombre, "Nombre");
        Validador.validarCampoObligatorio(apellido, "Apellido");
        Validador.validarEmail(email);
        Validador.validarCampoObligatorio(pais, "País");
        Validador.validarPassword(password);

        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.email = email.trim().toLowerCase();
        this.pais = pais.trim();
        this.password = password;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public String getPais() { return pais; }
    public String getPassword() { return password; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setPais(String pais) { this.pais = pais; }

    public void setPassword(String password) throws DatosInvalidosException {
        Validador.validarPassword(password);
        this.password = password;
    }

    public boolean verificarPassword(String passwordIngresada) {
        return this.password.equals(passwordIngresada);
    }

    public abstract void mostrarInfo();
    public abstract void accionEspecial();
}