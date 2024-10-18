package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] recordatarios;
    public ArregloRedimensionableDeRecordatorios() {
        this.recordatarios = new Recordatorio[0];
    }

    public int longitud() {
        return recordatarios.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] nuevoRecordatorios = new Recordatorio[this.recordatarios.length + 1];
        for (int j = 0; j < this.recordatarios.length; j++) {
            nuevoRecordatorios[j] = new Recordatorio(this.recordatarios[j]);
        }
        nuevoRecordatorios[recordatarios.length] = new Recordatorio(i);
        this.recordatarios = nuevoRecordatorios;
        
    }

    public Recordatorio obtener(int i) {
        return new Recordatorio(recordatarios[i]);
    }

    public void quitarAtras() {
        Recordatorio[] nuevoRecordatorios = new Recordatorio[this.recordatarios.length - 1];
        for (int i = 0; i < recordatarios.length - 1; i++) {
            nuevoRecordatorios[i] = new Recordatorio(recordatarios[i]);   
        }
        this.recordatarios = nuevoRecordatorios;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        this.recordatarios[indice] = new Recordatorio(valor);
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this.recordatarios = new Recordatorio[vector.longitud()];
        for (int i = 0; i < vector.longitud(); i++) {
            this.recordatarios[i] = vector.obtener(i);
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        return new ArregloRedimensionableDeRecordatorios(this);
    }
}
