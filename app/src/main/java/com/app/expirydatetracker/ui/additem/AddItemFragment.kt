package com.app.expirydatetracker.ui.additem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.app.expirydatetracker.R
import com.app.expirydatetracker.databinding.AddItemFragmentBinding
import com.app.expirydatetracker.helpers.DateHelper
import com.app.expirydatetracker.models.ExpiryItem
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddItemFragment : Fragment() {

    private var _binding: AddItemFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddItemViewModel by viewModels()

    private var validItemName: Boolean = false
    private var itemExpiryDate: Long? = null
    private var itemCategory: ItemCategories? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddItemFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Calendar.getInstance(TimeZone.getTimeZone("UTC"))

        val dayBuilder =
            MaterialDatePicker.Builder.datePicker().setTitleText("Select expiry date").build()

        binding.chooseDateButton.setOnClickListener {
            activity?.let { activity ->
                dayBuilder.show(
                    activity.supportFragmentManager,
                    "DATE_PICKER"
                )
            }
        }

        binding.itemNameInputText.addTextChangedListener {
            validItemName = it?.isNotBlank() == true
            buttonEnabling()
        }

        dayBuilder.addOnPositiveButtonClickListener { date ->
            val selectedDate = DateHelper.formatDateToString(date)
            binding.chosenDateText.text = selectedDate
            itemExpiryDate = date
            buttonEnabling()
        }



        populateMenu()

        binding.saveItemButton.setOnClickListener {

            viewModel.insertItem(
                ExpiryItem(
                    name = binding.itemNameInputText.editableText.toString(),
                    category = itemCategory!!,
                    dateAdded = DateHelper.today,
                    dateExpiring = itemExpiryDate!!
                )
            )
        }
    }

    private fun populateMenu() {
        val cat: List<ItemCategories> = listOf(
            ItemCategories.FOOD,
            ItemCategories.GIFT_CARD,
            ItemCategories.RETURNS
        )
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, cat.map { it.category })
        binding.menu.setAdapter(adapter)
        binding.menu.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val icon = cat[position].icon
            val drawable = ContextCompat.getDrawable(requireContext(), icon)
            binding.itemNameInputText.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)
            itemCategory = cat[position]
            buttonEnabling()
        }
    }

    fun buttonEnabling() {
        if(validItemName && itemExpiryDate != null && itemCategory != null) binding.saveItemButton.isEnabled = true
    }

}

enum class ItemCategories(
    val category: String,
    val icon: Int
) {
    UNDEFINED("Undefined", R.drawable.ic_expired),
    FOOD("Food", R.drawable.ic_baseline_set_meal_24),
    GIFT_CARD("Gift card", R.drawable.ic_baseline_card_giftcard_24),
    RETURNS("Returns", R.drawable.ic_baseline_assignment_return_24)
}