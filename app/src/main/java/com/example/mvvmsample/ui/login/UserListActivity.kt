package com.example.mvvmsample.ui.login

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsample.R
import com.example.mvvmsample.data.AppDatabase
import com.example.mvvmsample.repository.LoginRespository
import com.example.mvvmsample.util.LoginViewModelFactory
import com.example.mvvmsample.viewmodel.LoginViewModel

class UserListActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var userListAdapter: UserListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_list)
        val recyclerView: RecyclerView = findViewById(R.id.userRecyclerView)
        userListAdapter = UserListAdapter()
        recyclerView.adapter = userListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = LoginRespository.LoginRepository(database.userDao())
        viewModel = ViewModelProvider(this, LoginViewModelFactory(repository))
            .get(LoginViewModel::class.java)

        viewModel.users.observe(this) { users ->
            userListAdapter.setUsers(users)
        }

        viewModel.loadUsers()
    }
}
