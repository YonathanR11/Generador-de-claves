package com.yonathan.cuentas.repositorio;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yonathan.cuentas.entidades.Cuenta;

@Repository("cuentaRepositorio")
public interface cuentaRepositorio extends CrudRepository<Cuenta, Serializable> {

}
