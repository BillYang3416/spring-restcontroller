package com.example.springwebservice.model.specification;

import com.example.springwebservice.model.entity.User;
import org.springframework.data.jpa.domain.Specification;

public final class UserSpecifications {

    public static Specification<User> likeFirstName(String firstName) {
        if (firstName == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
        };
    }

    public static Specification<User> likeLastName(String lastName) {
        if (lastName == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%");
        };
    }

    public static Specification<User> equalGender(String gender) {
        if (gender == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("gender"), "%" + gender + "%");
        };
    }
}
