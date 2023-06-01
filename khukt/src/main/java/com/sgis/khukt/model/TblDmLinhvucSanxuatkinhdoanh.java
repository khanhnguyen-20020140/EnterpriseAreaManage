/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nguyen Quoc Khanh
 */
@Entity
@Table(name = "tbl_dm_linhvuc_sanxuatkinhdoanh")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDmLinhvucSanxuatkinhdoanh.findAll", query = "SELECT t FROM TblDmLinhvucSanxuatkinhdoanh t"),
    @NamedQuery(name = "TblDmLinhvucSanxuatkinhdoanh.findByIdLinhvucSanxuatkinhdoanh", query = "SELECT t FROM TblDmLinhvucSanxuatkinhdoanh t WHERE t.idLinhvucSanxuatkinhdoanh = :idLinhvucSanxuatkinhdoanh"),
    @NamedQuery(name = "TblDmLinhvucSanxuatkinhdoanh.findByLinhvucSanxuatkinhdoanh", query = "SELECT t FROM TblDmLinhvucSanxuatkinhdoanh t WHERE t.linhvucSanxuatkinhdoanh = :linhvucSanxuatkinhdoanh")})
public class TblDmLinhvucSanxuatkinhdoanh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_linhvuc_sanxuatkinhdoanh")
    private Integer idLinhvucSanxuatkinhdoanh;
    @Size(max = 2147483647)
    @Column(name = "linhvuc_sanxuatkinhdoanh")
    private String linhvucSanxuatkinhdoanh;

    public TblDmLinhvucSanxuatkinhdoanh() {
    }

    public TblDmLinhvucSanxuatkinhdoanh(Integer idLinhvucSanxuatkinhdoanh) {
        this.idLinhvucSanxuatkinhdoanh = idLinhvucSanxuatkinhdoanh;
    }

    public Integer getIdLinhvucSanxuatkinhdoanh() {
        return idLinhvucSanxuatkinhdoanh;
    }

    public void setIdLinhvucSanxuatkinhdoanh(Integer idLinhvucSanxuatkinhdoanh) {
        this.idLinhvucSanxuatkinhdoanh = idLinhvucSanxuatkinhdoanh;
    }

    public String getLinhvucSanxuatkinhdoanh() {
        return linhvucSanxuatkinhdoanh;
    }

    public void setLinhvucSanxuatkinhdoanh(String linhvucSanxuatkinhdoanh) {
        this.linhvucSanxuatkinhdoanh = linhvucSanxuatkinhdoanh;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLinhvucSanxuatkinhdoanh != null ? idLinhvucSanxuatkinhdoanh.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDmLinhvucSanxuatkinhdoanh)) {
            return false;
        }
        TblDmLinhvucSanxuatkinhdoanh other = (TblDmLinhvucSanxuatkinhdoanh) object;
        if ((this.idLinhvucSanxuatkinhdoanh == null && other.idLinhvucSanxuatkinhdoanh != null) || (this.idLinhvucSanxuatkinhdoanh != null && !this.idLinhvucSanxuatkinhdoanh.equals(other.idLinhvucSanxuatkinhdoanh))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblDmLinhvucSanxuatkinhdoanh[ idLinhvucSanxuatkinhdoanh=" + idLinhvucSanxuatkinhdoanh + " ]";
    }
    
}
