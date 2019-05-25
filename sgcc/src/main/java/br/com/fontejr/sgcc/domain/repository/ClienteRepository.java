package br.com.fontejr.sgcc.domain.repository;

        import br.com.fontejr.sgcc.domain.model.Cliente;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Component;
        import org.springframework.stereotype.Repository;

        import java.util.Optional;

@Component
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
        Iterable<Cliente> findByNomeContaining(String nome);

        @Override
        Iterable<Cliente> findAll();

        Optional<Cliente> findByEmailAndSenha(String email, String senha);
}