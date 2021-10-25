package com.istgerade.numberlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.istgerade.R
import com.istgerade.databinding.FragmentNumberListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NumberListFragment : Fragment() {

    lateinit var binding: FragmentNumberListBinding
    lateinit var adapter: NumbersAdapter

    private val viewModel: NumberListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_number_list, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setUpAdapter()
        observeForNumbersList()
        observeForLoading()
        observeForMessage()
        observeNavigation()
        observeForDeleteNumber()
        return binding.root
    }

    private fun observeForDeleteNumber() {
        viewModel.removedNumber.observe(viewLifecycleOwner) {
            it?.let { it1 ->
                adapter.removeItem(it1)
                viewModel.clearRemoveNumberState()
            }
        }
    }

    private fun observeNavigation() {
        viewModel.navigate.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(
                    NumberListFragmentDirections.actionNumberListFragmentToDialogFragment(
                        it
                    )
                )
                viewModel.clearNavigation()
            }
        }
    }

    private fun setUpAdapter() {
        adapter = NumbersAdapter(NumberClickListener { viewModel.onItemClicked(it) },
            NumberLongClickListener { viewModel.onLongClickListener(it) })
        binding.previousNumRv.adapter = adapter
        binding.previousNumRv.setHasFixedSize(true)


    }

    private fun observeForNumbersList() {
        viewModel.number.observe(viewLifecycleOwner, {
            it?.let { it1 ->
                adapter.addList(it1)
                binding.numberEt.text.clear()
                viewModel.clearNumber()
            }
        })
    }

    private fun observeForMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, resources.getText(it), Toast.LENGTH_SHORT).show()
        })
    }

    private fun observeForLoading() {
        viewModel.progress.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE

            }
        })
    }


}