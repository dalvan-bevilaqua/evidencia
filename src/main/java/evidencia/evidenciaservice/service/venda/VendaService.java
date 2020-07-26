package evidencia.evidenciaservice.service.venda;

import evidencia.evidenciaservice.model.Venda;
import evidencia.evidenciaservice.model.VendaProduto;
import evidencia.evidenciaservice.repository.venda.VendaRespository;
import evidencia.evidenciaservice.repository.vendaproduto.VendaProdutoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRespository vendaRespository;

    @Autowired
    private VendaProdutoRespository vendaProdutoRespository;

    @Transactional
    public Venda finalizarVenda(Venda vendaParm) {
        validarSeExisteProdutoNaVenda();

        Venda venda = Venda.builder()
                .formaPagamento(vendaParm.getFormaPagamento())
                .data(new Date())
                .build();

        venda = vendaRespository.save(venda);

        vendaProdutoRespository.finalizarVenda(venda.getCodigo());

        return venda;
    }

    private void validarSeExisteProdutoNaVenda() {
        List<VendaProduto> vendaProduto = vendaProdutoRespository.buscarProdutosVenda();
        if (vendaProduto.size() == 0) {
            throw new RuntimeException("Não exite Produto para finalizar");
        }
    }
}
