package vik.com.example.myappmulti.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vik.com.example.myappmulti.databinding.FragmentMapNavBinding
import vik.com.example.myappmulti.databinding.JobinfoBinding


class map_nav : Fragment() {

    lateinit var binding: FragmentMapNavBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapNavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}