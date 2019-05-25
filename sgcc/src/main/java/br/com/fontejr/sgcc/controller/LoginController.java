package br.com.fontejr.sgcc.controller;


import br.com.fontejr.sgcc.domain.model.Administrador;
import br.com.fontejr.sgcc.domain.model.Cliente;
import br.com.fontejr.sgcc.domain.model.Funcionario;
import br.com.fontejr.sgcc.domain.model.Usuario;
import br.com.fontejr.sgcc.domain.repository.AdministradorRepository;
import br.com.fontejr.sgcc.domain.repository.ClienteRepository;
import br.com.fontejr.sgcc.domain.repository.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {
    private AdministradorRepository administradorRepository;
    private FuncionarioRepository funcionarioRepository;
    private ClienteRepository clienteRepository ;

    @Autowired
    public LoginController(AdministradorRepository administradorRepository, FuncionarioRepository funcionarioRepository, ClienteRepository clienteRepository) {
        this.administradorRepository = administradorRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request) {
        return (Usuario.isLogado(request)) ? "redirect:/" : "/auth/login";
    }

    @PostMapping(value = "/login")
    public String efetuarLogin(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        if (administradorRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).isPresent()) {
            Optional<Administrador> administrador = administradorRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
            session.setAttribute("usuario", administrador.get());
            session.setAttribute("permissao", "ADMIN");
            model.addAttribute("loginSucesso", "Você está logado como ADMINISTRADOR");
        } else if (funcionarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).isPresent()) {
            Optional<Funcionario> funcionario = funcionarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
            session.setAttribute("usuario", funcionario.get());
            session.setAttribute("permissao", "FUNCIONARIO");
            model.addAttribute("loginSucesso", "Você está logado como FUNCIONARIO");
        } else if (clienteRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).isPresent()) {
            Optional<Cliente> cliente = clienteRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
            session.setAttribute("usuario", cliente.get());
            session.setAttribute("permissao", "CLIENTE");
            model.addAttribute("msgSucesso", "Você está logado como CLIENTE");
        } else {
            model.addAttribute("msgErro", "Email ou senha inválido");
            return "auth/login";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/logout")
    public String logout(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        session.removeAttribute("usuario");
        session.removeAttribute("permissao");
        return "redirect:/login";
    }

}
