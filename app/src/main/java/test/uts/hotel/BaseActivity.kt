package test.uts.hotel

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import java.util.*

open class BaseActivity : AppCompatActivity() {


    open fun setToast(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    open fun setToast(text: Int) {
        Toast.makeText(this, getString(text), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("ResourceAsColor")
    open fun setToolbar(toolbar: Toolbar, title : String) {
        setSupportActionBar(toolbar)
        val upArrow = AppCompatResources.getDrawable(applicationContext, R.drawable.ic_baseline_keyboard_arrow_left_24)
        upArrow!!.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP)
        toolbar.setTitleTextColor(Color.BLACK)
        toolbar.title = title
        toolbar.setTitleTextAppearance(this, R.style.textSizeToolbar)
        supportActionBar!!.setHomeAsUpIndicator(upArrow)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        }
    }

    open fun setColorTintButton(button: MaterialButton, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val stateList = ColorStateList.valueOf(color)
            button.backgroundTintList = stateList
        } else {
            button.background.current.colorFilter = PorterDuffColorFilter(color,
                    PorterDuff.Mode.MULTIPLY)
        }
    }


}
