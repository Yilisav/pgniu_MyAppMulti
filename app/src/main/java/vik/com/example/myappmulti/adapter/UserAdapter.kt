package vik.com.example.myappmulti.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user_layout.view.*
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.activities.CJobInfo
import vik.com.example.myappmulti.model.UserModel


class UserAdapter() :RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    private var userList = emptyList<UserModel>()
    lateinit var resultLauncher : ActivityResultLauncher<Intent>


    class UserViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       var  view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_layout,parent,false)
       return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.tv_last_name.text = userList[position].lastName
        holder.itemView.tv_first_name.text = userList[position].firstName
        holder.itemView.setOnClickListener{
//            val activityJobsInfo = Intent(this, CJobInfo::class.java)
//            resultLauncher.launch(activityJobsInfo)
//        Toast.makeText()
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setList(list: List<UserModel>){
        userList = list
        notifyDataSetChanged()
    }

}