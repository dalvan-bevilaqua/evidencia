package evidencia.evidenciaservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "vendaproduto")
@AllArgsConstructor
@NoArgsConstructor
public class VendaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "codigoVenda")
    private Long codigoVenda;

    @Column(name = "codigoProduto")
    private Long codigoProduto;

    @Column(name = "codigoBarras")
    private Long codigoBarras;

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "valorVendido")
    private BigDecimal valorVendido;


}
