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
@Table(name = "tbl_style_line")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblStyleLine.findAllOrderByID", query = "SELECT t FROM TblStyleLine t ORDER BY t.quyhoachId.id ASC"),
    @NamedQuery(name = "TblStyleLine.findById", query = "SELECT t FROM TblStyleLine t WHERE t.id = :id"),
    @NamedQuery(name = "TblStyleLine.findByLineBlur", query = "SELECT t FROM TblStyleLine t WHERE t.lineBlur = :lineBlur"),
    @NamedQuery(name = "TblStyleLine.findByLineCap", query = "SELECT t FROM TblStyleLine t WHERE t.lineCap = :lineCap"),
    @NamedQuery(name = "TblStyleLine.findByLineColor", query = "SELECT t FROM TblStyleLine t WHERE t.lineColor = :lineColor"),
    @NamedQuery(name = "TblStyleLine.findByLineDasharray", query = "SELECT t FROM TblStyleLine t WHERE t.lineDasharray = :lineDasharray"),
    @NamedQuery(name = "TblStyleLine.findByLineGapWidth", query = "SELECT t FROM TblStyleLine t WHERE t.lineGapWidth = :lineGapWidth"),
    @NamedQuery(name = "TblStyleLine.findByLineGradient", query = "SELECT t FROM TblStyleLine t WHERE t.lineGradient = :lineGradient"),
    @NamedQuery(name = "TblStyleLine.findByLineJoin", query = "SELECT t FROM TblStyleLine t WHERE t.lineJoin = :lineJoin"),
    @NamedQuery(name = "TblStyleLine.findByLineMiterLimit", query = "SELECT t FROM TblStyleLine t WHERE t.lineMiterLimit = :lineMiterLimit"),
    @NamedQuery(name = "TblStyleLine.findByLineOffset", query = "SELECT t FROM TblStyleLine t WHERE t.lineOffset = :lineOffset"),
    @NamedQuery(name = "TblStyleLine.findByLineOpacity", query = "SELECT t FROM TblStyleLine t WHERE t.lineOpacity = :lineOpacity"),
    @NamedQuery(name = "TblStyleLine.findByLineRoundLimit", query = "SELECT t FROM TblStyleLine t WHERE t.lineRoundLimit = :lineRoundLimit"),
    @NamedQuery(name = "TblStyleLine.findByLineSortKey", query = "SELECT t FROM TblStyleLine t WHERE t.lineSortKey = :lineSortKey"),
    @NamedQuery(name = "TblStyleLine.findByLineTranslate", query = "SELECT t FROM TblStyleLine t WHERE t.lineTranslate = :lineTranslate"),
    @NamedQuery(name = "TblStyleLine.findByLineTranslateAnchor", query = "SELECT t FROM TblStyleLine t WHERE t.lineTranslateAnchor = :lineTranslateAnchor"),
    @NamedQuery(name = "TblStyleLine.findByLineWidth", query = "SELECT t FROM TblStyleLine t WHERE t.lineWidth = :lineWidth"),
    @NamedQuery(name = "TblStyleLine.findByVisibility", query = "SELECT t FROM TblStyleLine t WHERE t.visibility = :visibility")})
public class TblStyleLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "line_blur")
    private Integer lineBlur;
    @Size(max = 50)
    @Column(name = "line_cap")
    private String lineCap;
    @Size(max = 50)
    @Column(name = "line_color")
    private String lineColor;
    @Size(max = 50)
    @Column(name = "line_dasharray")
    private String lineDasharray;
    @Column(name = "line_gap_width")
    private Integer lineGapWidth;
    @Size(max = 50)
    @Column(name = "line_gradient")
    private String lineGradient;
    @Size(max = 50)
    @Column(name = "line_join")
    private String lineJoin;
    @Column(name = "line_miter_limit")
    private Integer lineMiterLimit;
    @Column(name = "line_offset")
    private Integer lineOffset;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "line_opacity")
    private BigDecimal lineOpacity;
    @Column(name = "line_round_limit")
    private BigDecimal lineRoundLimit;
    @Column(name = "line_sort_key")
    private Integer lineSortKey;
    @Size(max = 100)
    @Column(name = "line_translate")
    private String lineTranslate;
    @Size(max = 50)
    @Column(name = "line_translate_anchor")
    private String lineTranslateAnchor;
    @Column(name = "line_width")
    private BigDecimal lineWidth;
    @Size(max = 50)
    @Column(name = "visibility")
    private String visibility;
    @JoinColumn(name = "quyhoach_id", referencedColumnName = "id")
    @ManyToOne
    private TblLoaiquyhoach quyhoachId;

    public TblStyleLine() {
    }

    public TblStyleLine(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLineBlur() {
        return lineBlur;
    }

    public void setLineBlur(Integer lineBlur) {
        this.lineBlur = lineBlur;
    }

    public String getLineCap() {
        return lineCap;
    }

    public void setLineCap(String lineCap) {
        this.lineCap = lineCap;
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    public String getLineDasharray() {
        return lineDasharray;
    }

    public void setLineDasharray(String lineDasharray) {
        this.lineDasharray = lineDasharray;
    }

    public Integer getLineGapWidth() {
        return lineGapWidth;
    }

    public void setLineGapWidth(Integer lineGapWidth) {
        this.lineGapWidth = lineGapWidth;
    }

    public String getLineGradient() {
        return lineGradient;
    }

    public void setLineGradient(String lineGradient) {
        this.lineGradient = lineGradient;
    }

    public String getLineJoin() {
        return lineJoin;
    }

    public void setLineJoin(String lineJoin) {
        this.lineJoin = lineJoin;
    }

    public Integer getLineMiterLimit() {
        return lineMiterLimit;
    }

    public void setLineMiterLimit(Integer lineMiterLimit) {
        this.lineMiterLimit = lineMiterLimit;
    }

    public Integer getLineOffset() {
        return lineOffset;
    }

    public void setLineOffset(Integer lineOffset) {
        this.lineOffset = lineOffset;
    }

    public BigDecimal getLineOpacity() {
        return lineOpacity;
    }

    public void setLineOpacity(BigDecimal lineOpacity) {
        this.lineOpacity = lineOpacity;
    }

    public BigDecimal getLineRoundLimit() {
        return lineRoundLimit;
    }

    public void setLineRoundLimit(BigDecimal lineRoundLimit) {
        this.lineRoundLimit = lineRoundLimit;
    }

    public Integer getLineSortKey() {
        return lineSortKey;
    }

    public void setLineSortKey(Integer lineSortKey) {
        this.lineSortKey = lineSortKey;
    }

    public String getLineTranslate() {
        return lineTranslate;
    }

    public void setLineTranslate(String lineTranslate) {
        this.lineTranslate = lineTranslate;
    }

    public String getLineTranslateAnchor() {
        return lineTranslateAnchor;
    }

    public void setLineTranslateAnchor(String lineTranslateAnchor) {
        this.lineTranslateAnchor = lineTranslateAnchor;
    }

    public BigDecimal getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(BigDecimal lineWidth) {
        this.lineWidth = lineWidth;
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
        if (!(object instanceof TblStyleLine)) {
            return false;
        }
        TblStyleLine other = (TblStyleLine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblStyleLine[ id=" + id + " ]";
    }
    
}
