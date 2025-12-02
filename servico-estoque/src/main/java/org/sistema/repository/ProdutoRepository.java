package org.sistema.repository;

import org.sistema.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// A interface JpaRepository da todos os métodos CRUD (save, findById, findAll, delete...)
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}