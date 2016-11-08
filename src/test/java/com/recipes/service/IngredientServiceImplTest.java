package com.recipes.service;

import com.recipes.dao.IngredientRepository;
import com.recipes.model.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceImplTest {
    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService = new IngredientServiceImpl();

    @Test
    public void saveAddsIngredient() throws Exception {
        List<Ingredient> ingredients = new ArrayList<>();
        when(ingredientRepository.save(any(Ingredient.class))).then(answer -> {
            ingredients.add(new Ingredient());
            return true;
        });

        ingredientService.save(new Ingredient());

        assertThat("ingredient was added", ingredients.size() == 1);
    }
}
