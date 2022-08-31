package com.example.rickmorty.app.data.utils.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.rickmorty.app.data.utils.extension.gone
import com.example.rickmorty.app.data.utils.extension.visible
import com.example.rickmorty.databinding.WidgetTitleAdapterHomeBinding


class TitleAdapterWidget: LinearLayout {
    private lateinit var binding: WidgetTitleAdapterHomeBinding
    private var text_title: String = ""
    private var text_more: String = "ดูทั้งหมด"
    private var showTitle: Boolean = true
    private var showMore: Boolean = true
    private var paddingVertical: Float = -1f

    private var eventMoreClickChangeListener: (() -> Unit)? = null
    fun setOnMoreClickListener(listener: () -> Unit) {
        eventMoreClickChangeListener = listener
    }

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    )
            : super(context, attrs, defStyleAttr) {
        setupView(context, attrs, defStyleAttr)
    }

    private fun setupView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        if(attrs == null) return
        binding = WidgetTitleAdapterHomeBinding.inflate(LayoutInflater.from(getContext()), this, true)
        //setTextTitle()
//        setTextMore()
//        setPaddingCustomPrivate()
    }

//    private fun setTextTitle(){
//        binding.titleText.text = text_title
//        if (showTitle){binding.titleText.visible()}
//        else{binding.titleText.gone()}
//    }
//    private fun setTextMore(){
//        binding.seeAllText.text = text_more
//        if (showMore){binding.seeAll.visible()}
//        else{binding.seeAll.invisible()}
//    }

    fun setTextTitle(title: String){
        binding.titleText.setText(title)
    }
//    fun setTextMore(more: String){
//        text_more = more
//        setTextMore()
//    }
//    fun setShowTitle(show: Boolean){
//        showTitle = show
//        setTextTitle()
//    }
//    fun setShowMore(show: Boolean){
//        showMore = show
//        setTextMore()
//    }
//    fun setTextMoreColor(@ColorRes colorRes: Int){
//        binding.seeAllText.setTextColor(ContextCompat.getColor(context, colorRes))
//        binding.seeAllTextIc.setTextColor(ContextCompat.getColor(context, colorRes))
//    }

//    private fun setPaddingCustomPrivate(){
//        if(paddingVertical != -1f){
//            setPaddingVertical(paddingVertical.toInt())
//        }
//        if(paddingHorizontal != -1f){
//            setPaddingHorizontal(paddingHorizontal.toInt())
//        }
//    }
//    fun setPaddingVertical(padding: Int){
//        binding.seeAll.updatePadding(top = padding, bottom = padding)
//    }
}