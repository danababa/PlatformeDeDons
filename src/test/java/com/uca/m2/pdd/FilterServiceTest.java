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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
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
        UUID userId = UUID.randomUUID();
        FilterDto filterDto = new FilterDto();
        filterDto.setZoneGeographique("Paris");
        filterDto.setEtat("Bon");
        filterDto.setModeDeRemise("ENVOI");

        Mockito.when(usersRepository.existsById(userId)).thenReturn(true);

        Filter savedFilter = new Filter();
        savedFilter.setId(UUID.randomUUID());
        savedFilter.setUserId(userId);
        Mockito.when(filterRepository.save(Mockito.any())).thenReturn(savedFilter);

        FilterDto result = filterService.createFilter(userId, filterDto);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Paris", result.getZoneGeographique());
    }

    @Test
    void testGetFiltersByUser_Success() {
        UUID userId = UUID.randomUUID();
        List<Filter> filters = List.of(new Filter(), new Filter());
        Mockito.when(filterRepository.findByUserId(userId)).thenReturn(filters);

        List<FilterDto> result = filterService.getFiltersByUser(userId);

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testDeleteFilter_Success() {
        UUID filterId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Filter filter = new Filter();
        filter.setId(filterId);
        filter.setUserId(userId);
        Mockito.when(filterRepository.findById(filterId)).thenReturn(Optional.of(filter));

        Assertions.assertDoesNotThrow(() -> filterService.deleteFilter(userId, filterId));
    }

    @Test
    void testDeleteFilter_NotFound() {
        UUID filterId = UUID.randomUUID();
        Mockito.when(filterRepository.findById(filterId)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> filterService.deleteFilter(UUID.randomUUID(), filterId));
    }
}
