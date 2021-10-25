package com.istgerade

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.istgerade.data.entity.NumberResponse

@BindingAdapter("textNumber")
fun bindTextNumber(textView: TextView, number: NumberResponse?) {
    number?.let {
        if (number.iseven == true) {
            textView.setText(R.string.even)
            textView.setTextColor(textView.context.getColor(R.color.green))
        } else {
            textView.setText(R.string.odd)
            textView.setTextColor(textView.context.getColor(R.color.red))
        }
    }
}

@BindingAdapter("textMessage")
fun bindTextMessage(textView: TextView, number: Long) {
    textView.text = textView.context.resources.getString(R.string.number_message, number)
}

