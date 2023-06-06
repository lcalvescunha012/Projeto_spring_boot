package med.voll.api.domain.medico;

public record DadosDetalhamentoMedico(Long id, String nome, Integer idade) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getIdade());
    }
}
