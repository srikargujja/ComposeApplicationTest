package com.example.composeapplicationtest

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.composeapplicationtest.network.RetrofitAPIBuilder
import com.example.composeapplicationtest.network.RetrofitAPIInterface
import com.example.composeapplicationtest.network.StudentsListRepository
import com.example.composeapplicationtest.ui.theme.ComposeApplicationTestTheme
import com.example.composeapplicationtest.viemodels.StudentViewModel
import com.example.composeappplication.models.DataClassModelItem
import com.example.myapplication.viewmodels.MyViewModelFactory

class MainActivity : ComponentActivity() {

    lateinit var studentViewModel: StudentViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar (
                                title = {
                                    Text(
                                        text = "High School Students List",
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            )
                        }
                    ) {
                        val retrofitAPIInterface =  RetrofitAPIBuilder.retrofitBuilderCall()
                        val studentsListRepository = StudentsListRepository(retrofitAPIInterface)
                        studentViewModel = ViewModelProvider(MainActivity.this, MyViewModelFactory(studentsListRepository))[StudentViewModel::class.java]
                        CallSchoolAPI(studentViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CallSchoolAPI(studentViewModel: StudentViewModel) {
        var itemList : List<DataClassModelItem>? = null
        val context = LocalContext.current
        studentViewModel.countriesList.observe( context , Observer {
                    itemList = it
        })

      LazyColumn {
             items(itemList){
                 
             }
      }
}
