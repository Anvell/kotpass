package io.github.anvell.kotpass.models

import java.time.Instant

data class CustomIcon(
    val data: ByteArray,
    val name: String?,
    val lastModified: Instant?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CustomIcon

        if (!data.contentEquals(other.data)) return false
        if (name != other.name) return false
        if (lastModified != other.lastModified) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data.contentHashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + lastModified.hashCode()
        return result
    }
}
