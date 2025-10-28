package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.jbence1994.webshop.product.CategoryDtoTestObject.categoryDto;
import static com.github.jbence1994.webshop.product.CategoryTestObject.category1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTests {

    @Mock
    private CategoryQueryService categoryQueryService;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    public void getCategoriesTest() {
        when(categoryQueryService.getCategories()).thenReturn(List.of(category1()));
        when(productMapper.toCategoryDto(any(Category.class))).thenReturn(categoryDto());

        var result = categoryController.getCategories();

        assertThat(result, not(empty()));
        assertThat(result.size(), equalTo(1));
    }
}
