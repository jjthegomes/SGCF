package br.com.fontejr.sgcc.domain.repository;

        import br.com.fontejr.sgcc.domain.model.Funcionario;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Component;
        import org.springframework.stereotype.Repository;

        import java.util.List;
        import java.util.Optional;
@Component
@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

        @Query(value = "select distinct estado from funcionario", nativeQuery = true)
        List<String> findDistinctByEstado();

        Optional<Funcionario> findByEmailAndSenha(String email, String senha);

}