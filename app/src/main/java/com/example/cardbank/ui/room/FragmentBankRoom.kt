package com.example.cardbank.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardbank.databinding.FragmentRoomBinding
import com.example.cardbank.domain.data.models.viewmodels.BankRoomViewModels
import com.example.cardbank.ui.room.recyclerview.RecyclerViewAdapterRoom
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentBankRoom : Fragment() {

    companion object {
        fun newInstance() = FragmentBankRoom()
    }

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BankRoomViewModels by viewModel()

    private val adapter = RecyclerViewAdapterRoom {
        viewModel.onShowListRoom()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initIncomingEvents()
    }

    private fun initViews() {
        viewModel.onShowListRoom()
        recyclerViewRoom()
    }

    private fun recyclerViewRoom() {
        binding.recyclerViewRoom.layoutManager = LinearLayoutManager(context)
        adapter.setHasStableIds(true)
        binding.recyclerViewRoom.adapter = adapter
    }

    private fun initIncomingEvents() {
        viewModel.repos.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}