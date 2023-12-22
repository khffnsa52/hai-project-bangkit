package com.capstone.hadirai.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.hadirai.R
import com.capstone.hadirai.databinding.FragmentHomeBinding
import com.capstone.hadirai.ui.ViewModelFactory
import com.capstone.hadirai.ui.presensi.PresensiActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }
    private lateinit var nama: String
    private lateinit var idUser: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)

        viewModel.getSession().observe(requireActivity()) {
            nama = it.nama
            idUser = it.idUser
            val currentDateTime = Calendar.getInstance(Locale.ENGLISH).time
            val formatter = SimpleDateFormat("yyyy, MMMM dd")
            val formattedDateTime = formatter.format(currentDateTime)
            val welcomeHome = getString(R.string.hello_format)
            binding.dateHome.text = formattedDateTime
            binding.nameHome.text = String.format(welcomeHome, nama)
            binding.buttonPresensi.setOnClickListener {
                val intent = Intent(requireActivity(), PresensiActivity::class.java)
                startActivity(intent)
            }
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                viewModel.logout()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}