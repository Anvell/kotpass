package io.github.anvell.kotpass.extensions

import org.redundent.kotlin.xml.parse
import java.io.ByteArrayInputStream

internal fun String.parseAsXml() = parse(ByteArrayInputStream(toByteArray()))
