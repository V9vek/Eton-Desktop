package com.eton.test

import com.eton.data.di.module.MyModule
import com.eton.data.repo.MyRepo
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MyModule::class
        // Add your modules here
    ]
)
interface TestComponent {
    fun myRepo(): MyRepo
}