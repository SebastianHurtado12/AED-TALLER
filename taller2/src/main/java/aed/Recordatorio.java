package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha);
        this.horario = new Horario(horario);
    }
    public Recordatorio(Recordatorio recordatorio){
        this.mensaje = recordatorio.mensaje();
        this.horario = recordatorio.horario();
        this.fecha = recordatorio.fecha();
    }

    public Horario horario() {
        return new Horario(this.horario);
    }

    public Fecha fecha() {
        return new Fecha(this.fecha);
    }

    public String mensaje() {
        return this.mensaje;
    } 

    @Override
    public String toString() {
        // mensaje @ fecha horario
        return this.mensaje + " @ " + this.fecha.toString() + " " + this.horario.toString();
    }

    @Override
    public boolean equals(Object otro) {
        boolean otroEsNull = (otro == null);

		boolean claseDistinta = otro.getClass() != this.getClass();

		if (otroEsNull || claseDistinta) {
			return false;
		}
        
        Recordatorio otroRecordatorio = (Recordatorio) otro;

        return (this.mensaje == otroRecordatorio.mensaje()) && this.fecha.equals(otroRecordatorio.fecha()) && this.horario.equals(otroRecordatorio.horario());
    }

}
