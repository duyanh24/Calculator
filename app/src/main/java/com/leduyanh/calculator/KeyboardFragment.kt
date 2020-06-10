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

        buttonZero.setOnClickListener(this)
        buttonOne.setOnClickListener(this)
        buttonTwo.setOnClickListener (this)
        buttonThree.setOnClickListener(this)
        buttonFour.setOnClickListener(this)
        buttonFire.setOnClickListener(this)
        buttonSix.setOnClickListener(this)
        buttonSeven.setOnClickListener(this)
        buttonEight.setOnClickListener(this)
        buttonNine.setOnClickListener(this)
        buttonAdd.setOnClickListener(this)
        buttonSub.setOnClickListener(this)
        buttonMulti.setOnClickListener(this)
        buttonDivision.setOnClickListener(this)
        buttonC.setOnClickListener(this)
        buttonAc.setOnClickListener(this)
        buttonEqualSign.setOnClickListener(this)
        buttonDot.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.buttonZero->{
                if(operator.isNotEmpty()){
                    if(operand2.isNotEmpty()){
                        addNumber(buttonZero.text.toString())
                    }
                }else{
                    if(operand1.isNotEmpty()){
                        addNumber(buttonZero.text.toString())
                    }
                }
            }

            R.id.buttonOne->{
                addNumber(buttonOne.text.toString())
            }
            R.id.buttonTwo->{
                addNumber(buttonTwo.text.toString())
            }
            R.id.buttonThree->{
                addNumber(buttonThree.text.toString())
            }
            R.id.buttonFour->{
                addNumber(buttonFour.text.toString())
            }
            R.id.buttonFire->{
                addNumber(buttonFire.text.toString())
            }
            R.id.buttonSix->{
                addNumber(buttonSix.text.toString())
            }
            R.id.buttonSeven->{
                addNumber(buttonSeven.text.toString())
            }
            R.id.buttonEight->{
                addNumber(buttonEight.text.toString())
            }
            R.id.buttonNine->{
                addNumber(buttonNine.text.toString())
            }
            R.id.buttonC->{
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
            R.id.buttonAc->{
                clear()
                clearColorButton()
                sendMessage.sendData(operand1.toString())
            }
            R.id.buttonAdd->{
                addOperator(buttonAdd.text.toString(),buttonAdd)
            }
            R.id.buttonSub->{
                addOperator(buttonSub.text.toString(),buttonSub)
            }
            R.id.buttonMulti->{
                addOperator(buttonMulti.text.toString(),buttonMulti)
            }
            R.id.buttonDivision->{
                addOperator(buttonDivision.text.toString(),buttonDivision)
            }
            R.id.buttonEqualSign->{
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
        buttonAdd.setBackgroundColor(buttonAdd.context.resources.getColor(R.color.colorBackgroundButton))
        buttonSub.setBackgroundColor(buttonSub.context.resources.getColor(R.color.colorBackgroundButton))
        buttonDivision.setBackgroundColor(buttonDivision.context.resources.getColor(R.color.colorBackgroundButton))
        buttonMulti.setBackgroundColor(buttonMulti.context.resources.getColor(R.color.colorBackgroundButton))
    }
}
