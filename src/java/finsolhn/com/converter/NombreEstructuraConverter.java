/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author rcardona
 */

@FacesConverter("nombreEstructuraConverter")
public class NombreEstructuraConverter implements Converter{

    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");//("yyyy-MM-dd");
    //SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");//("yyyy-MM-dd");
      
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String valorStr="";
        if(value!=null)
        {
            if(value.toString().equalsIgnoreCase(""))
          valorStr= "HOLA :)";// fecha= sdf.format( (Date)value );
        }  
     
        return value.toString();
    }
    
    
    
    
    
}
