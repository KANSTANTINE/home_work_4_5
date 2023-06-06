package kg.geeks.taskapp5.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kg.geeks.taskapp5.data.model.Task
import kg.geeks.taskapp5.databinding.ItemTaskBinding

class TaskAdapter(
    private val itemClick: (Task) -> Unit,
    private val longClick: (Task) -> Unit
) : Adapter<TaskAdapter.TaskViewHolder>() {

    private val arrayList = ArrayList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount() = arrayList.size

    fun addTasks(tasks: List<Task>) {
        arrayList.clear()
        arrayList.addAll(tasks)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.tvTitleTask.setText(task.title)
            binding.tvDescTask.setText(task.desc)

            binding.root.setOnLongClickListener {
                longClick(task)
                false
            }
            itemView.setOnClickListener {
                itemClick(task)
            }
        }
    }
}