package evidencia.evidenciaservice.model.dto;

import evidencia.evidenciaservice.model.Caixa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaixaDTO {
    List<Caixa> caixa;
    BigDecimal totalEntrada;
    BigDecimal totalSaida;
    BigDecimal totalSaldo;
}
