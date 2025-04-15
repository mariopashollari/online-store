package com.individual.online_store.controllers;


import com.individual.online_store.dtos.AuthorDto;
import com.individual.online_store.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/create")
    public AuthorDto create(@RequestBody AuthorDto authorDto) {return authorService.create(authorDto);}

    @GetMapping("/all")
    public List<AuthorDto> findAll() {return authorService.findAll();}

    @PutMapping("/update")
    public AuthorDto update(@RequestBody AuthorDto authorDto) {return authorService.update(authorDto);}

    @GetMapping("/by_id")
    public AuthorDto findById(@RequestParam Long id) {return authorService.getById(id);}



    }

