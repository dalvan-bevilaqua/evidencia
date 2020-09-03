package evidencia.evidenciaservice.repository.venda;

import evidencia.evidenciaservice.model.Venda;
import evidencia.evidenciaservice.model.filter.VendaFilter;

import java.util.List;

public interface VendaRepositoryCustom {
    List<Venda> buscarVendaPorData(VendaFilter vendaFilter);
}
