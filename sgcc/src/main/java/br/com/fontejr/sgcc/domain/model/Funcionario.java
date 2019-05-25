package br.com.fontejr.sgcc.domain.model;

        import lombok.Data;
        import lombok.experimental.Accessors;
        import javax.persistence.Entity;
        import java.io.Serializable;


@Data
@Entity
@Accessors(chain = true)
public class Funcionario extends Usuario implements Serializable {
        private String cpf;
        private String rg;
        private String sexo;
        private String dataNascimento;
        private String cep;
        private String estado;
        private String cidade;
        private String logradouro;
        private String bairro;
        private String numero;
        private String complemento;

        private String telefone;
        private String celular;
        private String estadoCivil;
}