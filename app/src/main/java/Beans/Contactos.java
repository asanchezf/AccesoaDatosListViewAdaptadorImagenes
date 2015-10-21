package Beans;

public class Contactos {
	
	
	/*
 *Id_Zona:
 *          1:Alcorcón y alrededores
 * 			2:Madrid capital
 * 			3:Madrid CC.AA.
 * 			4:Otra CC.AA..
 * 			5:Otro País
 * 		    6:Otros
 * valorar posibilidad de crear otra tabla...
 * */
	
	private long _id;
	private String Nombre;
	private String Apellidos;
	private String Direccion;
	private String Telefono;
	private String Email;
	private int Id_Zona;
	private String Observaciones;

	public Contactos(long _id, String nombre, String apellidos,
					 String direccion, String telefono, String email, int id_Zona,
					 String observaciones) {
		super();
		this._id = _id;
		Nombre = nombre;
		Apellidos = apellidos;
		Direccion = direccion;
		Telefono = telefono;
		Email = email;
		Id_Zona = id_Zona;
		Observaciones = observaciones;
	}

	public Contactos(long _id,String nombre, String email) {
		super();
		this._id = _id;
		Nombre = nombre;
		Email = email;
	}

	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getId_Zona() {
		return Id_Zona;
	}
	public void setId_Categoria(int id_Categoria) {
		Id_Zona = id_Categoria;
	}
	public String getObservaciones() {
		return Observaciones;
	}
	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}
	public long get_id() {
		return _id;
	}
	
	
	
	
	
}
