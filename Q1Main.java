public class Main {
    public static void main(String[] args) {
        int[][] salesData = {
                {300, 150, 700},
                {250, 200, 600}
        };

        ProductSales productSales = new ProductSales();

        // Generates the report
        System.out.println("PRODUCT SALES REPORT - 2025");
        System.out.println("---");
        System.out.println("Total sales: " + productSales.TotalSales(salesData));
        System.out.println("Average sales: " + Math.round(productSales.AverageSales(salesData)));
        System.out.println("Maximum sales: " + productSales.MaxSale(salesData));
        System.out.println("Minimum sales: " + productSales.MinSale(salesData));
    }
}
