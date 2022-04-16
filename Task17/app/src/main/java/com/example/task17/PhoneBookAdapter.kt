package com.example.task17

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.example.task17.databinding.PhoneBookElementBinding

class PhoneBookAdapter :
    RecyclerView.Adapter<PhoneRecordHolder>() {

    private val phoneBookList: SortedList<PhoneRecord> = SortedList(
        PhoneRecord::class.java,
        sortedListAdapterCallback()
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneRecordHolder {
        val binding = PhoneBookElementBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PhoneRecordHolder(binding)
    }

    override fun onBindViewHolder(holder: PhoneRecordHolder, position: Int) {
        holder.bind(phoneBookList[position])
    }

    override fun getItemCount(): Int = phoneBookList.size()

    private fun sortedListAdapterCallback() =
        object : SortedListAdapterCallback<PhoneRecord>(this) {
            override fun compare(
                o1: PhoneRecord,
                o2: PhoneRecord
            ): Int {
                return when {
                    o1.name > o2.name -> 1
                    o1.name < o2.name -> -1
                    else -> 0
                }
            }

            override fun areContentsTheSame(
                oldItem: PhoneRecord,
                newItem: PhoneRecord
            ): Boolean = oldItem.hashCode() == newItem.hashCode()

            override fun areItemsTheSame(
                item1: PhoneRecord,
                item2: PhoneRecord
            ): Boolean = item1 == item2
        }

    fun addRecords(recordList: MutableList<PhoneRecord>) {
        phoneBookList.addAll(recordList)
    }

    fun filterRecords(recordList: MutableList<PhoneRecord>, filter: String) {
        phoneBookList.beginBatchedUpdates()
        phoneBookList.clear()
        for (record in recordList) {
            if(record.hasSubSting(filter)) phoneBookList.add(record)
        }
        phoneBookList.endBatchedUpdates()
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
