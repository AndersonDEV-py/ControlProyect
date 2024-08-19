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

@FacesConverter("estadoConverter")
public class EstadoConverter implements Converter{

   // SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");//("yyyy-MM-dd");
    //SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");//("yyyy-MM-dd");
      
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String valorRetorno="";
        if(value!=null)
        {
            
            if(value.toString().equalsIgnoreCase("0"))
                valorRetorno="INACTIVO";
            else if(value.toString().equalsIgnoreCase("1"))
                valorRetorno="ACTIVO";
            else if(value.toString().equalsIgnoreCase("2"))
                valorRetorno="CAMBIOCLAVE";
            else
                valorRetorno="";
        }  
        
        
     
        return valorRetorno.trim();
    }
    
    
    
    
    
}
