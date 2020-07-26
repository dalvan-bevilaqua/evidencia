package evidencia.evidenciaservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "formaPagamento")
    private String formaPagamento;

}
