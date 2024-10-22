package pe.edu.cibertec.patita_frontend_wc_a.dto;

//JsonIgnoreProperties(ignoreUnknown = true)
public record LoginResponseDTO(String codigo, String mensaje,String nombreUsuario,String correoUsuario) {
}
