package com.yonathan.cuentas.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yonathan.cuentas.servicio.cuentaServicio;

@RestController
@RequestMapping(path = "/")
public class cuentaControlador {
	
	@Autowired
	@Qualifier("cuentaServicio")
	cuentaServicio cuentaServicios;
	
	private static final Logger log = LoggerFactory.getLogger(cuentaControlador.class);
	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
	
	
	@RequestMapping(path = "cuentas", method = RequestMethod.POST)
	public @ResponseBody boolean insertarCuenta(@RequestBody String cadena) {
		try {
//			Cuenta cuenta = mapper.readValue(cuentaJSON, Cuenta.class);
			if (cuentaServicios.agregar(cadena)) {
				return true;
			}
			return false;
		} catch (Exception ex) {
			log.error("ERROR(CTRL: registrarNota): "+ex.getMessage());
			return false;
		}
	}
}
