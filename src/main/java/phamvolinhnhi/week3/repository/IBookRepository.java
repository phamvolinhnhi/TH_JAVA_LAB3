package phamvolinhnhi.week3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phamvolinhnhi.week3.entity.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
}
