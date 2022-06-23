package com.example.RentalServiceProject.repo.specification;

import com.example.RentalServiceProject.dto.SearchCriteria;
import com.example.RentalServiceProject.model.AssetBooking;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AssetBookingSpecification implements Specification<AssetBooking> {

    private SearchCriteria searchCriteria;

    public AssetBookingSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<AssetBooking> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(searchCriteria.getOperation().equalsIgnoreCase(":")){
            return  criteriaBuilder.equal(root.get(searchCriteria.getKey()),searchCriteria.getValue());
        }
        else if(searchCriteria.getOperation().equalsIgnoreCase(">")){
            return criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()),searchCriteria.getValue().toString());
        }
        else if(searchCriteria.getOperation().equalsIgnoreCase("<")){
            return criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()),searchCriteria.getValue().toString());
        }
        return null;

    }
}
