//package com.textinsert.textToInsert;
//
//import org.apache.pdfbox.cos.COSName;
//import org.apache.pdfbox.pdmodel.font.PDFont;
//import org.apache.pdfbox.pdmodel.font.PDSimpleFont;
//import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
//import org.apache.pdfbox.pdmodel.interactive.form.PDField;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class EncodeFields {
//    public static void setStringValue(PDField field, String input) throws Exception
//    {
//        /* Extract font name */
//        String  da      = field.getCOSObject().getString(COSName.DA.getName());
//        Matcher m       = Pattern.compile("/?(.*) [\\d]+ Tf.*", Pattern.CASE_INSENSITIVE).matcher(da);
//        String  name    = m.find() ? m.group(1) : null;
//        PDFont font    = field.getAcroForm().getDefaultResources().getFont(COSName.getPDFName(name));
//
//        if (font instanceof PDSimpleFont)
//        {
//            /* Walk through used characters and replace ones with space that can not be represented by the font */
//            StringBuilder value = new StringBuilder();
//
//            Encoding encoding = ((PDSimpleFont) font).getEncoding();
//
//            for (int i=0;i<input.length();i++)
//            {
//                char c = input.charAt(i);
//
//                if (".notdef".equals(encoding.getName(c)) == false)
//                    value.append(c);
//                else
//                    value.append(' ');
//            }
//
//            field.setValue(value.toString());
//        }
//        else
//            field.setValue(input);
//    }
//}
