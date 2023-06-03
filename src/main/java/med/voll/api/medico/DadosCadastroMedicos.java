package med.voll.api.medico;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

public record DadosCadastroMedicos(@NotBlank String nome, @NumberFormat Integer idade) {
}
