package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo<T> primerNodo;
    private int longitudLista;

    private class Nodo<T> {
        T valor;
        Nodo<T> sig;
        Nodo<T> ant;
        
        Nodo(T v){
            valor = v;
            sig = null;
            ant = null;
        }
    }

    public ListaEnlazada() {
        this.longitudLista = 0;
        this.primerNodo = null;
    }

    public int longitud() {
        return longitudLista;
    }

    public void agregarAdelante(T elem) {
        Nodo<T> nuevoNodo = new Nodo<T>(elem);
        if(this.primerNodo != null){    
            this.primerNodo.ant = nuevoNodo;
        }
        nuevoNodo.sig = this.primerNodo;
        primerNodo = nuevoNodo;
        longitudLista += 1;
    }

    public void agregarAtras(T elem) {
        Nodo<T> nuevoNodo = new Nodo<T>(elem);
        if (primerNodo == null){
            primerNodo = nuevoNodo;
        } else{
            Nodo<T> actualNodo = primerNodo;
            while(actualNodo.sig != null){
                actualNodo = actualNodo.sig;
            }
            nuevoNodo.ant = actualNodo;
            actualNodo.sig = nuevoNodo;

        }
        longitudLista += 1;
    }

    public T obtener(int i) {
        Nodo<T> actualNodo = primerNodo;
        int j = 0;

        while(j!=i){
            actualNodo = actualNodo.sig;
            j++;
        }

        return actualNodo.valor;
    }

    public void eliminar(int i) {
        Nodo<T> ant = primerNodo;
        Nodo<T> actualNodo = primerNodo;
        Nodo<T> sig = primerNodo;
        int j = 0;

        while(j!=i){
            ant = actualNodo;
            actualNodo = actualNodo.sig;
            sig = actualNodo.sig;
            j++;
        }

        if(i==0) {
            primerNodo = actualNodo.sig;
            if(primerNodo != null){
                primerNodo.ant = null;
            }
        } else {
            ant.sig = sig;
            if(sig != null){
                sig.ant = ant.sig;
            }
        }
        longitudLista -= 1;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo<T> actualNodo = primerNodo;
        int j = 0;

        while(j!=indice){
            actualNodo = actualNodo.sig;
            j++;
        }
        actualNodo.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {

        int i = 0;
        
        while (i < lista.longitud()) {
            this.agregarAtras(lista.obtener(i));
            i++;
        }
        
     }
    
    @Override
    public String toString() {
        String res = "[";
        Nodo<T> actual = primerNodo;
        for (int i = 0; i < longitudLista; i++) {
            res += (i + 1 < longitudLista ? actual.valor + ", " : actual.valor);
            actual = actual.sig;
        }
        res += "]";
        return res;
    }

    private class ListaIterador implements Iterador<T> {
        int indicador;

        ListaIterador(){
            indicador = 0;
        }

        public boolean haySiguiente() {
            return indicador != longitudLista;
        }
        
        public boolean hayAnterior() {
	        return indicador - 1 != -1;
        }

        public T siguiente() {
            T res = obtener(indicador);
            indicador++;
            return res;
        }
        

        public T anterior() {
            indicador--;
	        T res = obtener(indicador);
            return res;
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
