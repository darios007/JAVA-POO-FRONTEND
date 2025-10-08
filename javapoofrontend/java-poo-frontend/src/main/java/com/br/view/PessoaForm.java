package com.br.pdvfrontend.view;

import com.br.pdvfrontend.model.Pessoa;
import com.br.pdvfrontend.service.PessoaService;

import javax.swing.*;
import java.awt.*;

public class PessoaForm extends JDialog {

    private JTextField txtNome;
    private JTextField txtEmail;
    private Pessoa pessoa;
    private PessoaService service = new PessoaService();
    private PessoaList parent;

    public PessoaForm(PessoaList parent, Pessoa pessoa) {
        this.parent = parent;
        this.pessoa = pessoa;

        setTitle(pessoa == null ? "Adicionar Pessoa" : "Editar Pessoa");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));
        setLocationRelativeTo(null);

        add(new JLabel("Nome:"));
        txtNome = new JTextField(pessoa != null ? pessoa.getNome() : "");
        add(txtNome);

        add(new JLabel("Email:"));
        txtEmail = new JTextField(pessoa != null ? pessoa.getEmail() : "");
        add(txtEmail);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarPessoa());
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void salvarPessoa() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();

        if (nome.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        if (pessoa == null) {
            pessoa = new Pessoa(null, nome, email);
        } else {
            pessoa.setNome(nome);
            pessoa.setEmail(email);
        }

        service.salvarPessoa(pessoa);
        parent.atualizarTabela();
        dispose();
    }
}
