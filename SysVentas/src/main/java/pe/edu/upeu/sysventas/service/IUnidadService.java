package pe.edu.upeu.sysventas.service;

import javafx.scene.control.ComboBox;
import pe.edu.upeu.sysventas.dto.ComboBoxOption;
import pe.edu.upeu.sysventas.model.UnidMedida;

import java.util.List;

public interface IUnidadService extends  ICrudGenericoService<UnidMedida,Long>{
    List<ComboBoxOption> listarCombobox();
}
