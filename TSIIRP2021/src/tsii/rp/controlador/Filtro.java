package tsii.rp.controlador;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tsii.rp.modelo.Usuario;

/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter("/Controlador")
public class Filtro implements Filter {

    /**
     * Default constructor. 
     */
    public Filtro() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		Usuario usr = (Usuario) req.getSession(false).getAttribute("u");
		String [] acciones = {"inicio", "login", "Aceptar", "Buscar", "nuevo","nuevoUsuario","reporte","Generar"};
		
		if(usr == null) {
			if(Arrays.asList(acciones).contains(req.getParameter("accion"))){
				chain.doFilter(req, res);
			}else {
				res.sendRedirect("login.jsp");
			}
		}else {
			chain.doFilter(req, res);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
