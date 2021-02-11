package com.myapp.myapplication.groups

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.contact_groups_fragment.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.add_group_dialog.view.*
import com.myapp.myapplication.*
import com.myapp.myapplication.contacts.ContactsViewModel
import com.myapp.myapplication.datadase.CGroup
import com.myapp.myapplication.datadase.CGroupWithContact

class CGroupsFragment : Fragment() {

    companion object {
        fun newInstance() = CGroupsFragment()
    }

    private lateinit var viewModel: CGroupsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.contact_groups_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CGroupsViewModel::class.java)
        viewModel.requestGroups()

        recycler.layoutManager = LinearLayoutManager(context)
        viewModel.groups.observe(this, Observer<List<CGroupWithContact>> {
            val mainActivity = activity as MainActivity
            val contactsViewModel = ViewModelProviders.of(mainActivity).get(ContactsViewModel::class.java)
            recycler.adapter = ContactGroupsAdapter(it, mainActivity, contactsViewModel)
        })

        activity?.fab?.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                createAndShowGroupDialog()
            }
        }
    }

    private fun createAndShowGroupDialog() {
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle(R.string.add_contact_group)


        val dialogView = layoutInflater
            .inflate(R.layout.add_group_dialog, null)

        builder.setView(dialogView)
        builder.setPositiveButton(R.string.ok) { dialogInterface, _ ->
            val color = dialogView.colorPickerView.selectedColor
            val title = dialogView.editTitle.text.toString()
            val description = dialogView.editDescription.text.toString()

            val group = CGroup(name = title, description = description, color = color)
            viewModel.saveGroup(group)
            dialogInterface.dismiss()


        }
        builder.setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

}
