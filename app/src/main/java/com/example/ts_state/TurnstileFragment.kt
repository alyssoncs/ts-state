package com.example.ts_state

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.ts_state.databinding.FragmentTurnstileBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TurnstileFragment : Fragment() {

    private var _binding: FragmentTurnstileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: TurnstileViewModel by viewModels { TurnstileViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTurnstileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.coin.setOnClickListener { viewModel.insertCoin() }
        binding.pass.setOnClickListener { viewModel.pass() }
        viewModel.uiState.observe(viewLifecycleOwner) { state: TurnstileViewModel.UiState ->
            val imageSrc = when (state) {
                TurnstileViewModel.UiState.UNLOCKED -> R.drawable.unlocked
                TurnstileViewModel.UiState.LOCKED -> R.drawable.locked
                TurnstileViewModel.UiState.THANKING -> R.drawable.thanks
                TurnstileViewModel.UiState.ALARMING -> R.drawable.alarm
            }
            binding.turnstileState.setImageResource(imageSrc)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val STRATEGY_NAME_KEY = "STRATEGY_NAME_KEY"
        const val NESTED_SWITCH_CASE = "nestedSwitchCase"
        const val TABLE_DRIVEN = "tableDriven"
        const val STATE_PATTERN = "statePattern"

        fun nestedSwitchCaseArgs() = createArguments(NESTED_SWITCH_CASE)
        fun tableDrivenArgs() = createArguments(TABLE_DRIVEN)
        fun statePatternArgs() = createArguments(STATE_PATTERN)
        private fun createArguments(strategyName: String) = bundleOf(STRATEGY_NAME_KEY to strategyName)
    }
}