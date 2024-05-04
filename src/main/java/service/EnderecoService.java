package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Endereco;
import repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco getEnderecoById(Long id) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
        return optionalEndereco.orElse(null);
    }

    public Endereco addEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco updateEndereco(Long id, Endereco endereco) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
        if (optionalEndereco.isPresent()) {
            endereco.setId(id);
            return enderecoRepository.save(endereco);
        } else {
            return null;
        }
    }

    public void deleteEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }
}
