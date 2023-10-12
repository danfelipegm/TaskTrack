package co.edu.upb.Vista;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Crear una cuenta de usuario");
        System.out.print("Ingrese un nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese una contraseña: ");
        String contrasena = scanner.nextLine();

        Usuario usuario = new Usuario(nombreUsuario, contrasena);

        System.out.println("Cuenta de usuario creada con éxito.");
        System.out.println("Inicie sesión para verificar la contraseña.");

        System.out.print("Ingrese su nombre de usuario: ");
        String inputUsuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String inputContrasena = scanner.nextLine();

        if(usuario.getNombreUsuario().equals(inputUsuario) && usuario.verificarContrasena(inputContrasena)){
            System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + usuario.getNombreUsuario() + "!");
        }else{
            System.out.println("Inicio de sesión fallido. Nombre de usuario o contraseña incorrectos.");
        }
        scanner.close();
    }
       
}

