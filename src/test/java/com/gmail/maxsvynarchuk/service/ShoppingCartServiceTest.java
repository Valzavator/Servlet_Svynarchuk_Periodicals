package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.provider.EntityProvider;
import com.gmail.maxsvynarchuk.service.entity.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {
    @InjectMocks
    private ShoppingCartService shoppingCartService = ShoppingCartService.getInstance();
    @Mock
    private PeriodicalService periodicalService;

    @Test
    void addItemToCartTest() {
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        User user = User.newBuilder()
                .setId(1L)
                .build();
        Periodical periodical = Periodical.newBuilder()
                .setId(1L)
                .build();
        SubscriptionPlan subscriptionPlan = EntityProvider.getOneMonthSubscriptionPlan();

        shoppingCartService.addItemToCart(shoppingCart, user, periodical, subscriptionPlan);

        verify(shoppingCart, times(1)).addItem(any(Subscription.class));
    }

    @Test
    void removeItemFromCartTest() {
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        long cartItemId = 1L;

        shoppingCartService.removeItemFromCart(shoppingCart, cartItemId);

        verify(shoppingCart, times(1)).removeItem(cartItemId);
    }

    @Test
    void removeAllItemFromCartTest() {
        ShoppingCart shoppingCart = mock(ShoppingCart.class);

        shoppingCartService.removeAllItemFromCart(shoppingCart);

        verify(shoppingCart, times(1)).removeAll();
    }

    @Test
    void updateShoppingCartItemsFromDatabaseTest() {
        Long periodicalId = 1L;
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        User user = User.newBuilder()
                .setId(1L)
                .build();
        Periodical periodical = Periodical.newBuilder()
                .setId(periodicalId)
                .build();
        SubscriptionPlan subscriptionPlan = EntityProvider.getOneMonthSubscriptionPlan();
        Subscription item = Subscription.newBuilder()
                .setUser(user)
                .setPeriodical(periodical)
                .setSubscriptionPlan(subscriptionPlan)
                .build();
        List<Subscription> items = new ArrayList<Subscription>() {{
            add(item);
        }};
        when(shoppingCart.getItems()).thenReturn(items);
        when(periodicalService.findPeriodicalById(periodicalId))
                .thenReturn(Optional.of(periodical));

        shoppingCartService.updateShoppingCartItemsFromDatabase(shoppingCart);

        verify(shoppingCart, times(1)).updateItem(item);
    }

    @Test
    void updateShoppingCartItemsFromDatabaseWithExceptionTest() {
        Long periodicalId = 1L;
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        User user = User.newBuilder()
                .setId(1L)
                .build();
        Periodical periodical = Periodical.newBuilder()
                .setId(periodicalId)
                .build();
        SubscriptionPlan subscriptionPlan = EntityProvider.getOneMonthSubscriptionPlan();
        Subscription item = Subscription.newBuilder()
                .setUser(user)
                .setPeriodical(periodical)
                .setSubscriptionPlan(subscriptionPlan)
                .build();
        List<Subscription> items = new ArrayList<Subscription>() {{
            add(item);
        }};
        when(shoppingCart.getItems()).thenReturn(items);
        when(periodicalService.findPeriodicalById(periodicalId))
                .thenReturn(Optional.empty());

        ServiceException e = assertThrows(ServiceException.class, () ->
                shoppingCartService.updateShoppingCartItemsFromDatabase(shoppingCart));
        assertEquals("A subscription cannot refer to a non-existent periodical", e.getMessage());
        verify(shoppingCart, never()).updateItem(item);
    }
}