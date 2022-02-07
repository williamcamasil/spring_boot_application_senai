package com.babyfix.babyfix.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.babyfix.babyfix.models.Veiculo;
import com.babyfix.babyfix.repository.VeiculoRepository;

@Controller
public class VeiculoController {

	@Autowired
	private VeiculoRepository vr;

	// CADASTRAR VEICULO
	@RequestMapping(value = "/cadastrarVeiculo", method = RequestMethod.GET)
	public String form() {
		return "veiculo/formVeiculo";
	}

	@RequestMapping(value = "/cadastrarVeiculo", method = RequestMethod.POST)
	public String form(@Valid Veiculo veiculo, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/cadastrarVeiculo";
		}

		vr.save(veiculo);
		attributes.addFlashAttribute("mensagem", "Veículo cadastrada com sucesso!");
		return "redirect:/cadastrarVeiculo";
	}

	// LISTAR VEICULO
	@RequestMapping("/veiculos")
	public ModelAndView listaVeiculos() {
		ModelAndView mv = new ModelAndView("veiculo/listaVeiculo");
		Iterable<Veiculo> veiculos = vr.findAll();
		mv.addObject("veiculos", veiculos);
		return mv;
	}

	// DELETA VEICULO
	@RequestMapping("/deletarVeiculo")
	public String deletarVeiculo(long codigo) {
		Veiculo veiculo = vr.findByCodigo(codigo);
		vr.delete(veiculo);
		return "redirect:/veiculos";
	}

	//PUT VEICULO
	@RequestMapping(value = "/editar-veiculo", method = RequestMethod.GET)
	public ModelAndView editarVeiculo(long codigo) {
		Veiculo veiculo = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("veiculo/update-veiculo");
		mv.addObject("veiculo", veiculo);
		return mv;		
	}
	
	
	//UPDATE VEICULO
	@RequestMapping(value = "/editar-veiculo", method = RequestMethod.POST)
	public String updateVeiculo(@Valid Veiculo veiculo, BindingResult result, RedirectAttributes attributes) {
		vr.save(veiculo);
		attributes.addFlashAttribute("success", "Veiculo alterada com sucesso!");
		
		long codigoLong = veiculo.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
}
