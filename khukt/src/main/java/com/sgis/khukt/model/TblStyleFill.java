/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nguyen Quoc Khanh
 */
@Entity
@Table(name = "tbl_style_fill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblStyleFill.findAllOrderByID", query = "SELECT t FROM TblStyleFill t ORDER BY t.quyhoachId.id ASC"),
    @NamedQuery(name = "TblStyleFill.findById", query = "SELECT t FROM TblStyleFill t WHERE t.id = :id"),
    @NamedQuery(name = "TblStyleFill.findByFillAntialias", query = "SELECT t FROM TblStyleFill t WHERE t.fillAntialias = :fillAntialias"),
    @NamedQuery(name = "TblStyleFill.findByFillColor", query = "SELECT t FROM TblStyleFill t WHERE t.fillColor = :fillColor"),
    @NamedQuery(name = "TblStyleFill.findByFillOpacity", query = "SELECT t FROM TblStyleFill t WHERE t.fillOpacity = :fillOpacity"),
    @NamedQuery(name = "TblStyleFill.findByFillOutlineColor", query = "SELECT t FROM TblStyleFill t WHERE t.fillOutlineColor = :fillOutlineColor"),
    @NamedQuery(name = "TblStyleFill.findByFillSortKey", query = "SELECT t FROM TblStyleFill t WHERE t.fillSortKey = :fillSortKey"),
    @NamedQuery(name = "TblStyleFill.findByFillTranslate", query = "SELECT t FROM TblStyleFill t WHERE t.fillTranslate = :fillTranslate"),
    @NamedQuery(name = "TblStyleFill.findByFillTranslateAnchor", query = "SELECT t FROM TblStyleFill t WHERE t.fillTranslateAnchor = :fillTranslateAnchor"),
    @NamedQuery(name = "TblStyleFill.findByVisibility", query = "SELECT t FROM TblStyleFill t WHERE t.visibility = :visibility")})
public class TblStyleFill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fill_antialias")
    private Boolean fillAntialias;
    @Size(max = 50)
    @Column(name = "fill_color")
    private String fillColor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fill_opacity")
    private BigDecimal fillOpacity;
    @Size(max = 50)
    @Column(name = "fill_outline_color")
    private String fillOutlineColor;
    @Column(name = "fill_sort_key")
    private Integer fillSortKey;
    @Size(max = 100)
    @Column(name = "fill_translate")
    private String fillTranslate;
    @Size(max = 100)
    @Column(name = "fill_translate_anchor")
    private String fillTranslateAnchor;
    @Size(max = 100)
    @Column(name = "visibility")
    private String visibility;
    @JoinColumn(name = "quyhoach_id", referencedColumnName = "id")
    @ManyToOne
    private TblLoaiquyhoach quyhoachId;

    public TblStyleFill() {
    }

    public TblStyleFill(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFillAntialias() {
        return fillAntialias;
    }

    public void setFillAntialias(Boolean fillAntialias) {
        this.fillAntialias = fillAntialias;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public BigDecimal getFillOpacity() {
        return fillOpacity;
    }

    public void setFillOpacity(BigDecimal fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

    public String getFillOutlineColor() {
        return fillOutlineColor;
    }

    public void setFillOutlineColor(String fillOutlineColor) {
        this.fillOutlineColor = fillOutlineColor;
    }

    public Integer getFillSortKey() {
        return fillSortKey;
    }

    public void setFillSortKey(Integer fillSortKey) {
        this.fillSortKey = fillSortKey;
    }

    public String getFillTranslate() {
        return fillTranslate;
    }

    public void setFillTranslate(String fillTranslate) {
        this.fillTranslate = fillTranslate;
    }

    public String getFillTranslateAnchor() {
        return fillTranslateAnchor;
    }

    public void setFillTranslateAnchor(String fillTranslateAnchor) {
        this.fillTranslateAnchor = fillTranslateAnchor;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public TblLoaiquyhoach getQuyhoachId() {
        return quyhoachId;
    }

    public void setQuyhoachId(TblLoaiquyhoach quyhoachId) {
        this.quyhoachId = quyhoachId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning _ this method won't work in the case the id fields are not set
        if (!(object instanceof TblStyleFill)) {
            return false;
        }
        TblStyleFill other = (TblStyleFill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblStyleFill[ id=" + id + " ]";
    }
    
}
