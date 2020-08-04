package evidencia.evidenciaservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@Entity
@Table(name = "caixa")
@AllArgsConstructor
@NoArgsConstructor
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "data")
    private Date data;

    @Column(name = "codigoVenda")
    private Long codigoVenda;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valorEntrada")
    private BigDecimal valorEntrada;

    @Column(name = "valorSaida")
    private BigDecimal valorSaida;

}
