package com.fappslab.libraries.arch.viewmodel

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import androidx.lifecycle.ViewModel as AndroidxViewModel

/**
 * Classe abstrata que fornece suporte para descarte automático de [Disposable]s em um ViewModel.
 */
abstract class DisposableViewModel : androidx.lifecycle.ViewModel() {

    /**
     * [CompositeDisposable] que armazena todos os [Disposable]s registrados.
     */
    private val disposable: CompositeDisposable = CompositeDisposable()

    /**
     * Adiciona o [Disposable] atual ao [CompositeDisposable] e retorna o [Disposable].
     *
     * @return o [Disposable] atual.
     */
    protected fun Disposable.handleDisposable(): Disposable =
        apply { disposable.add(this) }

    /**
     * Chamado quando o ViewModel é limpo. Descarta todos os [Disposable]s registrados e chama o método [onCleared] do ViewModel pai.
     */
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
