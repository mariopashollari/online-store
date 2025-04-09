package com.individual.online_store.services;


import com.individual.online_store.dtos.AuthorDto;
import com.individual.online_store.entities.Author;
import com.individual.online_store.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorDto create(AuthorDto authorDto) {
        if (authorDto.getAuthorId() != null) {
            return null;
        } else {
            Author author = AuthorDto.toEntity(authorDto);
            author = authorRepository.save(author);
            return AuthorDto.toDto(author);
        }
    }

    public AuthorDto update(AuthorDto authorDto) {
        if (authorDto.getAuthorId() != null) {
            Author author = authorRepository.findById(authorDto.getAuthorId())
                    .orElseThrow();
            author.setFirstName(author.getFirstName());
            author.setLastName(author.getLastName());
            author = authorRepository.save(author);
            return AuthorDto.toDto(author);
        } else {
            return null;
        }
    }

    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(AuthorDto::toDto)
                .toList();
    }

    public AuthorDto getById(Long id) {
        Author author = this.findById(id);
        return AuthorDto.toDto(author);
    }

    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow();
    }

}
