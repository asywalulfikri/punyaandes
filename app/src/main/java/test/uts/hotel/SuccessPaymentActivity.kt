package test.uts.hotel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail_order.*
import kotlinx.android.synthetic.main.activity_detail_order.btn_submit
import kotlinx.android.synthetic.main.activity_detail_order.toolbar
import kotlinx.android.synthetic.main.activity_succes_payment.*
import test.uts.hotel.helper.SharedPreference


class SuccessPaymentActivity : BaseActivity(){



    @Suppress("UNCHECKED_CAST")
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succes_payment)

        toolbar.setOnClickListener { onBackPressed() }
        setToolbar(toolbar,"Receipt")
        btn_submit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, 1313)
            finish()
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }




    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivityForResult(intent, 1313)
        finish()

    }

}
