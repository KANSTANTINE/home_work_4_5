package kg.geeks.taskapp5.ui.fragment.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import kg.geeks.taskapp5.R
import kg.geeks.taskapp5.data.local.pref.Pref
import kg.geeks.taskapp5.databinding.FragmentProfileBinding
import kg.geeks.taskapp5.utils.ext.loadImage

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        setListeners()
        binding.ivUserImage.loadImage(pref.getUserImage().toString())
        binding.etUserName.setText(pref.getUserName())
    }

    private fun setListeners() {
        binding.btnSave.setOnClickListener {
            pref.saveUserName(binding.etUserName.text.toString())
        }

        binding.ivUserImage.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(
            Intent.EXTRA_MIME_TYPES,
            arrayOf("image/png", "image/jpeg", "image/gif", "video/mp4")
        )
        intent.action = Intent.ACTION_GET_CONTENT
        launcher.launch(intent)

    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedUri = result.data?.data
                binding.ivUserImage.loadImage(selectedUri.toString())
                pref.saveUserImage(selectedUri.toString())
            }
        }
}