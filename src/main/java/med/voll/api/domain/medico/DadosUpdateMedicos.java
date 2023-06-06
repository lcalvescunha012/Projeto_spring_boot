package med.voll.api.domain.medico;


import jakarta.validation.constraints.NotNull;

public record DadosUpdateMedicos(@NotNull Long id, String nome, Integer idade) {
}
