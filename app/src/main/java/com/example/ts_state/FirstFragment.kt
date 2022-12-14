package com.example.ts_state

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.ts_state.databinding.FragmentFirstBinding
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    private var containerId by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        containerId = container?.id ?: 0
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nestedSwitchCase.setOnClickListener {
            navigateToTurnstileFragment(args = TurnstileFragment.nestedSwitchCaseArgs())
        }

        binding.tableDriven.setOnClickListener {
            navigateToTurnstileFragment(args = TurnstileFragment.tableDrivenArgs())
        }

        binding.statePattern.setOnClickListener {
            navigateToTurnstileFragment(args = TurnstileFragment.statePatternArgs())
        }
    }

    private fun navigateToTurnstileFragment(args: Bundle) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<TurnstileFragment>(containerId, args = args)
            addToBackStack(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}