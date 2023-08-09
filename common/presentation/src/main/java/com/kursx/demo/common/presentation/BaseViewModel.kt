package com.kursx.demo.common.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface UiState

interface UiEvent

interface UiEffect

abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }

    abstract fun createInitialState(): State

    private val _uiState: MutableStateFlow<State> by lazy { MutableStateFlow(initialState) }
    open val uiState: StateFlow<State> by lazy { _uiState.asStateFlow() }

    private val event: MutableSharedFlow<Event> = MutableSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    abstract fun handleEvent(event: Event)

    fun sendEvent(event: Event) {
        val newEvent = event
        viewModelScope.launch { this@BaseViewModel.event.emit(newEvent) }
    }

    protected fun setState(reduce: State.() -> State) {
        _uiState.update(reduce)
    }

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    inline fun <reified T> SavedStateHandle.delegate(key: String? = null): ReadWriteProperty<Any, T?> =
        object : ReadWriteProperty<Any, T?> {
            override fun getValue(thisRef: Any, property: KProperty<*>): T? {
                val stateKey = key ?: property.name
                return this@delegate[stateKey]
            }

            override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
                val stateKey = key ?: property.name
                this@delegate[stateKey] = value
            }
        }
}
