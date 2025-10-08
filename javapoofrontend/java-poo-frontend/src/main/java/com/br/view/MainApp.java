package com.br.pdvfrontend.view;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PessoaList().setVisible(true));
    }
}
