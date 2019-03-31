package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ModeloMonedas;
import com.example.demo.entity.Proveedor;
import com.example.demo.entity.StockMuseo;
import com.example.demo.service.MonedasRepository;
import com.example.demo.service.ProveedorRepository;
import com.example.demo.service.StockRepository;
@RequestMapping("/modelo-monedas")
@Controller
public class MonedasController {
	
	@Autowired
	private ProveedorRepository repProveedor;
	
	@Autowired
	private MonedasRepository repMonedas;
	
	@Autowired
	private StockRepository repStock;
	
	
	
	@RequestMapping("")
	public String goModeloMonedas(Model model) {
		List<ModeloMonedas> modelos = repMonedas.findAll();
		model.addAttribute("listaMonedas", modelos);
		return  "ConsultarModelosMonedas";
	}

	@RequestMapping("/add")
    public String aniadirModeloFormulario() {
        return "gestionarMonedas";
    }

    @RequestMapping(value = "/add/submit", method = RequestMethod.POST)
    public String submitProveedorFormulario(
            @RequestParam String valor_facial,
            @RequestParam String unidad_monetaria,
            @RequestParam String diametro,
            @RequestParam String peso,
            @RequestParam String metal,
            @RequestParam String descripcion) {
    	ModeloMonedas moneda = new ModeloMonedas(valor_facial, unidad_monetaria,Double.valueOf(diametro),Double.valueOf(peso), metal, descripcion);
        repMonedas.save(moneda);
        return "redirect:/modelo-monedas";
    }
    @RequestMapping("/modificar/{id}")
    public String modificarProveedorFormulario(Model model, @PathVariable int id) {
    	ModeloMonedas m = repMonedas.findById(id);
    	if (m == null) {
    	  return "redirect:/modelo-monedas";
    	}
    	model.addAttribute("m", m);
        return "modificarMoneda";
    }

    @RequestMapping(value = "/modificado/submit", method = RequestMethod.POST)
    public String submitProveedorFormularioModificar(
    		@RequestParam Integer id,
            @RequestParam Optional<String> valor_facial,
            @RequestParam Optional<String> unidad_monetaria,
            @RequestParam Optional<String> diametro,
            @RequestParam Optional<String> peso,
            @RequestParam Optional<String> metal,
            @RequestParam Optional<String> descripcion
            ) {
    	ModeloMonedas m = repMonedas.findById(id);
        if (m == null)
        	return "redirect:/modelo-monedas";
        
        if (valor_facial.isPresent())
            if (!valor_facial.get().isEmpty())
                m.setvalor_facial(valor_facial.get());
        if (unidad_monetaria.isPresent())
            if (!unidad_monetaria.get().isEmpty())
                m.setunidad_monetaria(unidad_monetaria.get());
        if (diametro.isPresent())
            if (!diametro.get().isEmpty())
                m.setDiametro(Double.valueOf(diametro.get()));
        if (peso.isPresent())
            if (!peso.get().isEmpty())
                m.setPeso(Double.valueOf(peso.get()));
        if (metal.isPresent())
            if (!metal.get().isEmpty())
                m.setMetal(metal.get());
        if (descripcion.isPresent())
            if (!descripcion.get().isEmpty())
                m.setDescripcion(descripcion.get());
        repMonedas.save(m);
        return "redirect:/modelo-monedas";
    }
    @RequestMapping(value = "/borrar/{id}")
    public String borrarMoneda(@PathVariable int id){       
    	ModeloMonedas m = repMonedas.findById(id);
    	List<StockMuseo> stockMuseos = repStock.findByModeloMonedas(m);
        if (m == null)
        	return "redirect:/modelo-monedas";
	    if (stockMuseos.isEmpty()) {   
        	repMonedas.delete(m);	        
	    }
	    return "redirect:/modelo-monedas";
    }
	
}

