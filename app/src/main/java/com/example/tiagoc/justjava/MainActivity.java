package com.example.tiagoc.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private int quantity;
    private double PRICE_COFFEE = 2;
    private double PRICE_CREAM = 0.5;
    private double PRICE_CHOCOLATE = 0.7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mViewHolder.quantityTextView = findViewById(R.id.quantity_text_view);
        this.mViewHolder.summaryTextView = findViewById(R.id.summary_text_view);
        this.mViewHolder.priceTextView = findViewById(R.id.price_text_view);
        this.mViewHolder.creamCheckBox = findViewById(R.id.cream_check_box);
        this.mViewHolder.chocolateCheckBox = findViewById(R.id.chocolate_check_box);
        this.mViewHolder.nameEditText = findViewById(R.id.name_edit_text);
    }

    /**
     * This method is called when the order button is clicked.
     *
     * @param view - View  of Main Activity
     */
    public void submitOrder(View view) {
        this.mViewHolder.priceTextView.setVisibility(View.GONE);
        this.displaySummary();
    }

    /**
     * This method is sum product when the order button is clicked.
     *
     * @param view - View  of Main Activity
     */
    public void sumOrder(View view) {
        this.quantity = Integer.valueOf(this.mViewHolder.quantityTextView.getText().toString()) + 1;
        this.displayQuantity();
    }

    /**
     * This method is subxtract product when the order button is clicked.
     *
     * @param view - View  of Main Activity
     */
    public void substractOrder(View view) {
        if (this.quantity > 0) {
            this.quantity = Integer.valueOf(this.mViewHolder.quantityTextView.getText().toString()) - 1;
        }
        displayQuantity();
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity() {
        this.mViewHolder.quantityTextView.setText(String.valueOf(this.quantity));
        this.mViewHolder.priceTextView.setText(NumberFormat.getCurrencyInstance().format(calculatePrice()));
    }

    /**
     * This method calculate price of pruduct set on display
     */
    private double calculatePrice(){
        double value = 0;
        if (this.mViewHolder.creamCheckBox.isChecked()){
            value = value + (quantity *PRICE_CREAM);
        }
        if (this.mViewHolder.chocolateCheckBox.isChecked()){
            value = value + (quantity *PRICE_CHOCOLATE);
        }
        value = value + (quantity *PRICE_COFFEE);
        return value;
    }

    private void displaySummary(){
        String message = "Summary\nName: " + this.mViewHolder.nameEditText.getText().toString();
        message = message + "\nWhipped Cream: ";
        if (this.mViewHolder.creamCheckBox.isChecked()) message = message + "Yes.";
        else message = message + "No.";
        message = message + "\nChocolate: ";
        if (this.mViewHolder.chocolateCheckBox.isChecked()) message = message + "Yes.";
        else message = message + "No.";
        message = message + "\nQuantity : " + String.valueOf(this.quantity);
        message = message + "\nTotal: " + NumberFormat.getCurrencyInstance().format(calculatePrice());
        if(calculatePrice() > 0){
            message = message + "\n Thank You!!";
        }else {
            message = message + "\n Free!!";
        }
        this.mViewHolder.summaryTextView.setText(message);
    }

    public void refreshPrice(View view) {
        this.displayQuantity();
    }

    private class ViewHolder{
        TextView quantityTextView;
        TextView priceTextView;
        TextView summaryTextView;
        CheckBox creamCheckBox;
        CheckBox chocolateCheckBox;
        EditText nameEditText;
    }
}