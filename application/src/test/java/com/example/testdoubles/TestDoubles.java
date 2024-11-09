package com.example.testdoubles;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;

@MockitoSettings
class TestDoubles {

    @Test
    void mocking() {
        var repository = mock(Repository.class);
        var useCases = new UseCases(repository);

        useCases.create();

        verify(repository).add(refEq(new Entity()));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void stubbing() {
        var repository = mock(Repository.class);
        var useCases = new UseCases(repository);

        var entityId = 1L;
        when(repository.find(entityId)).thenReturn(Optional.of(new Entity()));
//        doReturn(Optional.of(new Entity())).when(repository).find(entityId);

        var entity = useCases.find(entityId);

        assertThat(entity).containsInstanceOf(Entity.class);
        //anti-pattern that leads to test fragility. Only the outcome should be verified, not implementation details
//        verify(repository, times(1)).find(entityId);
    }

    @Test
    void mockingAndStubbing() {
        var repository = mock(Repository.class);
        var useCases = new UseCases(repository);

        var entityId = 1L;
        when(repository.find(entityId)).thenReturn(Optional.of(new Entity()));

        useCases.update(entityId);

        verify(repository).add(refEq(new Entity()));
        verifyNoMoreInteractions(repository);
    }
}

@AllArgsConstructor
class UseCases {

    Repository repository;

    Optional<Entity> find(Long id) {
        return repository.find(id); //in a real world scenario a projection of a domain object will be returned
    }

    void create() {
        var entity = new Entity();
        repository.add(entity);
    }

    void update(Long id) {
        repository.find(id).ifPresent(entity -> repository.add(entity));
    }
}

interface Repository {

    Optional<Entity> find(Long id);

    void add(Entity entity);
}

class Entity {

}
