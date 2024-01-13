package com.amegdev.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mindrot.jbcrypt.BCrypt;

import com.amegdev.dto.PublicacionDTO;
import com.amegdev.model.Usuario;
import com.amegdev.util.MensajeManager;
import com.amgdev.service.IUsuarioService;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private List<Usuario> usuarios;
	private String texto;
	private Usuario us;
	private boolean correcto;
	private String modificando;
	
	private String NContrasena;
	
	@Inject
	private IUsuarioService uservice;
	
	@Inject
	private MensajeManager msg;
	
	public UsuarioBean() {
		this.us = new Usuario();
	}
	
	@PostConstruct
	public void init() {
		//this.texto = "Buscar";
		this.listarUsuarios();
		this.correcto = false;
	}
	
	public void listarUsuarios() {
		try {
			this.usuarios = this.uservice.listar();
		} catch (Exception e) {
			msg.mostrarMensaje("Aviso", "Ocurrio un error inesperado", "ERROR");
		}
	}
	
	public void buscar() {
		try {
			
			if(this.texto != null && this.texto.length() > 0) {
				this.usuarios = this.uservice.leerPorNombreUsuarioLike(texto);
				
				if(this.usuarios.size() == 0) {
					msg.mostrarMensaje("Aviso", "Sin datos que mostrar", "WARN");
				}
			}
			else {
				msg.mostrarMensaje("Aviso", "Ingrese texto a buscar", "WARN");
			}
			
		} catch (Exception e) {
			msg.mostrarMensaje("Aviso", "Ocurrio un error inesperado", "ERROR");
		}
	}
	
	public void editar(Usuario usu) {
		try {
			this.us = usu;
			this.modificando = "Modificando usuario: " + this.us.getUsuario();
		} catch (Exception e) {
			msg.mostrarMensaje("Aviso", "Problemas al recuperar datos", "WARN");
		}
	}
	
	public void verificar() {
		try {
			if(this.us.getUsuario() != null && this.us.getContrasena() != null && this.us.getContrasena().length() > 0) {
				Usuario usu = this.uservice.login(us);
				if(usu.getId() != null && usu.getId() > 0) {
					this.correcto = true;
					msg.mostrarMensaje("Aviso", "OK!...", "INFO");
				}
				else {
					msg.mostrarMensaje("Aviso", "Credenciales incorrectos", "WARN");
				}
			}
			else {
				msg.mostrarMensaje("Aviso", "Ingrese credenciales actuales", "WARN");
			}
			
		} catch (Exception e) {
			msg.mostrarMensaje("Aviso", "Ocurrio un error inesperado", "ERROR");
		}
	}
	
	public void aceptar() {
		try {
			if(this.NContrasena != null && !this.NContrasena.isEmpty() ) {
				this.us.setContrasena(BCrypt.hashpw(this.NContrasena, BCrypt.gensalt()));
				this.uservice.modificar(us);
				msg.mostrarMensaje("Aviso", "OK!...", "INFO");
			}						
		} catch (Exception e) {
			msg.mostrarMensaje("Aviso", "Ocurrio un error inesperado", "ERROR");
		}
	}
	
	public void exportarPublicaciones() {		
				
		try {
			
			List<PublicacionDTO> pubs = this.uservice.publicacionesExport("");	
			
			if(pubs != null && !pubs.isEmpty()) {
				
				HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				response.addHeader("Content-disposition", "attachment; filename=publicaciones.xlsx");
				
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("Publicaciones");
				
				int rownum = 0;
				int colnum = 0;
				XSSFRow row = sheet.createRow(rownum);
				String[] encabezados = {"NOMBRES", "APELLIDOS", "USUARIO", "PUBLICACION"};
				
				for (String encabezado : encabezados) {
					XSSFCell cell1 = row.createCell(colnum);
					cell1.setCellValue(encabezado);
					colnum++;
				}
				
				rownum++;
				
				for(PublicacionDTO dto : pubs) {
					row = sheet.createRow(rownum);
					row.createCell(0).setCellValue(dto.getNombres());
					row.createCell(1).setCellValue(dto.getApellidos());
					row.createCell(2).setCellValue(dto.getUsuario());
					row.createCell(3).setCellValue(dto.getCuerpo());
					rownum++;
				}
				
				//FileOutputStream out = new FileOutputStream("publicaciones.xlsx");	
				OutputStream out = response.getOutputStream();
				workbook.write(out);				
				out.flush();
				out.close();	
				FacesContext.getCurrentInstance().responseComplete();	
			}
			else {
				msg.mostrarMensaje("Aviso", "Sin datos que exportar", "WARN");
			}								
			
		} catch (Exception e) {
			msg.mostrarMensaje("Aviso", "Ocurrio un error inesperado", "ERROR");
		}
		
	}
	
	public void cancelar() {
		this.us = new Usuario();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	public Usuario getUs() {
		return us;
	}

	public void setUs(Usuario us) {
		this.us = us;
	}

	public String getModificando() {
		return modificando;
	}

	public void setModificando(String modificando) {
		this.modificando = modificando;
	}

	public String getNContrasena() {
		return NContrasena;
	}

	public void setNContrasena(String nContrasena) {
		NContrasena = nContrasena;
	}
	
}
