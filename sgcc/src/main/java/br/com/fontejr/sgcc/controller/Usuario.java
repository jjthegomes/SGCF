
package br.com.fontejr.sgcc.controller;

        import br.com.fontejr.sgcc.domain.model.Usuario;
        import br.com.fontejr.sgcc.domain.repository.AdministradorRepository;
        import br.com.fontejr.sgcc.domain.repository.ClienteRepository;
        import br.com.fontejr.sgcc.domain.repository.FuncionarioRepository;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpServletRequest;

@Controller
class UsuarioController {

    @Autowired
    private AdministradorRepository administradorRepository;
    private FuncionarioRepository funcionarioRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public UsuarioController(AdministradorRepository administradorRepository,
                             FuncionarioRepository funcionarioRepository,
                             ClienteRepository clienteRepository) {
        this.administradorRepository = administradorRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping(value = "perfil")
    public String getPerfil(Model model, HttpServletRequest request) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar");

        if (Usuario.isAdministrador(request)) {
            model.addAttribute("title", "Perfil de Administrador");
            model.addAttribute("administrador", Usuario.getUsuario(request));
            model.addAttribute("action", "/administrador/perfil");
            model.addAttribute("actionDelete", "/administrador/deletar");
            return "administrador/manter";
        } else if (Usuario.isFuncionario(request)) {
            model.addAttribute("title", "Perfil de Funcionario");
            model.addAttribute("funcionario", Usuario.getUsuario(request));
            model.addAttribute("action", "/funcionario/perfil");
            model.addAttribute("actionDelete", "/funcionario/deletar");
            return "funcionario/manter";
        } else if (Usuario.isCliente(request)) {
            model.addAttribute("title", "Perfil de Cliente");
            model.addAttribute("cliente", Usuario.getUsuario(request));
            model.addAttribute("action", "/cliente/perfil");
            model.addAttribute("actionDelete", "/cliente/deletar");
            return "cliente/manter";
        }
        return "redirect:/";
    }
}

