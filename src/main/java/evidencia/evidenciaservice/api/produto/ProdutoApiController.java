package evidencia.evidenciaservice.api.produto;

import evidencia.evidenciaservice.model.Produto;
import evidencia.evidenciaservice.model.dto.EstoqueProdutoDTO;
import evidencia.evidenciaservice.relatorio.ProdutoRelatorio;
import evidencia.evidenciaservice.repository.produto.ProdutoRepository;
import evidencia.evidenciaservice.service.produto.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoApiController {

    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    @GetMapping("/{codigoBarras}")
    public ResponseEntity<Produto> buscarProdutoPorCodigoBarras(@PathVariable Long codigoBarras) {
        Produto produtoSalvo = produtoRepository.selecionaProdutoPorCodigoBarras(codigoBarras);
        return (produtoSalvo != null) ? ResponseEntity.ok(produtoSalvo) :
                ResponseEntity.status(HttpStatus.ACCEPTED).body(produtoSalvo);
    }

    @GetMapping("estoque")
    public ResponseEntity<List<EstoqueProdutoDTO>> buscarEstorqueProduto() {
        List<EstoqueProdutoDTO> produtoSalvo = produtoService.estoqueProduto();

        return (produtoSalvo != null) ? ResponseEntity.ok(produtoSalvo) :
                ResponseEntity.status(HttpStatus.ACCEPTED).body(produtoSalvo);
    }

    @RequestMapping(value = "/relatorioEstoque", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> relatorioVendas() {
        List<EstoqueProdutoDTO> estoqueProdutoDTOList = produtoService.estoqueProduto();
        ByteArrayInputStream bis = ProdutoRelatorio.emitirRelatorioEstoqueProduto(estoqueProdutoDTOList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> buscarProdutos() {
        List<Produto> produtoSalvo = produtoRepository.findAll();
        return (produtoSalvo != null) ? ResponseEntity.ok(produtoSalvo) :
                ResponseEntity.status(HttpStatus.ACCEPTED).body(produtoSalvo);
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Produto> Editar(@PathVariable Long codigo,
                                          @RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.atualizar(codigo, produto));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) throws Exception {
        produtoService.deleteById(codigo);
    }

}