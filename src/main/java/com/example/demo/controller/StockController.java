package com.example.demo.controller;

import java.sql.Date;
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
@RequestMapping("/stock-museo")
@Controller
public class StockController {
	
	@Autowired
	private ProveedorRepository repProveedor;
	
	@Autowired
	private MonedasRepository repMonedas;
	
	@Autowired
	private StockRepository repStock;
	
	
	@RequestMapping("")
	public String goStock(Model model) {
		List<StockMuseo> stock = repStock.findAll();
		model.addAttribute("stock", stock);
		return  "ConsultarStock";
	}
	
    @RequestMapping("/add")
    public String aniadirMonedaFormulario() {
        return "addStock";
    }

    @RequestMapping(value = "/add/submit", method = RequestMethod.POST)
    public String submitProveedorFormulario(@RequestParam String aAcunacion,
            @RequestParam String cAcunacion,
            @RequestParam String fechaAdquisicion,
            @RequestParam String estadoConservacion,
            @RequestParam String moneda,
            @RequestParam String proveedor) {
        Proveedor prov = repProveedor.findByNombre(proveedor);
        ModeloMonedas modeloMon = repMonedas.findById(Integer.valueOf(moneda));
        StockMuseo stock = new StockMuseo(Date.valueOf(aAcunacion),cAcunacion,Date.valueOf(fechaAdquisicion), estadoConservacion, prov, modeloMon);
        repStock.save(stock);
        return "redirect:/stock-museo";
    }
    @RequestMapping(value = "/borrar/{id}")
    public String borrarPropietario(@PathVariable int id){
        StockMuseo s = repStock.findById(id);
        if (s == null)
        	return "redirect:/stock-museo";
        //repStock.delete(repStock.findByProveedor(p));
        repStock.delete(s);
        return "redirect:/stock-museo";
    }
    @RequestMapping("/modificar/{id}")
    public String modificarProveedorFormulario(Model model, @PathVariable int id) {
    	StockMuseo m = repStock.findById(id);
    	if (m == null) {
    	  return "redirect:/stock-museo";
    	}
    	model.addAttribute("m", m);
    	List<Proveedor> proveedores = repProveedor.findAll();
		List<ModeloMonedas> modelosMonedas = repMonedas.findAll();
		model.addAttribute("proveedores", proveedores);
		model.addAttribute("monedas", modelosMonedas);
        return "modificarStock";
    }

    @RequestMapping(value = "/modificado/submit", method = RequestMethod.POST)
    public String submitProveedorFormularioModificar(
    		@RequestParam int id,
    		@RequestParam Optional<String> aAcunacion,
            @RequestParam Optional<String> cAcunacion,
            @RequestParam Optional<String> fechaAdquisicion,
            @RequestParam Optional<String> estadoConservacion,
            @RequestParam Optional<String> moneda,
            @RequestParam Optional<String> proveedor
            ) {
    	StockMuseo m = repStock.findById(id);
    	if (m == null) {
    	  return "redirect:/stock-museo";
    	}
        if (aAcunacion.isPresent())
            if (!aAcunacion.get().isEmpty())
                m.setAnioAcuniacion(Date.valueOf(aAcunacion.get()));
        if (cAcunacion.isPresent())
            if (!cAcunacion.get().isEmpty())
                m.setCiudadAcuniacion(cAcunacion.get());
        if (fechaAdquisicion.isPresent())
            if (!fechaAdquisicion.get().isEmpty())
                m.setFechaAdquisicion(Date.valueOf(fechaAdquisicion.get()));
        if (estadoConservacion.isPresent())
            if (!estadoConservacion.get().isEmpty())
                m.setEstadoConservacion(estadoConservacion.get());
        if (moneda.isPresent())
            if (!moneda.get().isEmpty()) {
            	ModeloMonedas moneda2 = repMonedas.findById(Long.valueOf(moneda.get())).get();
                m.setModeloMonedas(moneda2);
            }
        if (proveedor.isPresent())
            if (!proveedor.get().isEmpty()) {
            	Proveedor prove = repProveedor.findByNombre(proveedor.get());
                m.setProveedor(prove);
            }
        repStock.save(m);
        return "redirect:/stock-museo";
    }

}
