package br.com.alura.ForumHub.domain.curso.usuario.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTitulo(String titulo);
    boolean existsByMensagem(String mensagem);

    Page<Topico> findByCursoNome(String nome, Pageable paginacao);

    @Query("SELECT t FROM Topico t WHERE YEAR(t.dataCriacao) = :ano")
    Page<Topico> findByAno(@Param("ano") int ano, Pageable paginacao);

    Page<Topico> findByCursoNomeAndDataCriacaoYear(String nome, int ano, Pageable paginacao);
}
