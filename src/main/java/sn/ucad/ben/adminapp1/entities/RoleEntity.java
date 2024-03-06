package sn.ucad.ben.adminapp1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_role")
    private int id;
    @Column(unique = true,nullable = false,length = 100)
    private String nom;
    @ManyToMany(mappedBy = "roles")
    private List<UtilisateurEntity> utilisateurEntity;
}
