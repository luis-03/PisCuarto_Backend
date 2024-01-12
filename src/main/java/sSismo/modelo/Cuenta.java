package sSismo.modelo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@Entity
@Table(name = "cuenta")
@Getter
@Setter
public class Cuenta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 100)
    private String correo;
    @Column(length = 60)
    private String clave;
    @Column(length = 36)
    private String external_id;
    @CreatedDate
    @Column(name = "create_at", updatable = false, columnDefinition = "datetime default now()")
    private Date createAt;
    @LastModifiedDate
    @Column(name = "update_at", columnDefinition = "datetime default now()")
    private Date updateAt;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "id", unique = true, name = "id_persona")
    private Persona persona;
    
}
