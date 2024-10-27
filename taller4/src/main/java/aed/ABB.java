package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo _raiz;
    private int _cardinal;

    private class Nodo {
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;
        
        Nodo(T v){
            valor = v;
            izq = null;
            der = null;
            padre = null;
        }
    }

    public ABB() {
        this._cardinal = 0;
        this._raiz = null;
    }
    //constructor por copia
    public ABB(ABB<T> otroAbb) {
        this._cardinal = otroAbb._cardinal;
        this._raiz = otroAbb._raiz;
    }

    public int cardinal() {
        return this._cardinal;
    }

    public T minimo(){
        Nodo actual = _raiz;
        while(actual.izq != null){
            actual = actual.izq;
        }
        return actual.valor;
    }

    public T maximo(){
        Nodo actual = _raiz;
        while(actual.der != null){
            actual = actual.der;
        }
        return actual.valor;
    }

    public void insertar(T elem){

        if(!pertenece(elem)){
            if(_raiz == null){
                _raiz = new Nodo(elem);
                this._cardinal  += 1;
            } else { 
                _raiz = insertar_recusivo(this._raiz, null,  elem);
            }
        }
    }

    private Nodo insertar_recusivo(Nodo actual, Nodo ant, T elem){

        //caso base
        if(actual == null) {
            //creacion de nuevo nodo
            Nodo nuevo_nodo = new Nodo(elem);
            //asignar padre a nuevo_nodo
            nuevo_nodo.padre = ant;
            actual = nuevo_nodo;
            this._cardinal += 1;
        } 
        //paso recursivo
        else {
            if(actual.valor.compareTo(elem) > 0){
                actual.izq = insertar_recusivo(actual.izq,actual, elem);
            } else {
                actual.der = insertar_recusivo(actual.der,actual, elem);
            }
        }
        return actual;
    }

    public boolean pertenece(T elem){
        return pertenece_recursivo(_raiz, elem);   
    }

    private boolean pertenece_recursivo(Nodo actual, T elem){
        // nodo vacio
        boolean encontrado = false;
        if (actual == null){ encontrado = false;}
        // nodo con el valor de la busqueda
        else if (actual.valor.compareTo(elem) == 0) { encontrado = true;}
        else{
            
            if(actual.valor.compareTo(elem) > 0){
                encontrado = pertenece_recursivo(actual.izq, elem);
            } else { 
                encontrado = pertenece_recursivo(actual.der, elem);
            }
        }
        return encontrado;
    }

    public void eliminar(T elem){
        if (pertenece(elem)) {
            Nodo actual = _raiz;
            _raiz = eliminar_recursion(actual,elem);
            this._cardinal -= 1;
        }
    }
    private Nodo eliminar_recursion(Nodo actual, T elem){
        //valor con nodo matchea con elem
        if (actual.valor.compareTo(elem) == 0){
            //caso 1 - nodo sin hijos
            if (actual.izq == null && actual.der == null) {
                return null;
            } else{
                // caso 2 - nodo con un descendiente
                if(actual.der == null){
                    actual.izq.padre = actual.padre;
                    return actual.izq;
                } else if (actual.izq == null) {
                    actual.der.padre = actual.padre;
                    return actual.der;
                }
                // caso 3 - nodo con 2 hijos
                Nodo sucesor = sucesor(actual); 
                actual.der.padre = sucesor;
                actual.izq.padre = sucesor;
                eliminar_recursion(actual, sucesor.valor);

                sucesor.der = actual.der;
                sucesor.izq = actual.izq;
                sucesor.padre = actual.padre;

               
                
                return sucesor;
            }
        } else {
            //paso recursivo
            if(actual.valor.compareTo(elem) > 0){
                actual.izq = eliminar_recursion(actual.izq, elem);
            } else {
                actual.der = eliminar_recursion(actual.der, elem);
            }
        }
        return actual;
        
    }
    private Nodo sucesor(Nodo nodo){
        Nodo res;
        if (nodo.der != null){
            res = nodo.der;
            while(res.izq != null){
                res = res.izq;
            }
        } else {
            res = nodo.padre;
            while (res != null && res.der != null && res.der.valor.equals(nodo.valor)) {
                res = res.padre;
            }
        }
        return res;
    }
    public String toString(){
        Iterador<T> iterador = this.iterador();
        String res = "{";
        int i = 1;
        
        while(i <= _cardinal){
            res += (i != _cardinal ) ? iterador.siguiente() + "," : iterador.siguiente();
            i += 1;
        }
        
        res += "}";
        
        return res;
    }

    private Nodo minimo_nodo(){
        Nodo actual = _raiz;
        if(_raiz == null){
            return null;
        }
        while(actual.izq != null){
            actual = actual.izq;
        }
        return actual;
    }
    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        ABB_Iterador(){
            _actual = minimo_nodo();
        }

        public boolean haySiguiente() {       
            boolean hay_siguiente = false;
            if (_actual != null && _actual.valor.compareTo(maximo()) != 0){
                hay_siguiente = true;
            }
            return hay_siguiente;
        }
    
        public T siguiente() {
            T res =_actual.valor;
            _actual = sucesor(_actual); 
            return res;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
