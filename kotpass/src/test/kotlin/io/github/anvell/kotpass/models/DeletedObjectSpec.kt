package io.github.anvell.kotpass.models

import io.github.anvell.kotpass.cryptography.EncryptionSaltGenerator
import io.github.anvell.kotpass.extensions.parseAsXml
import io.github.anvell.kotpass.resources.DeletedObjectRes
import io.github.anvell.kotpass.xml.marshal
import io.github.anvell.kotpass.xml.unmarshalDeletedObject
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.redundent.kotlin.xml.PrintOptions

class DeletedObjectSpec : DescribeSpec({

    describe("DeletedObject") {
        it("Parsing from Xml string") {
            val root = DeletedObjectRes
                .BasicXml
                .parseAsXml()
            val deletedObject = unmarshalDeletedObject(root)

            deletedObject shouldBe DeletedObjectRes.BasicObject
        }

        it("Uuid is encoded as Base64") {
            val context = XmlContext.Encode(
                version = FormatVersion(4, 0),
                encryption = EncryptionSaltGenerator.ChaCha20(byteArrayOf()),
                binaries = linkedMapOf()
            )

            DeletedObjectRes
                .BasicObject
                .marshal(context)
                .toString(PrintOptions(singleLineTextElements = true))
                .indexOf(DeletedObjectRes.Base64StringUuid) shouldNotBe -1
        }
    }
})
