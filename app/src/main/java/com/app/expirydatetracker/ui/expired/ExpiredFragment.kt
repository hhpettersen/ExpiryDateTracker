package com.app.expirydatetracker.ui.expired

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.app.expirydatetracker.R
import com.app.expirydatetracker.databinding.ExpiredFragmentBinding
import com.app.expirydatetracker.databinding.ItemsFragmentBinding
import com.app.expirydatetracker.models.ExpiryItem
import com.app.expirydatetracker.ui.items.ItemsAdapter
import com.app.expirydatetracker.ui.items.ItemsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpiredFragment : Fragment() {

    private var _binding: ExpiredFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemAdapter: ItemsAdapter
    private lateinit var itemList: List<ExpiryItem>

    private val viewModel: ExpiredViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExpiredFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.readAllDataExpired.observe(viewLifecycleOwner, {
            setupAdapter(it)
        })

    }

    private fun setupAdapter(items: List<ExpiryItem>) {
        itemList = items
        itemAdapter = ItemsAdapter(itemList)  { position -> onListItemClick(position) }
        binding.recyclerView.adapter = itemAdapter
    }

    private fun onListItemClick(position: Int) {
        Toast.makeText(requireContext(), itemList[position].name, Toast.LENGTH_SHORT).show()
    }
}