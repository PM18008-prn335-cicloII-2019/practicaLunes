
package ues.occ.edu.sv.ingenieria.prn335.prueba;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Clasificacion;

@Stateless
@LocalBean
public class UtilidadesFacade implements Serializable{
    
    protected EntityManager em;
    
    final static EntityManagerFactory EMF=Persistence.createEntityManagerFactory("cinePU");
    
    public void iniciarEm(){
         this.em=EMF.createEntityManager();
    }
    
    public EntityTransaction getTX(){
        if (this.em !=null){
            return this.em.getTransaction();
        }
        return null;
    }
    //Metodo Crear
    public void crear(Clasificacion r){  
        try{
        EntityTransaction tx=this.getTX();
        this.em.persist(r);
        tx.commit();
        }catch(PersistenceException pe){
           System.out.println(pe);
        }
     
        
    }
    //Metodo Modificar
    public void modificar(Clasificacion r){ 
        try{
         EntityTransaction tx=this.getTX();
        this.em.merge(r);
        tx.commit();   
        }catch(PersistenceException pe){
            System.out.println(pe);
        }
        
    }

    //Metodo Eliminar
    public void eliminar(Clasificacion r){ 
        try{
        EntityTransaction tx=this.getTX();
        this.em.remove(r);
        tx.commit();   
        }catch(PersistenceException pe){
            System.out.println(pe);
        }
        
    }

     public List<Clasificacion> getClasificacion(){
        List<Clasificacion> lista=new ArrayList<Clasificacion>();
        if (em != null){
            try{
            Query query = em.createQuery("SELECT c FROM Clasificacion c");
            lista=query.getResultList();
            return lista;
            }
            catch(Exception e){
                System.out.println("error"+e.getMessage());
            }
        }    
        return lista;
        
    }
     
     public void handler(){
         List<Clasificacion> lista = getClasificacion();
         int calculo =0;
         if(lista.size() > 0){
             for(int i =0; i <= lista.size(); i++){
                 calculo =lista.get(i).getIdClasificacion() / 2;
                 if( calculo== 0){
                     System.out.println("Encontrado");
                 }else{
                     System.out.println("No encontrado");
                 }
             }
         }else{
             calculo = (int) Math.random();
         }
     }
    
}
