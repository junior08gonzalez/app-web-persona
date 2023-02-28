package tsii.rp.modelo;

public class Usuario {

	private Integer idU, permiso;
	private String usuario, clave, salt;
	public Usuario(Integer idU, Integer permiso, String usuario, String clave, String salt) {
		this.idU = idU;
		this.permiso = permiso;
		this.usuario = usuario;
		this.clave = clave;
		this.salt = salt;
	}
	
	public Usuario() {
		
	}

	public Integer getIdU() {
		return idU;
	}

	public void setIdU(Integer idU) {
		this.idU = idU;
	}

	public Integer getPermiso() {
		return permiso;
	}

	public void setPermiso(Integer permiso) {
		this.permiso = permiso;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	
}
