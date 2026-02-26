package week4.lab2a.tuswadlab42a.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import week4.lab2a.tuswadlab42a.repository.LocationRepository;

@Entity
@Table(name="restaurant")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonBackReference
    private Location location;
}
