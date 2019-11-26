package com.example.famousapp.famous.di.component

import com.example.famousapp.famous.di.ViewModelScope
import com.example.famousapp.famous.di.module.ViewHolderModule
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {


}