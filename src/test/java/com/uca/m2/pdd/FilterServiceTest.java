package com.uca.m2.pdd;

import com.uca.m2.pdd.Model.dto.FilterDto;
import com.uca.m2.pdd.Model.entity.Filter;
import com.uca.m2.pdd.Repository.FilterRepository;
import com.uca.m2.pdd.Repository.UsersRepository;
import com.uca.m2.pdd.Service.FilterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class FilterServiceTest {

    @Mock
    private FilterRepository filterRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private FilterService filterService;

    @Test
    void testCreateFilter_Success() {
        // Arrange
        UUID userId = UUID.randomUUID();
        FilterDto filterDto = new FilterDto();
        filterDto.setEtat("Bon");
        filterDto.setModeDeRemise("ENVOI");

        Mockito.when(usersRepository.existsById(userId)).thenReturn(true);

        Filter savedFilter = new Filter();
        savedFilter.setId(UUID.randomUUID());
        savedFilter.setUserId(userId);
        savedFilter.setEtat("Bon");
        savedFilter.setModeDeRemise("ENVOI");

        Mockito.when(filterRepository.save(Mockito.any(Filter.class))).thenReturn(savedFilter);

        // Act
        FilterDto result = filterService.createFilter(userId, filterDto);

        // Assert
        Assertions.assertNotNull(result.getId(), "Filter ID should not be null");
        Assertions.assertEquals("Bon", result.getEtat(), "Etat should match");
        Assertions.assertEquals("ENVOI", result.getModeDeRemise(), "Mode de remise should match");
    }

    @Test
    void testCreateFilter_UserNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();
        FilterDto filterDto = new FilterDto();

        Mockito.when(usersRepository.existsById(userId)).thenReturn(false);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> filterService.createFilter(userId, filterDto),
                "Should throw IllegalArgumentException if user is not found");
    }

    @Test
    void testGetFiltersByUser_Success() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Filter filter1 = new Filter();
        filter1.setId(UUID.randomUUID());
        filter1.setUserId(userId);

        Filter filter2 = new Filter();
        filter2.setId(UUID.randomUUID());
        filter2.setUserId(userId);

        Mockito.when(filterRepository.findByUserId(userId)).thenReturn(List.of(filter1, filter2));

        // Act
        List<FilterDto> result = filterService.getFiltersByUser(userId);

        // Assert
        Assertions.assertEquals(2, result.size(), "Number of filters should match");
    }

    @Test
    void testDeleteFilter_Success() {
        // Arrange
        UUID filterId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Filter filter = new Filter();
        filter.setId(filterId);
        filter.setUserId(userId);

        Mockito.when(filterRepository.findById(filterId)).thenReturn(Optional.of(filter));

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> filterService.deleteFilter(userId, filterId), "Should not throw any exception");
        Mockito.verify(filterRepository, Mockito.times(1)).delete(filter);
    }

    @Test
    void testDeleteFilter_NotFound() {
        // Arrange
        UUID filterId = UUID.randomUUID();
        Mockito.when(filterRepository.findById(filterId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> filterService.deleteFilter(UUID.randomUUID(), filterId),
                "Should throw IllegalArgumentException if filter is not found");
    }

    @Test
    void testDeleteFilter_UserNotOwner() {
        // Arrange
        UUID filterId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID otherUserId = UUID.randomUUID();

        Filter filter = new Filter();
        filter.setId(filterId);
        filter.setUserId(otherUserId);

        Mockito.when(filterRepository.findById(filterId)).thenReturn(Optional.of(filter));

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> filterService.deleteFilter(userId, filterId),
                "Should throw IllegalArgumentException if user is not the owner of the filter");
    }
}
