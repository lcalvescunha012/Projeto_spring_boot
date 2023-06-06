package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicos dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    /* Get List normal
    @GetMapping
      public List<DadosListagemMedicos> Listar(){
        return repository.findAll().stream().map(DadosListagemMedicos::new).toList();
      }
      */
    // Com paginação
    //@PageableDefault -> Caso nao passe nenhum parametro na URL, muda o padrao do Spring
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> Listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { //Paginação -> numero quantidade de registros que deseja retornar
        //return repository.findAll(paginacao).map(DadosListagemMedicos::new);
        var result = repository.findAll(paginacao).map(DadosListagemMedicos::new);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosUpdateMedicos dados) {
        var medicos = repository.getReferenceById(dados.id());
        medicos.atualizarInfos(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medicos));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        // Muda o retorno do DELETE
        // O padrão é ter o retorno 204 (No Content) // E não o 200 -OK
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


}

/*
*********************PADRÕES DE ERROS*********************

1XX: Informativo – a solicitação foi aceita ou o processo continua em andamento;
2XX: Confirmação – a ação foi concluída ou entendida;
3XX: Redirecionamento – indica que algo mais precisa ser feito ou precisou ser feito para completar a solicitação;
4XX: Erro do cliente – indica que a solicitação não pode ser concluída ou contém a sintaxe incorreta;
5XX: Erro no servidor – o servidor falhou ao concluir a solicitação.
 */
