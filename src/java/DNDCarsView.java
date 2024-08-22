
import java.io.Serializable;
import java.util.ArrayList;

import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import jakarta.annotation.PostConstruct;
import org.primefaces.event.DragDropEvent;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ds010106
 */
@Named("dndCarsView")
@ViewScoped
public class DNDCarsView implements Serializable {
  
    //@ManagedProperty("#{carService}")
    @Inject
    private CarService service;
 
    private List<Car> cars;
     
    private List<Car> droppedCars;
    
    private List<Car> droppedCarsFin;
     
    private Car selectedCar;
     
    @PostConstruct
    public void init() {
        cars = service.createCars(9);
      //droppedCars = new ArrayList<Car>();
        droppedCars = service.createCars(2);
       //droppedCarsFin = new ArrayList<Car>();
        droppedCarsFin = service.createCars(1);
       
    }
     
    public void onCarDrop(DragDropEvent ddEvent) {
        System.out.println("CAR1");
        Car car = ((Car) ddEvent.getData()); /*Comentado ´por Kevin Anderson*/
        //Comentado por Kevin Anderson
        System.out.println("1 Selecciono: "+car.id +"   "+car.brand +" "+ddEvent.getDragId());
        
        
        String origen[]=ddEvent.getDragId().split(":");
        if(origen[1].equalsIgnoreCase("availableCars"))
        {
             droppedCars.add(car);
             cars.remove(car);
        }else{
    
            droppedCars.add(car);
            droppedCarsFin.remove(car);
        }
        
      /* boolean x=false;
       System.out.println("1 Selecciono VAR: "+selectedCar );
       System.out.println("1 Selecciono: "+car.id );
        for (int i = 0; i < droppedCars.size(); i++) {
             System.out.println("Compara Sel "+car.id+" : "+droppedCars.get(i).id);
            if(car.id==droppedCars.get(i).id)
            {
                System.out.println("Existe1");
                x=true;
            }else{
               // System.out.println("No Existe1");
            }
        }
        
        if(!x)
        {
        
        }*/
        
    }
    public void onCarDrop2(DragDropEvent ddEvent) {
        System.out.println("CAR2");
        /*Car car = ((Car) ddEvent.getData());*/ /*Comentado ´por Kevin Anderson*/
        /*System.out.println("2 Selecciono: "+car.id +"   "+car.brand +" "+ddEvent.getDragId());*/ /*Comentado ´por Kevin Anderson*/
        /*
         boolean x=false;
         System.out.println("2 Selecciono VAR: "+selectedCar );
         System.out.println("2 Selecciono: "+car.id );
        for (int i = 0; i < cars.size(); i++) {
            System.out.println("Compara Sel "+car.id+" : "+cars.get(i).id);
            if(car.id==cars.get(i).id)
            {
                System.out.println("Existe2");
                x=true;
            }
        }
        if(!x)
        {
           
        }*/
        
        /*droppedCars.remove(car);
            cars.add(car);*/ /*Comentado ´por Kevin Anderson*/
       // droppedCars.add(car);
       // cars.remove(car);
    }
    
    
    public void onCarDrop3(DragDropEvent ddEvent) {
        System.out.println("CAR3");
        /*Car car = ((Car) ddEvent.getData());
        
         System.out.println("3 Selecciono: "+car.id +"   "+car.brand +" "+ddEvent.getDragId());
  
            droppedCars.remove(car);
            droppedCarsFin.add(car);*//*Comentado ´por Kevin Anderson*/
  
    }
    
        
    public void onCarDrop4(DragDropEvent ddEvent) {
        System.out.println("CAR4");
        /*Car car = ((Car) ddEvent.getData());
        
        System.out.println("4 Selecciono: "+car.id +"   "+car.brand +" "+ddEvent.getDragId());
  
        droppedCars.add(car);
        droppedCarsFin.remove(car);*/ /*Comentado ´por Kevin Anderson*/
  
    }
     
     public void revertir(Car ddEvent) {
        System.out.println("Regresar "+ddEvent.getBrand());
        Car car = ((Car) ddEvent);
        
        droppedCars.add(car);
        droppedCarsFin.remove(car);
       // droppedCars.remove(car);
       // cars.add(car);
       // droppedCars.add(car);
       // cars.remove(car);
    }
     
    public void setService(CarService service) {
        this.service = service;
    }
 
    public List<Car> getCars() {
        return cars;
    }

    public List<Car> getDroppedCarsFin() {
        return droppedCarsFin;
    }

    
    public List<Car> getDroppedCars() {
        return droppedCars;
    }    
 
    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setDroppedCars(List<Car> droppedCars) {
        this.droppedCars = droppedCars;
    }

    public void setDroppedCarsFin(List<Car> droppedCarsFin) {
        this.droppedCarsFin = droppedCarsFin;
    }
 
    
    
    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }
}