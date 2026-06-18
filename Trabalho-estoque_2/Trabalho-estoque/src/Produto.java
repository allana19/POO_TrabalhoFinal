import com.fasterxml.jackson.annotation.JsonCreator; // Uma anotação aplicada em cima de um construtor ou de um método estático
import com.fasterxml.jackson.annotation.JsonProperty; // Ela faz o mapeamento de/para nos JSON

public class Produto {
    private String nome;
    private int quantidade;
    private double preco;

    // Construtor Vazio -> obrigatório para o Jackson
    public Produto() {
    }

    public Produto(String nome, int quantidade, double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Getters e Setters (obrigatórios para o Jackson)
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public String toString() {
        return String.format("Produto: %s | Qtd: %d | Preço: R$ %.2f", nome, quantidade, preco);
    }
}