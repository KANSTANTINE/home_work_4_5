package kg.geeks.taskapp5.ui.fragment.onboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kg.geeks.taskapp5.data.model.OnBoard
import kg.geeks.taskapp5.databinding.ItemOnboardBinding
import kg.geeks.taskapp5.utils.ext.loadImage

class OnBoardAdapter(val navUpClick: () -> Unit) :
    RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {

    private val boards = arrayListOf<OnBoard>(
        OnBoard(
            "Planner",
            "Plan your weekends",
            "https://cdn-icons-png.flaticon.com/512/6193/6193108.png"
        ),
        OnBoard(
            "Tasks",
            "Plan your routine",
            "https://cdn-icons-png.flaticon.com/512/5526/5526196.png"
        ),
        OnBoard(
            "Aim",
            "Aim on your targets",
            "https://cdn-icons-png.flaticon.com/512/4072/4072626.png"
        ),
        OnBoard(
            "Get success",
            "Discipline yourself",
            "https://cdn-icons-png.flaticon.com/512/4214/4214140.png"
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        return OnBoardViewHolder(
            ItemOnboardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = boards.size

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.bind(boards[position])
    }

    inner class OnBoardViewHolder(private val binding: ItemOnboardBinding) :
        ViewHolder(binding.root) {

        fun bind(onBoard: OnBoard) {
            binding.tvTitle.setText(onBoard.title)
            binding.tvDesc.setText(onBoard.desc)
            binding.ivImage.loadImage(onBoard.image)

            binding.btnUp.isVisible = adapterPosition == boards.lastIndex
            binding.btnUp.setOnClickListener {
                navUpClick()
            }
        }
    }
}