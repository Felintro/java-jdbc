package view;

/*
 * @author allfel
 *
 * Data: 02/02/2023
 */

import controller.CategoriaController;
import controller.ProdutoController;
import model.Categoria;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProdutoCategoriaFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel labelNome, labelDescricao, labelCategoria, labelNomeCategoria;
    private JTextField textoNome, textoDescricao, textoNomeCategoria;
    private JComboBox<Categoria> comboCategoria;
    private JButton botaoSalvarProduto, botaoAlterarProduto, botaoLimparProduto, botarApagarProduto, botaoSalvarCategoria;
    private JTable tabela;
    private DefaultTableModel modelo;
    private ProdutoController produtoController;
    private CategoriaController categoriaController;

    public ProdutoCategoriaFrame() {
        super("Produtos");
        Container container = getContentPane();
        setLayout(null);

        this.categoriaController = new CategoriaController();
        this.produtoController = new ProdutoController();

        labelNome = new JLabel("Nome do Produto");
        labelDescricao = new JLabel("Descrição do Produto");
        labelCategoria = new JLabel("Categoria do Produto");
        labelNomeCategoria = new JLabel("Nome da Categoria");

        labelNome.setBounds(10, 10, 240, 15);
        labelDescricao.setBounds(10, 50, 240, 15);
        labelCategoria.setBounds(10, 90, 240, 15);
        labelNomeCategoria.setBounds(400, 10, 240, 15);

        labelNome.setForeground(Color.BLACK);
        labelDescricao.setForeground(Color.BLACK);
        labelCategoria.setForeground(Color.BLACK);
        labelNomeCategoria.setForeground(Color.BLACK);

        container.add(labelNome);
        container.add(labelDescricao);
        container.add(labelCategoria);
        container.add(labelNomeCategoria);

        textoNome = new JTextField();
        textoDescricao = new JTextField();
        textoNomeCategoria = new JTextField();
        comboCategoria = new JComboBox<>();

        atualizaComboBoxCategoria();

        textoNome.setBounds(10, 25, 265, 20);
        textoDescricao.setBounds(10, 65, 265, 20);
        textoNomeCategoria.setBounds(400, 25, 265, 20);
        comboCategoria.setBounds(10, 105, 265, 20);

        container.add(textoNome);
        container.add(textoDescricao);
        container.add(textoNomeCategoria);
        container.add(comboCategoria);

        botaoSalvarProduto = new JButton("Salvar Produto");
        botaoSalvarCategoria = new JButton("Salvar Categoria");
        botaoLimparProduto = new JButton("Limpar");

        botaoSalvarProduto.setBounds(10, 145, 80, 20);
        botaoSalvarCategoria.setBounds(400, 50, 160, 20);
        botaoLimparProduto.setBounds(100, 145, 80, 20);

        container.add(botaoSalvarProduto);
        container.add(botaoLimparProduto);
        container.add(botaoSalvarCategoria);

        tabela = new JTable();
        modelo = (DefaultTableModel) tabela.getModel();

        modelo.addColumn("Identificador do Produto");
        modelo.addColumn("Nome do Produto");
        modelo.addColumn("Descrição do Produto");

        preencherTabela();

        tabela.setBounds(10, 185, 760, 300);
        container.add(tabela);

        botarApagarProduto = new JButton("Excluir");
        botaoAlterarProduto = new JButton("Alterar");

        botarApagarProduto.setBounds(10, 500, 80, 20);
        botaoAlterarProduto.setBounds(100, 500, 80, 20);

        container.add(botarApagarProduto);
        container.add(botaoAlterarProduto);

        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);

        botaoSalvarProduto.addActionListener(
            e -> {
                salvarProduto();
                limparTabela();
                preencherTabela();
            }
        );

        botaoLimparProduto.addActionListener(
            e -> limpar()
        );

        botarApagarProduto.addActionListener(
            e -> {
                deletarProduto();
                limparTabela();
                preencherTabela();
            }
        );

        botaoAlterarProduto.addActionListener(
            e -> {
                alterar();
                limparTabela();
                preencherTabela();
            }
        );

        botaoSalvarCategoria.addActionListener(
            e -> {
                salvarCategoria();
                atualizaComboBoxCategoria();
            }
        );
    }

    private void atualizaComboBoxCategoria() {
        comboCategoria.removeAllItems();
        comboCategoria.addItem(new Categoria(0, "Selecione"));
        List<Categoria> categorias = this.listarCategoria();
        for(Categoria categoria : categorias) {
            comboCategoria.addItem(categoria);
        }
    }

    private void limparTabela() {
        modelo.getDataVector().clear();
    }

    private void alterar() {
        Object objetoDaLinha = modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
        if(objetoDaLinha instanceof Integer) {
            Integer id = (Integer) objetoDaLinha;
            String nome = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
            String descricao = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);
            this.produtoController.alterar(nome, descricao, id);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    private void deletarProduto() {
        Object objetoDaLinha = modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
        if(objetoDaLinha instanceof Integer) {
            Integer id = (Integer) objetoDaLinha;
            this.produtoController.deletar(id);
            modelo.removeRow(tabela.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    private void preencherTabela() {
        List<Produto> produtos = listarProduto();
        try {
            for(Produto produto : produtos) {
                modelo.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getDescricao()});
            }
        } catch(Exception e) {
            throw e;
        }
    }

    private List<Categoria> listarCategoria() {
        return this.categoriaController.listar();
    }

    private void salvarProduto() {
        if(!textoNome.getText().equals("") && !textoDescricao.getText().equals("")) {
            Produto produto = new Produto(textoNome.getText(), textoDescricao.getText());
            Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
            produto.setIdCategoria(categoria.getId());
            this.produtoController.salvar(produto);
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            this.limpar();
        } else {
            JOptionPane.showMessageDialog(this, "Nome e Descrição devem ser informados.");
        }
    }

    private void salvarCategoria() {
        if(!textoNomeCategoria.getText().equals("")) {
            Categoria categoria = new Categoria();
            categoria.setNome(textoNomeCategoria.getText());
            this.categoriaController.salvar(categoria);
            JOptionPane.showMessageDialog(this, "Categoria salva com sucesso!");
            this.limparCategoria();
        } else {
            JOptionPane.showMessageDialog(this, "Nome deve ser informado.");
        }
    }

    private List<Produto> listarProduto() {
        return this.produtoController.listar();
    }

    private void limpar() {
        this.textoNome.setText("");
        this.textoDescricao.setText("");
        this.comboCategoria.setSelectedIndex(0);
    }

    private void limparCategoria() {
        this.textoNomeCategoria.setText("");
    }

}