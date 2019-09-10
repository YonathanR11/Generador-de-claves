package com.yonathan.cuentas.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "contabilidad_cuenta")
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_cuenta;
	
	@NotNull
	@Column
	private int grupo_empresa_id_empresa;
	
	@NotNull
	@Column
	private int contabilidad_tipo_id_tipo;
	
	@Column
	private int creacion_usuario;
	
	@Column
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date creacion_fecha_hora;
	
	@Column(columnDefinition = "TINYINT")
//	@Type(type = "org.hibernate.type.NumericBooleanType")
	private int nivel;
	
	@Column(length = 100)
	private String cuenta;
	
	@Column(length = 200)
	private String nombre;
	
	@Column(columnDefinition = "TINYINT")
//	@Type(type = "org.hibernate.type.NumericBooleanType")
	private int detalle;//Nose si es boolean o int para verlo como TINYINT en la db
	
	@Column
	private char naturaleza;
	
	@Column(length = 3)
	private String moneda;
	
	@Column(columnDefinition = "TINYINT")
//	@Type(type = "org.hibernate.type.NumericBooleanType")
	private int bloqueado;//Nose si es boolean o int para verlo como TINYINT en la db
	
	@Column(columnDefinition = "TINYINT")
//	@Type(type = "org.hibernate.type.NumericBooleanType")
	private int status;//Nose si es boolean o int para verlo como TINYINT en la db
	
	

	public Cuenta() {
		
	}
	
	

	public Cuenta(@NotNull int grupo_empresa_id_empresa, @NotNull int contabilidad_tipo_id_tipo, int creacion_usuario,
			Date creacion_fecha_hora, int nivel, String cuenta, String nombre, int detalle, char naturaleza,
			String moneda, int bloqueado, int status) {
		super();
		this.grupo_empresa_id_empresa = grupo_empresa_id_empresa;
		this.contabilidad_tipo_id_tipo = contabilidad_tipo_id_tipo;
		this.creacion_usuario = creacion_usuario;
		this.creacion_fecha_hora = creacion_fecha_hora;
		this.nivel = nivel;
		this.cuenta = cuenta;
		this.nombre = nombre;
		this.detalle = detalle;
		this.naturaleza = naturaleza;
		this.moneda = moneda;
		this.bloqueado = bloqueado;
		this.status = status;
	}

	public Cuenta(long id_cuenta, @NotNull int grupo_empresa_id_empresa, @NotNull int contabilidad_tipo_id_tipo,
			int creacion_usuario, Date creacion_fecha_hora, int nivel, String cuenta, String nombre, int detalle,
			char naturaleza, String moneda, int bloqueado, int status) {
		super();
		this.id_cuenta = id_cuenta;
		this.grupo_empresa_id_empresa = grupo_empresa_id_empresa;
		this.contabilidad_tipo_id_tipo = contabilidad_tipo_id_tipo;
		this.creacion_usuario = creacion_usuario;
		this.creacion_fecha_hora = creacion_fecha_hora;
		this.nivel = nivel;
		this.cuenta = cuenta;
		this.nombre = nombre;
		this.detalle = detalle;
		this.naturaleza = naturaleza;
		this.moneda = moneda;
		this.bloqueado = bloqueado;
		this.status = status;
	}



	public long getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(long id_cuenta) {
		this.id_cuenta = id_cuenta;
	}

	public int getGrupo_empresa_id_empresa() {
		return grupo_empresa_id_empresa;
	}

	public void setGrupo_empresa_id_empresa(int grupo_empresa_id_empresa) {
		this.grupo_empresa_id_empresa = grupo_empresa_id_empresa;
	}

	public int getContabilidad_tipo_id_tipo() {
		return contabilidad_tipo_id_tipo;
	}

	public void setContabilidad_tipo_id_tipo(int contabilidad_tipo_id_tipo) {
		this.contabilidad_tipo_id_tipo = contabilidad_tipo_id_tipo;
	}

	public int getCreacion_usuario() {
		return creacion_usuario;
	}

	public void setCreacion_usuario(int creacion_usuario) {
		this.creacion_usuario = creacion_usuario;
	}

	public Date getCreacion_fecha_hora() {
		return creacion_fecha_hora;
	}

	public void setCreacion_fecha_hora(Date creacion_fecha_hora) {
		this.creacion_fecha_hora = creacion_fecha_hora;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDetalle() {
		return detalle;
	}

	public void setDetalle(int detalle) {
		this.detalle = detalle;
	}

	public char getNaturaleza() {
		return naturaleza;
	}

	public void setNaturaleza(char naturaleza) {
		this.naturaleza = naturaleza;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public int getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(int bloqueado) {
		this.bloqueado = bloqueado;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "Cuenta [id_cuenta=" + id_cuenta + ", grupo_empresa_id_empresa=" + grupo_empresa_id_empresa
				+ ", contabilidad_tipo_id_tipo=" + contabilidad_tipo_id_tipo + ", creacion_usuario=" + creacion_usuario
				+ ", creacion_fecha_hora=" + creacion_fecha_hora + ", nivel=" + nivel + ", cuenta=" + cuenta
				+ ", nombre=" + nombre + ", detalle=" + detalle + ", naturaleza=" + naturaleza + ", moneda=" + moneda
				+ ", bloqueado=" + bloqueado + ", status=" + status + "]";
	}
	
	
	
}
