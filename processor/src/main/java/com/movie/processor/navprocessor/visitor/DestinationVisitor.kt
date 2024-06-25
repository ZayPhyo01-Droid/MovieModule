package com.movie.processor.navprocessor.visitor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.movie.annotations.Route

import com.movie.processor.navprocessor.createRouteNameKey
import com.movie.processor.navprocessor.generateBundles
import com.movie.processor.navprocessor.generateRoute
import com.movie.processor.navprocessor.getSupportedRouteArgumentAnnotatedParameters
import com.movie.processor.navprocessor.isAnnotatedEnumType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeSpec

class DestinationVisitor(
    private val codeGenerator: CodeGenerator,
    private val dependencies: Dependencies,
    private val logger: KSPLogger,

) : KSVisitorVoid() {

    private val navigationComposeImport = "androidx.navigation.compose"
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        super.visitClassDeclaration(classDeclaration, data)
        classDeclaration.modifiers
    }

    override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
        function.annotations.firstOrNull() {
            it.shortName.asString() == Route::class.simpleName
        }?.let {
            val screenName = function.qualifiedName?.asString()?.replace(
                ".", "_"
            ).orEmpty()
            FileSpec.builder(
                packageName = function.packageName.asString(),
                fileName = function.simpleName.asString() + "Destination"
            ).apply {
                addImport(
                    navigationComposeImport,
                    "composable"
                )
                addType(
                    TypeSpec.objectBuilder(
                        function.simpleName.asString() + "Destination"
                    ).addModifiers(
                        if (it.arguments.first().value as Boolean)
                            KModifier.PUBLIC
                        else
                            KModifier.INTERNAL
                    ).addFunction(
                        FunSpec.builder("destination").returns(STRING).addStatement(
                            "return \"%L\"", createRouteNameKey(
                                screenName,
                                function.getSupportedRouteArgumentAnnotatedParameters().map {
                                    it.name?.asString().orEmpty()
                                }.toList()
                            )
                        ).build()
                    ).addFunction(
                        generateRoute(function, screenName)
                    ).addFunctions(
                        generateBundles(function)
                    ).apply {
                        if (function.getSupportedRouteArgumentAnnotatedParameters().iterator()
                                .hasNext()
                        ) {
                            this.addFunction(
                                generateTypeFunction(function, logger = logger)
                            )
                        }
                    }.build()
                )
            }.build().let { fileSpec ->
                codeGenerator.createNewFile(
                    dependencies , fileSpec.packageName , fileSpec.name
                ).bufferedWriter().use {
                    fileSpec.writeTo(it)
                }

            }
        }
    }

    private fun generateTypeFunction(function: KSFunctionDeclaration, logger: KSPLogger): FunSpec {
        return FunSpec.builder("types")
            .returns(
                ClassName(
                    "kotlin.collections","List"
                ).parameterizedBy(
                    ClassName(
                        "androidx.navigation","NamedNavArgument"
                    )
                )
            )
            .addStatement(
                "return listOf(" + function.getSupportedRouteArgumentAnnotatedParameters()
                    .map { prop ->
                        val isEnumType = prop.isAnnotatedEnumType()

                        val navType = if (isEnumType) {
                            "androidx.navigation.NavType.StringType"
                        } else {
                            getNavType(prop.type, logger)
                        }
                        "androidx.navigation.navArgument(\"${prop.name?.asString()}\"){" + "\ntype = $navType" + "\nnullable = ${prop.type.resolve().isMarkedNullable}" + "\n}"
                    }.toList().joinToString(",\n") {
                        it
                    } + ")").build()
    }


}


private fun getNavType(type: KSTypeReference, logger: KSPLogger): String? {

    val typeName = type.resolve().declaration.simpleName.asString()
    if (typeName == "String") return "androidx.navigation.NavType.StringType"
    if (typeName == "Int") return "androidx.navigation.NavType.IntType"
    if ("Boolean" == typeName) return "androidx.navigation.NavType.BoolType"
    if ("Float" == typeName) return "androidx.navigation.NavType.FloatType"
    logger.error("$typeName is not supported")
    return null
}

