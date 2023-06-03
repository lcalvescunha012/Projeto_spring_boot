package med.voll.api.medico;

public record DadosListagemMedicos(String nome, Integer idade) {
    public DadosListagemMedicos(Medico medico) {
        this(medico.getNome(), medico.getIdade());
    }
}
