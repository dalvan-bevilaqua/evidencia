package evidencia.evidenciaservice.service.entrada;

import evidencia.evidenciaservice.model.Entrada;
import evidencia.evidenciaservice.model.EntradaProduto;
import evidencia.evidenciaservice.model.Venda;
import evidencia.evidenciaservice.model.VendaProduto;
import evidencia.evidenciaservice.repository.entrada.EntradaRepository;
import evidencia.evidenciaservice.repository.entradaproduto.EntradaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class EntradaService {

    @Autowired
    EntradaRepository entradaRepository;

    @Autowired
    EntradaProdutoRepository entradaProdutoRepository;

    @Transactional
    public Entrada finalizarEntrada(Entrada entradaParm) {

        validarSeExisteProdutoNaEntrda();
        Entrada entrada = Entrada.builder()
                .data(new Date())
                .build();

        entrada = entradaRepository.save(entrada);
        entradaProdutoRepository.finalizarEntrada(entrada.getCodigo());

        return entrada;
    }

    private void validarSeExisteProdutoNaEntrda() {
        List<EntradaProduto> entradaProdutos = entradaProdutoRepository.buscarProdutosEntrada();
        if (entradaProdutos.size() == 0) {
            throw new RuntimeException("Não exite Produto para finalizar");
        }
    }
}
