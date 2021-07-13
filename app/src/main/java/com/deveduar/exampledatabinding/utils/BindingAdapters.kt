package com.deveduar.exampledatabinding.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.deveduar.exampledatabinding.R
import com.deveduar.exampledatabinding.ViewModel.SimpleViewModel

@BindingAdapter("app:popularityIcon")
fun popularityIcon(view: ImageView, popularity: SimpleViewModel.Popularity) {

    val color = getAssociatedColor(popularity, view.context)

    ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(color))

    view.setImageDrawable(getDrawablePopularity(popularity, view.context))
}



@BindingAdapter("app:progressTint")
fun tintPopularity(view: ProgressBar, popularity: SimpleViewModel.Popularity) {

    val color = getAssociatedColor(popularity, view.context)

    view.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("app:hideIfZero")
    fun hideIfZero(view:View, number:Int){
        view.visibility = if (number == 0) View.GONE else View.VISIBLE
    }

@BindingAdapter(value = ["app:progressScaled", "android:max"], requireAll = true)
fun setProgress(progressBar: ProgressBar, likes:Int, max:Int){
    progressBar.progress = (likes * max / 5 ).coerceAtMost(max)
}

private fun getDrawablePopularity(popularity: SimpleViewModel.Popularity, context: Context): Drawable?{
    return when(popularity){
        SimpleViewModel.Popularity.NORMAL -> {
            ContextCompat.getDrawable(context, R.drawable.ic_person_96dp)
        }
        SimpleViewModel.Popularity.POPULAR -> {
            ContextCompat.getDrawable(context, R.drawable.ic_whatshot_96dp)
        }
        SimpleViewModel.Popularity.STAR -> {
            ContextCompat.getDrawable(context, R.drawable.ic_whatshot_96dp)
        }
    }
}
private fun getAssociatedColor(popularity: SimpleViewModel.Popularity, context: Context): Int {
    return when (popularity) {
        SimpleViewModel.Popularity.NORMAL -> context.theme.obtainStyledAttributes(
            intArrayOf(android.R.attr.colorForeground)
        ).getColor(0, 0x000000)
        SimpleViewModel.Popularity.POPULAR -> ContextCompat.getColor(context,R.color.popular)
        SimpleViewModel.Popularity.STAR -> ContextCompat.getColor(context, R.color.star)
    }

}


