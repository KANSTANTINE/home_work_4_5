package kg.geeks.taskapp5.ui.fragment.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import kg.geeks.taskapp5.App
import kg.geeks.taskapp5.R
import kg.geeks.taskapp5.data.local.database.AppDatabase
import kg.geeks.taskapp5.data.model.Task
import kg.geeks.taskapp5.databinding.FragmentTaskBinding
import kg.geeks.taskapp5.ui.fragment.home.HomeFragment

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEditData()

        // HARD CODE BUT I WILL FIX IT! PROMISE!

        binding.fabSave.setOnClickListener {
            if (binding.fabSave.text.equals("update")) {
                if (!binding.etTitleTask.text.isBlank() && !binding.etDescTask.text.isBlank()) {
                    onUpdate()
                    findNavController().navigateUp()
                } else
                    Toast.makeText(requireContext(), "Fill the fields!", Toast.LENGTH_SHORT).show()

            } else {
                if (!binding.etTitleTask.text.isBlank() && !binding.etDescTask.text.isBlank()) {
                    onSave()
                    findNavController().navigateUp()
                } else
                    Toast.makeText(requireContext(), "Fill the fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onSave() {
        val result = Task(
            title = binding.etTitleTask.text.toString(),
            desc = binding.etDescTask.text.toString()
        )
        App.db.dao().insert(result)
    }

    private fun onUpdate() {
        val result = task?.copy(
            title = binding.etTitleTask.text.toString(),
            desc = binding.etDescTask.text.toString()
        )
        if (result != null) {
            App.db.dao().update(result)
        }
    }

    private fun initEditData() {
        task = arguments?.getSerializable(HomeFragment.EDIT_KEY_TASK) as Task?
        task?.let {
            binding.fabSave.setText("update")
            binding.etTitleTask.setText(it.title)
            binding.etDescTask.setText(it.desc)
        }
    }
}