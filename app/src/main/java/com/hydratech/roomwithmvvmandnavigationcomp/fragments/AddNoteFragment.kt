package com.hydratech.roomwithmvvmandnavigationcomp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hydratech.roomwithmvvmandnavigationcomp.R
import kotlinx.android.synthetic.main.fragment_add_note.*

class AddNoteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveButton.setOnClickListener {
            val noteTitle = titleTextView.text.toString().trim()
            val noteDesc = descreptionTextView.text.toString().trim()
            if (noteTitle.isEmpty()){
                titleTextView.error = "title required"
                titleTextView.requestFocus()
                return@setOnClickListener
            }
            if (noteDesc.isEmpty()){
                descreptionTextView.error = "Note required"
                descreptionTextView.requestFocus()
                return@setOnClickListener
            }

        }
    }


}