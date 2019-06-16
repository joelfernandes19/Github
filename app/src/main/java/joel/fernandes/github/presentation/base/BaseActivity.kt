package joel.fernandes.github.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import joel.fernandes.github.R

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var loadingDialog : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDialog()
    }


    protected fun showLoading() {
        loadingDialog.show()
    }

    protected fun hideLoading() {
        loadingDialog.dismiss()
    }

    private fun initDialog() {
        loadingDialog = AlertDialog.Builder(this).create()
        loadingDialog.setView(layoutInflater.inflate(R.layout.dialog_loading, null))
    }

    fun showToast(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}