package evidencia.evidenciaservice.api.entrada;

import evidencia.evidenciaservice.model.Entrada;
import evidencia.evidenciaservice.service.entrada.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/entrada")

public class EntradaApiController {

    @Autowired
    EntradaService entradaService;


    @PostMapping
    public ResponseEntity<Entrada> salvar(@RequestBody Entrada entrada) {
        return ResponseEntity.ok(entradaService.finalizarEntrada(entrada));
    }

}