package com.example.whatsappclone

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SimPhonebookContract.SimRecords.PHONE_NUMBER
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.whatsappclone.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var phoneNumber: String
    private lateinit var countryCode: String
    private lateinit var alertDialogBuilder: MaterialAlertDialogBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneNumberEt.addTextChangedListener {
            binding.nextBtn.isEnabled = !(it.isNullOrEmpty() || it.length < 10)
        }
        //Hint Request - Assignment
        binding.nextBtn.setOnClickListener {
            checkNumber()
        }
    }

    private fun checkNumber() {
        countryCode = binding.ccp.selectedCountryCodeWithPlus
        phoneNumber = countryCode + binding.phoneNumberEt.text.toString()

        if (validatePhoneNumber(binding.phoneNumberEt.text.toString())) {
            notifyUserBeforeVerify(
                "We will be verifying the phone number:$phoneNumber\n" +
                        "Is this OK, or would you like to edit the number?"
            )
        } else {
            toast("Please enter a valid number to continue!")
        }
    }

    private fun validatePhoneNumber(phone: String): Boolean {
        if (phone.isEmpty()) {
            return false
        }
        return true
    }

    private fun notifyUserBeforeVerify(message: String) {
        alertDialogBuilder = MaterialAlertDialogBuilder(this).apply {
            setMessage(message)
            setPositiveButton("Ok") { _, _ ->
                showLoginActivity()
            }

            setNegativeButton("Edit") { dialog, _ ->
                dialog.dismiss()
            }

            setCancelable(false)
            create()
            show()
        }
    }


    private fun showLoginActivity() {
        startActivity(
            Intent(this, OtpActivity::class.java).putExtra(PHONE_NUMBER, phoneNumber)
        )
        finish()
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
