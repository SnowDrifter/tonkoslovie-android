package ru.romanov.tonkoslovie.ui.screens.login.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import org.greenrobot.eventbus.EventBus
import ru.romanov.tonkoslovie.R
import ru.romanov.tonkoslovie.data.eventbus.LogoutEvent


class LogoutDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        builder.setMessage(R.string.logout_dialog_body)
        builder.setTitle(R.string.logout_dialog_title)
        builder.setPositiveButton(R.string.logout_dialog_submit, DialogInterface.OnClickListener { dialog, id ->
            EventBus.getDefault().post((LogoutEvent()))
        })
        builder.setNegativeButton(R.string.logout_dialog_cancel, DialogInterface.OnClickListener { dialog, id -> })

        return builder.create()
    }
}