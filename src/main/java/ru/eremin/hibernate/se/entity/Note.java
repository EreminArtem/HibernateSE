package ru.eremin.hibernate.se.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * @autor Eremin Artem on 26.03.2019.
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "note_table")
public class Note implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "note_description")
    private String description;

    @Column(name = "note_next")
    private String text;

    @ManyToOne
    private Board board;

}
