package test.uts.hotel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail_order.*
import test.uts.hotel.helper.SharedPreference


class DetailOrderActivity : BaseActivity(){

    var title: String? = null
    var subtitle:String? = null
    var description:String? = null
    var price:String? = null
    var icon = 0
    private var sharedPreference: SharedPreference? = null

    @Suppress("UNCHECKED_CAST")
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_order)

        toolbar.setOnClickListener { onBackPressed() }
        setToolbar(toolbar,"Detail Pemesanan")

        title = intent.getStringExtra("title")
        subtitle = intent.getStringExtra("subtitle")
        description = intent.getStringExtra("description")
        price = intent.getStringExtra("price")
        icon = intent.getIntExtra("icon", 0)

        sharedPreference = SharedPreference()
        textview_name.text = sharedPreference?.getUser(this)?.email
        tv_address.text = title + " "+ subtitle

        updateView()


        ll_metode_payment!!.setOnClickListener {
            val intent = Intent(this, ListPaymentMethod::class.java)
            startActivityForResult(intent, 1313)
        }



        btn_submit!!.setOnClickListener(View.OnClickListener {
            if (tv_method!!.text.toString().isEmpty()) {
                Toast.makeText(this, "Pilih metode pembayaran terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else {

                val intent = Intent(this, SuccessPaymentActivity::class.java)
                startActivityForResult(intent, 1313)
            }
        })

       /* iv_delete.setOnClickListener {

            edittext_discount.text = ""
            setToast(getString(R.string.text_voucher_delete))
            iv_delete.visibility = View.GONE
            percentage = 0
            max = 0
            cek()

        }*/

        edittext_discount!!.setOnClickListener{
            //showDialogBottom(false)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("SetTextI18n")
    fun updateView() {


    }


    override fun onBackPressed() {
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    @SuppressLint("SetTextI18n")
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1313 && resultCode == Activity.RESULT_OK) {
            val bundle = data!!.extras
            val bankName = bundle!!.getString("bankName")


            when (bankName) {
                "Bank BCA" -> iv_payment!!.setImageResource(R.drawable.bank_bca)
                "Bank BRI" -> iv_payment!!.setImageResource(R.drawable.bank_bri)
                "Bank Mandiri" -> iv_payment!!.setImageResource(R.drawable.bank_mandiri)
                "Bank BNI" -> iv_payment!!.setImageResource(R.drawable.bank_bni)
                "Bank Permata" -> iv_payment!!.setImageResource(R.drawable.bank_permata)
                "BCA Klik Pay" -> iv_payment!!.setImageResource(R.drawable.bca_klik_pay)
                "BNI iPay" -> iv_payment!!.setImageResource(R.drawable.bni_pay)
                "CIMB CLick" -> iv_payment!!.setImageResource(R.drawable.cimb_pay)
                "BRI e-Pay" -> iv_payment!!.setImageResource(R.drawable.bri_pay)
                "GO-PAY" -> iv_payment!!.setImageResource(R.drawable.gopay)
                "Sakuku" -> iv_payment!!.setImageResource(R.drawable.sakuku)
            }


            tv_method!!.text = bankName
            validation()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun cek() {
        /*insurance = 0.25 * priceItems / 100
        insurance2 = insurance.toInt()
        textview_insurance!!.text = getString(R.string.text_rp) + convertPrice(insurance2)
        textview_price_insurance!!.text = getString(R.string.text_rp) + convertPrice(insurance2)
        val total1 = insurance2 + ongkir

        val estimate = (percentage * total1 / 100).toDouble()
        val calculation = estimate.toInt()
        totalDiscount = if (calculation > max) {
            max
        } else {
            calculation
        }
        total = insurance2 + ongkir - totalDiscount
        textview_discount!!.text = "-Rp" + convertPrice(totalDiscount)
        textview_total_amount!!.text = getString(R.string.text_rp) + convertPrice(total)*/
    }

    @SuppressLint("NewApi")
    fun validation() {
        if (tv_method!!.text.toString().isNotEmpty()) {
            setColorTintButton(btn_submit,getColor(R.color.color_btn_enable))
        }
    }

}
