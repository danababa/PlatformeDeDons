package com.uca.m2.pdd;

import com.uca.m2.pdd.Model.dto.AnnonceDto;
import com.uca.m2.pdd.Model.entity.Annonce;
import com.uca.m2.pdd.Repository.AnnonceRepository;
import com.uca.m2.pdd.Service.AnnonceService;
import com.uca.m2.pdd.Model.Enum.ModeDeRemiseEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AnnonceServiceTests {

    @Mock
    private AnnonceRepository annonceRepository;

    @InjectMocks
    private AnnonceService annonceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAnnonces() {
        List<Annonce> mockAnnonces = Arrays.asList(
                new Annonce(UUID.randomUUID(), "Title 1", "Description 1", "Bon", new Date(), 45.0, 90.0, ModeDeRemiseEnum.MAINS_PROPRE, Arrays.asList("Key1")),
                new Annonce(UUID.randomUUID(), "Title 2", "Description 2", "Neuf", new Date(), 46.0, 91.0, ModeDeRemiseEnum.ENVOI, Arrays.asList("Key2"))
        );

        when(annonceRepository.findAll()).thenReturn(mockAnnonces);

        List<AnnonceDto> result = annonceService.getAllAnnonces();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Title 1", result.get(0).getTitre());
        verify(annonceRepository, times(1)).findAll();
    }

    @Test
    void testGetAnnonceById() {
        UUID annonceId = UUID.randomUUID();
        Annonce mockAnnonce = new Annonce(annonceId, "Title", "Description", "Bon", new Date(), 45.0, 90.0, ModeDeRemiseEnum.MAINS_PROPRE, Arrays.asList("Key1"));

        when(annonceRepository.findById(annonceId)).thenReturn(Optional.of(mockAnnonce));

        AnnonceDto result = annonceService.getAnnonceById(annonceId);

        assertNotNull(result);
        assertEquals("Title", result.getTitre());
        verify(annonceRepository, times(1)).findById(annonceId);
    }


    @Test
    void testCreateAnnonce() {
        UUID annonceId = UUID.randomUUID();
        Annonce newAnnonce = new Annonce(null, "New Title", "New Description", "Neuf", new Date(), 45.0, 90.0, ModeDeRemiseEnum.ENVOI, Arrays.asList("Key1"));
        Annonce savedAnnonce = new Annonce(annonceId, "New Title", "New Description", "Neuf", new Date(), 45.0, 90.0, ModeDeRemiseEnum.ENVOI, Arrays.asList("Key1"));

        when(annonceRepository.save(any(Annonce.class))).thenReturn(savedAnnonce);

        AnnonceDto result = annonceService.createAnnonce(new AnnonceDto(null, "New Title", "New Description", "Neuf", new Date(), 45.0, 90.0, ModeDeRemiseEnum.ENVOI, Arrays.asList("Key1")));

        assertNotNull(result);
        assertEquals(annonceId, result.getId());
        assertEquals("New Title", result.getTitre());
        verify(annonceRepository, times(1)).save(any(Annonce.class));
    }

    @Test
    void testUpdateAnnonce() {
        UUID annonceId = UUID.randomUUID();
        Annonce existingAnnonce = new Annonce(annonceId, "Old Title", "Old Description", "Bon", new Date(), 45.0, 90.0, ModeDeRemiseEnum.MAINS_PROPRE, Arrays.asList("Key1"));
        Annonce updatedAnnonce = new Annonce(annonceId, "Updated Title", "Updated Description", "Neuf", new Date(), 46.0, 91.0, ModeDeRemiseEnum.ENVOI, Arrays.asList("Key2"));

        when(annonceRepository.findById(annonceId)).thenReturn(Optional.of(existingAnnonce));
        when(annonceRepository.save(any(Annonce.class))).thenReturn(updatedAnnonce);

        AnnonceDto result = annonceService.updateAnnonce(new AnnonceDto(annonceId, "Updated Title", "Updated Description", "Neuf", new Date(), 46.0, 91.0, ModeDeRemiseEnum.ENVOI, Arrays.asList("Key2")), annonceId);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitre());
        verify(annonceRepository, times(1)).findById(annonceId);
        verify(annonceRepository, times(1)).save(any(Annonce.class));
    }

    @Test
    void testDeleteAnnonce() {
        UUID annonceId = UUID.randomUUID();

        // Simuler l'existence de l'annonce dans le repository
        Annonce mockAnnonce = new Annonce(annonceId, "Title", "Description", "Bon", new Date(), 45.0, 90.0, ModeDeRemiseEnum.ENVOI, Arrays.asList("Key1"));
        when(annonceRepository.findById(annonceId)).thenReturn(Optional.of(mockAnnonce));

        // Simuler la suppression
        doNothing().when(annonceRepository).deleteById(annonceId);

        // Appeler la méthode à tester
        annonceService.deleteAnnonce(annonceId);

        // Vérifier les interactions
        verify(annonceRepository, times(1)).findById(annonceId);
        verify(annonceRepository, times(1)).deleteById(annonceId);
    }

}
