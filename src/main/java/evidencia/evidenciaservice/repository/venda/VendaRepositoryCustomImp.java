package evidencia.evidenciaservice.repository.venda;

import evidencia.evidenciaservice.model.Venda;
import evidencia.evidenciaservice.model.filter.VendaFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VendaRepositoryCustomImp implements VendaRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Venda> buscarVendaPorData(VendaFilter vendaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Venda> criteria = builder.createQuery(Venda.class);
        Root<Venda> root = criteria.from(Venda.class);

        //Restrições
        Predicate[] predicates = criarRestricoes(vendaFilter, builder, root);
        criteria.where(predicates);

        //Order By
        List<Order> orderList = new ArrayList();
        orderList.add(builder.desc(root.get("codigo")));
        criteria.orderBy(orderList);

        TypedQuery<Venda> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] criarRestricoes(VendaFilter vendaFilter, CriteriaBuilder builder, Root<Venda> root) {

        List<Predicate> predicates = new ArrayList<>();
        Expression data = root.get("data");

        if (vendaFilter.getDataInicial() != null) {
            predicates.add(builder.greaterThanOrEqualTo(builder.function("Date", Date.class, data), vendaFilter.getDataInicial()));
        }

        if (vendaFilter.getDataFinal() != null) {
            predicates.add(builder.lessThanOrEqualTo(builder.function("Date", Date.class, data), vendaFilter.getDataFinal()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
