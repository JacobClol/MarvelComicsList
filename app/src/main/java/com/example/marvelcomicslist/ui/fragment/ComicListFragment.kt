package com.example.marvelcomicslist.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.marvelcomicslist.databinding.FragmentComicListBinding
import com.example.marvelcomicslist.domain.models.Comic
import com.example.marvelcomicslist.ui.adapter.ComicAdapter
import com.example.marvelcomicslist.ui.viewmodel.ComicListViewModel
import com.example.marvelcomicslist.ui.viewmodel.ViewModelScreenState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ComicListFragment : Fragment(), ComicAdapter.OnComicClickListener {

    private var _binding: FragmentComicListBinding? = null
    private val binding get() = _binding!!
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var viewModel: ComicListViewModel
    private val args: ComicListFragmentArgs by navArgs()
    private val adapter by lazy {
        ComicAdapter(requireContext(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ComicListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComicListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setupSwipeRefreshLayout()
        lifecycleScope.launchWhenResumed {
            subscribeToMovieState()
        }
        viewModel.fetchComicByHero(args.hero)
    }

    override fun onComicClick(comic: Comic) {
        val action = ComicListFragmentDirections.actionComicListFragmentToComicDetailFragment(comic)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {
        if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rvListComics.layoutManager = GridLayoutManager(context, 2)
        } else {
            binding.rvListComics.layoutManager = GridLayoutManager(context, 4)
        }
        binding.rvListComics.itemAnimator = DefaultItemAnimator()
        binding.rvListComics.adapter = adapter
    }

    private fun subscribeToMovieState() {
        viewModel.state.onEach(::handleMovieListState).launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleMovieListState(screenState: ViewModelScreenState) {
        when (screenState) {
            is ViewModelScreenState.Success -> {
                hideProgressBar()
                hideRefreshing()
                showComics(screenState.comics)
            }
            is ViewModelScreenState.Failure -> {
                hideProgressBar()
                hideRefreshing()
                showError(screenState.error)
            }
            is ViewModelScreenState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun showComics(comics: List<Comic>) {
        adapter.dataSet(comics)
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
           viewModel.refresh(args.hero)
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun hideRefreshing() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }
}