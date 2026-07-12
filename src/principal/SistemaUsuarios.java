package principal;

import excepciones.DatosInvalidosException;
import excepciones.EmailDuplicadoException;
import excepciones.UsuarioNoEncontradoException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaUsuarios {

    private final UsuarioRepositorio repositorio;
    private final Scanner scanner;
    private Usuario usuarioActual; // usuario con sesión iniciada (null = sin sesión)

    public SistemaUsuarios() {
        repositorio = UsuarioRepositorio.getInstance();
        scanner = new Scanner(System.in);
        cargarUsuariosDePrueba();
    }

    private void cargarUsuariosDePrueba() {
        try {
            repositorio.agregar(new Admin("Yanis", "Correa", "yaniscorrea@gmail.com", "Uruguay", "123456"));
            repositorio.agregar(new Admin("Leonardo", "Pérez", "leonardoperez@gmail.com", "Uruguay", "123456"));
            repositorio.agregar(new Tester("Paola", "Holzmann", "paola291187@gmail.com", "Uruguay", "Abcde1"));
        } catch (DatosInvalidosException | EmailDuplicadoException e) {
            // No debería ocurrir con estos datos fijos, pero se informa igual
            System.out.println("Error al cargar datos iniciales: " + e.getMessage());
        }
    }

    public void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("Sesión: " + (usuarioActual == null ? "sin iniciar"
                    : usuarioActual.getEmail() + " (" + usuarioActual.getClass().getSimpleName() + ")"));
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar usuario administrador");
            System.out.println("3. Alta usuario Tester");
            System.out.println("4. Listar usuarios");
            System.out.println("5. Buscar usuario por email");
            System.out.println("6. Cerrar sesión");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción válida: ");

            try {
                opcion = leerEntero();

                switch (opcion) {
                    case 1 -> loginUsuario();
                    case 2 -> registrarAdmin();
                    case 3 -> registrarTester();
                    case 4 -> listarUsuarios();
                    case 5 -> buscarUsuario();
                    case 6 -> cerrarSesion();
                    case 7 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción inválida. Elija un número entre 1 y 7.");
                }
            } catch (DatosInvalidosException | EmailDuplicadoException | UsuarioNoEncontradoException e) {
                // Errores de lógica de negocio: se informa y el sistema continúa
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalStateException e) {
                // Errores de estado/permiso (ej: acción que requiere sesión de admin)
                System.out.println("Operación no permitida: " + e.getMessage());
            } catch (Exception e) {
                // Cualquier otro error no previsto: no se cae el programa
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }

        } while (opcion != 7);

        scanner.close();
    }

    // Lee un entero del menú; ante entrada no numérica lanza InputMismatchException
    // ya "limpia" (consume la línea inválida) para no dejar el Scanner en mal estado.
    private int leerEntero() {
        try {
            int valor = scanner.nextInt();
            scanner.nextLine();
            return valor;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Debe ingresar un número. Intente nuevamente.");
            return -1;
        }
    }

    private void loginUsuario() throws UsuarioNoEncontradoException {
        System.out.println("=== Iniciar sesión ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Usuario u = repositorio.buscarPorEmail(email); // lanza UsuarioNoEncontradoException si no existe

        if (!u.verificarPassword(password)) {
            System.out.println("Contraseña incorrecta.");
            return;
        }

        usuarioActual = u;
        System.out.println("Login exitoso. Bienvenido/a " + u.getNombre() + ".");
        u.mostrarInfo();
        u.accionEspecial();
    }

    private void cerrarSesion() {
        if (usuarioActual == null) {
            System.out.println("No hay ninguna sesión iniciada.");
        } else {
            System.out.println("Sesión de " + usuarioActual.getEmail() + " cerrada.");
            usuarioActual = null;
        }
    }

    private void registrarAdmin() throws DatosInvalidosException, EmailDuplicadoException {
        System.out.println("=== Registro de Administrador ===");
        DatosUsuario datos = pedirDatosBasicos();

        Admin nuevo = new Admin(datos.nombre, datos.apellido, datos.email, datos.pais, datos.password);
        repositorio.agregar(nuevo);
        System.out.println("Administrador registrado correctamente.");
    }

    private void registrarTester() throws DatosInvalidosException, EmailDuplicadoException {
        // Regla de negocio: solo un admin con sesión iniciada puede dar de alta testers
        if (!(usuarioActual instanceof Admin)) {
            throw new IllegalStateException("Debe iniciar sesión como administrador para registrar testers.");
        }

        System.out.println("=== Alta de Tester ===");
        DatosUsuario datos = pedirDatosBasicos();

        Tester nuevo = new Tester(datos.nombre, datos.apellido, datos.email, datos.pais, datos.password);
        nuevo.setNivelTester(elegirNivelTester());
        repositorio.agregar(nuevo);
        System.out.println("Tester registrado correctamente.");
    }

    // Pequeño record interno para transportar los datos crudos del formulario
    private record DatosUsuario(String nombre, String apellido, String email, String pais, String password) {}

    private DatosUsuario pedirDatosBasicos() throws DatosInvalidosException, EmailDuplicadoException {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        Validador.validarCampoObligatorio(nombre, "Nombre");

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        Validador.validarCampoObligatorio(apellido, "Apellido");

        System.out.print("Email: ");
        String email = scanner.nextLine();
        Validador.validarEmail(email);

        if (repositorio.existeEmail(email)) {
            throw new EmailDuplicadoException(email);
        }

        System.out.print("Contraseña (mínimo " + Validador.getPasswordLongitudMinima() + " caracteres): ");
        String password = scanner.nextLine();
        Validador.validarPassword(password);

        System.out.print("Repetir contraseña: ");
        String repetir = scanner.nextLine();
        if (!password.equals(repetir)) {
            throw new DatosInvalidosException("Las contraseñas no coinciden.");
        }

        System.out.print("País: ");
        String pais = scanner.nextLine();
        Validador.validarCampoObligatorio(pais, "País");

        return new DatosUsuario(nombre, apellido, email, pais, password);
    }

    private String elegirNivelTester() {
        int opcion;
        do {
            System.out.println("Nivel de tester:");
            System.out.println("1. Junior");
            System.out.println("2. Senior");
            System.out.println("3. Líder");
            System.out.print("Seleccione: ");
            opcion = leerEntero();
        } while (opcion < 1 || opcion > 3);

        return switch (opcion) {
            case 1 -> "Junior";
            case 2 -> "Senior";
            default -> "Líder";
        };
    }

    private void listarUsuarios() {
        System.out.println("=== Lista de Usuarios ===");
        if (repositorio.listarTodos().isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario u : repositorio.listarTodos()) {
            u.mostrarInfo();
        }
    }

    private void buscarUsuario() throws UsuarioNoEncontradoException {
        System.out.print("Ingrese email a buscar: ");
        String email = scanner.nextLine();
        Usuario u = repositorio.buscarPorEmail(email);
        System.out.println("Usuario encontrado:");
        u.mostrarInfo();
    }
}