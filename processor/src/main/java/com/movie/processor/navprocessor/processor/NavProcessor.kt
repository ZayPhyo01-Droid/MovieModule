package com.movie.processor.navprocessor.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import com.movie.annotations.Route
import com.movie.processor.navprocessor.visitor.DestinationVisitor


/**
Nav processor to generate route and destination use in compose navigation
instead of hardcode string.

You can remove this generator after google support code generation in its navigation compose

Limitation : Processor only support for nav argument with types (com.movie.navprocessor.Types.kt) and enum , no object type support for parceable and serialization


 usage :

@Route(global = Boolean)
@Composable
fun SampleScreen(){

}

 Build -> Above code will generate SampleScreenDestination (funName + Destination)

@param global true -> public object

@param global false -> internal object (for feature base navigation)
 */

class NavProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val dependencies = Dependencies(false, *resolver.getAllFiles().toList().toTypedArray())

        val symbolAnnonation = resolver.getSymbolsWithAnnotation(
            Route::class.qualifiedName.toString()
        )
        val symbols = symbolAnnonation.filterIsInstance(KSFunctionDeclaration::class.java)

        symbols.forEach { symbol ->
            symbol.accept(
                DestinationVisitor(
                    codeGenerator, dependencies, logger
                ), Unit
            )
        }

        return symbolAnnonation.filterNot {
            it.validate()
        }.toList()
    }

}