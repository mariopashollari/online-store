package com.individual.online_store.dtos;


import com.individual.online_store.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthorDto {
    private Long authorId;
    private String firstName;
    private String lastName;


    public static Author toEntity(AuthorDto authorDto) {
        return Author.builder()
                .authorId(authorDto.getAuthorId())
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .build();
    }


    public static AuthorDto toDto(Author author) {
        return AuthorDto.builder()
                .authorId(author.getAuthorId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }
}
