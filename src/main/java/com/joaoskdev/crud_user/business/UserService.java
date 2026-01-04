package com.joaoskdev.crud_user.business;

import com.joaoskdev.crud_user.infrastructure.entitys.User;
import com.joaoskdev.crud_user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(User usuario){
        repository.saveAndFlush(usuario);
    }

    public User buscarUsuarioPorEmail(String email){
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }

    public void deletarUsaurioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Long id, User usuario){
        User usuarioEntity = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Id não encontrado")
        );
        User usuarioAtualizado = User.builder()
                .email(usuario.getEmail() != null ?
                        usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ?
                        usuario.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .build();

        repository.saveAndFlush(usuarioAtualizado);
    }

}
