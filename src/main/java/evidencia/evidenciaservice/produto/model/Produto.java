package evidencia.evidenciaservice.produto.model;


import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @Column(name = "codigoBarras")
    private Long codigoBarras;

    @NotNull
    private String nome;

    @NotNull
    private BigDecimal valor;

}
