package com.example.phatnguyen.demoecommerce.CustomView

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.phatnguyen.demoecommerce.R

/**
 * Created by phatnguyen on 12/17/17.
 */
class DynamicValueTextView : LinearLayout {
    /**
     * The currently value in TextView.
     */
    private var currentValueState = 0

    private var btnPrevious: ImageView? = null
    private var btnNext: ImageView? = null

    //set current state value for save instance state
    fun setValues(value: Int) {
        val currentValue = this.findViewById(R.id.text_value) as TextView
        currentValue.text = value.toString()
        checkInstanceValues()

        //set current state value for save instance state
        this.currentValueState = value
    }

    fun getValues(): Int {
        val currentValue = this.findViewById(R.id.text_value) as TextView
        val value = currentValue.text.toString()

        return Integer.parseInt(value)
    }

    constructor(context: Context) : super(context) {

        initializeViews(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextValue)
        typedArray.recycle()

        initializeViews(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextValue)
        typedArray.recycle()

        initializeViews(context)
    }

    /**
     * Inflates the views in the layout.
     *
     * @param context the current context for the control.
     */
    private fun initializeViews(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.layout_text_value, this)
    }

    private fun checkInstanceValues() {
        val currentValue = this.findViewById(R.id.text_value) as TextView
        // If the first value is show, hide the previous button
        if (currentValue.text.toString() == "0") {
            btnPrevious!!.visibility = View.INVISIBLE
            currentValue.setTextColor(Color.RED)
        } else {
            btnPrevious!!.visibility = View.VISIBLE
            currentValue.setTextColor(Color.BLUE)
        }

    }

    override fun onFinishInflate() {

        // When the controls in the layout are doing being inflated, set the
        // callbacks for the side arrows
        super.onFinishInflate()

        // When the previous button is pressed, select the previous item in the
        // list
        btnPrevious = this.findViewById(R.id.btn_previous) as ImageView
        btnPrevious!!.setImageResource(R.drawable.subtract)

        if (getValues() == 0) {
            btnPrevious!!.visibility = View.GONE
        }

        // When the next button is pressed, decrease value by 1 unit
        btnPrevious!!.setOnClickListener {
            val currentValue = findViewById(R.id.text_value) as TextView
            val value = currentValue.text.toString()
            val numOfvalue = Integer.parseInt(value)
            setValues(numOfvalue - 1)
        }

        // When the next button is pressed, increase value by 1 unit
        btnNext = this.findViewById(R.id.btn_next) as ImageView
        btnNext!!.setImageResource(R.drawable.add)
        btnNext!!.setOnClickListener {
            val currentValue = findViewById(R.id.text_value) as TextView
            val value = currentValue.text.toString()
            val numOfvalue = Integer.parseInt(value)
            setValues(numOfvalue + 1)
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()

        bundle.putParcelable(STATE_SUPER_CLASS, super.onSaveInstanceState())
        bundle.putInt(STATE_CURRENT_VALUE, currentValueState)

        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is Bundle) {

            super.onRestoreInstanceState(state.getParcelable(STATE_SUPER_CLASS))
            setValues(state.getInt(STATE_CURRENT_VALUE))
        } else
            super.onRestoreInstanceState(state)
    }

    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>) {
        // Makes sure that the state of the child views in the side
        // spinner are not saved since we handle the state in the
        // onSaveInstanceState.
        super.dispatchFreezeSelfOnly(container)
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>) {
        // Makes sure that the state of the child views in the side
        // spinner are not restored since we handle the state in the
        // onSaveInstanceState.
        super.dispatchThawSelfOnly(container)
    }

    companion object {

        /**
         * The state to save to keep the state of the super class correctly.
         */
        private val STATE_SUPER_CLASS = "SuperClass"
        private val STATE_CURRENT_VALUE = "currentValues"
    }
}