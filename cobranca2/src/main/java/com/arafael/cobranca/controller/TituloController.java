package com.arafael.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.arafael.cobranca.model.StatusTitulo;
import com.arafael.cobranca.model.Titulo;
import com.arafael.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	@Autowired
	private TituloRepository tr;
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		System.out.println("descrição -->" + titulo.getDescricao());
		tr.save(titulo);
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "todos os titulos salvos com sucesso!");
		return mv;
	}
	
	@ModelAttribute("todosStatusTitulos")
	public List<StatusTitulo> listarStatus() {
		return Arrays.asList(StatusTitulo.values());
	}
}
