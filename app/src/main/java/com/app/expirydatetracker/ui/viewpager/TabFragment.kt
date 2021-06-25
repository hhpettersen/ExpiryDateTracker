package com.app.expirydatetracker.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.expirydatetracker.R
import com.app.expirydatetracker.databinding.TabFragmentBinding
import com.app.expirydatetracker.helpers.Status
import com.app.expirydatetracker.ui.expired.ExpiredFragment
import com.app.expirydatetracker.ui.items.ItemsFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

//https://material.io/components/tabs/android#using-tabs

@AndroidEntryPoint
class TabFragment : Fragment() {

    private var _binding: TabFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TabFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf(
            ItemsFragment(),
            ExpiredFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = "Items"
                    tab.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_items) }
                }
                1 -> {
                    tab.text = "Expired"
                    tab.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_expired) }
                }
            }
        }.attach()
    }
}

