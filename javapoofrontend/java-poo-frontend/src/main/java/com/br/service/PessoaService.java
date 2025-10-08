package com.br.pdvfrontend.service;

import com.br.pdvfrontend.model.Pessoa;
import com.br.pdvfrontend.util.HttpClient;
import java.util.ArrayList;
import java.util.List;

public class PessoaService {
    private final String BASE_URL = "http://localhost:8080/pessoas"; // backend

    // Simulação local (caso não tenha backend)
    private static final List<Pessoa> fakeDB = new ArrayList<>();
    private static Long counter = 1L;

    public List<Pessoa> listarPessoas() {
        return new ArrayList<>(fakeDB);
    }

    public boolean salvarPessoa(Pessoa p) {
        if (p.getId() == null) {
            p.setId(counter++);
            fakeDB.add(p);
        } else {
            for (int i = 0; i < fakeDB.size(); i++) {
                if (fakeDB.get(i).getId().equals(p.getId())) {
                    fakeDB.set(i, p);
                    break;
                }
            }
        }
        return true;
    }

    public boolean deletarPessoa(Long id) {
        return fakeDB.removeIf(p -> p.getId().equals(id));
    }
}
