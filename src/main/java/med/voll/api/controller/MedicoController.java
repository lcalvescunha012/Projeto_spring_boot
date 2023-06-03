package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedicos;
import med.voll.api.medico.DadosListagemMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedicos dados) {
        repository.save(new Medico(dados));
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
    public Page<DadosListagemMedicos> Listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { //Paginação -> numero quantidade de registros que deseja retornar
        return repository.findAll(paginacao).map(DadosListagemMedicos::new);
    }

}
