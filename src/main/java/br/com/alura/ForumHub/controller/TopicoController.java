package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.curso.usuario.topico.CursoRepository;
import br.com.alura.ForumHub.domain.curso.usuario.topico.StatusTopico;
import br.com.alura.ForumHub.domain.curso.usuario.topico.Topico;
import br.com.alura.ForumHub.domain.curso.usuario.topico.TopicoRepository;
import br.com.alura.ForumHub.domain.curso.usuario.topico.UsuarioRepository;
import br.com.alura.ForumHub.dto.topico.DadosAtualizacaoTopico;
import br.com.alura.ForumHub.dto.topico.DadosCadastroTopico;
import br.com.alura.ForumHub.dto.topico.DadosDetalhamentoTopico;
import br.com.alura.ForumHub.dto.topico.DadosListagemTopico;
import jakarta.validation.Valid;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {
        if (topicoRepository.existsByTitulo(dados.titulo())) {
            return ResponseEntity.badRequest().body("Erro: Título já existe.");
        }
        if (topicoRepository.existsByMensagem(dados.mensagem())) {
            return ResponseEntity.badRequest().body("Erro: Mensagem já existe.");
        }

        var autor = usuarioRepository.findById(dados.autorId()).orElse(null);
        var curso = cursoRepository.findById(dados.cursoId()).orElse(null);

        if (autor == null || curso == null) {
            return ResponseEntity.badRequest().body("Autor ou Curso não encontrado.");
        }

        var novoTopico = new Topico(dados.titulo(), dados.mensagem(), LocalDateTime.now(), StatusTopico.ABERTO, autor, curso);
        topicoRepository.save(novoTopico);

        return ResponseEntity.ok("Tópico cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {

        var page = topicoRepository.findAll(paginacao);
        var dados = page.map(DadosListagemTopico::new);

        return ResponseEntity.ok(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);

        if (topico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico.get()));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados) {
        Optional<Topico> topicoEncontrado = topicoRepository.findById(dados.id());
        if (topicoEncontrado.isPresent()) {
            Topico topico = topicoEncontrado.get();
            if (dados.titulo() != null && topicoRepository.existsByTitulo(dados.titulo()) && !topico.getTitulo().equalsIgnoreCase(dados.titulo())) {
                return ResponseEntity.badRequest().body("Erro: Título já existe.");
            }
            if (dados.mensagem() != null && topicoRepository.existsByMensagem(dados.mensagem()) && !topico.getMensagem().equalsIgnoreCase(dados.mensagem())) {
                return ResponseEntity.badRequest().body("Erro: Mensagem já existe.");
            }

            topico.atualizar(dados);
            return ResponseEntity.ok("Tópico atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
