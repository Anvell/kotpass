@file:Suppress("unused")

package io.github.anvell.kotpass.constants

internal object Defaults {
    const val Generator = "Kotpass"
    const val KeyEncryptionRounds = 300_000
    const val MaintenanceHistoryDays = 365
    const val HistoryMaxItems = 10
    const val HistoryMaxSize = 6 * 1024 * 1024
    const val RecycleBinName = "Recycle Bin"
}
