package com.example.arnolibs.di

import com.example.arnolibs.ui.Ar.ArViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val services = module {

}

val viewModels = module {
    viewModel { ArViewModel()}
}