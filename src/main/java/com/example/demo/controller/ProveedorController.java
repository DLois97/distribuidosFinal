package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.management.InstanceNotFoundException;

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

@RequestMapping("/proveedor")
@Controller
public class ProveedorController {
	
	@Autowired
	private ProveedorRepository repProveedor;
	
	@Autowired
	private MonedasRepository repMonedas;
	
	@Autowired
	private StockRepository repStock;
	@RequestMapping("")
	public String goProveedor(Model model) {
		List<Proveedor> proveedores = repProveedor.findAll();
		model.addAttribute("listaProveedores", proveedores);
		return  "ConsultarProveedores";
	}

    @RequestMapping(value = "/add/submit", method = RequestMethod.POST)
    public String submitProveedorFormulario(@RequestParam String id,
            @RequestParam String nombre,
            @RequestParam String codigo,
            @RequestParam String email,
            @RequestParam String telefono) {
    	Proveedor proveedor = new Proveedor(Integer.valueOf(id),nombre,Integer.valueOf(codigo),email,telefono);
        repProveedor.save(proveedor);
        return "redirect:/proveedor";
    }
	
    @RequestMapping("/modificar/{id}")
    public String modificarProveedorFormulario(Model model, @PathVariable long id) {
    	Proveedor p = repProveedor.findById(id);
    	if (p == null) {
    		System.out.println("no existe");
    	}
    	model.addAttribute("p", p);
        return "modificarProveedor";
    }

    @RequestMapping(value = "/modificado/submit", method = RequestMethod.POST)
    public String submitProveedorFormularioModificar(
    		@RequestParam Long id,
            @RequestParam Optional<String> nombre,
            @RequestParam Optional<String> codigo,
            @RequestParam Optional<String> email,
            @RequestParam Optional<String> telefono
            ) {
        Proveedor p = repProveedor.findById((Long.valueOf(id)));
        if (p == null)
        	return "redirect:/proveedor";
        if (nombre.isPresent())
            if (!nombre.get().isEmpty())
                p.setNombre(nombre.get());
        if (codigo.isPresent())
            if (!codigo.get().isEmpty())
                p.setCP(Integer.valueOf(codigo.get()));
        if (email.isPresent())
            if (!email.get().isEmpty())
                p.setEmail(email.get());
        if (telefono.isPresent())
            if (!telefono.get().isEmpty())
                p.setTelefono(telefono.get());
        repProveedor.save(p);
        return "redirect:/proveedor";
    }
    
    @RequestMapping(value = "/borrar/{id}")
    public String borrarPropietario(@PathVariable long id){
        Proveedor p = repProveedor.findById(id);
        if (p == null)
        	return "redirect:/proveedor";
        List<StockMuseo> stock = repStock.findByProveedor(p);
        if (stock.isEmpty())
        	repProveedor.delete(p);
        return "redirect:/proveedor";
    }
   	

}
