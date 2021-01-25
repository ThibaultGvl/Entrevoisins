package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbourWithSuccess() {
        Neighbour neighbourToCreate = service.getNeighbours().get(0);
        service.createNeighbour(neighbourToCreate);
        assertTrue(service.getNeighbours().contains(neighbourToCreate));
    }

    @Test
    public void getFavoritesNeighboursWithSuccess() {
        List<Neighbour> favoritesneighbours = service.getFavoritesNeighbours();
        List<Neighbour> expectedFavoritesNeighbours = favoritesneighbours;
        assertThat(favoritesneighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedFavoritesNeighbours.toArray()));
    }

    @Test
    public void addToFavoritesWithSuccess() {
        Neighbour neighbourToAddFavorite = service.getNeighbours().get(0);
        service.addToFavorite(neighbourToAddFavorite);
        assertTrue(service.getFavoritesNeighbours().contains(neighbourToAddFavorite));
    }


    public void setup1() {addToFavoritesWithSuccess();}

    @Test
    public void deleteFromFavoritesWithSuccess() {
        setup1();
        Neighbour neighbourToDeleteFavorite = service.getFavoritesNeighbours().get(0);
        service.deleteFromFavorite(neighbourToDeleteFavorite);
        assertFalse(service.getFavoritesNeighbours().contains(neighbourToDeleteFavorite));
    }
}
