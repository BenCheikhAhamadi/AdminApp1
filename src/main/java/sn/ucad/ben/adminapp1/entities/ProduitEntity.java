package sn.ucad.ben.adminapp1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_produit")
public class ProduitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produit")
    private int id;
    @Column(unique = true,nullable = false)
    private String nom;
    private double qtStock;
    @ManyToOne
    private UtilisateurEntity utilisateurEntity;
}
