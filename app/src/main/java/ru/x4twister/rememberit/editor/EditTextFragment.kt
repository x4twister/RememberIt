/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.editor

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import ru.x4twister.rememberit.R
import ru.x4twister.rememberit.databinding.DialogEditTextBinding

class EditTextFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding: DialogEditTextBinding=DataBindingUtil
            .inflate(LayoutInflater.from(activity),R.layout.dialog_edit_text,null,false)

        val value=arguments!!.getString(ARG_VALUE)
        val title=arguments!!.getString(ARG_TITLE)
        binding.editText.setText(value)

        return AlertDialog.Builder(activity)
            .setView(binding.root)
            .setTitle(title)
            .setPositiveButton("Ok"){ dialogInterface: DialogInterface, i: Int ->
                sendResult(Activity.RESULT_OK,binding.editText.text.toString())
            }
            .create()
    }

    private fun sendResult(result: Int, text: String) {
        targetFragment?.let {
            val intent=Intent()
            intent.putExtra(EXTRA_TEXT,text)

            it.onActivityResult(targetRequestCode,result,intent)
        }
    }

    companion object{
        const val EXTRA_TEXT="ru.x4twister.rememberit.editor.text"
        const val ARG_VALUE="value"
        const val ARG_TITLE="title"

        fun newInstance(value: String, title: String): EditTextFragment {
            val args=Bundle()
            args.putString(ARG_VALUE,value)
            args.putString(ARG_TITLE,title)

            val fragment=EditTextFragment()
            fragment.arguments=args

            return fragment
        }
    }
}