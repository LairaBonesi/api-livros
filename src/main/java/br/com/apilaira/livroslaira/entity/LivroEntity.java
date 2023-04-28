package br.com.apilaira.livroslaira.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "livro")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "autor")
    private String autor;

    @Column(name = "paginas")
    private Integer paginas;

    @Column(name = "genero")
    private String genero;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "livro_status",
        joinColumns = @JoinColumn(name = "livro_id"),
        inverseJoinColumns = @JoinColumn(name = "status_id", updatable = false, insertable = false)
    )
    private List<StatusLivroEntity> status = new ArrayList<>();


}