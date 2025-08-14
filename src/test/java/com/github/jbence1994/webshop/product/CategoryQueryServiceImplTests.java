package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.github.jbence1994.webshop.product.CategoryTestConstants.CATEGORY_1_NAME;
import static com.github.jbence1994.webshop.product.CategoryTestObject.category1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryQueryServiceImplTests {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryQueryServiceImpl categoryQueryService;

    @Test
    public void getCategoriesTest() {
        when(categoryRepository.findAllByOrderByIdAsc()).thenReturn(List.of(category1()));

        var result = categoryQueryService.getCategories();

        assertThat(result, not(empty()));
        assertThat(result.size(), equalTo(1));
    }

    @Test
    public void getCategoryTest_HappyPath() {
        when(categoryRepository.findByName(any())).thenReturn(Optional.of(category1()));

        var result = assertDoesNotThrow(() -> categoryQueryService.getCategory(CATEGORY_1_NAME));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(category1().getId())),
                hasProperty("name", equalTo(category1().getName()))
        ));
    }

    @Test
    public void getCategoryTest_UnhappyPath_InvalidCategoryException() {
        when(categoryRepository.findByName(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                InvalidCategoryException.class,
                () -> categoryQueryService.getCategory("Unknow Category Name")
        );

        assertThat(result.getMessage(), equalTo("Invalid category with the following name: 'Unknow Category Name'."));
    }
}
