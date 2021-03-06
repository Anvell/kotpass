@file:Suppress("unused")

package io.github.anvell.kotpass.xml

import io.github.anvell.kotpass.extensions.addDateTime
import io.github.anvell.kotpass.extensions.addUuid
import io.github.anvell.kotpass.extensions.getUuid
import io.github.anvell.kotpass.models.DeletedObject
import io.github.anvell.kotpass.models.XmlContext
import org.redundent.kotlin.xml.Node
import org.redundent.kotlin.xml.node

internal fun unmarshalDeletedObject(node: Node): DeletedObject? {
    val uuid = node.firstOrNull(FormatXml.Tags.Uuid)?.getUuid()
    val dateTime = node.firstOrNull(FormatXml.Tags.DeletedObjects.Time)?.getInstant()

    return if (uuid != null && dateTime != null) {
        DeletedObject(uuid, dateTime)
    } else {
        null
    }
}

internal fun DeletedObject.marshal(context: XmlContext.Encode): Node {
    return node(FormatXml.Tags.DeletedObjects.Object) {
        FormatXml.Tags.Uuid { addUuid(id) }
        FormatXml.Tags.DeletedObjects.Time { addDateTime(context, deletionTime) }
    }
}
