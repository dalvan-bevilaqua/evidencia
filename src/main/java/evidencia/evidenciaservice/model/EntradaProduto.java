package evidencia.evidenciaservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "entradaproduto")
@AllArgsConstructor
@NoArgsConstructor
public class EntradaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "codigoEntrada")
    private Long codigoEntrada;

    @Column(name = "codigoProduto")
    private Long codigoProduto;

    @Column(name = "codigoBarras")
    private Long codigoBarras;

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "quantidade")
    private String quantidade;

}
