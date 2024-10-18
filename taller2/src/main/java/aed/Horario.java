package aed;

public class Horario {
    private int hora;
    private int minutos;
    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }
    public Horario(Horario horario){
        this.hora = horario.hora();
        this.minutos = horario.minutos();
    }
    public int hora() {
        return this.hora;
    }

    public int minutos() {
        return this.minutos;
    }

    @Override
    public String toString() {
        return this.hora + ":" + (this.minutos < 10 && this.minutos > 0 ? "0" + this.minutos:this.minutos);    
    }

    @Override
    public boolean equals(Object otro) {

        boolean otroEsNull = (otro == null);

		boolean claseDistinta = otro.getClass() != this.getClass();

		if (otroEsNull || claseDistinta) {
			return false;
		}
        
        Horario otroHorario = (Horario) otro;

        return (this.hora ==  otroHorario.hora()) && (this.minutos == otroHorario.minutos());
    }

}
