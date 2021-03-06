package com.team300.fridge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


//change finance to be an activity not fragment

//https://medium.com/@karthikganiga007/create-barchart-in-android-studio-14943339a211
public class FinanceTrackerActivity extends AppCompatActivity {

    HashMap<String, Float> priceCategories = new HashMap<String, Float>();
    //priceCategories Keys abbreviation meanings; values are just the associated price
        //  BF: Baby Food
        //  BGB: Baked Goods - Bakery
        //  BGBC: Baked Goods - Baking and Cooking
        //  BGRD: Baked Goods - Refrigerated Dough
        //  B: Beverages
        //  C: Condiments, Sauces & Canned Goods
        //  D: Dairy Products & Eggs
        //  F: Food Purchased Frozen
        //  G: Grains, Beans & Pasta
        //  MF: Meat - Fresh
        //  MSF: Meat - Shelf Stable Foods
        //  MSP: Meat - Smoked or Processed
        //  MSA: Meat - Stuffed or Assembled
        //  PCP: Poultry - Cooked or Processed
        //  PF: Poultry - Fresh
        //  PSF: Poultry - Shelf Stable Foods
        //  PSA: Poultry - Stuffed or Assembled
        //  PFF: Produce - Fresh Fruits
        //  PFV: Produce - Fresh Vegetables
        //  SF: Seafood - Fresh
        //  SS:Seafood - Shelfish
        //  SSM: Seafood - Smoked
        //  SSF: Shelf Stable Foods
        //  VP: Vegetarian Proteins
        //  DPF: Deli & Prepared Foods

    //month totals 0 = Jan '21, 1 = Feb '21, etc.
    private static ArrayList<Float> monthTotals = new ArrayList<Float>();

    //uses same keys as categories; keeps track of number of items wasted in that category
    //for simple multiplication to make monthTotal to add to monthTotals ArrayList
    HashMap<String, Integer> wastedItems = new HashMap<String, Integer>();

    Float thisMonthTotal;

    private static String currMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: update with real values
        //populating priceCategory hashmap
        priceCategories.put("BF", 1.0f); // Baby Food
        priceCategories.put("BGB", 1.0f); // Baked Goods - Bakery
        priceCategories.put("BGBC", 1.0f); // Baked Goods - Baking and Cooking
        priceCategories.put("BGRD", 1.0f); // Baked Goods - Refrigerated Dough
        priceCategories.put("B", 1.0f); // Beverages
        priceCategories.put("C", 1.0f); // Condiments, Sauces & Canned Goods
        priceCategories.put("D", 1.0f); // Dairy Products & Eggs
        priceCategories.put("F", 1.0f); // Food Purchased Frozen
        priceCategories.put("G", 1.0f); // Grains, Beans & Pasta
        priceCategories.put("MF", 1.0f); // Meat - Fresh
        priceCategories.put("MSF", 1.0f); // Meat - Shelf Stable Foods
        priceCategories.put("MSP", 1.0f); // Meat - Smoked or Processed
        priceCategories.put("MSA", 1.0f); // Meat - Stuffed or Assembled
        priceCategories.put("PCP", 1.0f); // Poultry - Cooked or Processed
        priceCategories.put("PF", 1.0f); // Poultry - Fresh
        priceCategories.put("PSF", 1.0f); // Poultry - Shelf Stable Foods
        priceCategories.put("PSA", 1.0f); // Poultry - Stuffed or Assembled
        priceCategories.put("PFF", 1.0f); // Produce - Fresh Fruits
        priceCategories.put("PFV", 1.0f); // Produce - Fresh Vegetables
        priceCategories.put("SF", 1.0f); // Seafood - Fresh
        priceCategories.put("SS", 1.0f); // Seafood - Shelfish
        priceCategories.put("SSM", 1.0f); // Seafood - Smoked
        priceCategories.put("SSF", 1.0f); // Shelf Stable Foods
        priceCategories.put("VP", 1.0f); // Vegetarian Proteins
        priceCategories.put("DPF", 1.0f); // Deli & Prepared Foods

        //this is the start of logic for a real implementation of this activity
//        //dummy data for months Jan and Feb in '21
//        monthTotals.add(91.31f); // Jan '21
//        monthTotals.add(73.68f); // Feb '21

//        //to iterate through wasted hashmap
////        Iterator it = wastedItems.entrySet().iterator();
////        while (it.hasNext()) {
////            Map.Entry pair = (Map.Entry)it.next();
////            System.out.println(pair.getKey() + " = " + pair.getValue());
////            thisMonthTotal += (float) pair.getValue(); //TODO: add logic other hashmap
////            it.remove(); // avoids a ConcurrentModificationException
////        }
//        thisMonthTotal += 11.91f; //dummydata
//
//        monthTotals.add(thisMonthTotal);
//
//        BarChart barChart = (BarChart) findViewById(R.id.barchart);
//
//        ArrayList<BarEntry> entries = new ArrayList<>();
////        entries.add(new BarEntry(monthTotals.get(0), 0));
////        entries.add(new BarEntry(monthTotals.get(1), 1));
////        entries.add(new BarEntry(monthTotals.get(2), 2));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_finance);

        BarChart barChart = (BarChart) findViewById(R.id.barchart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        //dummy data to show if the user had been using the app
        entries.add(new BarEntry(93.19f, 0)); //Jan
        entries.add(new BarEntry(22.86f, 1)); //Feb
        entries.add(new BarEntry(21.06f, 2)); //March
        entries.add(new BarEntry(0f, 3)); //April
        entries.add(new BarEntry(0f, 4)); //May
        entries.add(new BarEntry(0f, 5)); //June
        entries.add(new BarEntry(0f, 6)); //July
        entries.add(new BarEntry(0f, 7)); //August
        entries.add(new BarEntry(0f, 8)); //September
        entries.add(new BarEntry(0f, 9)); //October
        entries.add(new BarEntry(0f, 10)); //November
        entries.add(new BarEntry(0f, 11)); //December

        //add dummy values to monthTotals for updating
        for(int i = 0; i < monthTotals.size(); i++){
            monthTotals.set(i, entries.get(i).getVal());
        }

        BarDataSet bardataset = new BarDataSet(entries, "Months");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Jan '21");
        labels.add("Feb '21");
        labels.add("March '21");
        labels.add("April '21");
        labels.add("May '21");
        labels.add("June '21");
        labels.add("July '21");
        labels.add("Aug '21");
        labels.add("Sep '21");
        labels.add("Oct '21");
        labels.add("Nov '21");
        labels.add("Dec '21");

        BarData data = new BarData(labels, bardataset);
        data.setValueTextSize(10);
        barChart.setData(data); // set the data and list of labels into chart
        barChart.setDescription("Your Financial Tracker for 2021");  // set the description
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(5000);
    }

    public static ArrayList<Float> getMonthTotals(){
        return monthTotals;
    }

    public static void setMonthTotals(ArrayList<Float> newTotals){
        monthTotals = newTotals;
    }

    public static String getCurrMonth(){
        return currMonth;
    }
}
