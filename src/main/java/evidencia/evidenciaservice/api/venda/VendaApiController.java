package evidencia.evidenciaservice.api.venda;

import evidencia.evidenciaservice.model.Venda;
import evidencia.evidenciaservice.service.venda.VendaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/venda")
@AllArgsConstructor
public class VendaApiController {

    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<Venda> finalizarVenda(@RequestBody Venda venda) {
        return ResponseEntity.ok(vendaService.finalizarVenda(venda));
    }
}
