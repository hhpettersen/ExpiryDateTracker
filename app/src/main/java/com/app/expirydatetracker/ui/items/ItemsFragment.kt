package com.app.expirydatetracker.ui.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.expirydatetracker.R
import com.app.expirydatetracker.databinding.ItemsFragmentBinding
import com.app.expirydatetracker.models.ExpiryItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsFragment : Fragment() {

    private var _binding: ItemsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemAdapter: ItemsAdapter
    private lateinit var itemList: List<ExpiryItem>

    private val viewModel: ItemsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.readAllData.observe(viewLifecycleOwner, {
            setupAdapter(it)
        })

        binding.addItemButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_tabFragment_to_addItemFragment
            )
        }
    }

    private fun setupAdapter(items: List<ExpiryItem>) {
        itemList = items
        itemAdapter = ItemsAdapter(itemList) { position -> onListItemClick(position) }
        binding.recyclerView.adapter = itemAdapter
    }

    private fun onListItemClick(position: Int) {
        deleteItemAlert(itemList[position])
    }

    private fun deleteItemAlert(item: ExpiryItem) {
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.setTitle("Delete item?")
        alertDialog.setMessage("Are you sure you want to delete item: ${item.name}")
        alertDialog.setButton(
            AlertDialog.BUTTON_NEGATIVE, "Cancel"
        ) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "Delete"
        ) { dialog, _ ->
            viewModel.deleteItem(item)
            dialog.dismiss()
        }
        alertDialog.show()
    }
}