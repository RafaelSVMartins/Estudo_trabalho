package com.arafael.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arafael.cobranca.model.StatusTitulo;
import com.arafael.cobranca.model.Titulo;
import com.arafael.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	@Autowired
	private TituloRepository tr;
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String salvar(@Validated Titulo titulo,Errors errors, RedirectAttributes attributes) {
		System.out.println("descrição -->" + titulo.getDescricao());
		if(errors.hasErrors()) {
			return  "CadastroTitulo";
		}
		tr.save(titulo);
		attributes.addAttribute("mensagem", "todos os titulos salvos com sucesso!");
		return "redirect:/titulos/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisa() {
		List<Titulo> titulos = tr.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulo");
		mv.addObject("todosTitulos", titulos);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long tituloCodigo) {
		Titulo titulo = tr.getOne(tituloCodigo);
		ModelAndView mv = new ModelAndView("CadastroTitulo"); 
		mv.addObject(titulo);
		return mv;
	}
	
	@ModelAttribute("todosStatusTitulos")
	public List<StatusTitulo> listarStatus() {
		return Arrays.asList(StatusTitulo.values());
	}
}
