package com.duth.engapp.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class FavoriteId implements Serializable {
    private static final long serialVersionUID = 5695886831326989617L;
    @Column(name = "DICTIONARYID", nullable = false)
    private Long dictionaryid;
    @Column(name = "USERID", nullable = false)
    private Integer userid;
}