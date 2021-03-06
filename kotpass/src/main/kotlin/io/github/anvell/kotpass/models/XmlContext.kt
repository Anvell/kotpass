package io.github.anvell.kotpass.models

import io.github.anvell.kotpass.cryptography.EncryptionSaltGenerator
import okio.ByteString

sealed class XmlContext {
    abstract val version: FormatVersion

    class Encode(
        override val version: FormatVersion,
        val encryption: EncryptionSaltGenerator,
        val binaries: Map<ByteString, BinaryData>,
        val isXmlExport: Boolean = false
    ) : XmlContext()

    class Decode(
        override val version: FormatVersion,
        val encryption: EncryptionSaltGenerator,
        val binaries: Map<ByteString, BinaryData>
    ) : XmlContext()
}
