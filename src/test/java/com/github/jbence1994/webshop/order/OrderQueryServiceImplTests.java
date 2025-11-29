package com.github.jbence1994.webshop.order;

import com.github.jbence1994.webshop.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.Optional;

import static com.github.jbence1994.webshop.order.OrderTestObject.createdOrder1;
import static com.github.jbence1994.webshop.user.UserTestObject.user1WithoutAvatar;
import static com.github.jbence1994.webshop.user.UserTestObject.user2WithoutAvatar;
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
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderQueryServiceImplTests {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private OrderQueryServiceImpl orderQueryService;

    @BeforeEach
    public void setUp() {
        when(authService.getCurrentUser()).thenReturn(user1WithoutAvatar());
    }

    @Test
    public void getOrdersTest() {
        when(orderRepository.findAllByCustomer(any())).thenReturn(List.of(createdOrder1()));

        var result = orderQueryService.getOrders();

        assertThat(result, not(empty()));
        assertThat(result.size(), equalTo(1));
    }

    @Test
    public void getOrderTest_HappyPath() {
        when(orderRepository.findById(any())).thenReturn(Optional.of(createdOrder1()));

        var result = assertDoesNotThrow(() -> orderQueryService.getOrder(1L));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(createdOrder1().getId())),
                hasProperty("totalPrice", equalTo(createdOrder1().getTotalPrice())),
                hasProperty("status", equalTo(createdOrder1().getStatus())),
                hasProperty("createdAt", equalTo(createdOrder1().getCreatedAt()))
        ));
    }

    @Test
    public void getOrderTest_UnhappyPath_OrderNotFoundException() {
        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                OrderNotFoundException.class,
                () -> orderQueryService.getOrder(1L)
        );

        assertThat(result.getMessage(), equalTo("No order was found with the given ID: #1."));
    }

    @Test
    public void getOrderTest_UnhappyPath_AccessDeniedException() {
        when(authService.getCurrentUser()).thenReturn(user2WithoutAvatar());
        when(orderRepository.findById(any())).thenReturn(Optional.of(createdOrder1()));

        var result = assertThrows(
                AccessDeniedException.class,
                () -> orderQueryService.getOrder(1L)
        );

        assertThat(result.getMessage(), equalTo("Access denied."));
    }
}
