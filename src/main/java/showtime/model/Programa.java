package showtime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;;
import lombok.*;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Programa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serie;
    private Integer year;

    @ManyToMany(mappedBy = "programas")
    @JsonIgnore

    private List<Rider> riders;
}
