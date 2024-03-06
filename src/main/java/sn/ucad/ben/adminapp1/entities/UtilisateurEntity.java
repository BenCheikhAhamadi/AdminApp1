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
@Table(name="t_utilisateur")
public class UtilisateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_utilisateur")
    private int id;
    @Column(nullable = false,length = 150)
    private String nom;
    @Column(nullable = false,length = 200)
    private String prenom;
    @Column(nullable = false,length = 200)
    private String email;
    @Column(nullable = false,length = 200)
    private String password;
    private int etat;
    @ManyToMany
    private List<RoleEntity> roles;
    @OneToMany(mappedBy = "utilisateurEntity")
    private List<ProduitEntity> produitEntity;
}
