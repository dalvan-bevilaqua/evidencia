package evidencia.evidenciaservice.api.entradaproduto;

import evidencia.evidenciaservice.model.EntradaProduto;
import evidencia.evidenciaservice.repository.entradaproduto.EntradaProdutoRepository;
import evidencia.evidenciaservice.service.entradaproduto.EntradaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/entradaproduto")

public class EntradaProdutoApiController {
    @Autowired
    EntradaProdutoService entradaProdutoService;

    @Autowired
    EntradaProdutoRepository entradaProdutoRepository;

    @PostMapping
    public ResponseEntity<EntradaProduto> salvar(@RequestBody EntradaProduto entradaProduto) {
        EntradaProduto entradaProdutoSalvo = entradaProdutoService.salvar(entradaProduto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entradaProdutoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<EntradaProduto>> buscar() {
        List<EntradaProduto> entradaProdutos = entradaProdutoRepository.buscarProdutosEntrada();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(entradaProdutos);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        entradaProdutoRepository.deleteById(codigo);
    }
}
