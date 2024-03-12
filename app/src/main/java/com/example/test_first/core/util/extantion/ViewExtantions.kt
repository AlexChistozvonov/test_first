package com.example.test_first.core.util.extantion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.test_first.core.util.SafeClickListener
import com.example.test_first.R
import com.example.test_first.core.util.exception.UIExceptionMapper
import com.example.test_first.databinding.GenericDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun View.show() {
    if (this.visibility != View.VISIBLE) {
        this.visibility = View.VISIBLE
    }
}

fun View.hide() {
    if (this.visibility != View.INVISIBLE) {
        this.visibility = View.INVISIBLE
    }
}

fun View.onClick(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

inline fun <T : View> T.showIf(condition: (T) -> Boolean): T {
    if (condition(this)) {
        show()
    } else {
        hide()
    }
    return this
}


fun Fragment.showGeneralErrorDialog(
    context: Context,
    exception: Exception?,
    @StringRes closeButtonTextRes: Int = R.string.cancel,
    closeAction: (() -> Unit)? = null,
    submitAction: (() -> Unit)? = null,
    @StringRes submitButtonTextRes: Int = R.string.ok,
    isDialogCancellable: Boolean = true
) {
    val mapper = UIExceptionMapper()
    val builder = MaterialAlertDialogBuilder(
        context,
        R.style.CustomAlertDialog
    ).setCancelable(isDialogCancellable)

    val dialogBinding = GenericDialogBinding.inflate(
        LayoutInflater.from(
            context
        )
    )
    builder.setView(dialogBinding.root)
    val alertDialog = builder.create()

    with(dialogBinding) {
        mapper.titleMapper(context, exception).let { title ->
            tvTitle.showIf { title.isNotEmpty() }
            tvTitle.text = title
        }

        mapper.subtitleMapper(context, exception).let { subTitle ->
            tvSubtitle.showIf { subTitle.isNotEmpty() }
            tvSubtitle.text = subTitle
        }

        btnCancel.show()
        btnCancel.text = context.resources.getString(closeButtonTextRes)
        btnCancel.onClick {
            alertDialog.dismiss()
            closeAction?.invoke()
        }

        btnOk.show()
        btnOk.text = context.resources.getString(submitButtonTextRes)
        btnOk.onClick {
            alertDialog.dismiss()
            submitAction?.invoke()
        }
    }

    alertDialog.setOnDismissListener {
        if (isDialogCancellable) closeAction?.invoke()
    }

    alertDialog.show()
}