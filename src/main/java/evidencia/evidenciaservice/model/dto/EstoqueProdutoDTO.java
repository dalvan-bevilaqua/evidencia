package evidencia.evidenciaservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstoqueProdutoDTO {

    private Long codigo;
    private Long codigoBarras;
    private String nome;
    private Long quantidadeVendida;
    private Long quantidadeEntrada;
    private Long quantidadeEstoque;

}
