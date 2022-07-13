package com.example.favorite.di

import com.example.favorite.FavoriteVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteVM(get(), get()) }
}