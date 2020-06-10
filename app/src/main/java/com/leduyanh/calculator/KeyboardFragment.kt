package com.leduyanh.calculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_keyboard.*
import java.lang.StringBuilder
import kotlin.math.ceil

class KeyboardFragment(private val sendMessage: SendMessage) : Fragment(),View.OnClickListener {

    private val operand1 = StringBuilder("")
    private val operand2 = StringBuilder("")
    private var operator = ""
    private var result:Double? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keyboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_zero.setOnClickListener(this)
        button_one.setOnClickListener(this)
        button_two.setOnClickListener (this)
        button_three.setOnClickListener(this)
        button_four.setOnClickListener(this)
        button_fire.setOnClickListener(this)
        button_six.setOnClickListener(this)
        button_seven.setOnClickListener(this)
        button_eight.setOnClickListener(this)
        button_nine.setOnClickListener(this)
        button_add.setOnClickListener(this)
        button_sub.setOnClickListener(this)
        button_multi.setOnClickListener(this)
        button_division.setOnClickListener(this)
        button_c.setOnClickListener(this)
        button_ac.setOnClickListener(this)
        button_equal_sign.setOnClickListener(this)
        button_dot.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button_zero->{
                if(operator.isNotEmpty()){
                    if(operand2.isNotEmpty()){
                        addNumber("0")
                    }
                }else{
                    if(operand1.isNotEmpty()){
                        addNumber("0")
                    }
                }
            }

            R.id.button_one->{
                addNumber("1")
            }
            R.id.button_two->{
                addNumber("2")
            }
            R.id.button_three->{
                addNumber("3")
            }
            R.id.button_four->{
                addNumber("4")
            }
            R.id.button_fire->{
                addNumber("5")
            }
            R.id.button_six->{
                addNumber("6")
            }
            R.id.button_seven->{
                addNumber("7")
            }
            R.id.button_eight->{
                addNumber("8")
            }
            R.id.button_nine->{
                addNumber("9")
            }
            R.id.button_c->{
                if(operator.isNotEmpty()){
                    if(operand2.isNotEmpty()){
                        operand2.deleteCharAt(operand2.lastIndex)
                        sendMessage.sendData(operand2.toString())
                    }
                }else{
                    if(operand1.isNotEmpty()){
                        operand1.deleteCharAt(operand1.lastIndex)
                        sendMessage.sendData(operand1.toString())
                    }
                }
            }
            R.id.button_ac->{
                clear()
                clearColorButton()
                sendMessage.sendData(operand1.toString())
            }
            R.id.button_add->{
                addOperator("+",button_add)
            }
            R.id.button_sub->{
                addOperator("-",button_sub)
            }
            R.id.button_multi->{
                addOperator("x",button_multi)
            }
            R.id.button_division->{
                addOperator("/",button_division)
            }
            R.id.button_equal_sign->{
                calculator()
            }
        }
    }

    // add character number into operand StringBuilder
    private fun addNumber(number: String){
        if(operator.isNotEmpty()){
            if(operand2.length < 9){
                operand2.append(number)
                sendMessage.sendData(operand2.toString())
            }else{
                Toast.makeText(activity,"Tối đa 9 chữ số",Toast.LENGTH_LONG).show()
            }
        }else{
            if(operand1.length < 9){
                operand1.append(number)
                sendMessage.sendData(operand1.toString())
            }else{
                Toast.makeText(activity,"Tối đa 9 chữ số",Toast.LENGTH_LONG).show()
            }
        }
    }

    // add operator and change color button operator
    private fun addOperator(ope:String,button: Button){
        if(operand1.isNotEmpty()){
            operator = ope
            clearColorButton()
            button.setBackgroundColor(Color.BLUE)
        }
    }

    private fun calculator(){
        if(operand1.isNotEmpty() && operand2.isNotEmpty() && operator.isNotEmpty()){
            val number1 = operand1.toString().toDouble()
            val number2 = operand2.toString().toDouble()
            when(operator){
                "+"->{
                    result = number1 + number2
                }
                "-"->{
                    result = number1 - number2
                }
                "x"->{
                    result = number1 * number2
                }
                "/"->{
                    result = number1 / number2
                }
            }
            if(result != null) result = ceil(result!! * 10) / 10
            sendMessage.sendData(result.toString())
            clear()
            clearColorButton()
        }
    }

    // delete all data in operands and operator
    private fun clear() {
        operand1.delete(0, operand1.lastIndex + 1)
        operand2.delete(0, operand2.lastIndex + 1)
        operator = ""
    }

    // clear color for all button operator
    @SuppressLint("ResourceAsColor")
    private fun clearColorButton(){
        button_add.setBackgroundColor(button_add.context.resources.getColor(R.color.colorBackgroundButton))
        button_sub.setBackgroundColor(button_sub.context.resources.getColor(R.color.colorBackgroundButton))
        button_division.setBackgroundColor(button_division.context.resources.getColor(R.color.colorBackgroundButton))
        button_multi.setBackgroundColor(button_multi.context.resources.getColor(R.color.colorBackgroundButton))
    }
}
