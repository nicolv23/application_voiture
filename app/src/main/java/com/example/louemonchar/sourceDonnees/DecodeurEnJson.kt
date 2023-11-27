package com.example.louemonchar.sourceDonnees

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DecodeurEnJson {

    fun encoderEnJson(modeles: Map<String, List<String>>): String {
        return Json.encodeToString(modeles)
    }

    fun decoderEnMap(jsonString: String): Map<String, List<String>> {
        return Json.decodeFromString(jsonString)
    }
}

