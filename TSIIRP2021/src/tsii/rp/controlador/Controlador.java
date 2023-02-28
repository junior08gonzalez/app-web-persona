package tsii.rp.controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

//import java.awt.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tsii.rp.modelo.Operaciones;
import tsii.rp.modelo.Pais;
import tsii.rp.modelo.Persona;
import tsii.rp.modelo.Usuario;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Controlador() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		switch(accion) 
		{
			case "listar":{
				ArrayList<Persona> lp = Operaciones.listarPersonas();
				request.setAttribute("lista" ,lp);
				request.getRequestDispatcher("listar.jsp").forward(request, response);
				break;
			}
			case "listarPais":{
				ArrayList<Pais> lispais = Operaciones.listarPais();
				request.setAttribute("lispais" ,lispais);
				request.getRequestDispatcher("listarPais.jsp").forward(request, response);
				break;
			}
			case "nuevo":{
				ArrayList<Pais> lps = Operaciones.listarPais();
				request.setAttribute("lps" ,lps);
				request.getRequestDispatcher("nuevo.jsp").forward(request, response);
				break;
				
			}
			case "nuevoPais":{
				request.getRequestDispatcher("nuevoPais.jsp").forward(request, response);
				break;
				
			}
			case "Guardar":{
				//Obtener los datos del formulario
				Pais ps = new Pais();
				ps.setIdPais(Integer.parseInt(request.getParameter("idPais")));
				Persona p = new Persona(Integer.parseInt(request.getParameter("ci")),
						request.getParameter("nombre"),request.getParameter("apellido"),
						request.getParameter("fechaNac"),request.getParameter("email"),
						request.getParameter("telefono"),request.getParameter("sexo"),
						ps);
				//llamar y verificar el resultado de la operacion
				if(Operaciones.validarPersonaPorCI(Integer.parseInt(request.getParameter("ci")))){
					request.setAttribute("error", "Cedula invalido!");
					request.getRequestDispatcher("nuevo.jsp").forward(request, response);
				}
				else {
					if(Operaciones.nuevaPersona(p)) {
						//si fue correcto redireccionar al index
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
					else {
						//en caso contrario volver a mostrar el formulario nuevo (vacio)
						request.getRequestDispatcher("nuevo.jsp").forward(request, response);
					}
				}
					
				break;
			}
			case "editar":{
				Integer ci = Integer.parseInt(request.getParameter("ci"));
				Persona p = Operaciones.buscarPersonaPorCI(ci);
				if(p != null) {
					ArrayList<Pais> lps = Operaciones.listarPais();
					request.setAttribute("lps" ,lps);
					request.setAttribute("p", p);
					request.getRequestDispatcher("editar.jsp").forward(request, response);

				}
				break;
			}
			case "Buscar":{
				System.out.println("Estamos buscando la persona");
				Integer ci = Integer.parseInt(request.getParameter("ci"));
				Persona p = Operaciones.buscarPersonaPorCI(ci);
				if(p != null) {
					ArrayList<Pais> lps = Operaciones.listarPais();
					request.setAttribute("lps" ,lps);
					request.setAttribute("p", p);
					request.setAttribute("readonly", true);
					request.getRequestDispatcher("editar.jsp").forward(request, response);

				}
				else {
					request.setAttribute("error", "No se ha encontrado ninguna coincidencia");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				break;
			}
//			redireccionar al formulario para seleccionar el orden de la lista de los datos para agregar al PDF
			case "reporte":{
				request.getRequestDispatcher("reporte.jsp").forward(request, response);
				break;
			}
			case "editarPais":{
				Integer idPais = Integer.parseInt(request.getParameter("idPais"));
				Pais pais = Operaciones.buscarPais(idPais);
				if(pais != null) {
					request.setAttribute("pais", pais);
					request.getRequestDispatcher("editarPais.jsp").forward(request, response);

				}
				break;
			}
			case "Editar":{
				Pais ps = new Pais();
				ps.setIdPais(Integer.parseInt(request.getParameter("idPais")));
				Persona p = new Persona(Integer.parseInt(request.getParameter("ci")),
						request.getParameter("nombre"),request.getParameter("apellido"),
						request.getParameter("fechaNac"),request.getParameter("email"),
						request.getParameter("telefono"),request.getParameter("sexo"),
						ps);
				if(Operaciones.editarPersona(p)) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("Controlador?accion=listar").forward(request, response);
				}
				
				break;
			}
			case "EditarPais":{
				Pais ps = new Pais(Integer.parseInt(request.getParameter("idPais")),
						request.getParameter("nombrePais"));
//				ps.setIdPais(Integer.parseInt(request.getParameter("idPais")));
				if(Operaciones.editarPais(ps)) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("Controlador?accion=listarPais").forward(request, response);
				}
				
				break;
			}
			case "eliminar":{
				Integer ci = Integer.parseInt(request.getParameter("ci"));
				if(Operaciones.eliminarPersona(ci)) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("Controlador?accion=listar").forward(request, response);
				}
				break;
			}
			case "GuardarPais":{
				//Obtener los datos del formulario
				Pais ps = new Pais();
				ps.setNombrePais(request.getParameter("nombrePais"));
				//llamar y verificar el resultado de la operacion
				if(Operaciones.nuevoPais(ps)) {
					//si fue correcto redireccionar al index
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else {
					//en caso contrario volver a mostrar el formulario nuevo (vacio)
					request.getRequestDispatcher("nuevoPais.jsp").forward(request, response);
				}
						
				break;
			}
			case "login":{
				request.getRequestDispatcher("login.jsp").forward(request, response);
				break;
			}
			case "Aceptar":{
				String usuario = request.getParameter("usuario");
				String clave = request.getParameter("clave");
				if(usuario.isEmpty() || clave.isEmpty()){
					request.setAttribute("error", "Campos vacios!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else {
					Usuario u = Operaciones.validarUsuario(usuario, clave);
					if(u != null) {
						System.out.println("Usuario no vacio");
						System.out.println("Usuario: "+u.getUsuario());
						HttpSession sesion = request.getSession();
						System.out.println(sesion);
						sesion.setAttribute("u", u);
						System.out.println(u);
						request.getRequestDispatcher("index.jsp").forward(request, response);

					}else {
						request.setAttribute("error", "Usuario y/o Contraseña invalidos!");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				}
				break;
			}
			case "nuevoUsuario":{
				request.getRequestDispatcher("nuevoUsuario.jsp").forward(request, response);
				break;
			}
			case "Registrarse":{
				String usuario = request.getParameter("usuario");
				String clave = request.getParameter("clave");
				if(usuario.isEmpty() || clave.isEmpty()){
					request.setAttribute("error", "Campos vacios!");
					request.getRequestDispatcher("nuevoUsuario.jsp").forward(request, response);
				}else {
					
					if(Operaciones.buscarUsuario(usuario)) {
						request.setAttribute("error", "Usuario invalido!");
						request.getRequestDispatcher("nuevoUsuario.jsp").forward(request, response);
					}
					else {
						
						if(Operaciones.registrarUsuario(usuario, clave)) {
	
							request.getRequestDispatcher("index.jsp").forward(request, response);

						}
						
					}
				}
				break;
			}
			case "logout":{
				HttpSession sesion = request.getSession(false);
				if(sesion != null) {
					sesion.removeAttribute("u");
					sesion.invalidate();
					request.getRequestDispatcher("index.jsp").forward(request, response);

				}
				break;
			}
			case "inicio":{
				request.getRequestDispatcher("index.jsp").forward(request, response);
				break;
			}
			
			case "Generar":{
//				prepara el contenido como la salida de una aplicacion de tipo
//				pdf, esto permite que el navegador muestre correctamente el documento
				response.setContentType("application/pdf");

				try {
//					intentar crear el documento, con dimension A4 y en modo horizontal
					Document document = new Document(PageSize.A4.rotate());
//					permitir utilizar el documento creado, en la salida seleccionada
//					el modo de escritura
					PdfWriter.getInstance(document, response.getOutputStream());
//					para ello es necesario abrir el documento
					document.open();
//					todo texto que se agregue al documento se encuentra en un parrafo,
//					aqui se crean dos uno para el titulo y otro para la fecha
					Paragraph p, p2;
					
//					crear un objeto de tipo imagen con la dirección de la misma
					String imgUrl = "https://ucsa.edu.py/yeah/wp-content/uploads/2018/04/logo-fondo-blanco-png.png";
					Image logo = Image.getInstance(new URL(imgUrl));

//					adecuar el tamaño de la imagen y ubicar en el documento
					logo.scaleAbsolute(150f, 90f);
					logo.setAlignment(Element.ALIGN_LEFT);
//					agregar la imagen al documento
					document.add(logo);
					
//					el constructor del parrafo permite agregar a la vez el texto
					p = new Paragraph("Reporte de Personas");
//					luego alinear 
					p.setAlignment(Element.ALIGN_CENTER);
//					y agregar al documento, este proceso se repite para todo lo demas!
					document.add(new Paragraph(p));
//					agrega una linea en blanco
					document.add(Chunk.NEWLINE);
//					las tablas en itext son todo un tema aparte
//					primero se crea la tabla, con la cantidad de columnas necesarias
					PdfPTable tabla = new PdfPTable(8);
//					se define la dimension de cada columna a mano, expresadas en puntos flotantes
					float[] medidaCeldas = { 16f, 40f, 40f, 25f, 40f, 20f, 12f, 25f };
//					se asigna las dimensiones establecidas en el array anterior
					tabla.setWidths(medidaCeldas);
//					para que la tabla ocupe la totalidad de la hoja (horizontalmente)
					tabla.setWidthPercentage(100);
//					y para asegurar que el mismo este centrado
					tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
//					cada celda de la tabla contiene un parrafo, aqui se repite el proceso anterior
//					crear, agregar texto, ubicar y añadir a la tabla
					PdfPCell celda1 = new PdfPCell(new Paragraph("Nº CI"));
					PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre"));
					PdfPCell celda3 = new PdfPCell(new Paragraph("Apellido"));
					PdfPCell celda4 = new PdfPCell(new Paragraph("Fecha de Nac."));
					PdfPCell celda5 = new PdfPCell(new Paragraph("Email"));
					PdfPCell celda6 = new PdfPCell(new Paragraph("Teléfono"));
					PdfPCell celda7 = new PdfPCell(new Paragraph("Sexo"));
					PdfPCell celda8 = new PdfPCell(new Paragraph("Pais"));

//					ubicacion de cada texto en cada celda
					celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
					celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
					celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
					celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
					celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
					celda6.setHorizontalAlignment(Element.ALIGN_CENTER);
					celda7.setHorizontalAlignment(Element.ALIGN_CENTER);
					celda8.setHorizontalAlignment(Element.ALIGN_CENTER);
					
//					añadir cada celda a la tabla
					tabla.addCell(celda1);
					tabla.addCell(celda2);
					tabla.addCell(celda3);
					tabla.addCell(celda4);
					tabla.addCell(celda5);
					tabla.addCell(celda6);
					tabla.addCell(celda7);
					tabla.addCell(celda8);

//					intentar obtener los datos con lo cual se rellenara la tabla desde la bd
					try {
						ArrayList<Persona> persona = Operaciones.generarReportePDF(request.getParameter("pdf"),request.getParameter("fechaNacIni")
								,request.getParameter("fechaNacFin"));
						for (Persona per : persona) {
//							cada resultado del resultset debe ser convertido a string y agregar a la tabla,
//							dentro de su correspondiente celda
							tabla.addCell(String.valueOf(per.getCi()));
							tabla.addCell(per.getNombre());
							tabla.addCell(per.getApellido());
							tabla.addCell(per.getFechaNac().toString());
							tabla.addCell(per.getEmail());
							tabla.addCell(per.getTelefono());
							tabla.addCell(per.getSexo());
							tabla.addCell(per.getPais().getNombrePais());
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
//					finalmente la tabla se agrega al documento
					document.add(tabla);
//					otro espacio en blanco
					document.add(Chunk.NEWLINE);
//					se crea el contenido del segundo parrafo creado al inicio con la fecha
//					en este ejemplo tambien se muestra como modificar el estilo del texto y
//					el nombre del usuario logeado
					Usuario us = (Usuario)request.getSession().getAttribute("us");
					if(us!=null) {
						p2 = new Paragraph("Usuario: " + us.getUsuario() + " - Fecha: " + LocalDate.now(),
								FontFactory.getFont("arial", 10, Font.ITALIC, BaseColor.BLACK));
					}else {
						p2 = new Paragraph("Usuario: visitante - Fecha: " + LocalDate.now(),
								FontFactory.getFont("arial", 10, Font.ITALIC, BaseColor.BLACK));	
					}
//					la ubicacion de este parrafo
					p2.setAlignment(Element.ALIGN_CENTER);
//					agregar al documento
					document.add(new Paragraph(p2));
//					recordar siempre cerrar el documento al final
					document.close();
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			}

			
		}

	}

}
