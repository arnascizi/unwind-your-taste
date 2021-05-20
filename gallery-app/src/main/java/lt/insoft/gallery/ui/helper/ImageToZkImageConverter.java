package lt.insoft.gallery.ui.helper;

import java.io.IOException;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.image.AImage;
import org.zkoss.zul.Image;

public class ImageToZkImageConverter implements Converter<AImage, byte[], Image> {
    @Override
    public AImage coerceToUi(byte[] beanProp, Image component, BindContext ctx) {
        try {
            if(beanProp != null && beanProp.length > 0) {
                AImage im = new AImage("", beanProp);
                component.setContent(im);
                return im;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public byte[] coerceToBean(AImage compAttr, Image component, BindContext ctx) {
        return compAttr.getByteData();
    }
}
