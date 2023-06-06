package kg.geeks.taskapp5.ui.fragment.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kg.geeks.taskapp5.R
import kg.geeks.taskapp5.data.local.pref.Pref
import kg.geeks.taskapp5.databinding.FragmentOnBoardBinding
import kg.geeks.taskapp5.ui.fragment.onboard.adapter.OnBoardAdapter

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private val adapter = OnBoardAdapter(this::navUpClick)
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.viewPager2.adapter = adapter
        binding.circleIndicator3.setViewPager(binding.viewPager2)
    }

    private fun navUpClick() {
        pref.boardingIsSeen()
        findNavController().navigateUp()
    }
}