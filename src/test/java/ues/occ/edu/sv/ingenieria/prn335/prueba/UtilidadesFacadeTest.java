/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.prueba;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Clasificacion;

/**
 *
 * @author melvin
 */

@ExtendWith(MockitoExtension.class)
public class UtilidadesFacadeTest {

    public UtilidadesFacadeTest() {
    }
    UtilidadesFacade cut = new UtilidadesFacade();
    EntityManager MockEm = Mockito.mock(EntityManager.class);
    EntityManagerFactory MockEMF = Mockito.mock(EntityManagerFactory.class);
    EntityTransaction MockTx = Mockito.mock(EntityTransaction.class);
    Clasificacion MockC = Mockito.mock(Clasificacion.class);
    List<Clasificacion> lista = Mockito.mock(List.class);
    Query MockQ = Mockito.mock(Query.class);

    /**
     * Test of iniciarEm method, of class UtilidadesFacade.
     */
    @Test
    public void testIniciarEm() throws Exception {
        System.out.println("iniciarEm");
        cut.em = MockEm;
        Mockito.when(MockEMF.createEntityManager()).thenReturn(MockEm);
        cut.iniciarEm();
    }

    /**
     * Test of getTX method, of class UtilidadesFacade.
     */
    @Test
    public void testGetTX() throws Exception {
        System.out.println("getTX");
        //Con em null
        cut.em = null;
        cut.getTX();
        //sin em null
        cut.em = MockEm;
        cut.getTX();
    }

    /**
     * Test of crear method, of class UtilidadesFacade.
     */
    @Test
    public void testCrear() throws Exception {
        System.out.println("crear");
        cut.em = MockEm;
        Mockito.when(MockEm.getTransaction()).thenReturn(MockTx);
        cut.crear(MockC);
        //cuando genera una excepcion
        try {
            Mockito.doThrow(PersistenceException.class).when(MockEm).persist(MockC);
            cut.crear(MockC);
        } catch (PersistenceException e) {
        }
    }

    /**
     * Test of modificar method, of class UtilidadesFacade.
     */
    @Test
    public void testModificar() throws Exception {
        System.out.println("modificar");
        cut.em = MockEm;
        Mockito.when(MockEm.getTransaction()).thenReturn(MockTx);
        cut.modificar(MockC);
        try {
            Mockito.doThrow(PersistenceException.class).when(MockEm).merge(MockC);
            cut.modificar(MockC);
        } catch (PersistenceException e) {
        }

    }

    /**
     * Test of eliminar method, of class UtilidadesFacade.
     */
    @Test
    public void testEliminar() throws Exception {
        System.out.println("eliminar");
        cut.em = MockEm;
        Mockito.when(MockEm.getTransaction()).thenReturn(MockTx);
        cut.eliminar(MockC);
        try {
            Mockito.doThrow(PersistenceException.class).when(MockEm).remove(MockC);
            cut.eliminar(MockC);
        } catch (PersistenceException e) {
        }
    }

    /**
     * Test of getClasificacion method, of class UtilidadesFacade.
     */
    @Test
    public void testGetClasificacion() throws Exception {
        System.out.println("getClasificacion");
        Mockito.when(MockEm.createQuery(Mockito.anyString())).thenReturn(MockQ);
        Mockito.when(MockQ.getResultList()).thenReturn(lista);
        cut.getClasificacion();
        //cuando se genera una excepcion
        try {
            Mockito.when(MockEm.createQuery(Mockito.anyString())).thenReturn(null);
            cut.getClasificacion();
        } catch (Exception e) {
        }
        //cuando el em esta vacio
        cut.em = null;
        cut.getClasificacion();
    }

    /**
     * Test of handler method, of class UtilidadesFacade.
     */
    @Test
    public void testHandler() throws Exception {
        System.out.println("handler");
        cut.em = MockEm;
        //Obtener la lista con la query
        Mockito.when(MockEm.createQuery(Mockito.anyString())).thenReturn(MockQ);
        Mockito.when(MockQ.getResultList()).thenReturn(lista);
        //Definir un tamanio de la lista
        Mockito.when(lista.size()).thenReturn(10);
        //Obtener un ID de la lista
        Mockito.when(lista.get(Mockito.anyInt())).thenReturn(MockC);
        Mockito.when(MockC.getIdClasificacion()).thenReturn(0);
        cut.handler();
        //Suponer que el id no fue encontrado
        Mockito.when(MockC.getIdClasificacion()).thenReturn(2);
        cut.handler();
        //suponer que la lista esta vacia
        Mockito.when(lista.size()).thenReturn(0);
        cut.handler();

    }

}