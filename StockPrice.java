/*
 * Jesse Calvert
 * 11/17/2017
 */

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;


// Prints the price of the given stock from the command-line.
public class StockPrice {

    /**
     * Finds closing price of exactly one argument,
     * else prints an error message.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid call. Please use exactly "
                + "one stock symbol as an argument.");
        } else {
            try {
                new StockPrice().getClosingPrice(args[0]);
            } catch (Exception e) {
                System.out.println("Error connecting.");
            }
        }
    }
    
    /**
     * Finds the closing price of a given stock symbol from Quandl's database.
     * 
     * @param stockName the stock symbol to search for
     * @throws Exception if there is a problem connecting
     */
    private void getClosingPrice(String stockName) throws Exception {
        final int closingLoc = 4;                       //element the closing price occurs.
        String url = "https://www.quandl.com/api/v3/datasets/WIKI/" + stockName
                + "/data.csv?rows=1&api_key=ALL6aPy3B4xXAnkxHFfN";
        URL quandl = new URL(url);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(quandl.openStream()));
        in.readLine();                                  //skip data header
        String dataLine = in.readLine();                //actual data
        in.close();
        String[] dataArray = dataLine.split(",");
        System.out.println(new BigDecimal(dataArray[closingLoc]));
    }
}
