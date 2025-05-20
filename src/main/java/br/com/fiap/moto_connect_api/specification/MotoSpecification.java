package br.com.fiap.moto_connect_api.specification;

import br.com.fiap.moto_connect_api.controller.MotoController;
import br.com.fiap.moto_connect_api.model.Moto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MotoSpecification {

    public static Specification<Moto> withFilters(MotoController.MotoFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter == null) return null;

            if (filter.placa() != null && !filter.placa().isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("placa")), "%" + filter.placa().toLowerCase() + "%"));
            }

            if (filter.modelo() != null) {
                predicates.add(cb.equal(root.get("modelo"), filter.modelo()));
            }

            if (filter.status() != null) {
                predicates.add(cb.equal(root.get("status"), filter.status()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

