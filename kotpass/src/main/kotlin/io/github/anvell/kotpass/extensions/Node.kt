package io.github.anvell.kotpass.extensions

import io.github.anvell.kotpass.models.FormatContext
import io.github.anvell.kotpass.xml.FormatXml
import io.github.anvell.kotpass.xml.marshal
import org.apache.commons.codec.binary.Base64
import org.redundent.kotlin.xml.Node
import org.redundent.kotlin.xml.TextElement
import java.nio.ByteBuffer
import java.time.Instant
import java.util.*

internal fun Node.getText() = (children.firstOrNull() as? TextElement)?.text

internal fun Node.getUuid(): UUID? = getText()?.let { text ->
    val bytes = Base64.decodeBase64(text)
    val byteBuffer = ByteBuffer.wrap(bytes)
    val mostSigBits = byteBuffer.long
    val leastSigBits = byteBuffer.long
    UUID(mostSigBits, leastSigBits)
}

internal fun Node.getBytes(): ByteArray? {
    return getText()?.let { Base64.decodeBase64(it) }
}

internal fun Node.addDateTime(
    context: FormatContext,
    instant: Instant?
) {
    if (instant != null) {
        text(instant.marshal(context))
    }
}

internal fun Node.addBoolean(value: Boolean) {
    text(if (value) FormatXml.Values.True else FormatXml.Values.False)
}

internal fun Node.addUuid(value: UUID) {
    val buffer = ByteBuffer.allocate(16).apply {
        putLong(value.mostSignificantBits)
        putLong(value.leastSignificantBits)
    }
    text(Base64.encodeBase64String(buffer.array()))
}

internal fun Node.addBytes(bytes: ByteArray) {
    text(Base64.encodeBase64String(bytes))
}
