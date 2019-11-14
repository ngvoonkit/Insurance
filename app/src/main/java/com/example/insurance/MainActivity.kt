package com.example.insurance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long) {
        //val selectedItem = spinnerAge.selectedItemPosition
        val selectedItem = spinnerAge.getItemAtPosition(position)
        Toast.makeText(this,"Selected Item =" + selectedItem,Toast.LENGTH_LONG).show()
    }
    /*override fun onClick(v: View?) {
        when(v){
            buttonCalculate -> calculatePremium()
            buttonReset -> resetOutput()
            else -> //TODO do something here
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handling item selected listener for the spinner
        spinnerAge.onItemSelectedListener = this

        buttonCalculate.setOnClickListener{
            calculatePremium()
        }

        /*val buttonThird:Button
        buttonThird.setOnClickListener(this)*/
    }

    private fun calculatePremium(){
        //Get the age group . Position of an array
        val age: Int = spinnerAge.selectedItemPosition

        var premium = when(age){
            0 -> 60 //Less than 17
            1 -> 70 //17 to 25
            2 -> 90 //26 to 30
            3 -> 120 //31 to 40
            4 -> 150 //41 to 55
            else -> 150
        }

        //Get the gender selection. ID of radio button
        val gender: Int = radioGroupGender.checkedRadioButtonId

        if(gender == R.id.radioButtonMale){
            //Calculate premium for male
            when(age){
                0 -> premium
                1 -> premium += 50
                2 -> premium += 100
                3 -> premium += 150
                4 -> premium += 200
                else -> premium += 200
            }

        }else{
            //Calculate premium for female
            when(age){
                1 -> premium
                2 -> premium
                3 -> premium
                4 -> premium
                else -> premium
            }
        }

        //Determine smoker or non-smoker
        val smoker: Boolean = checkBoxSmoker.isChecked
        if(smoker){
            //Calculate premium for smoker
            when(age){
                0 -> premium
                1 -> premium += 100
                2 -> premium += 150
                3 -> premium += 200
                4 -> premium += 250
                else -> premium += 300
            }
        }
        val symbol = Currency.getInstance(Locale.getDefault()).symbol
        textViewPremium.text= String.format("%s %d",symbol,premium)
    }

    fun reset(view: View) {
        spinnerAge.setSelection(0)
        textViewPremium.setText(getString(R.string.insurance_premium))
        radioGroupGender.clearCheck()
        checkBoxSmoker.setChecked(false)
    }


}
