package com.amegdev.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amegdev.model.Persona;
import com.amgdev.service.IPersonaService;

@WebServlet("/imagen/*")
public class ImageServlet extends HttpServlet{

	@Inject
	private IPersonaService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String captura = req.getPathInfo().substring(1);
			if(captura != null && !captura.equalsIgnoreCase("")) {
				int id = Integer.parseInt(captura);
				Persona per = new Persona();
				per.setId_persona(id);
				per = service.obtenerPorId(per);
				if(per.getFoto() != null) {
					resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
					resp.setHeader("Pragma", "no-cache");
					resp.setDateHeader("Expires", 0);
					
					resp.setContentType(getServletContext().getMimeType("image/png"));
					resp.setContentLength(per.getFoto().length);
					resp.getOutputStream().write(per.getFoto());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
