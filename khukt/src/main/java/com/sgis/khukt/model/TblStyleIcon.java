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
@Table(name = "tbl_style_icon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblStyleIcon.findAllOrderByID", query = "SELECT t FROM TblStyleIcon t ORDER BY t.quyhoachId.id ASC"),
    @NamedQuery(name = "TblStyleIcon.findById", query = "SELECT t FROM TblStyleIcon t WHERE t.id = :id"),
    @NamedQuery(name = "TblStyleIcon.findByIconAllowOverlap", query = "SELECT t FROM TblStyleIcon t WHERE t.iconAllowOverlap = :iconAllowOverlap"),
    @NamedQuery(name = "TblStyleIcon.findByIconAnchor", query = "SELECT t FROM TblStyleIcon t WHERE t.iconAnchor = :iconAnchor"),
    @NamedQuery(name = "TblStyleIcon.findByIconColor", query = "SELECT t FROM TblStyleIcon t WHERE t.iconColor = :iconColor"),
    @NamedQuery(name = "TblStyleIcon.findByIconHaloBlur", query = "SELECT t FROM TblStyleIcon t WHERE t.iconHaloBlur = :iconHaloBlur"),
    @NamedQuery(name = "TblStyleIcon.findByIconHaloColor", query = "SELECT t FROM TblStyleIcon t WHERE t.iconHaloColor = :iconHaloColor"),
    @NamedQuery(name = "TblStyleIcon.findByIconHaloWidth", query = "SELECT t FROM TblStyleIcon t WHERE t.iconHaloWidth = :iconHaloWidth"),
    @NamedQuery(name = "TblStyleIcon.findByIconIgnorePlacement", query = "SELECT t FROM TblStyleIcon t WHERE t.iconIgnorePlacement = :iconIgnorePlacement"),
    @NamedQuery(name = "TblStyleIcon.findByIconImage", query = "SELECT t FROM TblStyleIcon t WHERE t.iconImage = :iconImage"),
    @NamedQuery(name = "TblStyleIcon.findByIconKeepUpright", query = "SELECT t FROM TblStyleIcon t WHERE t.iconKeepUpright = :iconKeepUpright"),
    @NamedQuery(name = "TblStyleIcon.findByIconOffset", query = "SELECT t FROM TblStyleIcon t WHERE t.iconOffset = :iconOffset"),
    @NamedQuery(name = "TblStyleIcon.findByIconOpacity", query = "SELECT t FROM TblStyleIcon t WHERE t.iconOpacity = :iconOpacity"),
    @NamedQuery(name = "TblStyleIcon.findByIconOptional", query = "SELECT t FROM TblStyleIcon t WHERE t.iconOptional = :iconOptional"),
    @NamedQuery(name = "TblStyleIcon.findByIconOverlap", query = "SELECT t FROM TblStyleIcon t WHERE t.iconOverlap = :iconOverlap"),
    @NamedQuery(name = "TblStyleIcon.findByIconPadding", query = "SELECT t FROM TblStyleIcon t WHERE t.iconPadding = :iconPadding"),
    @NamedQuery(name = "TblStyleIcon.findByIconPitchAlignment", query = "SELECT t FROM TblStyleIcon t WHERE t.iconPitchAlignment = :iconPitchAlignment"),
    @NamedQuery(name = "TblStyleIcon.findByIconRotate", query = "SELECT t FROM TblStyleIcon t WHERE t.iconRotate = :iconRotate"),
    @NamedQuery(name = "TblStyleIcon.findByIconRotationAlignment", query = "SELECT t FROM TblStyleIcon t WHERE t.iconRotationAlignment = :iconRotationAlignment"),
    @NamedQuery(name = "TblStyleIcon.findByIconSize", query = "SELECT t FROM TblStyleIcon t WHERE t.iconSize = :iconSize"),
    @NamedQuery(name = "TblStyleIcon.findByIconTextFit", query = "SELECT t FROM TblStyleIcon t WHERE t.iconTextFit = :iconTextFit"),
    @NamedQuery(name = "TblStyleIcon.findByIconTextFitPadding", query = "SELECT t FROM TblStyleIcon t WHERE t.iconTextFitPadding = :iconTextFitPadding"),
    @NamedQuery(name = "TblStyleIcon.findByIconTranslate", query = "SELECT t FROM TblStyleIcon t WHERE t.iconTranslate = :iconTranslate"),
    @NamedQuery(name = "TblStyleIcon.findByIconTranslateAnchor", query = "SELECT t FROM TblStyleIcon t WHERE t.iconTranslateAnchor = :iconTranslateAnchor"),
    @NamedQuery(name = "TblStyleIcon.findBySymbolAvoidEdges", query = "SELECT t FROM TblStyleIcon t WHERE t.symbolAvoidEdges = :symbolAvoidEdges"),
    @NamedQuery(name = "TblStyleIcon.findBySymbolPlacement", query = "SELECT t FROM TblStyleIcon t WHERE t.symbolPlacement = :symbolPlacement"),
    @NamedQuery(name = "TblStyleIcon.findBySymbolSortKey", query = "SELECT t FROM TblStyleIcon t WHERE t.symbolSortKey = :symbolSortKey"),
    @NamedQuery(name = "TblStyleIcon.findBySymbolSpacing", query = "SELECT t FROM TblStyleIcon t WHERE t.symbolSpacing = :symbolSpacing"),
    @NamedQuery(name = "TblStyleIcon.findBySymbolZOrder", query = "SELECT t FROM TblStyleIcon t WHERE t.symbolZOrder = :symbolZOrder"),
    @NamedQuery(name = "TblStyleIcon.findByTextAllowOverlap", query = "SELECT t FROM TblStyleIcon t WHERE t.textAllowOverlap = :textAllowOverlap"),
    @NamedQuery(name = "TblStyleIcon.findByTextAnchor", query = "SELECT t FROM TblStyleIcon t WHERE t.textAnchor = :textAnchor"),
    @NamedQuery(name = "TblStyleIcon.findByTextColor", query = "SELECT t FROM TblStyleIcon t WHERE t.textColor = :textColor"),
    @NamedQuery(name = "TblStyleIcon.findByTextField", query = "SELECT t FROM TblStyleIcon t WHERE t.textField = :textField"),
    @NamedQuery(name = "TblStyleIcon.findByTextFont", query = "SELECT t FROM TblStyleIcon t WHERE t.textFont = :textFont"),
    @NamedQuery(name = "TblStyleIcon.findByTextHaloBlur", query = "SELECT t FROM TblStyleIcon t WHERE t.textHaloBlur = :textHaloBlur"),
    @NamedQuery(name = "TblStyleIcon.findByTextHaloColor", query = "SELECT t FROM TblStyleIcon t WHERE t.textHaloColor = :textHaloColor"),
    @NamedQuery(name = "TblStyleIcon.findByTextHaloWidth", query = "SELECT t FROM TblStyleIcon t WHERE t.textHaloWidth = :textHaloWidth"),
    @NamedQuery(name = "TblStyleIcon.findByTextIgnorePlacement", query = "SELECT t FROM TblStyleIcon t WHERE t.textIgnorePlacement = :textIgnorePlacement"),
    @NamedQuery(name = "TblStyleIcon.findByTextJustify", query = "SELECT t FROM TblStyleIcon t WHERE t.textJustify = :textJustify"),
    @NamedQuery(name = "TblStyleIcon.findByTextKeepUpright", query = "SELECT t FROM TblStyleIcon t WHERE t.textKeepUpright = :textKeepUpright"),
    @NamedQuery(name = "TblStyleIcon.findByTextLetterSpacing", query = "SELECT t FROM TblStyleIcon t WHERE t.textLetterSpacing = :textLetterSpacing"),
    @NamedQuery(name = "TblStyleIcon.findByTextLineHeight", query = "SELECT t FROM TblStyleIcon t WHERE t.textLineHeight = :textLineHeight"),
    @NamedQuery(name = "TblStyleIcon.findByTextMaxAngle", query = "SELECT t FROM TblStyleIcon t WHERE t.textMaxAngle = :textMaxAngle"),
    @NamedQuery(name = "TblStyleIcon.findByTextMaxWidth", query = "SELECT t FROM TblStyleIcon t WHERE t.textMaxWidth = :textMaxWidth"),
    @NamedQuery(name = "TblStyleIcon.findByTextOffset", query = "SELECT t FROM TblStyleIcon t WHERE t.textOffset = :textOffset"),
    @NamedQuery(name = "TblStyleIcon.findByTextOpacity", query = "SELECT t FROM TblStyleIcon t WHERE t.textOpacity = :textOpacity"),
    @NamedQuery(name = "TblStyleIcon.findByTextOptional", query = "SELECT t FROM TblStyleIcon t WHERE t.textOptional = :textOptional"),
    @NamedQuery(name = "TblStyleIcon.findByTextOverlap", query = "SELECT t FROM TblStyleIcon t WHERE t.textOverlap = :textOverlap"),
    @NamedQuery(name = "TblStyleIcon.findByTextPadding", query = "SELECT t FROM TblStyleIcon t WHERE t.textPadding = :textPadding"),
    @NamedQuery(name = "TblStyleIcon.findByTextPitchAlignment", query = "SELECT t FROM TblStyleIcon t WHERE t.textPitchAlignment = :textPitchAlignment"),
    @NamedQuery(name = "TblStyleIcon.findByTextRadialOffset", query = "SELECT t FROM TblStyleIcon t WHERE t.textRadialOffset = :textRadialOffset"),
    @NamedQuery(name = "TblStyleIcon.findByTextRotate", query = "SELECT t FROM TblStyleIcon t WHERE t.textRotate = :textRotate"),
    @NamedQuery(name = "TblStyleIcon.findByTextRotationAlignment", query = "SELECT t FROM TblStyleIcon t WHERE t.textRotationAlignment = :textRotationAlignment"),
    @NamedQuery(name = "TblStyleIcon.findByTextSize", query = "SELECT t FROM TblStyleIcon t WHERE t.textSize = :textSize"),
    @NamedQuery(name = "TblStyleIcon.findByTextTransform", query = "SELECT t FROM TblStyleIcon t WHERE t.textTransform = :textTransform"),
    @NamedQuery(name = "TblStyleIcon.findByTextTranslate", query = "SELECT t FROM TblStyleIcon t WHERE t.textTranslate = :textTranslate"),
    @NamedQuery(name = "TblStyleIcon.findByTextTranslateAnchor", query = "SELECT t FROM TblStyleIcon t WHERE t.textTranslateAnchor = :textTranslateAnchor"),
    @NamedQuery(name = "TblStyleIcon.findByTextVariableAnchor", query = "SELECT t FROM TblStyleIcon t WHERE t.textVariableAnchor = :textVariableAnchor"),
    @NamedQuery(name = "TblStyleIcon.findByTextWritingMode", query = "SELECT t FROM TblStyleIcon t WHERE t.textWritingMode = :textWritingMode"),
    @NamedQuery(name = "TblStyleIcon.findByVisibility", query = "SELECT t FROM TblStyleIcon t WHERE t.visibility = :visibility")})
