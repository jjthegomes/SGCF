package br.com.fontejr.sgcc.controller;
import br.com.fontejr.sgcc.domain.model.Cliente;
import br.com.fontejr.sgcc.domain.model.Usuario;
import br.com.fontejr.sgcc.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping(value = "")
    public String clientes(Model model, HttpServletRequest request) {
        if (Usuario.isFuncionario(request) || Usuario.isAdministrador(request)) {
            model.addAttribute("operacao", "listar");
            model.addAttribute("title", "Listar Clientes");
            model.addAttribute("botaoOperacao", "Listar Cliente");
            model.addAttribute("clientes", clienteRepository.findAll());
            return "cliente/pesquisar";
        }
        return "redirect:/";
    }

    @GetMapping(value = "add")
    public String displayClienteForm(Model model, HttpServletRequest request) {
        if (Usuario.isLogado(request)) {
            model.addAttribute("operacao", "adicionar");
            model.addAttribute("title", "Cadastrar Cliente");
            model.addAttribute("botaoOperacao", "Cadastrar");
            return "cliente/manter";
        }
        return "redirect:/login";
    }

    @PostMapping(value = "add")
    public String processClienteForm(@ModelAttribute Cliente cliente, Model model) {
        clienteRepository.save(cliente);
        model.addAttribute("msgSucesso", "Cadastro como cliente efetuado com sucesso!");
        return "redirect:/";
    }

    @GetMapping(value = "edit/{id}")
    public String clienteEdit(Model model, @PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar Cliente");
        model.addAttribute("botaoOperacao", "Editar Cliente");

        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
        }
        return "cliente/manter";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@ModelAttribute Cliente cliente, Model model,
                       @PathVariable Long id) throws Exception {
        if (id.equals(cliente.getId())) {
            clienteRepository.save(cliente);
        } else {
            model.addAttribute("error", "Dados Incorretos");
        }
        return "redirect:/cliente";
    }

    @GetMapping(value = "delete/{id}") // site.com/cliente/delete/1
    public String clienteDelete(Model model, @PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir Cliente");
        model.addAttribute("botaoOperacao", "Excluir Cliente");
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
        }
        return "cliente/manter";
    }

    @PostMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        clienteRepository.delete(cliente);
        return "redirect:/cliente";
    }

    @RequestMapping(value = "/busca")
    public String busca(Model model, @RequestParam("nome") String nome, HttpServletRequest request) {
        if (Usuario.isFuncionario(request) || Usuario.isAdministrador(request)) {
            model.addAttribute("clientes", clienteRepository.findByNomeContaining(nome));
            model.addAttribute("operacao", "buscar");
            model.addAttribute("title", "Buscar Cliente");
            model.addAttribute("botaoOperacao", "buscar Cliente");
            return "cliente/pesquisar";
        }
        return "redirect:/";
    }

    @PostMapping(value = "perfil")
    public String editarPerfil(Model model, @ModelAttribute Cliente cliente, HttpServletRequest request) {
        if (Usuario.isCliente(request)) {
            Cliente usuario = (Cliente) Usuario.getUsuario(request);
            cliente.setId(usuario.getId());
            clienteRepository.save(cliente);
            request.getSession().setAttribute("usuario", cliente);
            model.addAttribute("operacao", "editar");
            model.addAttribute("botaoOperacao", "Editar");
            model.addAttribute("title", "Perfil de Cliente");
            model.addAttribute("cliente", Usuario.getUsuario(request));
            model.addAttribute("action", "/cliente/perfil");
            model.addAttribute("msgSucesso", "Dados alterados com sucesso!");
            return "cliente/manter";
        }
        return "redirect:/perfil";
    }

    @PostMapping(value = "deletar")
    public String deletarPerfil(@ModelAttribute Cliente cliente, HttpServletRequest request) {
        if (Usuario.isCliente(request)) {
            Cliente usuario = (Cliente) Usuario.getUsuario(request);
            cliente.setId(usuario.getId());
            clienteRepository.delete(cliente);
            request.getSession().removeAttribute("usuario");
            request.getSession().removeAttribute("permissao");
            return "redirect:/";
        }
        return "redirect:/perfil";
    }

}
