package tsii.rp.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ts2.rp.agodic2019.modelo.Hash;
import tsii.rp.conexion.BDConexion;


public class Operaciones {
	
	/**
	 * Metodo para obtener una lista de persona mediante una conexion a la DB 
	 * @return Object Lista de tipo Persona
	 */
	public static ArrayList<Persona> listarPersonas(){
		//consulta a la base de datos
		String consulta = "select * from persona p inner join pais as ps on p.idPais=ps.idPais";
		ArrayList<Persona> lp = new ArrayList<Persona>();
		Persona p = null;
		Pais ps = null;
		try(
				Connection con = BDConexion.obtenerConexion();
				Statement sentencia = con.createStatement();
				ResultSet rs = sentencia.executeQuery(consulta);
				)
		{
			while(rs.next()) {
				ps = new Pais(rs.getInt("idPais"),rs.getString("nombrePais"));
				p =new Persona(rs.getInt("ci"),rs.getString("nombre"),rs.getString("apellido"),
						rs.getString("fechaNac"),rs.getString("email"),rs.getString("telefono"),
						rs.getString("sexo"),ps);
				lp.add(p);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta para listar personas, "+e.getMessage());
		}
		
		return lp;
	}
	
	
	
	/**
	 * Metodo que permite insertar una nueva Persona a la base de datos
	 * @param p Objeto de tipo Persona conteniendo los datos a insertar a la base de datos
	 * @return boolean indicando el resultado de la operacion
	 * **/
	public static boolean nuevaPersona(Persona p) {
		
		String consulta = "insert into persona values(?,?,?,?,?,?,?,?)";
		boolean resultado = false;
		
		try(
				Connection con = BDConexion.obtenerConexion();
				//uso de sentencia preparada para evitar sql injection 
				//los parametros de la consulta deben ser especificado por comodines "?"
				PreparedStatement sentPreparada = con.prepareStatement(consulta);
				
				)
		{
			
			//se asigna el valor de cada comodin respetando el orden en la consulta (esto es importante)
			//y el tipo de dato de cada uno de ellos.
			sentPreparada.setInt(1, p.getCi());
			sentPreparada.setString(2, p.getNombre());
			sentPreparada.setString(3, p.getApellido());
			sentPreparada.setString(4, p.getFechaNac());
			sentPreparada.setString(5, p.getEmail());
			sentPreparada.setString(6, p.getTelefono());
			sentPreparada.setString(7, p.getSexo());
			//recordar que el idPais esta dentro de la clase Pais, que es un atributo de persona 
			sentPreparada.setInt(8, p.getPais().getIdPais());
			//se ejecuta la sentencia el excuteUpdate retorna un valor int, conteniendo la cantidad de filas
			//afectadas o 0 si se produce un error
			if(sentPreparada.executeUpdate() == 1) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la transaccion para insertar una persona, "+e.getMessage());
		}
				
		return resultado;
	}
	
	/**
	 * Metodo para obtener una persona mediante una conexion a la DB 
	 * @param Integer cedula
	 * @return Object de tipo Persona
	 */
	public static Persona buscarPersonaPorCI(Integer cedula){
		//consulta a la base de datos
		String consulta = "select * from persona p inner join pais as ps on p.idPais=ps.idPais where p.ci = ?";
		Persona p = null;
		Pais ps = null;
		try(
				Connection con = BDConexion.obtenerConexion();
				PreparedStatement sentPreparada = con.prepareStatement(consulta);
				)
		{
			sentPreparada.setInt(1, cedula);
			ResultSet rs = sentPreparada.executeQuery();
			if(rs.next()) {
				ps = new Pais(rs.getInt("idPais"),rs.getString("nombrePais"));
				p =new Persona(rs.getInt("ci"),rs.getString("nombre"),rs.getString("apellido"),
						rs.getString("fechaNac"),rs.getString("email"),rs.getString("telefono"),
						rs.getString("sexo"),ps);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta para buscar personas, "+e.getMessage());
		}
		
		return p;
	}
	
	
	/**
	 * Metodo para obtener una persona mediante una conexion a la DB 
	 * @param Integer cedula
	 * @return boolean
	 */
	public static boolean validarPersonaPorCI(Integer cedula){
		//consulta a la base de datos
		String consulta = "select * from persona where ci = ?";
		boolean resultado = false;
		try(
				Connection con = BDConexion.obtenerConexion();
				PreparedStatement sentPreparada = con.prepareStatement(consulta);
				)
		{
			sentPreparada.setInt(1, cedula);
			ResultSet rs = sentPreparada.executeQuery();
			if(rs.next()) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta para validar persona, "+e.getMessage());
		}
		
		return resultado;
	}
	
	/**
	 * Metodo para obtener un pais mediante una conexion a la DB 
	 * @param Integer idPais
	 * @return Object de tipo Pais
	 */
	public static Pais buscarPais(Integer idPais){
		//consulta a la base de datos
		String consulta = "select * from pais where idPais = ?";
		Pais ps = null;
		try(
				Connection con = BDConexion.obtenerConexion();
				PreparedStatement sentPreparada = con.prepareStatement(consulta);
				)
		{
			sentPreparada.setInt(1, idPais);
			ResultSet rs = sentPreparada.executeQuery();
			if(rs.next()) {
				ps = new Pais(rs.getInt("idPais"),rs.getString("nombrePais"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta para buscar pais, "+e.getMessage());
		}
		
		return ps;
	}
	
	/**
	 * Metodo para actualizar una persona mediante una conexion en la DB
	 * @param Object p 
	 * @return boolean resultado de la operacion
	 */
	public static boolean editarPersona(Persona p) {
		
		String consulta = "update persona set nombre=?, apellido=?, fechaNac=?, email=?, "
				+ "telefono=?, sexo=?, idPais=? where ci=?";
		boolean resultado = false;
		
		try(
				Connection con = BDConexion.obtenerConexion();
				PreparedStatement sentPreparada = con.prepareStatement(consulta);
				
				)
		{
		
			sentPreparada.setString(1, p.getNombre());
			sentPreparada.setString(2, p.getApellido());
			sentPreparada.setString(3, p.getFechaNac());
			sentPreparada.setString(4, p.getEmail());
			sentPreparada.setString(5, p.getTelefono());
			sentPreparada.setString(6, p.getSexo());
			sentPreparada.setInt(7, p.getPais().getIdPais());
			sentPreparada.setInt(8, p.getCi());
			
			if(sentPreparada.executeUpdate() == 1) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la transaccion para editar una persona, "+e.getMessage());
		}
				
		return resultado;
	}
	
	/**
	 * Metodo para actualizar un pais mediante una conexion en la DB
	 * @param Object Pais 
	 * @return boolean resultado de la operacion
	 */
	public static boolean editarPais(Pais pais) {
		
		String consulta = "update pais set nombrePais=? where idPais=?";
		boolean resultado = false;
		
		try(
				Connection con = BDConexion.obtenerConexion();
				PreparedStatement sentPreparada = con.prepareStatement(consulta);
				
				)
		{
		
			sentPreparada.setString(1, pais.getNombrePais());
			sentPreparada.setInt(2, pais.getIdPais());
			if(sentPreparada.executeUpdate() == 1) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la transaccion para editar un pais, "+e.getMessage());
		}
				
		return resultado;
	}
	
	/**
	 * Metodo para eliminar una persona a traves de una conexion a la DB 
	 * @param Integer cedula
	 * @return boolean resultado de la operacion
	 */
	public static boolean eliminarPersona(Integer cedula){
		//consulta a la base de datos
		String consulta = "delete from persona where ci = ?";
		boolean resultado = false;
		try(
				Connection con = BDConexion.obtenerConexion();
				PreparedStatement sentPreparada = con.prepareStatement(consulta);
				)
		{
			sentPreparada.setInt(1, cedula);
			
			if(sentPreparada.executeUpdate() == 1) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la transaccion de eliminar persona, "+e.getMessage());
		}
		
		return resultado;
	}
	
	/**
	 * Metodo para obtener una lista de pais mediante una conexion a la DB 
	 * @return Object Lista de tipo Pais
	 */
	public static ArrayList<Pais> listarPais(){
		//consulta a la base de datos
		String consulta = "select * from pais";
		ArrayList<Pais> lp = new ArrayList<Pais>();
		Pais ps = null;
		try(
				Connection con = BDConexion.obtenerConexion();
				Statement sentencia = con.createStatement();
				ResultSet rs = sentencia.executeQuery(consulta);
				)
		{
			while(rs.next()) {
				ps = new Pais(rs.getInt("idPais"),rs.getString("nombrePais"));
				lp.add(ps);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta para listar pais, "+e.getMessage());
		}
		
		return lp;
	}
	
	/**
	 * Metodo que permite insertar un nuevo Pais a la base de datos
	 * @param Object de tipo Pais conteniendo los datos a insertar a la base de datos
	 * @return boolean indicando el resultado de la operacion
	 * **/
	public static boolean nuevoPais(Pais pais) {
		
		String consulta = "insert into pais values(?)";
		boolean resultado = false;
		
		try(
				Connection con = BDConexion.obtenerConexion();
				//uso de sentencia preparada para evitar sql injection 
				//los parametros de la consulta deben ser especificado por comodines "?"
				PreparedStatement sentPreparada = con.prepareStatement(consulta);
				
				)
		{
			
			//se asigna el valor de cada comodin respetando el orden en la consulta (esto es importante)
			//y el tipo de dato de cada uno de ellos.
			sentPreparada.setString(1, pais.getNombrePais());
			//se ejecuta la sentencia el excuteUpdate retorna un valor int, conteniendo la cantidad de filas
			//afectadas o 0 si se produce un error
			if(sentPreparada.executeUpdate() == 1) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la transaccion para insertar un pais, "+e.getMessage());
		}
				
		return resultado;
	}
	
	
	public static Usuario validarUsuario(String usuario, String clave) {
		String consulta = "select * from usuario where usuario = ?";
		Usuario u = null;
		try(
				Connection con = BDConexion.obtenerConexion();
				PreparedStatement sentPreparada = con.prepareStatement(consulta);
				)
		{
			sentPreparada.setString(1, usuario);
			ResultSet rs = sentPreparada.executeQuery();
			if(rs.next()) {
				if(Hash.verificarClave(clave, rs.getString("salt"), rs.getString("clave"))) {
					u = new Usuario();
					u.setIdU(rs.getInt("idU"));
					u.setUsuario(rs.getString("usuario"));
					u.setPermiso(rs.getInt("permiso"));
				}
				
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta para validar un usuario, "+e.getMessage());
		}
		
		return u;
	}
	
	public static boolean registrarUsuario(String usuario, String clave) {
		String  consul = "SELECT * FROM usuario";
		String consulta = "insert into usuario values(?,?,?,?)";
		boolean resultado = false;
		boolean usuario_invalido = false;
		try(
				Connection con = BDConexion.obtenerConexion();
				Statement sentencia = con.createStatement();
				ResultSet usuarios = sentencia.executeQuery(consul);
				PreparedStatement sentPreparada = con.prepareStatement(consulta);
				)
		{
			if(usuarios.next()) {
//				if( (usuario == usuarios.getString("usuario") || Hash.verificarClave(clave, usuarios.getString("salt"), usuarios.getString("clave")))
//						|| ((usuario == usuarios.getString("usuario") && Hash.verificarClave(clave, usuarios.getString("salt"), usuarios.getString("clave"))))){
				if(usuario.equals(usuarios.getString("usuario"))){
					usuario_invalido = true;
					System.out.println("USUARIO INVALIDO "+ usuario_invalido);
					
				}
				
			}
			if (usuario_invalido == false) {
				
				String salt = Hash.generarSalt().substring(0, 64);
				System.out.println("SALT: "+salt + " "+salt.length());
			
				String hash = Hash.hashString(clave, salt);
				System.out.println("HASH: "+hash + " "+hash.length());
			
				sentPreparada.setString(1, usuario);
				sentPreparada.setString(2, hash);
				sentPreparada.setString(3, salt);
				sentPreparada.setInt(4, 1);
			
				if(sentPreparada.executeUpdate() == 1) {
					resultado = true;
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta para agregar un usuario, "+e.getMessage());
		}
		
		return resultado;
	}
	
	public static boolean buscarUsuario(String usuario) {
		String consulta = "select * from usuario where usuario = ?";
		boolean resultado = false;
		try(
				Connection con = BDConexion.obtenerConexion();
				PreparedStatement sentPreparada = con.prepareStatement(consulta);
				)
		{
			sentPreparada.setString(1, usuario);
			ResultSet rs = sentPreparada.executeQuery();
			if(rs.next()) {
				resultado = true;
			}
			
		}catch(SQLException e) {
			System.out.println("Error al realizar la busqueda de usuario, "+e.getMessage());
		}
		
		return resultado;
	}
	
	/**
	 * Método que permite listar todos los datos de la base de datos ordenados por una columna seleccionada por el usuario
	 * @param orden String, FechaNacimientoInicial String, FechaNacimientoFinal String
	 * @return ArrayList<Persona>
	 */
	public static ArrayList<Persona> generarReportePDF(String orden, String fechaNacimientoInicial, String fechaNacimientoFinal){
//		no se utiliza sentencia preparada simplemente porque el valor del parámetro orden corresponde a una columna de la base de datos
//		y no a un párametro, motivo por el cual simplemente se concatena
		System.out.println(((Object)fechaNacimientoInicial).getClass().getSimpleName() + " "+ ((Object)fechaNacimientoFinal).getClass().getSimpleName());
		String consulta = "declare @inicial INT = "+ fechaNacimientoInicial+", @final INT = "+fechaNacimientoFinal+
						" declare @ini as varchar(10), @fin as varchar(10)" +
						" select @ini = cast(@inicial as varchar(10)), @fin =cast(@final as varchar(10)) " +
						" declare @fechaini Date = @ini, @fechafin Date = @fin "+
						" select * from persona p inner join pais ps on p.idPais=ps.idPais where p.fechaNac between @fechaini" 
						+ " and " + "@fechafin" + " order by " + orden;
		ArrayList<Persona> lp = new ArrayList<Persona>();
		Pais ps = null;
		Persona p = null;
		try (
				Connection con = BDConexion.obtenerConexion();
				Statement sentencia = con.createStatement();
				ResultSet rs = sentencia.executeQuery(consulta);
			){
			while(rs.next()) {
				ps = new Pais(rs.getInt("idPais"), rs.getString("nombrePais"));
				p = new Persona(rs.getInt("ci"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("fechaNac"), rs.getString("email"), rs.getString("telefono"),
						rs.getString("sexo"), ps);
				lp.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta para listar Personas ordenadas, " + e.getMessage());
		}
		return lp;
	}
		
}

