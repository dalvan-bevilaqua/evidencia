package evidencia.evidenciaservice.service.entradaproduto;

import evidencia.evidenciaservice.model.EntradaProduto;
import evidencia.evidenciaservice.repository.entradaproduto.EntradaProdutoRepository;
import evidencia.evidenciaservice.repository.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EntradaProdutoService {

    @Autowired
    private EntradaProdutoRepository entradaProdutoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public EntradaProduto salvar(EntradaProduto entradaProduto) {

        return entradaProdutoRepository.save(entradaProduto);
    }
}
