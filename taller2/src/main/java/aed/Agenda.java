package aed;

public class Agenda {
    private Fecha fecha;
    private ArregloRedimensionableDeRecordatorios recordatorios;
    public Agenda(Fecha fechaActual) {
        this.fecha = new Fecha(fechaActual);
        this.recordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        this.recordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
    
        String mensaje = this.fecha.toString() + "\n" + "=====\n";
        for (int i = 0; i < recordatorios.longitud(); i++) {
            mensaje += (recordatorios.obtener(i).fecha().dia() == this.fecha.dia() && recordatorios.obtener(i).fecha().mes() == this.fecha.mes()) 
                        ? recordatorios.obtener(i).toString() + "\n" : "";
        }
        return mensaje;
    }

    public void incrementarDia() {
        this.fecha.incrementarDia();
    }

    public Fecha fechaActual() {
        return new Fecha(this.fecha);
    }

}
