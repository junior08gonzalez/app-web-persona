package tsii.rp.modelo;

public class Pais {

	private Integer idPais;
	private String nombrePais;
	
	public Pais() {
	}
	
	/**
	 * Constructor Parametrizado
	 * @param isPais Integer
	 * @param nombrePais String
	 * **/
	public Pais(Integer idPais, String nombrePais) {
		
		this.idPais = idPais;
		this.nombrePais = nombrePais;
	}
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	
}
