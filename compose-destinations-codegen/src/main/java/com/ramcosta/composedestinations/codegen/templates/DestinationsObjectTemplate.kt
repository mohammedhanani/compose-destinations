package com.ramcosta.composedestinations.codegen.templates

import com.ramcosta.composedestinations.codegen.commons.*

//region anchors
internal const val IMPORTS_BLOCK = "[IMPORTS_BLOCK]"
internal const val NAV_GRAPHS_DECLARATION = "[NAV_GRAPHS_DECLARATION]"
internal const val DEFAULT_NAV_CONTROLLER_PLACEHOLDER = "[DEFAULT_NAV_CONTROLLER_PLACEHOLDER]"
internal const val INNER_NAV_HOST_PLACEHOLDER = "[INNER_NAV_HOST_PLACEHOLDER]"
internal const val EXPERIMENTAL_API_PLACEHOLDER = "[EXPERIMENTAL_API_PLACEHOLDER]"
internal const val ANIMATION_DEFAULT_PARAMS_PLACEHOLDER = "[ANIMATION_DEFAULT_PARAMS_PLACEHOLDER]"
internal const val ANIMATION_PARAMS_TO_INNER_PLACEHOLDER_1 = "[ANIMATION_PARAMS_TO_INNER_PLACEHOLDER_1]"
internal const val ANIMATION_PARAMS_TO_INNER_PLACEHOLDER_2 = "[ANIMATION_PARAMS_TO_INNER_PLACEHOLDER_2]"
internal const val SCAFFOLD_FUNCTION_START = "//region Scaffold"
internal const val SCAFFOLD_FUNCTION_END = "//endregion Scaffold"
//endregion

internal val destinationsObjectTemplate = """
package $PACKAGE_NAME

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.navigation.compose.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import androidx.compose.runtime.getValue //TODO check these
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import kotlin.reflect.KClass
import com.google.accompanist.navigation.animation.composable as animationComposable
import com.google.accompanist.navigation.animation.navigation as animationNavigation

import androidx.navigation.NavBackStackEntry
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.ui.Alignment
import androidx.compose.animation.*

$IMPORTS_BLOCK

/**
 * Class generated if any Composable is annotated with `@$DESTINATION_ANNOTATION`.
 * It aggregates all [$GENERATED_DESTINATION]s and has 
 * [DestinationsNavHost]/[DestinationsScaffold] equivalent methods which
 * will relay the destinations to be used in the navigation graph.
 */
object $DESTINATIONS_AGGREGATE_CLASS_NAME {

    //region NavGraphs
$NAV_GRAPHS_DECLARATION
    //endregion NavGraphs

    //region NavHost
    /**
     * Like [androidx.navigation.compose.NavHost] but uses composables annotated with
     * `@$DESTINATION_ANNOTATION` to pass in as the destinations available.
     *
     * @see [androidx.navigation.compose.NavHost]
     */
    @Composable$EXPERIMENTAL_API_PLACEHOLDER
    fun NavHost(
        navController: NavHostController = $DEFAULT_NAV_CONTROLLER_PLACEHOLDER,
        modifier: Modifier = Modifier,
        startDestination: $GENERATED_DESTINATION = ${GENERATED_NAV_GRAPH}s.root.startDestination,$ANIMATION_DEFAULT_PARAMS_PLACEHOLDER
    ) {
        InnerDestinationsNavHost(
            navController = navController,
            modifier = modifier,
            startDestination = startDestination,
            situationalParametersProvider = { mutableMapOf() },$ANIMATION_PARAMS_TO_INNER_PLACEHOLDER_1
        )
    }
    //endregion NavHost

    $SCAFFOLD_FUNCTION_START
    /**
     * Like [androidx.compose.material.Scaffold] but it adds a navigation graph using [$DESTINATIONS_AGGREGATE_CLASS_NAME.NavHost].
     *
     * Also, composables that can depend on the current [$GENERATED_DESTINATION] are
     * passed the current [$GENERATED_DESTINATION] so that they can update when the user
     * navigates through the app.
     *
     * Lastly, [modifierForPaddingValues] is used to let callers determine
     * a [Modifier] to set on the [$DESTINATIONS_AGGREGATE_CLASS_NAME.NavHost] for a combination
     * of [PaddingValues] (given from this [Scaffold]) and current [$GENERATED_DESTINATION].
     *
     * @see [androidx.compose.material.Scaffold]
     */
    @Composable$EXPERIMENTAL_API_PLACEHOLDER
    fun Scaffold(
        modifier: Modifier = Modifier,
        startDestination: $GENERATED_DESTINATION = ${GENERATED_NAV_GRAPH}s.root.startDestination,
        navController: NavHostController = $DEFAULT_NAV_CONTROLLER_PLACEHOLDER,
        scaffoldState: ScaffoldState = rememberScaffoldState(),$ANIMATION_DEFAULT_PARAMS_PLACEHOLDER
        topBar: (@Composable ($GENERATED_DESTINATION) -> Unit) = {},
        bottomBar: @Composable ($GENERATED_DESTINATION) -> Unit = {},
        snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
        floatingActionButton: @Composable ($GENERATED_DESTINATION) -> Unit = {},
        floatingActionButtonPosition: FabPosition = FabPosition.End,
        isFloatingActionButtonDocked: Boolean = false,
        drawerContent: @Composable (ColumnScope.($GENERATED_DESTINATION) -> Unit)? = null,
        drawerGesturesEnabled: Boolean = true,
        drawerShape: Shape = MaterialTheme.shapes.large,
        drawerElevation: Dp = DrawerDefaults.Elevation,
        drawerBackgroundColor: Color = MaterialTheme.colors.surface,
        drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
        drawerScrimColor: Color = DrawerDefaults.scrimColor,
        backgroundColor: Color = MaterialTheme.colors.background,
        contentColor: Color = contentColorFor(backgroundColor),
        modifierForDestination: ($GENERATED_DESTINATION, PaddingValues) -> Modifier = { _, _ -> Modifier }
    ) {
        val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()

        val destination = (currentBackStackEntryAsState?.destination?.route
            ?.let { ${GENERATED_NAV_GRAPH}s.root.findDestination(it) }
            ?: startDestination) as $GENERATED_DESTINATION

        Scaffold(
            modifier,
            scaffoldState,
            { topBar(destination) },
            { bottomBar(destination) },
            snackbarHost,
            { floatingActionButton(destination) },
            floatingActionButtonPosition,
            isFloatingActionButtonDocked,
            drawerContent?.let{ { drawerContent.invoke(this, destination) } },
            drawerGesturesEnabled,
            drawerShape,
            drawerElevation,
            drawerBackgroundColor,
            drawerContentColor,
            drawerScrimColor,
            backgroundColor,
            contentColor,
        ) { paddingValues ->
            InnerDestinationsNavHost(
                navController = navController,
                modifier = modifierForDestination(destination, paddingValues),
                startDestination = startDestination,
                situationalParametersProvider = {
                    mutableMapOf(ScaffoldState::class to scaffoldState)
                },$ANIMATION_PARAMS_TO_INNER_PLACEHOLDER_2
            )
        }
    }
    $SCAFFOLD_FUNCTION_END
}

//region internals
$INNER_NAV_HOST_PLACEHOLDER
//endregion
""".trimIndent()