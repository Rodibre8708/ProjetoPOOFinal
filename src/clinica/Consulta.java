package clinica;

public class Consulta {
    private String data;
    private Paciente paciente;
    private Medico medico;

    public Consulta(String data, Paciente paciente, Medico medico) {
        this.data = data;
        this.paciente = paciente;
        this.medico = medico;        
    }   

    public String getData() {
        return data;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    
}