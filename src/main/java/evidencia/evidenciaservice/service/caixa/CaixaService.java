package evidencia.evidenciaservice.service.caixa;

import evidencia.evidenciaservice.model.Caixa;
import evidencia.evidenciaservice.model.dto.CaixaDTO;
import evidencia.evidenciaservice.repository.caixa.CaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class CaixaService {

    @Autowired
    CaixaRepository caixaRepository;

    public List<Caixa> buscarVendas() {
        List<Caixa> caixa = caixaRepository.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
        return caixa;
    }

    public CaixaDTO getCaixaDTO() {
        BigDecimal totalEntrada = caixaRepository.getTotalEntrada();
        BigDecimal totalSaida = caixaRepository.getTotalSaida();
        BigDecimal totalSaldo = totalEntrada.subtract(Objects.isNull(totalSaida) ? BigDecimal.valueOf(0) : totalSaida);

        return CaixaDTO.builder()
                .caixa(buscarVendas())
                .totalEntrada(totalEntrada)
                .totalSaida(totalSaida)
                .totalSaldo(totalSaldo)
                .build();
    }
}
