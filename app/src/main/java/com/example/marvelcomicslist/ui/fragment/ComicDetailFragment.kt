package com.example.marvelcomicslist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.marvelcomicslist.R
import com.example.marvelcomicslist.databinding.FragmentComicDetailBinding
import com.google.android.material.appbar.AppBarLayout

class ComicDetailFragment : Fragment(), AppBarLayout.OnOffsetChangedListener {

    private var _binding: FragmentComicDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ComicDetailFragmentArgs by navArgs()
    private var scrollRange = -1
    private var isShow = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComicDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCollapsingToolbar()
        initData()
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (scrollRange == -1) {
            scrollRange = appBarLayout?.totalScrollRange ?: -1
        }
        if (scrollRange + verticalOffset == 0) {
            binding.collapsingToolbar.title = args.comic.title
            isShow = true
        } else {
            binding.collapsingToolbar.title = ""
            isShow = false
        }
    }

    private fun initCollapsingToolbar() {
        binding.appBar.addOnOffsetChangedListener(this)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        with(binding) {
            collapsingToolbar.title = ""
            appBar.setExpanded(true)
            collapsingToolbar.setupWithNavController(
                binding.toolbar, navController, appBarConfiguration
            )
        }

    }

    private fun initData() {
        with(binding) {
            val comic = args.comic
            try {
                Glide.with(requireContext()).load(comic.image).placeholder(R.drawable.load)
                    .into(thumbnailHeader)
            } catch (e: Exception) {
                e.message?.let { error ->
                    showError(error)
                }
            }
            tvTitle.text = comic.title
            tvDescription.text = comic.description
            tvVariantDescription.text = comic.variantDescription
        }
        hideProgressBar()
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }
}