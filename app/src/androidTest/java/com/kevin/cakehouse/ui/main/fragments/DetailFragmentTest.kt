package com.kevin.cakehouse.ui.main.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.kevin.cakehouse.R
import com.kevin.cakehouse.data.local.entities.ShoppingItem
import com.kevin.cakehouse.getOrAwaitValue
import com.kevin.cakehouse.launchFragmentInHiltContainer
import com.kevin.cakehouse.repositories.FakeShoppingRepositoryAndroidTest
import com.kevin.cakehouse.ui.main.viewmodels.ShoppingViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class DetailFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: TestShoppingFragmentFactory
    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun clickInsertIntoDb_shoppingItemInsertedIntoDb() {
        val testViewModel = ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
        launchFragmentInHiltContainer<DetailFragment> (
            fragmentFactory = fragmentFactory
                ){
            viewModel = testViewModel
        }
        onView(withId(R.id.etShoppingItemName)).perform(replaceText("shopping item"))
        onView(withId(R.id.etShoppingItemAmount)).perform(replaceText("5"))
        onView(withId(R.id.etShoppingItemPrice)).perform(replaceText("5.5"))
        onView(withId(R.id.btnAddShoppingItem)).perform(click())
        assertThat(testViewModel.shoppingItems.getOrAwaitValue())
            .contains(ShoppingItem("shopping item", 5, 5.5f,))
    }


}























