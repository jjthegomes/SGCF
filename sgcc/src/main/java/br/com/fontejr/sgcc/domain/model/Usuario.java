package br.com.fontejr.sgcc.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Data
@MappedSuperclass
@Accessors(chain = true)
public class Usuario implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    @Column(unique=true)
    private String email;
    private String senha;

    public static boolean isLogado(HttpServletRequest request) {
        return request.getSession().getAttribute("usuario") != null;
    }

    public static boolean hasPermissao(HttpServletRequest request, String permissao) {
        return request.getSession().getAttribute("permissao") != null && request.getSession().getAttribute("permissao").equals(permissao);
    }

    public static boolean isAdministrador(HttpServletRequest request) {
        return (Usuario.isLogado(request) && Usuario.hasPermissao(request,"ADMIN"));
    }

    public static boolean isFuncionario(HttpServletRequest request) {
        return (Usuario.isLogado(request) && Usuario.hasPermissao(request,"FUNCIONARIO"));
    }

    public static boolean isCliente(HttpServletRequest request) {
        return (Usuario.isLogado(request) && Usuario.hasPermissao(request,"CLIENTE"));
    }

    public static Usuario getUsuario(HttpServletRequest request) {
        return (Usuario) request.getSession().getAttribute("usuario");
    }
}
