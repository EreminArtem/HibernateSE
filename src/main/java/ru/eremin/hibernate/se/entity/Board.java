package ru.eremin.hibernate.se.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @autor Eremin Artem on 26.03.2019.
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "board_table")
public class Board implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "board_name")
    private String name;

    @Column(name = "board_date")
    private Date date;

}
