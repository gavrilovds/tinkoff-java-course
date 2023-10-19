package edu.hw3.task6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Assertions.*;

public class StockMarketTest {

    private StockMarket moscowExchange;

    @BeforeEach
    private void initStockMarket() {
        moscowExchange = new MoscowExchange();
        moscowExchange.add(new Stock("$SBER", 300));
        moscowExchange.add(new Stock("$TCSG", 3600));
        moscowExchange.add(new Stock("$GAZP", 170));
    }

    @Test
    @DisplayName("#remove test")
    public void stock_shouldRemoveGivenStockFromStockMarket() {
        moscowExchange.remove(new Stock("$TCSG", 3600));
        assertThat(moscowExchange.getMostValuebleStock())
            .extracting("ticker", "price")
            .containsExactly("$SBER", 300d);
    }

    @Test
    @DisplayName("#getMostValuableStock test")
    public void stockMarket_shouldReturnTheMostValuableStock() {
        assertThat(moscowExchange.getMostValuebleStock())
            .extracting("ticker", "price")
            .containsExactly("$TCSG", 3600d);
    }

    @Test
    @DisplayName("#add test")
    public void stock_shouldAddGivenStockToStockMarket() {
        moscowExchange.add(new Stock("$TRNFP", 147400));
        assertThat(moscowExchange.getMostValuebleStock())
            .extracting("ticker", "price")
            .containsExactly("$TRNFP", 147400d);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null test for #add and #remove")
    public void stock_shouldThrowException_whenStockIsNull(Stock testStock) {
        org.junit.jupiter.api.Assertions.assertAll(
            () -> assertThatThrownBy(() -> moscowExchange.remove(testStock)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> moscowExchange.add(testStock)).isInstanceOf(IllegalArgumentException.class)
        );
    }
}
