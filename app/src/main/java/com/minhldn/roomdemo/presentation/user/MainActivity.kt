package com.minhldn.roomdemo.presentation.user

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.minhldn.roomdemo.R
import com.minhldn.roomdemo.data.model.User
import com.minhldn.roomdemo.data.repository.UserRepositoryImpl
import com.minhldn.roomdemo.data.room.UserDatabase
import com.minhldn.roomdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var userDatabase: UserDatabase
    private lateinit var userAdapter: UserAdapter

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        initListener()

        /*        val user = User(userName = "Quang", userAge = 30)

                viewModel.insertUser(user)*/
    }

    private fun initView() {
        binding.rvUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initListener() {
        userDatabase = UserDatabase.getDatabase(this)!!

        val repository = UserRepositoryImpl(userDatabase.userDao())

        viewModel = UserViewModel(repository)

        viewModel.users.observe(this) {
            userAdapter = UserAdapter(it)
            binding.rvUser.adapter = userAdapter
        }

        viewModel.getUserData()

        binding.btnAddUser.setOnClickListener {
            val userName = binding.edtName.text.toString()
            val userAge = binding.edtAge.text.toString().toIntOrNull() ?: 0

            if (userName.isNotEmpty()) {
                val newUser = User(userName = userName, userAge = userAge)
                viewModel.insertUser(newUser)

                binding.edtName.text.clear()
                binding.edtAge.text.clear()
            }
        }

        binding.btnDelUser.setOnClickListener{
            val userId = binding.edtID.text.toString().toIntOrNull()

            if (userId != null) {
                val userToDelete = User(userId = userId, userName = "", userAge = 0)
                viewModel.deleteUser(userToDelete)

                binding.edtID.text.clear()
            }
        }
        binding.btnUpdateUser.setOnClickListener{
            val userId = binding.edtID.text.toString().toIntOrNull()
            val userName = binding.edtName.text.toString()
            val userAge = binding.edtAge.text.toString().toIntOrNull() ?: 0

            if (userId != null && userName.isNotEmpty()) {
                val updatedUser = User(userId = userId, userName = userName, userAge = userAge)
                viewModel.updateUser(updatedUser)

                binding.edtID.text.clear()
                binding.edtName.text.clear()
                binding.edtAge.text.clear()
            }
        }
    }
}