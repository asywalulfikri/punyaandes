package test.uts.hotel

import android.annotation.SuppressLint
import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_metode_payment.*
import test.uts.hotel.Model.Bank
import test.uts.hotel.adapter.PaymentMethodAdapter

import java.util.ArrayList

class ListPaymentMethod : BaseActivity() {

    private lateinit var paymentMethodAdapter: PaymentMethodAdapter
    private lateinit var paymentMethodAdapter2: PaymentMethodAdapter
    private var type = ""
    private var stringList = ArrayList<Bank>()
    internal var virtual = arrayOf("Bank BCA", "Bank BRI", "Bank Mandiri", "Bank BNI", "Bank Permata")


    private var virtualCode = arrayOf("REGOPBVA01", "REGOPBRIVA01", "REGOPMVA01", "REGOPBNIVA01", "REGOPPVA01")


    /*-----------------------------------------------------*/

    private var stringList2 = ArrayList<Bank>()

    internal var transfer = arrayOf("BCA Klik Pay", "BNI iPay", "BRI e-Pay", "CIMB CLick", "GO-PAY", "Sakuku")


    private var codeTransfer = arrayOf("REGOPKP01", //BCA
            "REGOPIPAY01", //BNI
            "REGOPEPAY01", //BRI
            "REGOPCIMBCL01", //CIMB
            "REGOPGOPAY01", //GOPAY
            "REGOPSAK01" //SAKUKU
    )

    private lateinit var bank1: Bank
    private lateinit var bank2: Bank

    private var bankName: String? = null
    private var bankCode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_metode_payment)

        setToolbar(toolbar,"List Bank")
        virtualAccount()
        transferBank()
        btn_submit.setOnClickListener {
            if (type != "") {
                val intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                intent.putExtra("bankName", bankName)
                intent.putExtra("channelId", bankCode)
                intent.putExtra("type", type)
                finish()
            } else {
               Toast.makeText(this,"Pilih metode pembayarn terlebih dahulu",Toast.LENGTH_LONG).show()
            }
        }

    }

    @SuppressLint("NewApi")
    fun cekButton() {
        if (type != "") {
            setColorTintButton(btn_submit,getColor(R.color.color_btn_enable))
        } else {
            setColorTintButton(btn_submit,getColor(R.color.color_btn_disable))
        }
    }

    private fun virtualAccount() {
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerViewVirtual.setHasFixedSize(true)
        recyclerViewVirtual.layoutManager = linearLayoutManager
        recyclerViewVirtual.isNestedScrollingEnabled = true

        stringList = ArrayList()
        for (i in virtual.indices) {
            val bank = Bank()
            bank.name = virtual[i]
            bank.codeBank = virtualCode[i]
            stringList.add(bank)
        }
        paymentMethodAdapter = PaymentMethodAdapter(this, stringList, image_virtual)
        paymentMethodAdapter.setEmployees(stringList)
        recyclerViewVirtual.adapter = paymentMethodAdapter

        paymentMethodAdapter.actionQuestion { _, position ->
            bank1 = stringList[position]
            paymentMethodAdapter2.removeCheck()
            if (bank1.checked) {
                bankName = bank1.name
                bankCode = bank1.codeBank
                type = "Virtual Account"

            } else {
                type = ""

            }
            cekButton()
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun transferBank() {
        recyclerViewOnline.isNestedScrollingEnabled = true
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerViewOnline.setHasFixedSize(true)
        recyclerViewOnline.layoutManager = linearLayoutManager


        stringList2 = ArrayList()
        for (i in transfer.indices) {
            val bank = Bank()
            bank.name = transfer[i]
            bank.codeBank = codeTransfer[i]
            stringList2.add(bank)
        }
        paymentMethodAdapter2 = PaymentMethodAdapter(this, stringList2, image_transfer)
        paymentMethodAdapter2.setEmployees(stringList2)
        recyclerViewOnline.adapter = paymentMethodAdapter2

        paymentMethodAdapter2.actionQuestion { _, position ->
            bank2 = stringList2[position]
            paymentMethodAdapter.removeCheck()
            if (bank2.checked) {
                bankName = bank2.name
                bankCode = bank2.codeBank
                type = "Online Payment"
            } else {
                type = ""

            }
            cekButton()
        }
    }

    companion object {

        var image_virtual = intArrayOf(R.drawable.bank_bca, R.drawable.bank_bri, R.drawable.bank_mandiri, R.drawable.bank_bni, R.drawable.bank_permata)


        var image_transfer = intArrayOf(R.drawable.bca_klik_pay, R.drawable.bni_pay, R.drawable.bri_pay, R.drawable.cimb_pay, R.drawable.gopay, R.drawable.sakuku)
    }

}
