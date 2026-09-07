package pe.edu.upeu.sysventas.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // geter and seter
@Builder // referenciar de manera directa "instancia"
@NoArgsConstructor // constructoe vacio
@AllArgsConstructor //  constructor con todos los parametros
public class Categoria {
    private Long idCategoria;
    private String nombre;
}