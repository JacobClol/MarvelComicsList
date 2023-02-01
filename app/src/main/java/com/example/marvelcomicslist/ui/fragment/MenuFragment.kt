package com.example.marvelcomicslist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.marvelcomicslist.R
import com.example.marvelcomicslist.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImageForButton()
        binding.btnIronMan.setOnClickListener { navigateComicListIronMan() }
        binding.btnThor.setOnClickListener { navigateComicListThor() }
        binding.btnHulk.setOnClickListener { navigateComicListHulk() }
        binding.btnCaptainAmerica.setOnClickListener { navigateComicListCaptainAmerica() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadImageForButton(){
        with(binding){
            Glide.with(requireContext()).load(getString(R.string.path_remote_image_iron_man))
                .placeholder(R.drawable.load).into(btnIronMan)
            Glide.with(requireContext()).load(getString(R.string.path_remote_image_thor))
                .placeholder(R.drawable.load).into(btnThor)
            Glide.with(requireContext()).load(getString(R.string.path_remote_image_hulk))
                .placeholder(R.drawable.load).into(btnHulk)
            Glide.with(requireContext()).load(getString(R.string.path_remote_image_captain_america))
                .placeholder(R.drawable.load).into(btnCaptainAmerica)
        }
    }

    private fun navigateComicListIronMan(){
        val action = MenuFragmentDirections
            .actionMenuFragmentToComicListFragment(getString(R.string.iron_man))
        findNavController().navigate(action)
    }

    private fun navigateComicListThor(){
        val action = MenuFragmentDirections
            .actionMenuFragmentToComicListFragment(getString(R.string.thor))
        findNavController().navigate(action)
    }

    private fun navigateComicListHulk(){
        val action = MenuFragmentDirections
            .actionMenuFragmentToComicListFragment(getString(R.string.hulk))
        findNavController().navigate(action)
    }

    private fun navigateComicListCaptainAmerica(){
        val action = MenuFragmentDirections
            .actionMenuFragmentToComicListFragment(getString(R.string.captain_america))
        findNavController().navigate(action)
    }
}