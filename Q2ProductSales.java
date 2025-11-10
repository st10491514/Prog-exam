public class ProductSales implements iProductSales {
        private int[][] productSales;
        private int salesLimit;

        public ProductSales(int[][] salesData, int salesLimit) {
            this.productSales = salesData;
            this.salesLimit = salesLimit;
        }

        @Override
        public int[][] GetProductsales() {
            return productSales;
        }

        @Override
        public int GetTotalSales() {
            int total = 0;
            for (int[] yearSales : productSales) {
                for (int sale : yearSales) {
                    total += sale;
                }
            }
            return total;
        }

        @Override
        public int GetSalesOverLimit() {
            int count = 0;
            for (int[] yearSales : productSales) {
                for (int sale : yearSales) {
                    if (sale > salesLimit) {
                        count++;
                    }
                }
            }
            return count;
        }

        @Override
        public int GetSalesUnderLimit() {
            int count = 0;
            for (int[] yearSales : productSales) {
                for (int sale : yearSales) {
                    if (sale < salesLimit) {
                        count++;
                    }
                }
            }
            return count;
        }

        @Override
        public int GetProductsProcessed() {
            return productSales.length; // Returns number of years processed
        }

        @Override
        public double GetAverageSales() {
            int totalSales = GetTotalSales();
            int totalProducts = 0;

            for (int[] yearSales : productSales) {
                totalProducts += yearSales.length;
            }

            return totalProducts > 0 ? (double) totalSales / totalProducts : 0;
        }
    }