public class TblStyleIcon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "icon_allow_overlap")
    private Boolean iconAllowOverlap;
    @Size(max = 100)
    @Column(name = "icon_anchor")
    private String iconAnchor;
    @Size(max = 50)
    @Column(name = "icon_color")
    private String iconColor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "icon_halo_blur")
    private BigDecimal iconHaloBlur;
    @Size(max = 50)
    @Column(name = "icon_halo_color")
    private String iconHaloColor;
    @Column(name = "icon_halo_width")
    private BigDecimal iconHaloWidth;
    @Column(name = "icon_ignore_placement")
    private Boolean iconIgnorePlacement;
    @Size(max = 150)
    @Column(name = "icon_image")
    private String iconImage;
    @Column(name = "icon_keep_upright")
    private Boolean iconKeepUpright;
    @Size(max = 100)
    @Column(name = "icon_offset")
    private String iconOffset;
    @Column(name = "icon_opacity")
    private BigDecimal iconOpacity;
    @Column(name = "icon_optional")
    private Boolean iconOptional;
    @Size(max = 50)
    @Column(name = "icon_overlap")
    private String iconOverlap;
    @Column(name = "icon_padding")
    private BigDecimal iconPadding;
    @Size(max = 50)
    @Column(name = "icon_pitch_alignment")
    private String iconPitchAlignment;
    @Column(name = "icon_rotate")
    private BigDecimal iconRotate;
    @Size(max = 50)
    @Column(name = "icon_rotation_alignment")
    private String iconRotationAlignment;
    @Column(name = "icon_size")
    private BigDecimal iconSize;
    @Size(max = 50)
    @Column(name = "icon_text_fit")
    private String iconTextFit;
    @Size(max = 50)
    @Column(name = "icon_text_fit_padding")
    private String iconTextFitPadding;
    @Size(max = 50)
    @Column(name = "icon_translate")
    private String iconTranslate;
    @Size(max = 50)
    @Column(name = "icon_translate_anchor")
    private String iconTranslateAnchor;
    @Column(name = "symbol_avoid_edges")
    private Boolean symbolAvoidEdges;
    @Size(max = 50)
    @Column(name = "symbol_placement")
    private String symbolPlacement;
    @Column(name = "symbol_sort_key")
    private Integer symbolSortKey;
    @Column(name = "symbol_spacing")
    private BigDecimal symbolSpacing;
    @Size(max = 50)
    @Column(name = "symbol_z_order")
    private String symbolZOrder;
    @Column(name = "text_allow_overlap")
    private Boolean textAllowOverlap;
    @Size(max = 50)
    @Column(name = "text_anchor")
    private String textAnchor;
    @Size(max = 50)
    @Column(name = "text_color")
    private String textColor;
    @Size(max = 50)
    @Column(name = "text_field")
    private String textField;
    @Size(max = 200)
    @Column(name = "text_font")
    private String textFont;
    @Column(name = "text_halo_blur")
    private BigDecimal textHaloBlur;
    @Size(max = 50)
    @Column(name = "text_halo_color")
    private String textHaloColor;
    @Column(name = "text_halo_width")
    private BigDecimal textHaloWidth;
    @Column(name = "text_ignore_placement")
    private Boolean textIgnorePlacement;
    @Size(max = 50)
    @Column(name = "text_justify")
    private String textJustify;
    @Column(name = "text_keep_upright")
    private Boolean textKeepUpright;
    @Column(name = "text_letter_spacing")
    private BigDecimal textLetterSpacing;
    @Column(name = "text_line_height")
    private BigDecimal textLineHeight;
    @Column(name = "text_max_angle")
    private BigDecimal textMaxAngle;
    @Column(name = "text_max_width")
    private BigDecimal textMaxWidth;
    @Size(max = 50)
    @Column(name = "text_offset")
    private String textOffset;
    @Column(name = "text_opacity")
    private BigDecimal textOpacity;
    @Column(name = "text_optional")
    private Boolean textOptional;
    @Size(max = 50)
    @Column(name = "text_overlap")
    private String textOverlap;
    @Column(name = "text_padding")
    private BigDecimal textPadding;
    @Size(max = 50)
    @Column(name = "text_pitch_alignment")
    private String textPitchAlignment;
    @Column(name = "text_radial_offset")
    private BigDecimal textRadialOffset;
    @Column(name = "text_rotate")
    private BigDecimal textRotate;
    @Size(max = 100)
    @Column(name = "text_rotation_alignment")
    private String textRotationAlignment;
    @Column(name = "text_size")
    private BigDecimal textSize;
    @Size(max = 50)
    @Column(name = "text_transform")
    private String textTransform;
    @Size(max = 100)
    @Column(name = "text_translate")
    private String textTranslate;
    @Size(max = 100)
    @Column(name = "text_translate_anchor")
    private String textTranslateAnchor;
    @Size(max = 100)
    @Column(name = "text_variable_anchor")
    private String textVariableAnchor;
    @Size(max = 50)
    @Column(name = "text_writing_mode")
    private String textWritingMode;
    @Size(max = 50)
    @Column(name = "visibility")
    private String visibility;
    @JoinColumn(name = "quyhoach_id", referencedColumnName = "id")
    @ManyToOne
    private TblLoaiquyhoach quyhoachId;

    public TblStyleIcon() {
    }

    public TblStyleIcon(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIconAllowOverlap() {
        return iconAllowOverlap;
    }

    public void setIconAllowOverlap(Boolean iconAllowOverlap) {
        this.iconAllowOverlap = iconAllowOverlap;
    }

    public String getIconAnchor() {
        return iconAnchor;
    }

    public void setIconAnchor(String iconAnchor) {
        this.iconAnchor = iconAnchor;
    }

    public String getIconColor() {
        return iconColor;
    }

    public void setIconColor(String iconColor) {
        this.iconColor = iconColor;
    }

    public BigDecimal getIconHaloBlur() {
        return iconHaloBlur;
    }

    public void setIconHaloBlur(BigDecimal iconHaloBlur) {
        this.iconHaloBlur = iconHaloBlur;
    }

    public String getIconHaloColor() {
        return iconHaloColor;
    }

    public void setIconHaloColor(String iconHaloColor) {
        this.iconHaloColor = iconHaloColor;
    }

    public BigDecimal getIconHaloWidth() {
        return iconHaloWidth;
    }

    public void setIconHaloWidth(BigDecimal iconHaloWidth) {
        this.iconHaloWidth = iconHaloWidth;
    }

    public Boolean getIconIgnorePlacement() {
        return iconIgnorePlacement;
    }

    public void setIconIgnorePlacement(Boolean iconIgnorePlacement) {
        this.iconIgnorePlacement = iconIgnorePlacement;
    }

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }

    public Boolean getIconKeepUpright() {
        return iconKeepUpright;
    }

    public void setIconKeepUpright(Boolean iconKeepUpright) {
        this.iconKeepUpright = iconKeepUpright;
    }

    public String getIconOffset() {
        return iconOffset;
    }

    public void setIconOffset(String iconOffset) {
        this.iconOffset = iconOffset;
    }

    public BigDecimal getIconOpacity() {
        return iconOpacity;
    }

    public void setIconOpacity(BigDecimal iconOpacity) {
        this.iconOpacity = iconOpacity;
    }

    public Boolean getIconOptional() {
        return iconOptional;
    }

    public void setIconOptional(Boolean iconOptional) {
        this.iconOptional = iconOptional;
    }

    public String getIconOverlap() {
        return iconOverlap;
    }

    public void setIconOverlap(String iconOverlap) {
        this.iconOverlap = iconOverlap;
    }

    public BigDecimal getIconPadding() {
        return iconPadding;
    }

    public void setIconPadding(BigDecimal iconPadding) {
        this.iconPadding = iconPadding;
    }

    public String getIconPitchAlignment() {
        return iconPitchAlignment;
    }

    public void setIconPitchAlignment(String iconPitchAlignment) {
        this.iconPitchAlignment = iconPitchAlignment;
    }

    public BigDecimal getIconRotate() {
        return iconRotate;
    }

    public void setIconRotate(BigDecimal iconRotate) {
        this.iconRotate = iconRotate;
    }

    public String getIconRotationAlignment() {
        return iconRotationAlignment;
    }

    public void setIconRotationAlignment(String iconRotationAlignment) {
        this.iconRotationAlignment = iconRotationAlignment;
    }

    public BigDecimal getIconSize() {
        return iconSize;
    }

    public void setIconSize(BigDecimal iconSize) {
        this.iconSize = iconSize;
    }

    public String getIconTextFit() {
        return iconTextFit;
    }

    public void setIconTextFit(String iconTextFit) {
        this.iconTextFit = iconTextFit;
    }

    public String getIconTextFitPadding() {
        return iconTextFitPadding;
    }

    public void setIconTextFitPadding(String iconTextFitPadding) {
        this.iconTextFitPadding = iconTextFitPadding;
    }

    public String getIconTranslate() {
        return iconTranslate;
    }

    public void setIconTranslate(String iconTranslate) {
        this.iconTranslate = iconTranslate;
    }

    public String getIconTranslateAnchor() {
        return iconTranslateAnchor;
    }

    public void setIconTranslateAnchor(String iconTranslateAnchor) {
        this.iconTranslateAnchor = iconTranslateAnchor;
    }

    public Boolean getSymbolAvoidEdges() {
        return symbolAvoidEdges;
    }

    public void setSymbolAvoidEdges(Boolean symbolAvoidEdges) {
        this.symbolAvoidEdges = symbolAvoidEdges;
    }

    public String getSymbolPlacement() {
        return symbolPlacement;
    }

    public void setSymbolPlacement(String symbolPlacement) {
        this.symbolPlacement = symbolPlacement;
    }

    public Integer getSymbolSortKey() {
        return symbolSortKey;
    }

    public void setSymbolSortKey(Integer symbolSortKey) {
        this.symbolSortKey = symbolSortKey;
    }

    public BigDecimal getSymbolSpacing() {
        return symbolSpacing;
    }

    public void setSymbolSpacing(BigDecimal symbolSpacing) {
        this.symbolSpacing = symbolSpacing;
    }

    public String getSymbolZOrder() {
        return symbolZOrder;
    }

    public void setSymbolZOrder(String symbolZOrder) {
        this.symbolZOrder = symbolZOrder;
    }

    public Boolean getTextAllowOverlap() {
        return textAllowOverlap;
    }

    public void setTextAllowOverlap(Boolean textAllowOverlap) {
        this.textAllowOverlap = textAllowOverlap;
    }

    public String getTextAnchor() {
        return textAnchor;
    }

    public void setTextAnchor(String textAnchor) {
        this.textAnchor = textAnchor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public String getTextFont() {
        return textFont;
    }

    public void setTextFont(String textFont) {
        this.textFont = textFont;
    }

    public BigDecimal getTextHaloBlur() {
        return textHaloBlur;
    }

    public void setTextHaloBlur(BigDecimal textHaloBlur) {
        this.textHaloBlur = textHaloBlur;
    }

    public String getTextHaloColor() {
        return textHaloColor;
    }

    public void setTextHaloColor(String textHaloColor) {
        this.textHaloColor = textHaloColor;
    }

    public BigDecimal getTextHaloWidth() {
        return textHaloWidth;
    }

    public void setTextHaloWidth(BigDecimal textHaloWidth) {
        this.textHaloWidth = textHaloWidth;
    }

    public Boolean getTextIgnorePlacement() {
        return textIgnorePlacement;
    }

    public void setTextIgnorePlacement(Boolean textIgnorePlacement) {
        this.textIgnorePlacement = textIgnorePlacement;
    }

    public String getTextJustify() {
        return textJustify;
    }

    public void setTextJustify(String textJustify) {
        this.textJustify = textJustify;
    }

    public Boolean getTextKeepUpright() {
        return textKeepUpright;
    }

    public void setTextKeepUpright(Boolean textKeepUpright) {
        this.textKeepUpright = textKeepUpright;
    }

    public BigDecimal getTextLetterSpacing() {
        return textLetterSpacing;
    }

    public void setTextLetterSpacing(BigDecimal textLetterSpacing) {
        this.textLetterSpacing = textLetterSpacing;
    }

    public BigDecimal getTextLineHeight() {
        return textLineHeight;
    }

    public void setTextLineHeight(BigDecimal textLineHeight) {
        this.textLineHeight = textLineHeight;
    }

    public BigDecimal getTextMaxAngle() {
        return textMaxAngle;
    }

    public void setTextMaxAngle(BigDecimal textMaxAngle) {
        this.textMaxAngle = textMaxAngle;
    }

    public BigDecimal getTextMaxWidth() {
        return textMaxWidth;
    }

    public void setTextMaxWidth(BigDecimal textMaxWidth) {
        this.textMaxWidth = textMaxWidth;
    }

    public String getTextOffset() {
        return textOffset;
    }

    public void setTextOffset(String textOffset) {
        this.textOffset = textOffset;
    }

    public BigDecimal getTextOpacity() {
        return textOpacity;
    }

    public void setTextOpacity(BigDecimal textOpacity) {
        this.textOpacity = textOpacity;
    }

    public Boolean getTextOptional() {
        return textOptional;
    }

    public void setTextOptional(Boolean textOptional) {
        this.textOptional = textOptional;
    }

    public String getTextOverlap() {
        return textOverlap;
    }

    public void setTextOverlap(String textOverlap) {
        this.textOverlap = textOverlap;
    }

    public BigDecimal getTextPadding() {
        return textPadding;
    }

    public void setTextPadding(BigDecimal textPadding) {
        this.textPadding = textPadding;
    }

    public String getTextPitchAlignment() {
        return textPitchAlignment;
    }

    public void setTextPitchAlignment(String textPitchAlignment) {
        this.textPitchAlignment = textPitchAlignment;
    }

    public BigDecimal getTextRadialOffset() {
        return textRadialOffset;
    }

    public void setTextRadialOffset(BigDecimal textRadialOffset) {
        this.textRadialOffset = textRadialOffset;
    }

    public BigDecimal getTextRotate() {
        return textRotate;
    }

    public void setTextRotate(BigDecimal textRotate) {
        this.textRotate = textRotate;
    }

    public String getTextRotationAlignment() {
        return textRotationAlignment;
    }

    public void setTextRotationAlignment(String textRotationAlignment) {
        this.textRotationAlignment = textRotationAlignment;
    }

    public BigDecimal getTextSize() {
        return textSize;
    }

    public void setTextSize(BigDecimal textSize) {
        this.textSize = textSize;
    }

    public String getTextTransform() {
        return textTransform;
    }

    public void setTextTransform(String textTransform) {
        this.textTransform = textTransform;
    }

    public String getTextTranslate() {
        return textTranslate;
    }

    public void setTextTranslate(String textTranslate) {
        this.textTranslate = textTranslate;
    }

    public String getTextTranslateAnchor() {
        return textTranslateAnchor;
    }

    public void setTextTranslateAnchor(String textTranslateAnchor) {
        this.textTranslateAnchor = textTranslateAnchor;
    }

    public String getTextVariableAnchor() {
        return textVariableAnchor;
    }

    public void setTextVariableAnchor(String textVariableAnchor) {
        this.textVariableAnchor = textVariableAnchor;
    }

    public String getTextWritingMode() {
        return textWritingMode;
    }

    public void setTextWritingMode(String textWritingMode) {
        this.textWritingMode = textWritingMode;
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
        if (!(object instanceof TblStyleIcon)) {
            return false;
        }
        TblStyleIcon other = (TblStyleIcon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblStyleIcon[ id=" + id + " ]";
    }
    
}
