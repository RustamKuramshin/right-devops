package ru.zencode.rightdevops;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cats")
public class CatController {

    private final CatService catService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cat> getAllCats() {
        return catService.getAllCats();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cat createCat(@RequestBody Cat cat) {
        return catService.createCat(cat);
    }
}
