package sSismo.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "nodos")
public class Nodo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(length = 50)
    private String referencia;

    @Column(length = 50)
    private String facultad;

    @Column
    private double latitud;

    @Column
    private double longitud;

    @Column(name = "tipo_de_nodo", length = 20)
    private String tipoDeNodo; // "Nodo de paso", "Zona segura", etc.

    @ManyToOne
    @JoinColumn(name = "nodo_padre_external_id")
    @JsonBackReference
    private Nodo nodoPadre; // Autoreferencia para representar la conexión entre nodos

    @OneToMany(mappedBy = "nodoPadre", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Nodo> nodosConectados;
    
    @Column(length = 50)
    private String external_id;

    // Otros campos si es necesario

    // Getters y setters
}
