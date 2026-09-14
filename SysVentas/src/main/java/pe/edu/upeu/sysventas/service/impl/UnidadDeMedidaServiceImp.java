package pe.edu.upeu.sysventas.service.impl;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.sysventas.model.UnidMedida;
import pe.edu.upeu.sysventas.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysventas.repository.UnidadMedidaRepository;
import pe.edu.upeu.sysventas.service.IUnidadService;
@RequiredArgsConstructor
public class UnidadDeMedidaServiceImp extends  CrudGenericoServiceImp<UnidMedida, Long> implements IUnidadService {
    private final UnidadMedidaRepository unidadMedidaRepository;


    @Override
    protected ICrudGenericoRepository<UnidMedida, Long> getRepo() {
        return unidadMedidaRepository;
    }
}
