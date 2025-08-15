package br.com.alura.ForumHub.dto.topico;

import br.com.alura.ForumHub.domain.curso.usuario.topico.Topico;
import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String status,
        String autor,
        String curso
) {
    public DadosListagemTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus().toString(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );
    }
}