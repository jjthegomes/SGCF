package br.com.fontejr.sgcc.controller;

import br.com.fontejr.sgcc.domain.model.Funcionario;
import br.com.fontejr.sgcc.domain.model.Usuario;
import br.com.fontejr.sgcc.domain.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
    @Controller
    @RequestMapping(value = "funcionario")
    public class FuncionarioController {
        @Autowired
        private FuncionarioRepository funcionarioRepository;


        @GetMapping(value = "")
        public String funcionario(Model model) {
            model.addAttribute("operacao", "listar");
            model.addAttribute("title", "Lista Funcionarios");
            model.addAttribute("botaoOperacao", "Lista de Funcionarios");

            model.addAttribute("funcionarios", funcionarioRepository.findAll());
            return "funcionario/pesquisar";
        }

        @GetMapping(value = "add")
        public String displayFuncionarioForm(Model model,  HttpServletRequest request) {
            if (Usuario.isLogado(request)) {
                model.addAttribute("operacao", "adicionar");
                model.addAttribute("title", "Cadastro de Funcionario");
                model.addAttribute("botaoOperacao", "Cadastrar");

                return "funcionario/manter";
            }
            return "redirect:/login";
        }

        @PostMapping(value = "add")
        public String processFuncionarioForm(@ModelAttribute Funcionario funcionario, Model model) {
            funcionarioRepository.save(funcionario);
            model.addAttribute("msgSucesso", "Cadastro como funcionario efetuado com sucesso!");
            return "redirect:/";
        }

        @GetMapping(value = "edit/{id}") // site.com/funcionario/edit
        public String funcionarioEdit(Model model, @PathVariable Long id) {
            Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
            model.addAttribute("operacao", "editar");
            model.addAttribute("title", "Editar Funcionario");
            model.addAttribute("botaoOperacao", "Editar Funcionario ");

            if (funcionario.isPresent()) {
                model.addAttribute("funcionario", funcionario.get());
            }
            return "funcionario/manter";
        }

        @PostMapping(value = "edit/{id}") // site.com/funcionario/edit/1/
        public String edit(@ModelAttribute Funcionario funcionario, Model model,
                           @PathVariable Long id) throws Exception {
            if (id.equals(funcionario.getId())) {
                funcionarioRepository.save(funcionario);
            } else {
                model.addAttribute("error", "Dados Incorretos");
            }
            return "redirect:/funcionario";
        }

        @GetMapping(value = "delete/{id}") // site.com/funcionario/delete/1
        public String funcionarioDelete(Model model, @PathVariable Long id) {
            Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
            model.addAttribute("operacao", "deletar");
            model.addAttribute("title", "Excluir Funcionario");
            model.addAttribute("botaoOperacao", "Excluir Funcionario");
            if (funcionario.isPresent()) {
                model.addAttribute("funcionario", funcionario.get());
            }
            return "funcionario/manter";
        }

        @PostMapping(value = "delete/{id}")
        public String delete(@PathVariable Long id, @ModelAttribute Funcionario funcionario) {
            funcionarioRepository.delete(funcionario);
            return "redirect:/funcionario";
        }

        @PostMapping(value = "perfil")
        public String editarPerfil(Model model, @ModelAttribute Funcionario funcionario, HttpServletRequest request) {
            if(Usuario.isFuncionario(request)) {
                Funcionario usuario = (Funcionario) Usuario.getUsuario(request);
                funcionario.setId(usuario.getId());
                funcionarioRepository.save(funcionario);
                request.getSession().setAttribute("usuario", funcionario);
                model.addAttribute("operacao", "editar");
                model.addAttribute("botaoOperacao", "Editar");
                model.addAttribute("title", "Perfil de Funcionario");
                model.addAttribute("funcionario", Usuario.getUsuario(request));
                model.addAttribute("action", "/funcionario/perfil");
                model.addAttribute("msgSucesso", "Dados alterados com sucesso!");
                return "funcionario/manter";
            }
            return "redirect:/perfil";
        }

        @PostMapping(value = "deletar")
        public String deletarPerfil(@ModelAttribute Funcionario funcionario, HttpServletRequest request) {
            if(Usuario.isFuncionario(request)) {
                Funcionario usuario = (Funcionario) Usuario.getUsuario(request);
                funcionario.setId(usuario.getId());
                funcionarioRepository.delete(funcionario);
                request.getSession().removeAttribute("usuario");
                request.getSession().removeAttribute("permissao");
                return "redirect:/";
            }
            return "redirect:/perfil";
        }
    }
