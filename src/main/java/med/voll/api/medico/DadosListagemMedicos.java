package med.voll.api.medico;

public record DadosListagemMedicos(Long id, String nome, Integer idade) {
    public DadosListagemMedicos(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getIdade());
    }
}
