package kg.geeks.taskapp5.ui.fragment.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kg.geeks.taskapp5.App
import kg.geeks.taskapp5.R
import kg.geeks.taskapp5.data.model.Task
import kg.geeks.taskapp5.databinding.FragmentHomeBinding
import kg.geeks.taskapp5.ui.fragment.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = TaskAdapter(this::onItemClick, this::onLongClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewHome.adapter = adapter

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

        getDataResult()
    }

    private fun getDataResult() {
        val tasks = App.db.dao().getAll()
        adapter.addTasks(tasks)
    }

    private fun onItemClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf(EDIT_KEY_TASK to task))
    }

    private fun onLongClick(task: Task) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Item")
            .setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Delete") { _, _ ->
                App.db.dao().delete(task)
                getDataResult()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    companion object {
        const val EDIT_KEY_TASK = "edit_key.task"
    }
}