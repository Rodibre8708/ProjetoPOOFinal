package evento;

import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nome;
    private String data;
    private Local local;
    private int capacidade;
    private List<Participante> participantes;

    public Evento(String nome, String data, Local local, int capacidade) {
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.capacidade = capacidade;
        this.participantes = new ArrayList<>();
    }

    public boolean registrarParticipante(Participante participante) {
        if (participantes.size() < capacidade) {
            participantes.add(participante);
            return true;
        }
        System.out.println("Evento lotado!");
        return false;
    }

    public void gerarRelatorio() {
        System.out.println("Evento: " + nome);
        for (Participante p : participantes) {
            System.out.println("Participante: " + p.getNome());
        }
    }
    
    public String getNome() {
        return nome;
    }
}