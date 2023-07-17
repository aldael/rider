package showtime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rider;
    private String identidad;
    private String armor;
    private String serie;

    @ManyToMany
    @JoinTable(
            name = "aparicion",
            joinColumns = @JoinColumn(name = "rider_id"),
            inverseJoinColumns = @JoinColumn(name = "show_id")
    )
    private List<Programa> programas = new ArrayList<>();
}


