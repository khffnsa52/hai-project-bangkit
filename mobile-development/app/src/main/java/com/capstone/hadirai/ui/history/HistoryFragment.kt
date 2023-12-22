package com.capstone.hadirai.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.hadirai.R
import com.capstone.hadirai.databinding.FragmentHistoryBinding
import com.capstone.hadirai.ui.ViewModelFactory
import com.capstone.hadirai.util.ResultState

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private val viewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }
    private lateinit var adapter: HistoryAdapter
    private lateinit var nama: String
    private lateinit var idUser: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.listHistory.layoutManager = layoutManager

        adapter = HistoryAdapter()
        binding.listHistory.adapter = adapter
        viewModel.getSession().observe(requireActivity()) {
            nama = it.nama
            idUser = it.idUser
            val namaformat = getString(R.string.name_history_format)
            val idUserformat = getString(R.string.user_id_format)
            binding.idUserHistory.text = String.format(idUserformat, idUser)
            binding.nameHistory.text = String.format(namaformat, nama)
        }

        viewModel.getHistory().observe(requireActivity()) { value ->
            if (value != null) {
                when (value) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        binding.listHistory.visibility = View.VISIBLE
                        val data = value.data.presensiHistory
                        if (data == null) {
                            val message = "No attendance history"
                            Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
                        } else {
                            adapter.submitList(value.data.presensiHistory)
                        }
                    }

                    is ResultState.Error -> {
                        showLoading(false)
                    }
                }
            }
        }
        return view
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}