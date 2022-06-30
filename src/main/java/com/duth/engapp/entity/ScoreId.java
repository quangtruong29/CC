package com.duth.engapp.entity;

import lombok.Data;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class ScoreId implements Serializable {
    private static final long serialVersionUID = -8709543732309686086L;
    @Column(name = "USERID", nullable = false)
    private Integer userid;
    @Column(name = "SETID", nullable = false)
    private Integer setid;

    public ScoreId(Integer userid, Integer setid) {
        this.userid = userid;
        this.setid = setid;
    }

    public ScoreId() {

    }

    public Integer getSetid() {
        return setid;
    }

    public void setSetid(Integer setid) {
        this.setid = setid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(setid, userid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ScoreId entity = (ScoreId) o;
        return Objects.equals(this.setid, entity.setid) &&
                Objects.equals(this.userid, entity.userid);
    }
}