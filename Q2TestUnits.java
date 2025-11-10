import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Enclosed.class)
public class TestUnits{

public static class ProductSalesTest {

    @Test
    public void GetSalesOverLimit_ReturnsNumberOfSales() {
        // Arrange
        int[][] testData = {
                {300, 150, 700}, // 700 > 500
                {250, 200, 600} // 600 > 500
        };
        ProductSales productSales = new ProductSales(testData, 500);

        // Act
        int overLimit = productSales.GetSalesOverLimit();

        // Assert
        assertEquals(2, overLimit, "Should return 2 sales over the limit (700 and 600)");
    }

    @Test
    public void GetSalesUnderLimit_ReturnsNumberOfSales() {
        // Arrange
        int[][] testData = {
                {300, 150, 700}, // 300 < 500, 150 < 500
                {250, 200, 600} // 250 < 500, 200 < 500
        };
        ProductSales productSales = new ProductSales(testData, 500);

        // Act
        int underLimit = productSales.GetSalesUnderLimit();

        // Assert
        assertEquals(4, underLimit, "Should return 4 sales under the limit (300, 150, 250, 200)");
    }

    @Test
    public void GetTotalSales_ReturnsCorrectTotal() {
        // Arrange
        int[][] testData = {
                {300, 150, 700},
                {250, 200, 600}
        };
        ProductSales productSales = new ProductSales(testData, 500);

        // Act
        int total = productSales.GetTotalSales();

        // Assert
        assertEquals(2200, total, "Total should be 300+150+700+250+200+600 = 2200");
    }

    @Test
    public void GetAverageSales_ReturnsCorrectAverage() {
        // Arrange
        int[][] testData = {
                {300, 150, 700},
                {250, 200, 600}
        };
        ProductSales productSales = new ProductSales(testData, 500);

        // Act
        double average = productSales.GetAverageSales();

        // Assert
        assertEquals(366.666, average, 0.001, "Average should be 2200/6 ≈ 366.666");
    }

    @Test
    public void GetProductsProcessed_ReturnsNumberOfYears() {
        // Arrange
        int[][] testData = {
                {300, 150, 700},
                {250, 200, 600}
        };
        ProductSales productSales = new ProductSales(testData, 500);

        // Act
        int yearsProcessed = productSales.GetProductsProcessed();

        // Assert
        assertEquals(2, yearsProcessed, "Should return 2 years processed");
    }
}
}
