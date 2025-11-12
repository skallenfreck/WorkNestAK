package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

    // Método para validar si un correo electrónico tiene un formato correcto
    public static boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Método para validar si una contraseña tiene al menos 8 caracteres
    // y contiene al menos un número, una letra mayúscula y un carácter especial
    public static boolean validarClave(String clave) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(clave);
        return matcher.matches();
    }

    // Método para validar si una cadena está vacía
    public static boolean esVacio(String cadena) {
        return cadena == null || cadena.trim().isEmpty();
    }
}
