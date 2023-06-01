package com.sgis.khukt.model;



import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ImageHandle;
import org.eclipse.birt.report.model.api.LabelHandle;
import org.eclipse.birt.report.model.api.ListHandle;
import org.eclipse.birt.report.model.api.MasterPageHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.EmbeddedImage;
import org.eclipse.birt.report.model.elements.MasterPage;

public class common {
    public common(){}
    /*
     * Thiết lập phông chữ và cỡ chữ cho báo cáo
     * ex: facename = "Times New Roman", sizefont = "13pt"
     */
    public void setFontReport(String facename, String sizefont, ReportDesignHandle designHandle){
        try{
//            elementFactory.newStyle( "Data" );
            designHandle.findStyle("report").setProperty(StyleHandle.FONT_FAMILY_PROP, facename);
            designHandle.findStyle("report").setProperty(StyleHandle.FONT_SIZE_PROP, sizefont);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * Căn lề cho văn bản:
     * alignment = {'left','right','center','justify'}
     */
    public void setAlignReport(String alignment, ReportDesignHandle designHandle){
        try{
            designHandle.findStyle("report").setProperty(StyleHandle.TEXT_ALIGN_PROP, alignment);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * Thiết lập kiểu trang, hướng trang (dọc, ngang), lề trái, lề phải, lề trên, lề dưới
     * ex:
     * type={'a4','us-legal','us-letter','custom'}
     * orientation= {'auto','landscape','portrait'}
     * left = "3cm", right = "2cm", top="2cm", bottom = "2cm"
     */
    public void setMarginReport(String type, String orientation, String left, String right, String top, String bottom, ReportDesignHandle designHandle){
        try{
            MasterPageHandle simpleMasterPage = designHandle.findMasterPage("Simple MasterPage");
            simpleMasterPage.setPageType(type);
            simpleMasterPage.setOrientation(orientation);
            simpleMasterPage.setProperty(MasterPage.BOTTOM_MARGIN_PROP, bottom);
            simpleMasterPage.setProperty(MasterPage.TOP_MARGIN_PROP, top);
            simpleMasterPage.setProperty(MasterPage.LEFT_MARGIN_PROP, left);
            simpleMasterPage.setProperty(MasterPage.RIGHT_MARGIN_PROP, right);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * Taọ style
     * nameStyle: tên của style
     * weight: B - chữ in đậm, N - chữ thường, I - chữ in nghiêng, U - chữ gạch chân dưới, S - chữ gạch ngang thân,
     * indentfirst: set indent first
     * uppercase: true/false - có chuyển thành chữ hoa không
     */
    public StyleHandle createStyle(String nameStyle, String weight, String indentfirst, boolean uppercase, ReportDesignHandle designHandle){
        StyleHandle style = null;
        try{
            ElementFactory designFactory = designHandle.getElementFactory();
            style = designFactory.newStyle( nameStyle );
            weight = weight.toUpperCase();
            for(int i=0;i<weight.length();i++){
                char c = weight.charAt(i);
                if(c=='B'){
                    style.setProperty( StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD );
                }else if(c=='N'){
                    style.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_NORMAL);
                }else if(c=='I'){
                    style.setProperty(StyleHandle.FONT_STYLE_PROP, DesignChoiceConstants.FONT_STYLE_ITALIC);
                }else if(c=='U'){
                    style.setProperty(StyleHandle.TEXT_UNDERLINE_PROP, DesignChoiceConstants.TEXT_UNDERLINE_UNDERLINE);
                }else if(c=='S'){
                    style.setProperty(StyleHandle.TEXT_LINE_THROUGH_PROP, DesignChoiceConstants.TEXT_LINE_THROUGH_LINE_THROUGH);
                }
            }
            style.setProperty( StyleHandle.TEXT_INDENT_PROP,indentfirst);//Không có tác dụng với label
            if(uppercase){
                style.setProperty( StyleHandle.TEXT_TRANSFORM_PROP, DesignChoiceConstants.TRANSFORM_UPPERCASE);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return style;
    }

    /*
     * Taọ style
     * nameStyle: tên của style
     * weight: B - chữ in đậm, N - chữ thường, I - chữ in nghiêng, U - chữ gạch chân dưới, S - chữ gạch ngang thân,
     * fName: phông chữ
     * fSize: cỡ chữ
     * fColor: màu chữ
     * indentfirst: set indent first
     * uppercase: true/false - có chuyển thành chữ hoa không
     */
    public StyleHandle createStyle(String nameStyle, String weight, String fName, String fSize, String fColor, String indentfirst, boolean uppercase, ReportDesignHandle designHandle){
        StyleHandle style = null;
        try{
            ElementFactory designFactory = designHandle.getElementFactory();
            style = designFactory.newStyle( nameStyle );
            weight = weight.toUpperCase();
            for(int i=0;i<weight.length();i++){
                char c = weight.charAt(i);
                if(c=='B'){
                    style.setProperty( StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD );
                }else if(c=='N'){
                    style.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_NORMAL);
                }else if(c=='I'){
                    style.setProperty(StyleHandle.FONT_STYLE_PROP, DesignChoiceConstants.FONT_STYLE_ITALIC);
                }else if(c=='U'){
                    style.setProperty(StyleHandle.TEXT_UNDERLINE_PROP, DesignChoiceConstants.TEXT_UNDERLINE_UNDERLINE);
                }else if(c=='S'){
                    style.setProperty(StyleHandle.TEXT_LINE_THROUGH_PROP, DesignChoiceConstants.TEXT_LINE_THROUGH_LINE_THROUGH);
                }
            }
            style.setProperty( StyleHandle.FONT_FAMILY_PROP, fName ); //"Arial"
            style.setProperty( StyleHandle.FONT_SIZE_PROP, fSize ); //"13pt"
            style.setProperty( StyleHandle.COLOR_PROP, fColor ); //"#008000"

            style.setProperty( StyleHandle.TEXT_INDENT_PROP,indentfirst);//Không có tác dụng với label
            if(uppercase){
                style.setProperty( StyleHandle.TEXT_TRANSFORM_PROP, DesignChoiceConstants.TRANSFORM_UPPERCASE);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return style;
    }

    /*
     * Chèn dòng văn bản mới vào báo cáo
     */
    public LabelHandle addLabel(String content, ReportDesignHandle designHandle){
        LabelHandle label=null;
        try{
            label = designHandle.getElementFactory().newLabel("");
            label.setText(content);
            designHandle.getBody().add(label);
        }catch(Exception e){
            e.printStackTrace();
        }

        return label;
    }

    /*
     * Chèn dòng văn bản mới vào báo cáo
     */
    public LabelHandle addLabel(String content, String nameStyle, ReportDesignHandle designHandle, Boolean... breakbefore){
        LabelHandle label=null;
        assert breakbefore.length <= 1;
        boolean tmp = breakbefore.length > 0 ? breakbefore[0].booleanValue() : false;
        try{
            label = designHandle.getElementFactory().newLabel("");
            label.setText(content);
//            label.setStyleName(nameStyle);


            if(tmp==true){
                label.setProperty(StyleHandle.PAGE_BREAK_BEFORE_PROP, "always");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return label;
    }

    /*
     * Chèn dòng văn bản mới vào báo cáo
     */
    public void addText(String content, ReportDesignHandle designHandle){
        try{
            TextItemHandle text = designHandle.getElementFactory().newTextItem("");
            text.setContent(content);
            designHandle.getBody().add(text);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * Chèn dòng văn bản mới vào báo cáo
     */
    public void addTextHTML(String content, ReportDesignHandle designHandle){
        try{
            TextItemHandle text = designHandle.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_CONTENT_TYPE_HTML);
            text.setContent(content);
            designHandle.getBody().add(text);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * Chèn dòng văn bản mới vào báo cáo
     */
    public void addText(String content, String nameStyle, ReportDesignHandle designHandle, Boolean... breakbefore){
        assert breakbefore.length <= 1;
        boolean tmp = breakbefore.length > 0 ? breakbefore[0].booleanValue() : false;
        try{
            TextItemHandle text = designHandle.getElementFactory().newTextItem("");
            text.setContent(content);
            text.setStyleName(nameStyle);
            designHandle.getBody().add(text);
            if(tmp==true){
                text.setProperty(StyleHandle.PAGE_BREAK_BEFORE_PROP, "always");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private byte[] load(String pathName) throws IOException
    {
        FileInputStream fis = null;
        InputStream is = null;
        fis = new FileInputStream(pathName);
        is = new BufferedInputStream(fis);
        byte data[] = null;
        if(is != null)
        {
            try
            {
                data = new byte[is.available( )];
                is.read( data );
            }
            catch ( IOException e1 )
            {
                throw e1;
            }
        }
        return data;
    }

    /*
     * Chèn ảnh vào báo cáo
     * name: định danh của ảnh
     * pathName: đường dẫn đến file ảnh
     * typeImage: kiểu ảnh (bmp,gif,ico,jpg,svg,tiff)
     *  designHandle: điều khiển của report
     */
    public void addEmbedImage(String name, String pathName, String typeImage, ReportDesignHandle designHandle, Boolean... breakbefore){
        assert breakbefore.length <= 1;
        boolean tmp = breakbefore.length > 0 ? breakbefore[0].booleanValue() : false;
        try{
            EmbeddedImage img = StructureFactory.createEmbeddedImage();
            if(typeImage.equalsIgnoreCase("bmp")){
                img.setType(DesignChoiceConstants.IMAGE_TYPE_IMAGE_BMP);
            }else if(typeImage.equalsIgnoreCase("gif")){
                img.setType(DesignChoiceConstants.IMAGE_TYPE_IMAGE_GIF);
            }else if(typeImage.equalsIgnoreCase("ico")){
                img.setType(DesignChoiceConstants.IMAGE_TYPE_IMAGE_ICO);
            }else if(typeImage.equalsIgnoreCase("jpg")){
                img.setType(DesignChoiceConstants.IMAGE_TYPE_IMAGE_JPEG);
            }else if(typeImage.equalsIgnoreCase("svg")){
                img.setType(DesignChoiceConstants.IMAGE_TYPE_IMAGE_SVG);
            }else if(typeImage.equalsIgnoreCase("tiff")){
                img.setType(DesignChoiceConstants.IMAGE_TYPE_IMAGE_TIFF);
            }else{
                return;
            }
            img.setData(load(pathName));
            img.setName(name);
            designHandle.addImage(img);
            ImageHandle imageHandle = designHandle.getElementFactory().newImage("handle");
            imageHandle.setSource( DesignChoiceConstants.IMAGE_REF_TYPE_EMBED );
            imageHandle.setImageName(name);
            designHandle.getBody().add(imageHandle);
            if(tmp==true){
                imageHandle.setProperty(StyleHandle.PAGE_BREAK_BEFORE_PROP, "always");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * add table
     */
    public GridHandle getTable(List<List<String>> data, List<String> width, List<String> alignment,
                               String clrHeader, String clrTextHeader, String clrEven, String clrOdd, ReportDesignHandle design){
        GridHandle table=null;
        try {
            table = design.getElementFactory().newGridItem("Table", data.get(0).size(), data.size());
            table.setWidth("100%");

            //set width of column
            for (int c=0; c<width.size(); c++){
                ((ColumnHandle) table.getColumns().get(c)).getWidth().setValue(width.get(c));
            }

            TextItemHandle text;
            for (int i=0; i<data.size(); i++){
                for (int j=0; j<data.get(i).size(); j++){
                    text=design.getElementFactory().newTextItem("");
                    text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
                    text.setContent(data.get(i).get(j));

                    ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
                    ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
                    ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");

                    ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
                    ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
                    ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
                    if(j==data.get(i).size()-1){
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
                    }

                    ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
                    ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_SIZE_PROP, "13pt");

                    if (i==0) {
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD);
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BACKGROUND_COLOR_PROP, clrHeader);
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.COLOR_PROP, clrTextHeader);
                    }else{
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.TEXT_ALIGN_PROP, alignment.get(j));
                    }

                    if (i > 0 && i % 2 != 0) {
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_SIZE_PROP, "13pt");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BACKGROUND_COLOR_PROP, clrOdd);
                    } else if (i > 0 && i % 2 == 0) {
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_SIZE_PROP, "13pt");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
                        ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BACKGROUND_COLOR_PROP, clrEven);
                    }

                    ((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).getContent().add(text);
                }
            }

			/*CellHandle cell1 = ((CellHandle)((RowHandle) table.getRows().get(1)).getCells().get(0));
			CellHandle cell2 = ((CellHandle)((RowHandle) table.getRows().get(1)).getCells().get(1));
			cell2.drop();
			cell1.setColumnSpan(2);*/

            //table.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return table;
    }

    /*
     * Tạo dòng văn bản mới
     */
    public LabelHandle createLabel(String content, ReportDesignHandle designHandle){
        LabelHandle label=null;
        try{
            label = designHandle.getElementFactory().newLabel("");
            label.setText(content);
        }catch(Exception e){
            e.printStackTrace();
        }
        return label;
    }

    /*
     * Tạo dòng văn bản mới
     */
    public LabelHandle createLabel(String content, String nameStyle, ReportDesignHandle designHandle){
        LabelHandle label=null;
        try{
            label = designHandle.getElementFactory().newLabel("");
            label.setText(content);
            label.setStyleName(nameStyle);
        }catch(Exception e){
            e.printStackTrace();
        }
        return label;
    }

    /*
     * Chèn một danh sách vào báo cáo
     */
    public void addList(String nameHeaderStyle, String txtHeader, String txtFooter, String[] arrContent, ReportDesignHandle designHandle){
        try{
            if(arrContent.length==0 || arrContent==null) return;
            ListHandle list = designHandle.getElementFactory().newList("");
            list.setWidth("100%");
            designHandle.getBody().add(list);

            if(txtHeader!=null){
                list.getHeader().add(createLabel(txtHeader,nameHeaderStyle,designHandle));
            }
            for(int i=0;i<arrContent.length;i++){
                list.getDetail().add(createLabel(arrContent[i],designHandle));
            }
            if(txtFooter!=null){
                list.getFooter().add(createLabel(txtFooter,nameHeaderStyle,designHandle));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

