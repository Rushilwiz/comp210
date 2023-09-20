package assn02;
import java.text.DecimalFormat;
import java.util.Scanner;

// Here is a starter code that you may optionally use for this assignment.
// TODO: You need to complete these sections

public class JavaWarmUp {
    public static void main(String[] args) {

        final double WAGE = 16.0;
        final DecimalFormat df = new DecimalFormat("0.00");

        Scanner s = new Scanner(System.in);

        String[] categoriesList = {"phone", "laptop", "smart_watch"};

        int n = s.nextInt();
        // MM/DD/YY, HH:MM, Name, Price, Quantity, Rating, Duration

        s.nextLine();
        String[] input = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = s.nextLine();
        }

        s.close();
        

        // create corresponding size arrays
        String dateT[] = new String[n];
        String timeT[] = new String[n];
        String categoryT[] = new String[n];
        double Assembling_fee[] = new double[n];
        int quantityT[] = new int[n];
        double Assembling_Time [] = new double[n];
        double Energy_and_Device_Cost [] = new double[n];

		// TODO: Fill in the above arrays with data entered from the console.
        // Your code starts here:
        for (int i = 0; i < n; i++) {
            String[] temp = input[i].split(" ");
            dateT[i] = temp[0];
            timeT[i] = temp[1];
            categoryT[i] = temp[2];
            Assembling_fee[i] = Double.parseDouble(temp[3]);
            quantityT[i] = Integer.parseInt(temp[4]);
            Assembling_Time[i] = Double.parseDouble(temp[5]);
            Energy_and_Device_Cost[i] = Double.parseDouble(temp[6]);
        }
		// Your code ends here.

        // Find items with highest and lowest price per unit
        int highestItemIndex = getMaxPriceIndex(Assembling_fee);
        int lowestItemIndex = getMinPriceIndex(Assembling_fee);


        // 6
        // 6/8/22 19:32 laptop 41.73 593 384.5 1607
        // 5/9/22 22:26 phone 20.79 3606 1795.0 2252
        // 9/21/22 14:34 laptop 49.36 1525 1044.6 1779
        // 8/6/22 11:26 phone 20.91 5401 1958.6 2381
        // 9/15/22 1:38 smart_watch 12.99 1046 158.4 1756
        // 9/6/21 5:09 smart_watch 12.18 670 100.5 1728

		// TODO: Print items with highest and lowest price per unit.
		// Your code starts here:
        System.out.println(dateT[highestItemIndex]);
        System.out.println(timeT[highestItemIndex]);
        System.out.println(categoryT[highestItemIndex]);
        System.out.println(Assembling_fee[highestItemIndex]);

        System.out.println(dateT[lowestItemIndex]);
        System.out.println(timeT[lowestItemIndex]);
        System.out.println(categoryT[lowestItemIndex]);
        System.out.println(Assembling_fee[lowestItemIndex]);
		// Your code ends here.

        // Calculate the average price, rating and duration of sales by category.
        // Maintain following category-wise stats in Arrays
        int[] numOfCategoriesC = new int[categoriesList.length];// so numOfCategoriesC[0] = # of categories of type categoriesList[0]
        double[] totPriceC = new double[categoriesList.length]; // total price of each category = sum(price x qty)
        int[] totQuantityC = new int[categoriesList.length];    // total qty of each category = sum (qty)
        double[] totAssembling_TimeC = new double[categoriesList.length]; // total Rating of each category = sum(price x qty)
        double[] totEnergy_and_Device_CostC = new double[categoriesList.length]; // total Duration of each category = sum(price x qty)


		// TODO: set the value of catIndex for each i to be such that categoryT[i] == categoriesList[i].
		// Your code starts here:
        int[] catIndex = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < categoriesList.length; j++) {
                if (categoryT[i].equals(categoriesList[j])) {
                    catIndex[i] = j;
                }
            }
        }
		// Your code ends here.

		// TODO: Calculate & Print Category-wise Statistics
        // Your code starts here:
        for (int i = 0; i < categoriesList.length; i++) {
            for (int j = 0; j < n; j++) {
                if (catIndex[j] == i) {
                    numOfCategoriesC[i]++;
                    totPriceC[i] += Assembling_fee[j] * quantityT[j];
                    totQuantityC[i] += quantityT[j];
                    totAssembling_TimeC[i] += Assembling_Time[j];
                    totEnergy_and_Device_CostC[i] += Energy_and_Device_Cost[j];
                }
            }
        }

        for (int i = 0; i < categoriesList.length; i++) {
            System.out.println(categoriesList[i]);
            System.out.println(totQuantityC[i]);
            System.out.println(df.format(totPriceC[i] / totQuantityC[i]));
            System.out.println(df.format((totPriceC[i] - totAssembling_TimeC[i] * WAGE - totEnergy_and_Device_CostC[i])/totQuantityC[i]));
        }
		// Your code ends here.
    }

    // TODO: Find index of item with the highest price per unit.
    static int getMaxPriceIndex(double[] priceT){
        double max = priceT[0];
        int maxIndex = 0;
        for (int i = 0; i < priceT.length; i++) {
            if (priceT[i] > max) {
                max = priceT[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // TODO: Find index of item with the lowest price per unit.
    static int getMinPriceIndex(double[] priceT){
        double min = priceT[0];
        int minIndex = priceT.length - 1;
        for (int i = 0; i < priceT.length; i++) {
            if (priceT[i] < min) {
                min = priceT[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
