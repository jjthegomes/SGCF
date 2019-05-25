
package br.com.fontejr.sgcc.controller;

import br.com.fontejr.sgcc.domain.model.Usuario;
import br.com.fontejr.sgcc.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    private FuncionarioRepository funcionarioRepository;
    private ClienteRepository clienteRepository;


    @Autowired
    public HomeController( FuncionarioRepository funcionarioRepository,
                          ClienteRepository clienteRepository ) {
        this.funcionarioRepository = funcionarioRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping(value = "")
    public String home(Model model, HttpServletRequest request) {
        if (Usuario.isFuncionario(request)) {
            model.addAttribute("dataAtual", new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
            model.addAttribute("title", "Dashboard");
            model.addAttribute("numClientes", clienteRepository.count());
            return "funcionario/home";

        } else if (Usuario.isAdministrador(request)) {
            model.addAttribute("dataAtual", new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
            model.addAttribute("title", "Dashboard");
            model.addAttribute("numFuncionarios", funcionarioRepository.count());
            model.addAttribute("numClientes", clienteRepository.count());
            return "administrador/home";
        }
        model.addAttribute("dataAtual", new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        model.addAttribute("title", "Dashboard");
       // model.addAttribute("corridas", corridaRepository.findAll());
        return "home";
    }

}