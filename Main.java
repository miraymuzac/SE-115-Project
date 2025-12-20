// Main.java — Students version

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    static int[][][] profits;


    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
        profits = new int[MONTHS][DAYS][COMMS];
        Scanner reader;
        try {
            for (int i = 0; i < MONTHS; i++) {
                reader = new Scanner(Paths.get("Data_Files/" + months[i] + ".txt"));
                reader.nextLine();
                while (reader.hasNextLine()) {
                    String[] splitLine = reader.nextLine().split(",");
                    int dayIndex = Integer.parseInt(splitLine[0]) - 1;
                    String commInput = splitLine[1];
                    int commsIndex = 0;
                    for (int j = 0; j < COMMS; j++) {
                        if (commInput.equals(commodities[j])) {
                            commsIndex = j;
                            break;
                        }
                    }
                    int profit = Integer.parseInt(splitLine[2]);
                    profits[i][dayIndex][commsIndex] = profit;
                }
            }
        } catch (IOException e) {
            System.out.println("File does not exists!");
        }
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        if (month < 0 || month > 11) {
            return "INVALID_MONTH";
        }
        int[] comms = new int[COMMS];
        for (int i = 0; i < DAYS; i++) {
            for (int j = 0; j < COMMS; j++) {
                int profit = profits[month][i][j];
                comms[j] += profit;
            }
        }
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < comms.length; i++) {
            if (comms[i] >= max) {
                max = comms[i];
                index = i;
            }
        }
        return commodities[index] + " " + max;
    }

    public static int totalProfitOnDay(int month, int day) {
        if (month < 0 || month > 11) {
            return -99999;
        }
        if (day < 1 || day > 28) {
            return -99999;
        }
        int totalProfit = 0;
        for (int i = 0; i < COMMS; i++){
            totalProfit += profits[month][day-1][i];
        }
        return totalProfit ;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        if (from < 1 || to > 28) {
            return -99999;
        }
        if (from > to) {
            return -99999;
        }
        int commsIndex = -1;
        for (int j = 0; j < COMMS; j++) {
            if (commodity.equals(commodities[j])) {
                commsIndex = j;
                break;
            }
        }
        if (commsIndex == -1) {
            return -99999;
        }
        int total = 0;
        for (int i = 0; i < MONTHS; i++){
            for (int j = from; j <= to; j++){
                total += profits[i][j][commsIndex];
            }
        }
        return total;
    }

    public static int bestDayOfMonth(int month) {
        if (month < 0 || month > 11) {
            return -1;
        }
        int total[] = new int[DAYS];
        for (int i = 0; i < DAYS; i++) {
            for (int j = 0; j < COMMS; j++) {
                total[i] += profits[month][i][j];
            }
        }
        int day = 0;
        int max = 0;
        for (int i = 0; i < total.length; i++) {
            if (total[i] >= max) {
                max = total[i];
                day = i;
            }
        }
        return day;
    }

    public static String bestMonthForCommodity(String comm) {
        int commsIndex = -1;
        for (int i = 0; i < COMMS; i++) {
            if (comm.equals(commodities[i])) {
                commsIndex = i;
                break;
            }
        }
        if (commsIndex == -1) {
            return "INVALID_COMMODITY";
        }
        int total[] = new int[MONTHS];
        for (int i = 0; i < MONTHS; i++) {
            for (int j = 0; j < DAYS; j++) {
                total[i] += profits[i][j][commsIndex];
            }
        }
        int month = 0;
        int max = 0;
        for (int i = 0; i < total.length; i++) {
            if (total[i] >= max) {
                max = total[i];
                month = i;
            }
        }

        return months[month] + " " + max;
    }

    public static int consecutiveLossDays(String comm) {
        return 1234;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        return 1234;
    }

    public static int biggestDailySwing(int month) {
        return 1234;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }

    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }

}


