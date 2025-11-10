import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnits {

    private final int[][] testSalesData = {
            {300, 150, 700},
            {250, 200, 600}
    };

    private final ProductSales productSales = new ProductSales();

    @Test
    public void CalculateTotalSales_ReturnsTotalSales() {
        int expectedTotal = 300 + 150 + 700 + 250 + 200 + 600;
        int actualTotal = productSales.TotalSales(testSalesData);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void AverageSales_ReturnsAverageProductSales() {
        double expectedAverage = (300 + 150 + 700 + 250 + 200 + 600) / 6.0;
        double actualAverage = productSales.AverageSales(testSalesData);
        assertEquals(expectedAverage, actualAverage, 0.01);
    }

    @Test
    public void MaxSale_ReturnsMaximumSale() {
        int expectedMax = 700;
        int actualMax = productSales.MaxSale(testSalesData);
        assertEquals(expectedMax, actualMax);
    }

    @Test
    public void MinSale_ReturnsMinimumSale() {
        int expectedMin = 150;
        int actualMin = productSales.MinSale(testSalesData);
        assertEquals(expectedMin, actualMin);
    }
}
