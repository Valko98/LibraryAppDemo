package com.example.library.repositories;

import com.example.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    static User FindById(Long userId) {
      return  UserRepository.FindById(userId);
    }
}
