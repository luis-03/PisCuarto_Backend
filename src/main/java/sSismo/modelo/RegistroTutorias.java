package sSismo.modelo;


import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

@Entity
@Table(name = "registroTutorias")
public class RegistroTutorias implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @CreatedDate
    @Column(name = "create_at", updatable = false, columnDefinition = "datetime default now()")
    private Date created_at;
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;    
    @Column(length = 30)
    private String asignatura;
    @Column(length = 30)
    private String periodo;
    @Column(length = 30)
    private String paralelo;
    @Column(length = 30)
    private String carrera;
    @Column(length = 30)
    private String facultad;
    @Column(length = 36)
    private String external_id;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "id", name = "id_persona", nullable = false)
    private Persona persona;
    //@OneToMany(mappedBy = "registroTutorias", cascade = CascadeType.ALL)
    //private List<Tutorias> tutorias;
}
