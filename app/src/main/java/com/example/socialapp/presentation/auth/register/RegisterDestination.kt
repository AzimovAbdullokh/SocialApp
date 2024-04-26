package com.example.socialapp.presentation.auth.register

import com.example.socialapp.presentation.graph.Destination

object RegisterDestination : Destination {

    override val route: String
        get() = "register_destination"

    override val routeWithArgs: String
        get() = "register_with_args"
}