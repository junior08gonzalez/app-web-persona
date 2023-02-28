package tsii.rp.modelo;

public class Persona {
	
	private Integer ci;
	private String nombre, apellido, fechaNac, email, telefono, sexo;
	private Pais pais;
	
	public Persona() {
		
	}


	/**
	 * Constructor Parametrizado
	 * @param ci Integer
	 * @param nombre String
	 * @param apellido String
	 * @param fechaNac String
	 * @param email String
	 * @param telefono String
	 * @param sexo String
	 * @param pais Pais(idPais Integer, nombre String)
	 * **/
	public Persona(Integer ci, String nombre, String apellido, String fechaNac, String email, String telefono,
			String sexo, Pais pais) {
		
		this.ci = ci;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.email = email;
		this.telefono = telefono;
		this.sexo = sexo;
		this.pais = pais;
	}

	
	public Integer getCi() {
		return ci;
	}

	public void setCi(Integer ci) {
		this.ci = ci;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
	

}
