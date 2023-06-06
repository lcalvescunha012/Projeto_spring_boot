package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

//Estudar JPA -- Esses atributos todos são de JPA
@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;
    private Date data;

    public Medico(DadosCadastroMedicos dados) {
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.data = new Date();
    }

    public void atualizarInfos(DadosUpdateMedicos dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.idade() != null) {
            this.idade = dados.idade();
        }


    }
}
