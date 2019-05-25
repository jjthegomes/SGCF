package br.com.fontejr.sgcc.domain.model;

        import lombok.Data;
        import lombok.EqualsAndHashCode;
        import lombok.experimental.Accessors;
        import javax.persistence.Entity;
        import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
public class Cliente extends Usuario implements Serializable {
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
    private String situacao; //okay, warning, danger
    private String estadoCivil;
    private String nomeSocial;

}
