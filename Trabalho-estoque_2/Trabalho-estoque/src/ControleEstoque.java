// Biblioteca Jackson -> Converte objetos para arquivos JSON

import com.fasterxml.jackson.databind.ObjectMapper; // Mapeador de objetos
import com.fasterxml.jackson.databind.type.CollectionType; // Auxilia a entender tipos complexos
import java.io.File; // Verifica o arquivo na sua máquina
import java.io.IOException; // Verifica se o arquivo possui algum erro 'externo'
import java.util.ArrayList;
import java.util.List; // Cria o espaço físico na memória onde os produtos serão guardados


public class ControleEstoque {
    private List<Produto> produtos;
    private final String CAMINHO_ARQUIVO = "estoque.json";
    private final ObjectMapper mapper;

    public ControleEstoque() {
        this.mapper = new ObjectMapper();
        this.produtos = new ArrayList<>();
        carregarEstoqueDoJson(); // Carrega os dados assim que o controle é iniciado
    }

    // Adiciona um novo produto e salva no JSON
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto adicionado com sucesso!");
        salvarEstoqueNoJson();
    }

    // Atualiza a quantidade de um produto existente e salva no JSON
    public void atualizarQuantidade(String nome, int novaQuantidade) {
        boolean encontrado = false;
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                p.setQuantidade(novaQuantidade);
                encontrado = true;
                System.out.println("Quantidade atualizada com sucesso!");
                salvarEstoqueNoJson();
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto não encontrado no estoque.");
        }
    }

    // Atualiza o preço de um produto existente e salva no JSON
    public void atualizarPreco(String nome, double novoPreco) {
        boolean encontrado = false;
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                p.setPreco(novoPreco);
                encontrado = true;
                System.out.println("Preço atualizado com sucesso no estoque.");
                salvarEstoqueNoJson();
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto não encontrado no estoque");
        }
    }

    // Lista todos os produtos na tela
    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio.");
            return;
        }
        System.out.println("\n--- ITENS NO ESTOQUE ---");
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    // Salva a lista atual de produtos no arquivo estoque.json
    private void salvarEstoqueNoJson() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CAMINHO_ARQUIVO), produtos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar no arquivo JSON: " + e.getMessage());
        }
    }

    // Carrega a lista de produtos a partir do arquivo estoque.json (se ele existir)
    private void carregarEstoqueDoJson() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de estoque não encontrado. Iniciando estoque vazio.");
            return;
        }

        try {
            // Configura o Jackson para entender que queremos ler uma Lista de Produtos: List<Produto>
            CollectionType tipoLista = mapper.getTypeFactory().constructCollectionType(List.class, Produto.class);
            this.produtos = mapper.readValue(arquivo, tipoLista);
            System.out.println("Estoque carregado do arquivo JSON com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo JSON: " + e.getMessage());
            this.produtos = new ArrayList<>();
        }
    }
}