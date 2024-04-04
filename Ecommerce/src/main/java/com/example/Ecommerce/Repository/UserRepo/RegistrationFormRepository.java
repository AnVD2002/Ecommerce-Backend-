package com.example.Ecommerce.Repository.UserRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RegistrationFormRepository extends JpaRepository<RegistrationForm,Integer> {
}
