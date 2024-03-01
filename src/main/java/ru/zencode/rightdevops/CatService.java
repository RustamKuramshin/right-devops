package ru.zencode.rightdevops;

import java.util.List;

public interface CatService {

    List<Cat> getAllCats();

    Cat createCat(Cat cat);
}
