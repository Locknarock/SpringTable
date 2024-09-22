package com.springtableforportfolio.table.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springtableforportfolio.table.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
