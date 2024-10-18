package aed;

class Funciones {
    int cuadrado(int x) {
        return x*x;
    }

    double distancia(double x, double y) {
        double res = Math.sqrt(x*x + y*y);
        return res;
    }

    boolean esPar(int n) {
        return n % 2 == 0;
    }

    boolean esBisiesto(int n) {
        return (n % 4 == 0 && n % 100 != 0) || (n % 400 == 0);
    }

    int factorialIterativo(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }

        return res;
    }

    int factorialRecursivo(int n) {
        if (n == 0 || n == 1){
            return 1;
        }

        return n * factorialRecursivo(n-1);
    }

    boolean esPrimo(int n) {
        int cont = 0;
        for (int i = 1; i <= n; i++) {
            if(n % i == 0) {
                cont ++;
            }
        }

        return cont==2;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for (int num : numeros) {
            res += num;
        }
        
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int pos_busqueda = 0;
        for (int i = 0; i < numeros.length; i++) {
            if(numeros[i]==buscado){
                pos_busqueda = i;
            }
        }
        return pos_busqueda;
    }

    boolean tienePrimo(int[] numeros) {
        for (int num : numeros) {
            if (esPrimo(num)){
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        for (int num : numeros) {
            if (!esPar(num)){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {    
        if(s1.length() > s2.length()){
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)){
                return false;
            }
        }
        return true;
    }

    boolean esSufijo(String s1, String s2) {

        int cant_letras_s1 = s1.length() - 1;
        int cant_letras_s2 = s2.length() - 1;
        if(s1.length() > s2.length()){
            return false;
        }
        for (int i = s1.length() - 1; i > 0; i--) {
            if(s1.charAt(cant_letras_s1 - i) != s2.charAt(cant_letras_s2 - i)){
                return false;
            }
        }
        return true;
    }
}
