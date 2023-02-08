package com.example.uptodo.Navigation

sealed class Screen(val route: String){
object OnoardingScreen:Screen(route = "onboarding_screen")
object FirstIntroScreen:Screen(route = "first_intro_screen")
object SecondIntroScreen:Screen(route = "second_intro_screen")
object ThirdIntroScreen:Screen(route = "third_intro_screen")
object WelcomeScreen:Screen(route = "welcome_screen")
object LoginScreen:Screen(route = "login_screen")
object SignUpScreen:Screen(route = "signup_screen")
object DashBoardScreen:Screen(route = "dash_board_screen")
object ProfileScreen:Screen(route = "profile_screen")
object CreateNewCategory:Screen(route = "create_new_category_screen")
object IndexScreen:Screen(route = "index_screen")
object SettingsScreen:Screen(route = "settings_screen")
}
