package com.movie.processor.navprocessor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueParameter
import com.movie.annotations.RouteArg
import com.movie.annotations.RouteArgEnum
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.STRING


fun generateRoute(
    function: KSFunctionDeclaration, screenName: String
) = FunSpec.builder("route").returns(STRING).addParameters(
    function.getSupportedRouteArgumentAnnotatedParameters().map {
        val type = it.type.resolve()
        ParameterSpec.builder(
            it.name?.asString().orEmpty(), ClassName(
                packageName = type.declaration.packageName.asString(),
                type.declaration.simpleName.asString()
            ).copy(nullable = type.isMarkedNullable)
        ).build()
    }.toList()
).addStatement(
    "return \"%L\"", createArgsRoute(
        screenName, function.getSupportedRouteArgumentAnnotatedParameters().toList()
    )
).build()


private fun createArgsRoute(screen: String, props: List<KSValueParameter>): String {
    val pair = props.map {
        it.name?.asString() to it.isAnnotatedEnumType()
    }.toList()


    if (pair.isNotEmpty()) {
        val formattedArgs = pair.joinToString(
            separator = "&"
        ) { it.first + "=" + if (!it.second) "$" + "{" + it.first + "}" else "$" + "{" + it.first + ".name }" }
        return "$screen?$formattedArgs"
    }
    return screen
}

fun createRouteNameKey(screen: String, key: List<String>): String {
    if (key.isNotEmpty()) {
        val formattedKeys = key.joinToString(
            separator = "&"
        ) { "$it={$it}" }
        return "$screen?$formattedKeys"
    }
    return screen
}


fun KSValueParameter.isAnnotatedEnumType() =
    this.annotations.filter {
        it.shortName.asString() == "RouteArgEnum"
    }.toList().isNotEmpty()


@OptIn(KspExperimental::class)
fun KSFunctionDeclaration.getSupportedRouteArgumentAnnotatedParameters() = parameters.filter {
    it.isAnnotationPresent(RouteArg::class) || it.isAnnotationPresent(RouteArgEnum::class)
}
