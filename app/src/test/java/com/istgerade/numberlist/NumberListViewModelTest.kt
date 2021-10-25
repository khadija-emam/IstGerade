package com.istgerade.numberlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.istgerade.MainCoroutineRule
import com.istgerade.R
import com.istgerade.data.entity.NumberResponse
import com.istgerade.data.repository.Repository
import com.istgerade.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import java.lang.Exception


@ExperimentalCoroutinesApi
class NumberListViewModelTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var repository: Repository

    lateinit var viewModel: NumberListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = NumberListViewModel(repository)
    }

    @Test
    fun isEven_repositoryReturnsNumber_addToList() {

        val number = 6L
        val numberResponse = NumberResponse(iseven = true)

        coEvery { repository.isEven(number) } returns numberResponse



        viewModel.userNumber = "6"
        viewModel.isEven()

        val newNumber = viewModel.number.getOrAwaitValue()

        assertEquals(number, newNumber?.num)
        assertEquals(true, newNumber?.iseven)

    }

    @Test
    fun isEven_repositoryReturnsException_errorMessage() {

        val number = 6L
        val exp = Exception( "Network error")

        coEvery { repository.isEven(number) } throws exp

        viewModel.userNumber = "6"
        viewModel.isEven()
        val resultMessage = viewModel.errorMessage.getOrAwaitValue()

        assertEquals("Network error", resultMessage)


    }
}