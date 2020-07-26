package evidencia.evidenciaservice.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@Data
@Table(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
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
