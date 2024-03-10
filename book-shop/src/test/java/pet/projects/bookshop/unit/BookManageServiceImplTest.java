package pet.projects.bookshop.unit;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pet.projects.bookshop.dto.Book;
import pet.projects.bookshop.repositories.BookRepository;
import pet.projects.bookshop.service.impl.BookManageServiceImpl;
import pet.projects.bookshop.tool.exception.BookAlreadyExistException;
import pet.projects.bookshop.tool.exception.BookNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BookManageServiceImplTest {
    @Mock
    private BookRepository repository;
    @InjectMocks
    private BookManageServiceImpl service;

    @Test
    public void addNonExistentBook() {
        final var book = buildBook();
        when(repository.existsByNameAndAuthor(
                book.getName(),
                book.getAuthor()
        )).thenReturn(false);

        service.add(book);

        verify(repository, times(1)).save(book);
    }

    @Test
    public void addExistentBook() {
        final var book = buildBook();
        when(repository.existsByNameAndAuthor(
                book.getName(),
                book.getAuthor()
        )).thenReturn(true);

        assertThatThrownBy(() -> service.add(book)).
                isInstanceOf(BookAlreadyExistException.class);
    }

    @Test
    public void deleteExistentBook() {
        final var id = Integer.MAX_VALUE;
        when(repository.existsById(id))
                .thenReturn(true);

        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void deleteNonExistentBook() {
        final var id = Integer.MAX_VALUE;
        when(repository.existsById(id))
                .thenReturn(false);

        assertThatThrownBy(() -> service.deleteById(id))
                .isInstanceOf(BookNotFoundException.class);
    }

    @Test
    public void updateExistentBook() {
        final var book = buildBook();
        final var id = book.getId();
        when(repository.existsById(id))
                .thenReturn(true);

        service.update(book);

        verify(repository, times(1)).deleteById(id);
        verify(repository, times(1)).save(book);
    }

    @Test
    public void updateNonExistentBook() {
        final var book = buildBook();
        final var id = book.getId();
        when(repository.existsById(id))
                .thenReturn(false);

        assertThatThrownBy(() -> service.update(book))
                .isInstanceOf(BookNotFoundException.class);
    }

    private Book buildBook() {
        return Book.builder()
                .id(1)
                .name("name")
                .author("author")
                .description("description")
                .purchases(new ArrayList<>())
                .price(BigDecimal.ZERO)
                .build();
    }
}
