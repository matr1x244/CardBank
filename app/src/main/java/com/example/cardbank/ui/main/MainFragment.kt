package com.example.cardbank.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.example.cardbank.R
import com.example.cardbank.databinding.FragmentMainBinding
import com.example.cardbank.domain.data.models.viewmodels.MainViewModels
import com.example.cardbank.ui.room.FragmentBankRoom
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModels by viewModel(named("main_view_model"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initIncomingEvents()
        buttonRoom()
    }

    private fun initViews() {
        binding.inputLayoutTextWindow.setEndIconOnClickListener {
            val numberBin = binding.binEditText.text.toString()
            if (numberBin.isEmpty()) {
                Toast.makeText(context, "Введите номер карты", Toast.LENGTH_LONG).show()
            } else {
                viewModel.onShowCard(numberBin)
                urlBankWeb()
                phoneBank()
                mapsGo()
            }
        }
    }

    private fun initIncomingEvents() {
        viewModel.card.observe(viewLifecycleOwner) {
            binding.bankTv.text = it.bank?.name
            binding.schemeTv.text = it.scheme
            binding.typeTv.text = it.type
            binding.brandTv.text = it.brand
            binding.countryTv.text = it.country?.name
            binding.urlTv.text = it.bank?.url
            binding.phoneTv.text = it.bank?.phone
            binding.latitudeTv.text = it.country?.latitude.toString()
            binding.longitudeTv.text = it.country?.longitude.toString()
            it.bank?.let { bank ->
                if (binding.bankTv.text.isNotEmpty()) {
                    viewModel.onSaveBank(bank)
                }
            }
        }
    }

    private fun urlBankWeb() {
        if (binding.urlTv.text.toString().isEmpty()) {
            binding.urlTv.setOnClickListener {
                val webUrl = "http://${binding.urlTv.text}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
                startActivity(intent)
            }
        }
    }

    private fun phoneBank() {
        if (binding.phoneTv.text.toString().isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkPermission()
            }
            binding.phoneTv.setOnClickListener {
                val phoneNumber = binding.phoneTv.text.toString()
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${phoneNumber}"))
                startActivity(intent)
            }
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(), Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                Toast.makeText(requireActivity(), "Для звонка требуется доступ", Toast.LENGTH_SHORT)
                    .show()
            }
            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    checkPermission()
                }
            }
        }
    }

    private fun mapsGo() {
        val lan = binding.latitudeTv.text
        val lon = binding.longitudeTv.text
        if (lan.isEmpty() && lon.isEmpty()) {
            binding.latitudeTv.setOnClickListener {
                val mapsUri = "geo:$lan,$lon?z=10"
                val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapsUri))
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }

    private fun buttonRoom() {
        binding.buttonFragmentRoom.setOnClickListener {
            activity?.supportFragmentManager?.let { fragment ->
                fragment.beginTransaction()
                    .add(R.id.container_main_activity, FragmentBankRoom.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}