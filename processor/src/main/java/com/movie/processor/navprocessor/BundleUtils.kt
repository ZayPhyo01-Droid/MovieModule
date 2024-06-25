package com.movie.processor.navprocessor

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSTypeReference
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

fun generateBundles(function: KSFunctionDeclaration) =
    function.getSupportedRouteArgumentAnnotatedParameters().map { prop ->
        val type = prop.type.resolve()
        FunSpec.builder(
            name = prop.name?.asString().orEmpty()
        ).returns(
            ClassName(
                packageName = type.declaration.packageName.asString(),
                type.declaration.simpleName.asString()
            ).copy(nullable = type.isMarkedNullable)
        ).addParameter(
            ParameterSpec.builder(
                "bundle", ClassName("android.os", "Bundle")
            ).build()

        ).addCode(
            """ return ${
                getBundleValueByReferenceType(
                    isEnumType = prop.isAnnotatedEnumType(),
                    prop.type, prop.name?.asString().orEmpty()
                ).orEmpty()
            }${if (type.isMarkedNullable) "" else "!!"}
                                        """.trimIndent()
        ).build()

    }.toList()

private fun getBundleValueByReferenceType(
    isEnumType: Boolean,
    type: KSTypeReference,
    key: String
): String? {
    val typeName = type.resolve().declaration.simpleName.asString()
    if (isEnumType) return "${typeName}.valueOf(bundle.getString(\"$key\")!!)"
    return if (STRING_TYPE == typeName) "bundle.getString(\"$key\")"
    else if (BOOLEAN_TYPE == typeName) "bundle.getBoolean(\"$key\")"
    else if (INT_TYPE == typeName) "bundle.getInt(\"$key\")"
    else if (FLOAT_TYPE == typeName) "bundle.getFloat(\"$key\")"
    else if (DOUBLE_TYPE == typeName) "bundle.getDouble(\"$key\")"
    else if (LONG_TYPE == typeName) "bundle.getLong(\"$key\")"
    else return null
}


