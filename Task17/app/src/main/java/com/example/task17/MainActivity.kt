package com.example.task17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task17.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

const val JSON_STRING =
    """[ { "name": "(Приёмная)", "phone": "+375 (2239) 7-17-80", "type": "" }, { "name": "(Бухгалтерия)", "phone": "+375 (2239) 7-17-64", "type": "" }, { "name": "(Бухгалтерия)", "phone": "+375 (2239) 7-18-08", "type": "" }, { "name": "(Юридическое бюро)", "phone": "+375 (2239) 7-17-63", "type": "" }, { "name": "(Отдел правовой и кадровой работы)", "phone": "+375 (2239) 7-17-93", "type": "" }, { "name": "(Отдел материально-технического снабжения)", "phone": "+375 (2239) 7-18-12", "type": "" }, { "name": "", "phone": "+375 44 712 36 26", "type": "Сектор сбыта бумаги" }, { "name": "(Реализация на внутренний рынок)", "phone": "+375 (2239) 7-17-79", "type": "Сектор сбыта бумаги" }, { "name": "(Реализация на внешний рынок)", "phone": "+375 (2239) 4-11-77", "type": "Сектор сбыта бумаги" }, { "name": "(Реализация на внутренний рынок)", "phone": "+375 (2239) 7-18-25", "type": "Сектор сбыта бумаги" }, { "name": "", "phone": "+375 44 580 09 70", "type": "Сектор сбыта продукции деревообработки" }, { "name": "(Реализация продукции деревообработки)", "phone": "+375 (2239) 7-18-42", "type": "Сектор сбыта продукции деревообработки" }, { "name": "(Реализация продукции деревообработки)", "phone": "+375 (2239) 3-64-71", "type": "Сектор сбыта продукции деревообработки" }, { "name": "", "phone": "+375 29 352 25 20", "type": "Реализация домов, бань, беседок, клеёного бруса" }, { "name": "", "phone": "+375 (2239) 7-18-43", "type": "Реализация домов, бань, беседок, клеёного бруса" }, { "name": "(приемная)", "phone": "+375 (2239) 7-17-80", "type": "Факс" }, { "name": "(отдел сбыта)", "phone": "+375 (2239) 7-17-79", "type": "Факс" }, { "name": "(отдел материально-технического снабжения)", "phone": "+375 (2239) 7-17-82", "type": "Факс" }, { "name": "(служба главного энергетика)", "phone": "+375 (2239) 7-18-06", "type": "Факс" }]"""

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val phoneList: List<PhoneRecord> = deSerializeJSON()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRcView()
        initPhoneBookFiltering()
    }

    private fun deSerializeJSON(): List<PhoneRecord> =
        Gson().fromJson(JSON_STRING, object : TypeToken<List<PhoneRecord>>() {}.type)

    private fun initRcView() {
        binding.rcView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rcView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binding.rcView.adapter = PhoneBookAdapter(phoneList)
    }

    private fun initPhoneBookFiltering() {
        binding.btnFilter.setOnClickListener {
            val filterString = binding.edtFilter.text.toString()
            (binding.rcView.adapter as? PhoneBookAdapter)?.filterRecords(filterString)
        }
    }
}
