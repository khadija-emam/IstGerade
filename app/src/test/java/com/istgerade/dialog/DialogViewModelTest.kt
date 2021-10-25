package com.istgerade.dialog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.istgerade.MainCoroutineRule
import com.istgerade.data.entity.TranslatedTextResponse
import com.istgerade.data.repository.Repository
import com.istgerade.getOrAwaitValue
import com.istgerade.numberlist.NumberListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class DialogViewModelTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var repository: Repository

    lateinit var viewModel: DialogViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = DialogViewModel(repository)
    }

    @Test
    fun translateAd_repositoryReturnsTranslation() {
        val str="number"
        val response= TranslatedTextResponse("zahl")
        coEvery { repository.translate(str) } returns response

        viewModel.translateAd(str)
        val trans=viewModel.ad.getOrAwaitValue ()
        assertEquals("zahl",trans)
    }
}