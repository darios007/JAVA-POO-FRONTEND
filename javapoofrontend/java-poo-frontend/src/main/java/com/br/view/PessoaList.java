package com.br.pdvfrontend.view;

import com.br.pdvfrontend.model.Pessoa;
import com.br.pdvfrontend.service.PessoaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PessoaList extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private PessoaService service = new PessoaService();

    public PessoaList() {
        setTitle("Lista de Pessoas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new Object[]{"ID", "Nome", "Email"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton add = new JButton("Adicionar");
        JButton edit = new JButton("Editar");
        JButton del = new JButton("Excluir");

        buttons.add(add);
        buttons.add(edit);
        buttons.add(del);
        add(buttons, BorderLayout.SOUTH);

        add.addActionListener(e -> new PessoaForm(this, null).setVisible(true));
        edit.addActionListener(e -> editarPessoa());
        del.addActionListener(e -> excluirPessoa());

        atualizarTabela();
    }

    public void atualizarTabela() {
        model.setRowCount(0);
        List<Pessoa> pessoas = service.listarPessoas();
        for (Pessoa p : pessoas) {
            model.addRow(new Object[]{p.getId(), p.getNome(), p.getEmail()});
        }
    }

    private void editarPessoa() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Long id = Long.parseLong(model.getValueAt(row, 0).toString());
            String nome = model.getValueAt(row, 1).toString();
            String email = model.getValueAt(row, 2).toString();
            new PessoaForm(this, new Pessoa(id, nome, email)).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para editar!");
        }
    }

    private void excluirPessoa() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Long id = Long.parseLong(model.getValueAt(row, 0).toString());
            service.deletarPessoa(id);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir!");
        }
    }
}
