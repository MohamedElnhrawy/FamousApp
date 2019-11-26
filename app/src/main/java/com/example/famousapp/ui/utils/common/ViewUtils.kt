package com.example.famousapp.famous.utils.common

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.famousapp.famous.utils.interfaces.DialogActions

object ViewUtils {

    open fun  showAlertDialog(context : Context, actions: DialogActions, title:String, message : String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            actions.onAccept()
            //  dialog.dismiss()
        }
        builder.setNegativeButton(android.R.string.no) { _, _ ->
            actions.onDecline()
            // dialog.dismiss()
        }
        builder.show()
    }



}