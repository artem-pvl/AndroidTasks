package com.example.task17

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task17.databinding.PhoneBookElementBinding

class PhoneBookAdapter(private val phoneList: List<PhoneRecord>) :
    RecyclerView.Adapter<PhoneRecordHolder>() {
    private var filteredPhoneList = phoneList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneRecordHolder {
        val binding = PhoneBookElementBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PhoneRecordHolder(binding)
    }

    override fun onBindViewHolder(holder: PhoneRecordHolder, position: Int) {
        holder.bind(filteredPhoneList[position])
    }

    override fun getItemCount(): Int = filteredPhoneList.count()

    fun filterRecords(filter: String) {
        filteredPhoneList = phoneList.filter { it.hasSubSting(filter) }
        notifyDataSetChanged()
    }
}

class PhoneRecordHolder(private val binding: PhoneBookElementBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(phoneRecord: PhoneRecord) {
        binding.txtNameData.text = phoneRecord.name
        binding.txtPhoneData.text = phoneRecord.phone
        binding.txtTypeData.text = phoneRecord.type
    }
}
