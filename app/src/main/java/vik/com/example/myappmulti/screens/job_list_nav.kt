package vik.com.example.myappmulti.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import vik.com.example.myappmulti.MAIN
import vik.com.example.myappmulti.activities.CJobInfo
import vik.com.example.myappmulti.databinding.FragmentJobListNavBinding




class job_list_nav : Fragment() {

    lateinit var binding: FragmentJobListNavBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobListNavBinding.inflate(layoutInflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
            }
        }

        binding.listJobs.setOnClickListener {
            val activityJobsInfo = Intent(MAIN, CJobInfo::class.java)
            resultLauncher.launch(activityJobsInfo)
        }

    }

}