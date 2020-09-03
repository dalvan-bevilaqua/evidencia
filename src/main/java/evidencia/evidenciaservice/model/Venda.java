package evidencia.evidenciaservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@Entity
@Table(name = "venda")
@AllArgsConstructor
@NoArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "data")
    private Date data;

    @Transient
    private Date dataFinal;

    @Column(name = "formaPagamento")
    private String formaPagamento;

    @Column(name = "valorVenda")
    private BigDecimal valorVenda;

}
