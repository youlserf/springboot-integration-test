package com.repcode.demo.dao;

import com.repcode.demo.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<BookEntity, Long> {
}
