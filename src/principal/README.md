VC1 Funcionalidades, descripciones y datos 

Autenticación
- Login: Iniciar sesión como administrador (Email y Contraseña)
- Reiniciar contraseña: Recuperar cuenta (Email, Contraseña y Repetir contraseña)
- Logout: Cerrar sesión (Sí o Cancelar)

Gestión de cuentas
- Crear cuenta administrador: Dar alta de cuenta con Nombre, Apellido, Email, Contraseña, Repetir contraseña y País de nacimiento
- Crear cuenta tester: Dar alta de cuenta con Nombre, Apellido, Email, País de nacimiento, Contraseña por defecto y Perfil (Tester Junior, Tester Senior, Tester Líder)

Gestión de usuarios
- Ver usuarios: Ver usuarios registrados (Nombre, Apellido, Email, País, Perfil (Tester Junior/Senior/Líder o Administrador)
- (Derivado del anterior) Eliminar usuarios: Eliminar usuarios registrados en el sistema (Aceptar o Cancelar)
- Ver perfil de usuario: Ver detalles del perfil (Nombre, Apellido, Email, País, Perfil)
- (Derivado del anterior) Modificar datos de perfil de usuario: Cambiar detalles del perfil (Nombre, Apellido, Email, País, Perfil)


VC3 – Modelado con POO

Diagrama UML

mermaid



class Usuario
— nombre : String
— apellido : String
— email : String
— pais : String
— perfil : String
— password : String
+ getNombre() : String
+ getApellido() : String
+ getEmail() : String
+ getPais() : String
+ getPerfil() : String
+ getPassword() : String
+ setNombre(), setApellido(), …
+ mostrarInfo() : void

class Admin
— nivelAcceso : String
+ getNivelAcceso() : String
+ setNivelAcceso() : void
+ mostrarInfo() : void

class Tester
— areaAsignada : String
+ getAreaAsignada() : String
+ setAreaAsignada() : void
+ mostrarInfo() : void

class SistemaUsuarios 
— usuarios : ArrayList<Usuario>
+ mostrarMenu() : void
– loginUsuario() : void
– registrarUsuario() : void


