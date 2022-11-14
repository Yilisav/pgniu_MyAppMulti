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
import androidx.recyclerview.widget.RecyclerView
import vik.com.example.myappmulti.adapter.UserAdapter
import vik.com.example.myappmulti.databinding.FragmentJobListNavLayoutBinding
import vik.com.example.myappmulti.model.UserModel


class job_list_nav : Fragment() {

    lateinit var binding: FragmentJobListNavLayoutBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    lateinit var adapter: UserAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobListNavLayoutBinding.inflate(layoutInflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
            }
        }

//        binding.listJobs.setOnClickListener {
//            val activityJobsInfo = Intent(MAIN, CJobInfo::class.java)
//            resultLauncher.launch(activityJobsInfo)
//        }
        initial()

    }

    private fun initial() {
        recyclerView = binding.rvUser
        adapter = UserAdapter()
        recyclerView.adapter = adapter
        adapter.setList(myUser())

    }

    fun myUser (): ArrayList<UserModel>{
        val userList = ArrayList<UserModel>()
        val  user = UserModel ("Petrov", "Vasya")
        userList.add(user)
        val  user2 = UserModel ("Ivanov", "Ilya")
        userList.add(user2)
        val  user3 = UserModel ("Sidirov", "Ivan")
        userList.add(user3)
        val  user4 = UserModel ("Arhipov", "Petr")
        userList.add(user4)
        val  user5 = UserModel ("Afonin", "Kolya")
        userList.add(user5)
        val  user6 = UserModel ("Petrov", "Vasya")
        userList.add(user6)
        val  user7 = UserModel ("Ivanov", "Ilya")
        userList.add(user7)
        val  user8 = UserModel ("Sidirov", "Ivan")
        userList.add(user8)
        val  user9 = UserModel ("Arhipov", "Petr")
        userList.add(user9)
        val  user10 = UserModel ("Afonin", "Kolya")
        userList.add(user10)
        val  user11 = UserModel ("Petrov", "Vasya")
        userList.add(user11)
        val  user12 = UserModel ("Ivanov", "Ilya")
        userList.add(user12)
        val  user13 = UserModel ("Sidirov", "Ivan")
        userList.add(user13)
        val  user14 = UserModel ("Arhipov", "Petr")
        userList.add(user14)
        val  user15 = UserModel ("Afonin", "Kolya")
        userList.add(user15)
        return userList
    }

}