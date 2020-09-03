package evidencia.evidenciaservice.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaFilter {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInicial;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataFinal;
}
