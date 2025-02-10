package com.example.fitnessapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RunCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import com.example.fitnessapp.Navigation.Routes
import com.example.fitnessapp.ViewModel.UserInfoViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetGenderInfo(navController: NavController, userInfoViewModel: UserInfoViewModel) {
    val userGender by userInfoViewModel.userGender.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Previous Screen",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                navigationIconContentColor = MaterialTheme.colorScheme.secondary,
                titleContentColor = MaterialTheme.colorScheme.primary,
            )
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Box(contentAlignment = Alignment.TopCenter) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Text
                    Text(
                        text = "What is your gender?",
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        lineHeight = 40.sp,
                        style = TextStyle(
                            fontSize = 32.sp,

                        )
                    )


                    Spacer(modifier = Modifier.height(16.dp))
                    ElevatedCard(
                        onClick = { userInfoViewModel.selectGender("Male")
                                  navController.navigate(Routes.get_goal_info)
                                  Log.d("CardClick", userGender?:"No user selected")},

                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(
                                width = if (userGender == "Male") 4.dp else 0.dp,
                                color = if (userGender == "Male") Color.Blue else Color.Transparent,
                                shape = MaterialTheme.shapes.medium
                            )
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Male",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                            Image(painter = painterResource(id = R.drawable.male),
                                contentDescription ="Male"
                            )


                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    ElevatedCard(
                        onClick = { userInfoViewModel.selectGender("Female")
                                  navController.navigate(Routes.get_goal_info)
                                  Log.d("CardClick", userGender?:"No user selected")},
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(
                                width = if (userGender == "Female") 4.dp else 0.dp,
                                color = if (userGender == "Female") Color.Magenta else Color.Transparent,
                                shape = MaterialTheme.shapes.medium
                            )
                    ) {

                            Row(modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Female",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onTertiary
                                )
                                Image(painter = painterResource(id = R.drawable.female),
                                    contentDescription ="Female"
                                    )

                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetBodyTypeInfo(navController: NavController, userInfoViewModel: UserInfoViewModel){
    val userBodyType by userInfoViewModel.userBodyType.collectAsState()
    Scaffold(topBar = {
        TopAppBar(title = { },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Previous Screen",
                        tint = MaterialTheme.colorScheme.onBackground)
                }
            })
    }) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(contentAlignment = Alignment.TopCenter) {
                Column {
                    Text(
                        text = "What is your body type?",
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        lineHeight = 40.sp,
                        style = TextStyle(
                            fontSize = 32.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ElevatedCard(onClick = { userInfoViewModel.selectBodyType("Ectomorph")
                                            navController.navigate(Routes.get_final_details)},

                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(
                                width = if (userBodyType == "Ectomorph") 4.dp else 0.dp,
                                color = if (userBodyType == "Ectomorph") Color.Red else Color.Transparent,
                                shape = MaterialTheme.shapes.medium
                            ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)) {
                        Row(modifier=Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Ectomorph",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiary)
                            Image(painter = painterResource(id = R.drawable.ectomorph),
                                contentDescription ="Ectomorph",
                                contentScale = ContentScale.Fit)
                        }

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ElevatedCard(onClick = { userInfoViewModel.selectBodyType("Mesomorph")
                                           navController.navigate(Routes.get_final_details)},

                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(
                                width = if (userBodyType == "Mesomorph") 4.dp else 0.dp,
                                color = if (userBodyType == "Mesomorph") Color.Red else Color.Transparent,
                                shape = MaterialTheme.shapes.medium
                            ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)) {
                        Row(modifier=Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Mesomorph",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiary)
                            Image(painter = painterResource(id = R.drawable.mesomorph),
                                contentDescription ="Mesomorph",
                                contentScale = ContentScale.Fit)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ElevatedCard(onClick = { userInfoViewModel.selectBodyType("Endomorph")
                                            navController.navigate(Routes.get_final_details)},

                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(
                                width = if (userBodyType == "Endomorph") 4.dp else 0.dp,
                                color = if (userBodyType == "Endomorph") Color.Red else Color.Transparent,
                                shape = MaterialTheme.shapes.medium
                            ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)) {
                        Row(modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Endomorph",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiary)
                            Image(painter = painterResource(id = R.drawable.endpmorph),
                                contentDescription ="Endomorph",
                                contentScale = ContentScale.Fit,
                            )
                        }
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetGoalInfo(navController: NavController, userInfoViewModel: UserInfoViewModel){
    val userGoal by userInfoViewModel.userGoal.collectAsState()
    Scaffold(topBar = {
        TopAppBar(title = { },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Previous Screen",
                        tint = MaterialTheme.colorScheme.onBackground)
                }
            })
    }) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(contentAlignment = Alignment.TopCenter) {
                Column {
                    Text(
                        text = "What is your goal?",
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        lineHeight = 40.sp,
                        style = TextStyle(
                            fontSize = 32.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ElevatedCard(onClick = { navController.navigate(Routes.get_body_type_info)
                                           userInfoViewModel.selectGoal("Bulk")},
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(
                                width = if (userGoal == "Bulk") 4.dp else 0.dp,
                                color = if (userGoal == "Bulk") Color.Red else Color.Transparent,
                                shape = MaterialTheme.shapes.medium
                            ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)) {
                        Row(modifier=Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Bulk",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiary)
                            Image(painter = painterResource(id = R.drawable.manbulk),
                                contentDescription ="Bulking",
                                contentScale = ContentScale.Fit)
                        }
                        
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ElevatedCard(onClick = { userInfoViewModel.selectGoal("Cut")
                                           navController.navigate(Routes.get_body_type_info)
                                           },
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(
                                width = if (userGoal == "Cut") 4.dp else 0.dp,
                                color = if (userGoal == "Cut") Color.Red else Color.Transparent,
                                shape = MaterialTheme.shapes.medium
                            )
                        ,
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)) {
                        Row(modifier=Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Cut",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiary)
                            Image(painter = painterResource(id = R.drawable.mancut),
                                contentDescription ="Cutting",
                                contentScale = ContentScale.Fit)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ElevatedCard(onClick = { userInfoViewModel.selectGoal("Maintain")
                                           navController.navigate(Routes.get_body_type_info)},
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(
                                width = if (userGoal == "Maintain") 4.dp else 0.dp,
                                color = if (userGoal == "Maintain") Color.Red else Color.Transparent,
                                shape = MaterialTheme.shapes.medium
                            ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)) {
                        Row(modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Maintain",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiary)
                            Image(painter = painterResource(id = R.drawable.maintainman),
                                contentDescription ="Maintaining",
                                contentScale = ContentScale.Fit,
                                )
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetFinalDetails(navController: NavController, userInfoViewModel: UserInfoViewModel){
    val userName by userInfoViewModel.userName.collectAsState()
    val userDateOfBirth by userInfoViewModel.userDateOfBirth.collectAsState()
    val userAge by userInfoViewModel.userAge.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()
    val userBodyweight by userInfoViewModel.userBodyWeight.collectAsState()
    var isError by remember{ mutableStateOf(false) }
    val userActvityrate by userInfoViewModel.userActivityRate.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    fun formatDate(dateinmillis:Long): String {
        val sdf =SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date(dateinmillis))
    }



    Scaffold(topBar = { TopAppBar(title = { /*TODO*/ },
        navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Previous Screen",
                tint=MaterialTheme.colorScheme.onBackground)
        }})},
        floatingActionButton = { FloatingActionButton(onClick = {
            if(userBodyweight.isNullOrEmpty()||userInfoViewModel.userName.value.isNullOrEmpty()){
                isError=true
            }
            else{
                navController.navigate(Routes.get_response)
            }
        }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.NavigateNext, contentDescription = "Next Screen")
        }}) { paddingValues ->
        Column(modifier= Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            Box(contentAlignment = Alignment.TopCenter) {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Few more details!",
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        lineHeight = 40.sp,
                        style = TextStyle(
                            fontSize = 32.sp,

                        )
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    OutlinedTextField(
                        value = userName?:"",  // ✅ Uses collected state
                        onValueChange = { newValue ->
                            userInfoViewModel.enterUserName(newValue)  // ✅ Updates ViewModel correctly
                        },
                        placeholder = { Text(text = "Name") },
                        leadingIcon = { Icon(imageVector = Icons.Outlined.Person, contentDescription = "") },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        isError = isError,
                        supportingText = { Text(text = "*required") }
                    )


                    OutlinedTextField(value = userBodyweight?:"", onValueChange = {
                        newValue->userInfoViewModel.enterBodyWeight(newValue)
                        isError=newValue.isEmpty() },
                        placeholder = { Text(text = "Weight")},
                        leadingIcon = { Icon(imageVector = Icons.Outlined.MonitorWeight, contentDescription = "")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = {}),
                        isError = isError,
                        supportingText = { Text(text = "*required")}
                        )


                    OutlinedTextField(
                        value = userDateOfBirth?:"",
                        onValueChange = { newValue-> userInfoViewModel.enterDob(newValue)
                                        isError=newValue.isEmpty()},
                        placeholder = { Text(text = "Date Of Birth") },
                        leadingIcon = {
                            Icon(imageVector = Icons.Outlined.CalendarToday, contentDescription = null)
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .pointerInput(Unit) {
                                awaitEachGesture {
                                    awaitFirstDown(pass = PointerEventPass.Initial)
                                    val upEvent =
                                        waitForUpOrCancellation(pass = PointerEventPass.Initial)
                                    if (upEvent != null) {
                                        showDatePicker = true
                                    }
                                }
                            }
                            , supportingText = { Text(text = "Your Age: ${userAge?:""}")},
                            isError = isError
                    )

                    if (showDatePicker) {
                        DatePickerDialog(
                            onDismissRequest = { showDatePicker = false },
                            confirmButton = {
                                TextButton(onClick = { showDatePicker = false
                                val selectedDateMillis=dateState.selectedDateMillis
                                if(selectedDateMillis!=null){
                                    userInfoViewModel.enterDob(formatDate(selectedDateMillis))
                                    userInfoViewModel.calculateAndSetAge(selectedDateMillis)
                                    isError=userInfoViewModel.userDateOfBirth.value.isNullOrEmpty()||userAge==0
                                }
                                }) {
                                    Text(text = "Ok")
                                }
                            }
                        ) {
                            DatePicker(state = dateState)
                        }
                    }
                    OutlinedTextField(
                        value = userActvityrate?:"",
                        onValueChange = { newValue->  userInfoViewModel.enterActivityRate(newValue)
                                        isError=newValue.isEmpty()},
                        placeholder = { Text(text = "How often do you perform physical activity?") },
                        leadingIcon = {
                            Icon(imageVector = Icons.Outlined.RunCircle, contentDescription = null)
                        },
                        readOnly = true, // Prevents keyboard from opening
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .pointerInput(Unit) {
                                awaitEachGesture {
                                    awaitFirstDown(pass = PointerEventPass.Initial)
                                    val upEvent =
                                        waitForUpOrCancellation(pass = PointerEventPass.Initial)
                                    if (upEvent != null) {
                                        expanded = true
                                    }
                                }
                            }
                        ,
                        supportingText = { Text(text = "*required")},
                        isError = isError
                    )

                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded=false }, offset = DpOffset(x=230.dp,y=0.dp)) {
                        DropdownMenuItem(text = { Text(text = "Very Often") }, onClick = { expanded=false 
                                                                                            userInfoViewModel.enterActivityRate("Very Often") })
                        DropdownMenuItem(text = { Text(text = "Sometimes") }, onClick = { userInfoViewModel.enterActivityRate("Sometimes")
                                                                                            expanded=false})
                        DropdownMenuItem(text = { Text(text = "Never") }, onClick = { userInfoViewModel.enterActivityRate("Never")
                                                                                            expanded=false})
                    }

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GetGenderInfoPreview() {
    val mock = TestNavHostController(LocalContext.current)
    val mockmodel=UserInfoViewModel()
    GetGoalInfo(navController = mock, userInfoViewModel = mockmodel)
}

