package ru.zencode.rightdevops;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Override
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    @Override
    public Cat createCat(Cat cat) {
        return catRepository.save(cat);
    }
}
