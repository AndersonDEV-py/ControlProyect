/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.converter;

//import static groovy.xml.Entity.gt;
//import static groovy.xml.Entity.lt;
import java.util.List;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UISelectItem;
import jakarta.faces.component.UISelectItems;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author rcardona
 */
/**
 * The Class SelectOneMenuConverter.
 */
@FacesConverter("itemConverter")
public class ItemConverter implements Converter {
 
    @Override
    public Object getAsObject(final FacesContext arg0, final UIComponent arg1, final String objectString) {
        if (objectString == null) {
            return null;
        }
 
        return fromSelect(arg1, objectString);
    }
 
    /**
     * Serialize.
     *
     * @param object
     *            the object
     * @return the string
     */
    private String serialize(final Object object) {
        if (object == null) {
            return null;
        }
        return object.getClass() + "@" + object.hashCode();
    }
 
    /**
     * From select.
     *
     * @param currentcomponent
     *            the currentcomponent
     * @param objectString
     *            the object string
     * @return the object
     */
    private Object fromSelect(final UIComponent currentcomponent, final String objectString) {
 
        if (currentcomponent.getClass() == UISelectItem.class) {
            final UISelectItem item = (UISelectItem) currentcomponent;
            final Object value = item.getValue();
            if (objectString.equals(serialize(value))) {
                return value;
            }
        }
 
        if (currentcomponent.getClass() == UISelectItems.class) {
            final UISelectItems items = (UISelectItems) currentcomponent;
            //final List&lt;Object&gt; elements = (List&lt;Object&gt;) items.getValue();
            final List<Object> elements = (List<Object>) items.getValue();
            for (final Object element : elements) {
                if (objectString.equals(serialize(element))) {
                    return element;
                }
            }
        }
 
 
        if (!currentcomponent.getChildren().isEmpty()) {
            for (final UIComponent component : currentcomponent.getChildren()) {
                final Object result = fromSelect(component, objectString);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
 
    @Override
    public String getAsString(final FacesContext arg0, final UIComponent arg1, final Object object) {
        return serialize(object);
    }
 
}