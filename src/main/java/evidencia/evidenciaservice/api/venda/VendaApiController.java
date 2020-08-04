package evidencia.evidenciaservice.api.venda;

import evidencia.evidenciaservice.model.Venda;
import evidencia.evidenciaservice.repository.venda.VendaRespository;
import evidencia.evidenciaservice.service.venda.VendaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/venda")
@AllArgsConstructor
public class VendaApiController {

    private VendaService vendaService;
    private VendaRespository vendaRespository;

    @PostMapping
    public ResponseEntity<Venda> finalizarVenda(@RequestBody Venda venda) {
        return ResponseEntity.ok(vendaService.finalizarVenda(venda));
    }

    @GetMapping
    public ResponseEntity<List<Venda>> buscarVenda() {
        List<Venda> vendasSalvas = vendaRespository.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
        return (vendasSalvas != null) ? ResponseEntity.ok(vendasSalvas) :
                ResponseEntity.status(HttpStatus.ACCEPTED).body(vendasSalvas);
    }

}
