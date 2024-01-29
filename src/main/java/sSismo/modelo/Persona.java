package sSismo.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity(name = "persona")
public class Persona implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 75)
    private String apellidos;
    @Column(length = 75)
    private String nombres;
    @Column(length = 15)
    private String identificacion; 
    @Column(length = 30)
    private String direccion;
    @Column(length = 15)
    private String telefono;
    @Column(length = 36)
    private String external_id;
    @CreatedDate
    @Column(name = "create_at", updatable = false, columnDefinition = "datetime default now()")
    private Date createAt;
    @LastModifiedDate
    @Column(name = "update_at", columnDefinition = "datetime default now()")
    private Date updateAt;
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Cuenta cuenta;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "id", name = "id_rol")
    private Rol rol;
}
