package com.kevin.cakehouse.ui.main.fragments

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.kevin.cakehouse.R
import com.kevin.cakehouse.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class DetailFragmentTest{
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun clickAddShoppingItemButton_navigateToAddShoppingItemFragment(){
            val navController = mock(NavController::class.java)
                launchFragmentInHiltContainer<DetailFragment> {
                    Navigation.setViewNavController(requireView(), navController)
                }
        onView(withId(R.id.btnAddShoppingItem)).perform(click())
        verify(navController).navigate(
                DetailFragmentDirections.actionDetailFragmentToShoppingFragment()
        )

    }
}
















