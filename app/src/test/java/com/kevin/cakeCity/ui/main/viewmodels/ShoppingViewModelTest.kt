package com.kevin.cakeCity.ui.main.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.kevin.cakeCity.MainCoroutineRule
import com.kevin.cakeCity.getOrAwaitValueTest
import com.kevin.cakeCity.other.Status
import com.kevin.cakeCity.repositories.FakeShoppingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var viewModel: ShoppingViewModel
    @Before
    fun setup() {
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }
    @Test
    fun `insert shopping item with valid input, returns success`() {
        viewModel.insertShoppingItem("name", 5, 3.0f)

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
    @Test
    fun `insert shopping item with no name ,returns error`(){
        viewModel.insertShoppingItem("", 5, 3.0f)
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }
}