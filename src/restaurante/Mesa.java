package restaurante; 

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private int numero;
    private boolean selecionada;
    private List<Pedido> pedidos;

    public Mesa(int numero) {
        this.numero = numero;
        this.selecionada = false;
        this.pedidos = new ArrayList<>();
    }

    public void ocuparMesa() {
        selecionada = true;
        System.out.println("Mesa " + numero + " foi selecionada.");
    }

    public void registrarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public double calcularTotal() {
        double total = 0;
        for (Pedido pedido : pedidos) {
            total += pedido.calcularTotal();
        }
        return total;
    }
}